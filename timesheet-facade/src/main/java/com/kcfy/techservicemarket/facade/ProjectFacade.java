package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.ProjectDTO;
import com.kcfy.techservicemarket.facade.dto.ProjectImport;
import com.kcfy.techservicemarket.facade.dto.UserFitDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;

public interface ProjectFacade {

	public InvokeResult getProject(Long id);
	
	public InvokeResult creatProject(ProjectDTO project);
	
	public InvokeResult updateProject(ProjectDTO project);
	
	public InvokeResult removeProject(Long id);
	
	public InvokeResult removeProjects(Long[] ids);
	
	public List<ProjectDTO> findAllProject();
	
	public Page<ProjectDTO> pageQueryProject(ProjectDTO project, int currentPage, int pageSize);

	InvokeResult closeProject(Long id);

	InvokeResult openProject(Long id);

	void importProjects(List<ProjectImport> projectList);

	InvokeResult findAllProjectAndUserProject(Long userId);

	public InvokeResult getStagesByProjectId(Long projectId);

	public InvokeResult getProjectTimes(List<Long> projectIds,String startTime, String endTime);

	public InvokeResult getProjectListByDept(Long deptId);

	InvokeResult updateProjectAddTeam(Long projectId, Long[] idArrs);

	InvokeResult updateProjectRemoveTeam(Long projectId, Long[] idArrs);

	Page<UserFitDTO> pagingQueryNotGrantUsers(int page, int pagesize, Long projectId);

	Page<UserFitDTO> pagingQueryGrantUserByProjectId(int page, int pagesize, Long projectId);
}

