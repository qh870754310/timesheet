package com.kcfy.techservicemarket.application.impl;


import com.kcfy.techservicemarket.application.ProjectTypeApplication;
import com.kcfy.techservicemarket.core.domain.project.ProjectType;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;
import java.util.Set;

@Named
@Transactional
public class ProjectTypeApplicationImpl implements ProjectTypeApplication {

	public ProjectType getProjectType(Long id) {
		return ProjectType.get(ProjectType.class, id);
	}
	
	public void creatProjectType(ProjectType projectType) {
		projectType.save();
	}
	
	public void updateProjectType(ProjectType projectType) {
		projectType .save();
	}
	
	public void removeProjectType(ProjectType projectType) {
		if(projectType != null){
			projectType.remove();
		}
	}
	
	public void removeProjectTypes(Set<ProjectType> projectTypes) {
		for (ProjectType projectType : projectTypes) {
			projectType.remove();
		}
	}
	
	public List<ProjectType> findAllProjectType() {
		return ProjectType.findAll(ProjectType.class);
	}
	
}
