package com.kcfy.techservicemarket.application;

import com.kcfy.techservicemarket.core.domain.project.ProjectStage;

import java.util.List;
import java.util.Set;

public interface ProjectStageApplication {

	public ProjectStage getProjectStage(Long id);
	
	public void creatProjectStage(ProjectStage projectStage);
	
	public void updateProjectStage(ProjectStage projectStage);
	
	public void removeProjectStage(ProjectStage projectStage);
	
	public void removeProjectStages(Set<ProjectStage> projectStages);
	
	public List<ProjectStage> findAllProjectStage();

	public List<ProjectStage> findByProjectType(Long projectTypeId);

	public List<ProjectStage> findByProjectTypeAndName(Long projectTypeId, String name);

	public List<ProjectStage> getByProjectId(Long projectId);
}

