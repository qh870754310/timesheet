package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.Constants;
import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import com.kcfy.techservicemarket.core.domain.project.ProjectType;
import com.kcfy.techservicemarket.facade.dto.ProjectDTO;
import org.openkoala.organisation.core.domain.Department;
import org.openkoala.organisation.core.domain.Employee;
import org.openkoala.organisation.core.domain.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ProjectAssembler {

    public static ProjectDTO toDTO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDTO result = new ProjectDTO();
        result.setId(project.getId());
        result.setVersion(project.getVersion());
        result.setId(project.getId());
        result.setName(project.getName());
        result.setMemo(project.getMemo());
        result.setStatus(project.getStatus());
        result.setStatusName((project.getStatus() != null && project.getStatus() == Constants.ProjectStatus.CLOSE) ?"关闭":"正常");
        if (project.getProjectType() != null) {
            result.setProjectTypeId(project.getProjectType().getId());
            result.setProjectTypeName(project.getProjectType().getName());
        }
        if (project.getProjectStage() != null) {
            result.setProjectStageId(project.getProjectStage().getId());
            result.setProjectStageName(project.getProjectStage().getName());
        }
        if (project.getPm() != null) {
            result.setPmId(project.getPm().getId());
            result.setPmName(project.getPm().getName());
        }
        if (project.getDepartment() != null) {
            result.setDepartmentId(project.getDepartment().getId());
            result.setDepartmentName(project.getDepartment().getName());
        }
        return result;
    }

    public static List<ProjectDTO> toDTOs(Collection<Project> projects) {
        if (projects == null) {
            return null;
        }
        List<ProjectDTO> results = new ArrayList<ProjectDTO>();
        for (Project each : projects) {
            results.add(toDTO(each));
        }
        return results;
    }

    public static Project toEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }
        Project result = new Project();
        result.setId(projectDTO.getId());
        result.setVersion(projectDTO.getVersion());
        result.setId(projectDTO.getId());
        result.setName(projectDTO.getName());
        result.setMemo(projectDTO.getMemo());
        result.setStatus(projectDTO.getStatus());
        if (projectDTO.getProjectTypeId() != null) {
            result.setProjectType(new ProjectType(projectDTO.getProjectTypeId()));
        }
        if (projectDTO.getProjectStageId() != null) {
            result.setProjectStage(new ProjectStage(projectDTO.getProjectStageId()));
        }
        if (projectDTO.getPmId() != null) {
            result.setPm(new Employee(projectDTO.getPmId()));
        }
        if (projectDTO.getDepartmentId() != null) {
            result.setDepartment(new Department(projectDTO.getDepartmentId()));
        }
        return result;
    }

    public static List<Project> toEntities(Collection<ProjectDTO> projectDTOs) {
        if (projectDTOs == null) {
            return null;
        }

        List<Project> results = new ArrayList<Project>();
        for (ProjectDTO each : projectDTOs) {
            results.add(toEntity(each));
        }
        return results;
    }
}
