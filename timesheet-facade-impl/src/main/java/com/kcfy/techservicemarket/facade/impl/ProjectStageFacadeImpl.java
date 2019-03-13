package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.ProjectStageApplication;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import com.kcfy.techservicemarket.facade.ProjectStageFacade;
import com.kcfy.techservicemarket.facade.dto.ProjectStageDTO;
import com.kcfy.techservicemarket.facade.dto.ProjectTypeDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.ProjectStageDetail;
import com.kcfy.techservicemarket.facade.dto.weixin.ProjectStageDetailList;
import com.kcfy.techservicemarket.facade.impl.assembler.ProjectStageAssembler;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
public class ProjectStageFacadeImpl implements ProjectStageFacade {

	@Inject
	private ProjectStageApplication application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getProjectStage(Long id) {
		return InvokeResult.success(ProjectStageAssembler.toDTO(application.getProjectStage(id)));
	}
	
	public InvokeResult creatProjectStage(ProjectStageDTO projectStageDTO) {
		application.creatProjectStage(ProjectStageAssembler.toEntity(projectStageDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateProjectStage(ProjectStageDTO projectStageDTO) {
		application.updateProjectStage(ProjectStageAssembler.toEntity(projectStageDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeProjectStage(Long id) {
		application.removeProjectStage(application.getProjectStage(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeProjectStages(Long[] ids) {
		Set<ProjectStage> projectStages= new HashSet<ProjectStage>();
		for (Long id : ids) {
			projectStages.add(application.getProjectStage(id));
		}
		application.removeProjectStages(projectStages);
		return InvokeResult.success();
	}
	
	public List<ProjectStageDTO> findAllProjectStage() {
		return ProjectStageAssembler.toDTOs(application.findAllProjectStage());
	}
	
	public Page<ProjectStageDTO> pageQueryProjectStage(ProjectStageDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _projectStage from ProjectStage _projectStage   where 1=1 ");
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _projectStage.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	   	if (queryVo.getMemo() != null && !"".equals(queryVo.getMemo())) {
	   		jpql.append(" and _projectStage.memo like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getMemo()));
	   	}
		if (queryVo.getProjectTypeId() != null) {
			jpql.append(" and _projectStage.projectType.id = ?");
			conditionVals.add(Long.valueOf(MessageFormat.format("{0}", queryVo.getProjectTypeId())));
		}
		Page<ProjectStage> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<ProjectStageDTO>(pages.getStart(), pages.getResultCount(),pageSize, ProjectStageAssembler.toDTOs(pages.getData()));
	}

	@Override
	public ProjectTypeDTO findProjectTypeByProjectStage(Long id) {
		return null;
	}

    @Override
    public List<ProjectStageDTO> findProjectStageListByTypeId(Long id) {
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _projectStage from ProjectStage _projectStage where _projectStage.projectType.id = ? ");
        conditionVals.add(Long.valueOf(MessageFormat.format("{0}", id)));
        List<ProjectStage> list = getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals).list();
        return ProjectStageAssembler.toDTOs(list);
    }

	@Override
	public InvokeResult getByProjectId(Long projectId) {
		List<ProjectStage> list = application.getByProjectId(projectId);
		ProjectStageDetailList projectStageDetailList = new ProjectStageDetailList();
		List<ProjectStageDetail> projectStageList = new ArrayList<>();
		ProjectStageDetail projectStageDetail = new ProjectStageDetail();;
		for(ProjectStage projectStage : list) {
			projectStageDetail.setProjectStageId(String.valueOf(projectStage.getId()));
			projectStageDetail.setProjectStageName(projectStage.getName());
			projectStageList.add(projectStageDetail);
		}
		projectStageDetailList.setProjectStageList(projectStageList);
		return InvokeResult.success(projectStageDetailList);
	}

}
