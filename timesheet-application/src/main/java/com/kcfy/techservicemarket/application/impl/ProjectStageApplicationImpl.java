package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.ProjectApplication;
import com.kcfy.techservicemarket.application.ProjectStageApplication;
import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import com.kcfy.techservicemarket.core.domain.project.ProjectType;
import com.kcfy.techservicemarket.core.domain.sysmgmt.DictionaryData;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
@Transactional
public class ProjectStageApplicationImpl implements ProjectStageApplication {

	@Inject
	private ProjectApplication projectApplication;

	public ProjectStage getProjectStage(Long id) {
		return ProjectStage.get(ProjectStage.class, id);
	}
	
	public void creatProjectStage(ProjectStage projectStage) {
		projectStage.save();
	}
	
	public void updateProjectStage(ProjectStage projectStage) {
		projectStage .save();
	}
	
	public void removeProjectStage(ProjectStage projectStage) {
		if(projectStage != null){
			projectStage.remove();
		}
	}
	
	public void removeProjectStages(Set<ProjectStage> projectStages) {
		for (ProjectStage projectStage : projectStages) {
			projectStage.remove();
		}
	}
	
	public List<ProjectStage> findAllProjectStage() {
		return ProjectStage.findAll(ProjectStage.class);
	}


	@Override
	public List<ProjectStage> findByProjectType(Long projectTypeId) {
		return ProjectStage.findByProperty(ProjectStage.class, "projectType.id", projectTypeId);
	}

	@Override
	public List<ProjectStage> findByProjectTypeAndName(Long projectTypeId, String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("projectType.id",projectTypeId);
		map.put("name", name);
		return ProjectStage.findByProperties(ProjectStage.class, map);
	}

	@Override
	public List<ProjectStage> getByProjectId(Long projectId) {
		Project project = projectApplication.getProject(projectId);
		List<ProjectStage> list = new ArrayList<ProjectStage>();
		if(project != null) {
			ProjectType projectType = project.getProjectType();
			if(projectType != null) {
				list =  findByProjectType(projectType.getId());
			}
		}
		return list;
	}
}
