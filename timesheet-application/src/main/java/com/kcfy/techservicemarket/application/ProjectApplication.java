package com.kcfy.techservicemarket.application;

import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProjectApplication {

    public Project getProject(Long id);

    public void creatProject(Project project);

    public void updateProject(Project project);

    public void removeProject(Project project);

    public void removeProjects(Set<Project> projects);

    public List<Project> findAllProject();

    InvokeResult closeProject(Long id);

    InvokeResult openProject(Long id);

    List<Project> getProjectsByPmUserId(Long userId);

    InvokeResult updateProjectAddTeam(Long projectId, Long[] idArrs);

    InvokeResult updateProjectRemoveTeam(Long projectId, Long[] idArrs);


    public List<TimesheetDetail> getStagesByProjectId(Long projectId);

    public List<TimesheetDetail> getProjectTimes(List<Long> projectIds,String startTime, String endTime);

    public List<Project> getProjectListByDept(Long deptId);
}

