package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.*;
import com.kcfy.techservicemarket.core.Constants;
import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import com.kcfy.techservicemarket.core.domain.project.ProjectType;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.facade.ProjectFacade;
import com.kcfy.techservicemarket.facade.dto.ProjectDTO;
import com.kcfy.techservicemarket.facade.dto.ProjectImport;
import com.kcfy.techservicemarket.facade.dto.UserFitDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.ProjectAllAndMineDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.ProjectFitDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.ProjectStageFitDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.*;
import com.kcfy.techservicemarket.facade.impl.assembler.ProjectAssembler;
import org.apache.commons.lang.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.common.core.constants.Availability;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.organisation.core.domain.Department;
import org.openkoala.organisation.core.domain.Employee;
import org.openkoala.organisation.core.domain.Party;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.MessageFormat;
import java.util.*;

@Named
public class ProjectFacadeImpl implements ProjectFacade {

    @Inject
    private ProjectApplication application;
    @Inject
    private TimesheetApplication timesheetApplication;
    @Inject
    private TimeSettingApplication timeSettingApplication;
    @Inject
    TimeSheetDetailApplication timeSheetDetailApplication;
    @Inject
    private ProjectTypeApplication projectTypeapplication;
    @Inject
    private ProjectStageApplication projectStageApplication;
    private QueryChannelService queryChannel;
    @Inject
    private UserApplication userApplication;
    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Override
    public InvokeResult findAllProjectAndUserProject(Long userId) {
        ProjectAllAndMineDTO projectAllAndMineDTO = new ProjectAllAndMineDTO();
        // 所有项目列表
        List<Project> projectList = application.findAllProject();
        List<ProjectFitDTO> projectFitDTOList = new ArrayList<>();
        for(Project project : projectList) {
            ProjectFitDTO projectFitDTO = new ProjectFitDTO();
            projectFitDTO.setProjectId(String.valueOf(project.getId()));
            projectFitDTO.setProjectName(project.getName());
            projectFitDTOList.add(projectFitDTO);
        }
        projectAllAndMineDTO.setProjectList(projectFitDTOList);
        // 我的项目信息
        Set<Project> myProjects = userApplication.getUserProjects(userId);
        if(myProjects.iterator().hasNext()) {
            Project myProject = myProjects.iterator().next();
            projectAllAndMineDTO.setMyProjectId(String.valueOf(myProject.getId()));
            if(myProject.getProjectStage() != null) {
                projectAllAndMineDTO.setMyProjectCurrentStageId(String.valueOf(myProject.getProjectStage().getId()));
            }
            // 我的项目的类型的所有的阶段
            if(myProject.getProjectType() != null) {
                List<ProjectStage> stageList = projectStageApplication.findByProjectType(myProject.getProjectType().getId());
                List<ProjectStageFitDTO> projectStageFitDTOList = new ArrayList<>();
                for(ProjectStage projectStage : stageList) {
                    ProjectStageFitDTO projectStageFitDTO = new ProjectStageFitDTO();
                    projectStageFitDTO.setProjectStageId(String.valueOf(projectStage.getId()));
                    projectStageFitDTO.setStageName(projectStage.getName());
                    projectStageFitDTOList.add(projectStageFitDTO);
                }
                projectAllAndMineDTO.setMyProjectStageList(projectStageFitDTOList);
            }
        }
        return InvokeResult.success(projectAllAndMineDTO);
    }

    public InvokeResult getProject(Long id) {
        return InvokeResult.success(ProjectAssembler.toDTO(application.getProject(id)));
    }

    public InvokeResult creatProject(ProjectDTO projectDTO) {
        application.creatProject(ProjectAssembler.toEntity(projectDTO));
        return InvokeResult.success();
    }

    public InvokeResult updateProject(ProjectDTO projectDTO) {
        application.updateProject(ProjectAssembler.toEntity(projectDTO));
        return InvokeResult.success();
    }

    public InvokeResult removeProject(Long id) {
        application.removeProject(application.getProject(id));
        return InvokeResult.success();
    }

    public InvokeResult removeProjects(Long[] ids) {
        Set<Project> projects = new HashSet<Project>();
        for (Long id : ids) {
            projects.add(application.getProject(id));
        }
        application.removeProjects(projects);
        return InvokeResult.success();
    }

    public List<ProjectDTO> findAllProject() {
        return ProjectAssembler.toDTOs(application.findAllProject());
    }

    public Page<ProjectDTO> pageQueryProject(ProjectDTO queryVo, int currentPage, int pageSize) {
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _project from Project _project   where 1=1 ");
        if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
            jpql.append(" and _project.name like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
        }
        if (queryVo.getMemo() != null && !"".equals(queryVo.getMemo())) {
            jpql.append(" and _project.memo like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getMemo()));
        }
        Page<Project> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(currentPage, pageSize)
                .pagedList();

        return new Page<ProjectDTO>(pages.getStart(), pages.getResultCount(), pageSize, ProjectAssembler.toDTOs(pages.getData()));
    }

    @Override
    public InvokeResult closeProject(Long id) {
        return application.closeProject(id);
    }

    @Override
    public InvokeResult openProject(Long id) {
        return application.openProject(id);
    }

    @Override
    public void importProjects(List<ProjectImport> projectList) {
        for (ProjectImport projectImport : projectList) {
            Project project = new Project();
            project.setStatus(Constants.ProjectStatus.OPEN);
            project.setVersion(0);
            project.setIsAvailable(Availability.available);
            project.setName(projectImport.getName());
            if(StringUtils.isNotEmpty(projectImport.getDepartment())) {
                project.setDepartment(Department.getByName(projectImport.getDepartment()));
            }
            if(StringUtils.isNotEmpty(projectImport.getPm())) {
                Party party = Employee.getByName(projectImport.getPm());
                if (party != null) {
                    project.setPm((Employee) party);
                }
            }
            if(StringUtils.isNotEmpty(projectImport.getType())) {
                ProjectType projectType = ProjectType.getByName(projectImport.getType());
                if (projectType == null) {
                    projectType = new ProjectType();
                    projectType.setName(projectImport.getType());
                    projectType.setVersion(0);
                    projectType.setIsAvailable(Availability.available);
                    projectTypeapplication.creatProjectType(projectType);
                }
                project.setProjectType(projectType);
                if(StringUtils.isNotEmpty(projectImport.getStage())) {
                    ProjectStage stage = null;
                    List<ProjectStage> stageList = projectStageApplication.findByProjectTypeAndName(projectType.getId(), projectImport.getStage());
                    if(stageList.size() > 0) {
                        stage = stageList.get(0);
                    } else {
                        stage = new ProjectStage();
                        stage.setProjectType(projectType);
                        stage.setVersion(0);
                        stage.setIsAvailable(Availability.available);
                        stage.setName(projectImport.getStage());
                        projectStageApplication.creatProjectStage(stage);
                    }
                    project.setProjectStage(stage);
                }
            }
            this.application.creatProject(project);
        }
    }

    @Override
    public InvokeResult updateProjectAddTeam(Long projectId, Long[] idArrs) {
        return application.updateProjectAddTeam(projectId, idArrs);
    }

    @Override
    public InvokeResult updateProjectRemoveTeam(Long projectId, Long[] idArrs) {
        return application.updateProjectRemoveTeam(projectId, idArrs);
    }

    @Override
    public Page<UserFitDTO> pagingQueryNotGrantUsers(int page, int pagesize, Long projectId) {
        return getUserFitByProjectId(page, pagesize, projectId, " NOT IN ");
    }

    @Override
    public Page<UserFitDTO> pagingQueryGrantUserByProjectId(int page, int pagesize, Long projectId) {
        return getUserFitByProjectId(page, pagesize, projectId, " IN ");
    }

    private Page<UserFitDTO> getUserFitByProjectId(int page, int pagesize, Long projectId, String str3) {
        if (projectId == null) {
            InvokeResult.failure("项目ID不能为空");
        }
        StringBuilder jpql = new StringBuilder("SELECT NEW com.kcfy.techservicemarket.facade.dto.UserFitDTO(_user.id, _user.name, _user.employee.person.email)  FROM User _user WHERE 1 = 1 ");
        jpql.append("And _user.id ");
        jpql.append(str3);
        jpql.append(" (SELECT _users.id FROM Project _project JOIN _project.users _users WHERE _project.id= :projectId)");
        HashMap<String, Long> conditionVals = new HashMap<>();
        conditionVals.put("projectId", projectId);

        return getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(page, pagesize)
                .pagedList();
    }

    @Override
    public InvokeResult getStagesByProjectId(Long projectId) {
        ProjectStageHourDTO projectStageHourDTO = new ProjectStageHourDTO();
        List<ProjectStageHour> projectStageHourList = new ArrayList<>();
        ProjectStageHour projectStageHour = new ProjectStageHour();
        projectStageHourDTO.setProjectId(String.valueOf(projectId));
        projectStageHourDTO.setProjectName(application.getProject(projectId).getName());
        if(application.getProject(projectId).getProjectStage() != null){
            List<TimesheetDetail> list = application.getStagesByProjectId(projectId);
            for(TimesheetDetail timesheetDetail : list) {
                if(timesheetDetail != null && timesheetDetail.getId() != null){
                    projectStageHour.setProjectStageId(String.valueOf(timesheetDetail.getProjectStage().getId()));
                    projectStageHour.setStageName(timesheetDetail.getProjectStage().getName());
                    projectStageHour.setHour(String.valueOf(timesheetDetail.getHour()));
                    projectStageHourList.add(projectStageHour);
                }
            }
        }
        projectStageHourDTO.setTimeList(projectStageHourList);
        return InvokeResult.success(projectStageHourDTO);
    }

    @Override
    public InvokeResult getProjectTimes(List<Long> projectIds,String startTime, String endTime) {
        ProjectTimeDetailList projectTimeDetailList = new ProjectTimeDetailList();
        List<ProjectTimesDTO> projectTimesDTOList = new ArrayList<>();
        List<TimesheetDetail> list = application.getProjectTimes(projectIds, startTime, endTime);
        //存放时间
        Map<Long,Long> maps = new HashMap<Long,Long>();
        //存放名字
        Map<Long,String> nameMaps = new HashMap<>();
        ProjectTimesDTO projectTimesDTO = null;
        for(TimesheetDetail timesheetDetail : list){
            if(!nameMaps.containsKey(timesheetDetail.getProject().getId())){
                nameMaps.put(timesheetDetail.getProject().getId(),timesheetDetail.getProject().getName());
            }
            if(!maps.containsKey(timesheetDetail.getProject().getId())){
                maps.put(timesheetDetail.getProject().getId(),timesheetDetail.getHour());
            }else{
                Long hour = maps.get(timesheetDetail.getProject().getId());
                maps.put(timesheetDetail.getProject().getId(),hour+timesheetDetail.getHour());
            }
        }
        List<Long> projectIdList = new ArrayList<Long>();
        for(Long projectId : projectIds){
            if(!maps.containsKey(projectId)){
                maps.put(projectId,0L);
                projectIdList.add(projectId);
            }
        }
        for(Long projectId : projectIdList){
            Project project = application.getProject(projectId);
            nameMaps.put(projectId,project.getName());
        }

        Iterator iterator = maps.keySet().iterator();
        while(iterator.hasNext()){
            Long projectId = (Long)iterator.next();
            Long hour = (Long)maps.get(projectId);
            projectTimesDTO = new ProjectTimesDTO();
            projectTimesDTO.setProjectId((String.valueOf(projectId)));
            projectTimesDTO.setProjectName(nameMaps.get(projectId));
            projectTimesDTO.setHour(String.valueOf(hour));
            projectTimesDTOList.add(projectTimesDTO);
        }
        projectTimeDetailList.setProjectTimeList(projectTimesDTOList);
        return InvokeResult.success(projectTimeDetailList);
    }

    @Override
     public InvokeResult getProjectListByDept(Long deptId) {
        ProjectListDTO projectListDTO = new ProjectListDTO();
        List<ProjectFitDTO> projectList = new ArrayList<>();
        List<Project> list =  application.getProjectListByDept(deptId);
        for(Project project : list) {
            ProjectFitDTO projectFitDTO = new ProjectFitDTO();
            projectFitDTO.setProjectId(String.valueOf(project.getId()));
            projectFitDTO.setProjectName(project.getName());
            projectList.add(projectFitDTO);
        }
        projectListDTO.setProjectList(projectList);
        return InvokeResult.success(projectListDTO);
    }
}
