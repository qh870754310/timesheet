package com.kcfy.techservicemarket.core.domain.project;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_project_type")
public class ProjectType extends CustomKoalaAbstractEntity{
	@Column(name = "name")
	private String name;
	@Column(name = "memo")
	private String memo;
	@OneToMany(cascade={}, fetch = FetchType.LAZY, mappedBy="projectType")
	private List<ProjectStage> projectStageList = new ArrayList<ProjectStage>();
	public ProjectType() {
		super();
	}
	public ProjectType(Long projectTypeId) {
		super.setId(projectTypeId);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<ProjectStage> getProjectStageList() {
		return projectStageList;
	}

	public void setProjectStageList(List<ProjectStage> projectStageList) {
		this.projectStageList = projectStageList;
	}

	@Override
	public String[] businessKeys() {
		return new String[0];
	}

	public static ProjectType getByName(String name) {
		List<ProjectType> projectTypeList = getRepository().findByProperty(ProjectType.class, "name", name);
		return projectTypeList.size() > 0 ? projectTypeList.get(0) : null;
	}
}
