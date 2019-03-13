package com.kcfy.techservicemarket.core.domain.project;
import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_project_stage")
public class ProjectStage extends CustomKoalaAbstractEntity {
	@Column(name = "name")
	private String name;
	@Column(name = "memo")
	private String memo;
	@ManyToOne(cascade={CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true,targetEntity=ProjectType.class)
	@JoinColumn(name = "project_type_id")
	private ProjectType projectType;
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
	public ProjectType getProjectType() {
		return projectType;
	}
	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public ProjectStage(){
		super();
	}
	public ProjectStage(Long id){
		super.setId(id);
	}
	@Override
	public String[] businessKeys() {
		return new String[0];
	}
}
