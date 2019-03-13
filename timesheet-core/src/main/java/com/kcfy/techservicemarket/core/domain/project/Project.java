package com.kcfy.techservicemarket.core.domain.project;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;
import org.openkoala.organisation.core.domain.Department;
import org.openkoala.organisation.core.domain.Employee;
import org.openkoala.security.core.domain.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_project")
public class Project extends CustomKoalaAbstractEntity{

	@Column(name = "name")
	private String name;
	@Column(name = "memo")
	private String memo;
	@ManyToOne(cascade={CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true,targetEntity=Employee.class)
    @JoinColumn(name = "pm_user_id")
	private Employee pm;
	@ManyToOne(cascade={CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true,targetEntity=ProjectType.class)
    @JoinColumn(name = "project_type_id")
	private ProjectType projectType;
	@ManyToOne(cascade={CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true,targetEntity=ProjectStage.class)
    @JoinColumn(name = "project_stage_id")
	private ProjectStage projectStage;
	@ManyToOne(cascade={CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true,targetEntity=Department.class)
    @JoinColumn(name = "department_id")
	private Department department;
	@Column(name = "status")
	private Long status;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "t_project_user", inverseJoinColumns = { @JoinColumn(name = "user_id") },
			joinColumns = { @JoinColumn(name = "project_id") })
	private Set<User> users;


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

	public Employee getPm() {
		return pm;
	}
	public void setPm(Employee pm) {
		this.pm = pm;
	}
	public ProjectType getProjectType() {
		return projectType;
	}
	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}
	public ProjectStage getProjectStage() {
		return projectStage;
	}
	public void setProjectStage(ProjectStage projectStage) {
		this.projectStage = projectStage;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}


}
