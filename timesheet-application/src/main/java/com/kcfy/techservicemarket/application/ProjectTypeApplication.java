package com.kcfy.techservicemarket.application;

import com.kcfy.techservicemarket.core.domain.project.ProjectType;

import java.util.List;
import java.util.Set;

public interface ProjectTypeApplication {

	public ProjectType getProjectType(Long id);
	
	public void creatProjectType(ProjectType projectType);
	
	public void updateProjectType(ProjectType projectType);
	
	public void removeProjectType(ProjectType projectType);
	
	public void removeProjectTypes(Set<ProjectType> projectTypes);
	
	public List<ProjectType> findAllProjectType();
	
}

