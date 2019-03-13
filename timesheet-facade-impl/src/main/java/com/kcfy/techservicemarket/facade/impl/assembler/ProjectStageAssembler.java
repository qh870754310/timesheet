package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import com.kcfy.techservicemarket.core.domain.project.ProjectType;
import com.kcfy.techservicemarket.facade.dto.ProjectStageDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectStageAssembler {

    public static ProjectStageDTO toDTO(ProjectStage projectStage) {
        if (projectStage == null) {
            return null;
        }
        ProjectStageDTO result = new ProjectStageDTO();
        result.setId(projectStage.getId());
        result.setName(projectStage.getName());
        result.setMemo(projectStage.getMemo());
        result.setVersion(projectStage.getVersion());
        result.setProjectTypeId(projectStage.getProjectType().getId());
        result.setProjectTypeName(projectStage.getProjectType().getName());
        return result;
    }

    public static List<ProjectStageDTO> toDTOs(Collection<ProjectStage> projectStages) {
        if (projectStages == null) {
            return null;
        }
        List<ProjectStageDTO> results = new ArrayList<ProjectStageDTO>();
        for (ProjectStage each : projectStages) {
            results.add(toDTO(each));
        }
        return results;
    }

    public static ProjectStage toEntity(ProjectStageDTO projectStageDTO) {
        if (projectStageDTO == null) {
            return null;
        }
        ProjectStage result = new ProjectStage();
        result.setId(projectStageDTO.getId());
        result.setName(projectStageDTO.getName());
        result.setMemo(projectStageDTO.getMemo());
        result.setProjectType (new ProjectType(projectStageDTO.getProjectTypeId()));
        result.setVersion(projectStageDTO.getVersion());
        return result;
    }

    public static List<ProjectStage> toEntities(Collection<ProjectStageDTO> projectTypeDTOs) {
        if (projectTypeDTOs == null) {
            return null;
        }

        List<ProjectStage> results = new ArrayList<ProjectStage>();
        for (ProjectStageDTO each : projectTypeDTOs) {
            results.add(toEntity(each));
        }
        return results;
    }
}
