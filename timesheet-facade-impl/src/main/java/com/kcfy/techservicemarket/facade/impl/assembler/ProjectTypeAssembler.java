package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.domain.project.ProjectType;
import com.kcfy.techservicemarket.facade.dto.ProjectTypeDTO;
import com.kcfy.techservicemarket.infra.util.DateTool;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectTypeAssembler {

    public static ProjectTypeDTO toDTO(ProjectType projectType) {
        if (projectType == null) {
            return null;
        }
        ProjectTypeDTO result = new ProjectTypeDTO();
        result.setId(projectType.getId());
        result.setName(projectType.getName());
        result.setMemo(projectType.getMemo());
        result.setIsAvailable(projectType.getIsAvailable());
        result.setCreateDate(DateTool.convertDateToString("yyyy-MM-dd HH:mm:ss", projectType.getCreateDate()));
        result.setCreatorId(projectType.getCreatorId());
        result.setModifierId(projectType.getModifierId());
        result.setModifyDate(DateTool.convertDateToString("yyyy-MM-dd HH:mm:ss", projectType.getModifyDate()));
        result.setVersion(projectType.getVersion());
        return result;
    }

    public static List<ProjectTypeDTO> toDTOs(Collection<ProjectType> projectTypes) {
        if (projectTypes == null) {
            return null;
        }
        List<ProjectTypeDTO> results = new ArrayList<ProjectTypeDTO>();
        for (ProjectType each : projectTypes) {
            results.add(toDTO(each));
        }
        return results;
    }

    public static ProjectType toEntity(ProjectTypeDTO projectTypeDTO) throws ParseException {
        if (projectTypeDTO == null) {
            return null;
        }
        ProjectType result = new ProjectType();
        result.setId(projectTypeDTO.getId());
        result.setName(projectTypeDTO.getName());
        result.setMemo(projectTypeDTO.getMemo());
        result.setIsAvailable(projectTypeDTO.getIsAvailable());

        result.setCreateDate(DateTool.convertStringToDate("yyyy-MM-dd HH:mm:ss", projectTypeDTO.getCreateDate()));
        result.setModifyDate(DateTool.convertStringToDate("yyyy-MM-dd HH:mm:ss", projectTypeDTO.getModifyDate()));

        result.setCreatorId(projectTypeDTO.getCreatorId());
        result.setModifierId(projectTypeDTO.getModifierId());

        result.setVersion(projectTypeDTO.getVersion());
        return result;
    }

    public static List<ProjectType> toEntities(Collection<ProjectTypeDTO> projectTypeDTOs) throws ParseException {
        if (projectTypeDTOs == null) {
            return null;
        }

        List<ProjectType> results = new ArrayList<ProjectType>();
        for (ProjectTypeDTO each : projectTypeDTOs) {
            results.add(toEntity(each));
        }
        return results;
    }
}
