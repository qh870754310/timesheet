package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.ProjectApplication;
import com.kcfy.techservicemarket.core.Constants;
import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.infra.util.DateTool;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.core.domain.Actor;
import org.openkoala.security.core.domain.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Named
@Transactional
public class ProjectApplicationImpl implements ProjectApplication {

    public Project getProject(Long id) {
        return Project.get(Project.class, id);
    }

    public void creatProject(Project project) {
        project.save();
    }

    public void updateProject(Project project) {
        project.save();
    }

    public void removeProject(Project project) {
        if (project != null) {
            project.remove();
        }
    }

    public void removeProjects(Set<Project> projects) {
        for (Project project : projects) {
            project.remove();
        }
    }

    public List<Project> findAllProject() {
        return Project.findAll(Project.class);
    }

    @Override
    public InvokeResult closeProject(Long id) {
        Project project = getProject(id);
        project.setStatus(Constants.ProjectStatus.CLOSE);
        updateProject(project);
        return InvokeResult.success();
    }

    @Override
    public InvokeResult openProject(Long id) {
        Project project = getProject(id);
        project.setStatus(Constants.ProjectStatus.OPEN);
        updateProject(project);
        return InvokeResult.success();
    }

    @Override
    public List<Project> getProjectsByPmUserId(Long userId) {
        Long employeeId = User.getEmployeeId(userId);
        List<Project> list = Project.getRepository().createCriteriaQuery(Project.class)
                .eq("pm.id", employeeId).list();
        return list;
    }

    @Override
    public InvokeResult updateProjectAddTeam(Long projectId, Long[] idArrs) {
        Project project = Project.get(Project.class, projectId);
        for(Long id : idArrs) {
            User user = User.get(User.class, id);
            project.getUsers().add(user);
        }
        updateProject(project);
        return InvokeResult.success();
    }

    @Override
    public InvokeResult updateProjectRemoveTeam(Long projectId, Long[] idArrs) {
        Project project = Project.get(Project.class, projectId);
        for(Long id : idArrs) {
            User user = User.get(User.class, id);
            project.getUsers().remove(user);
        }
        updateProject(project);
        return InvokeResult.success();
    }
    @Override
    public List<TimesheetDetail> getStagesByProjectId(Long projectId) {
        return TimesheetDetail.getRepository().createCriteriaQuery(TimesheetDetail.class)
                .eq("project.id", projectId).list();
    }


    @Override
    public List<TimesheetDetail> getProjectTimes(List<Long> projectIds,String startTime, String endTime) {
        return TimesheetDetail.getRepository().createCriteriaQuery(TimesheetDetail.class)
                .in("project.id", projectIds).between("timesheet.date", DateTool.convertStringToDate("yyyy-MM-dd", startTime), DateTool.convertStringToDate("yyyy-MM-dd", endTime)).list();
    }


    @Override
    public List<Project> getProjectListByDept(Long deptId) {
        return Project.getRepository().createCriteriaQuery(Project.class).eq("department.id",deptId).list();
    }
}
