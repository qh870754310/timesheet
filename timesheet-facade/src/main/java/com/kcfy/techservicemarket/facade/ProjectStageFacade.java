package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.ProjectStageDTO;
import com.kcfy.techservicemarket.facade.dto.ProjectTypeDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;

public interface ProjectStageFacade {

    public InvokeResult getProjectStage(Long id);

    public InvokeResult creatProjectStage(ProjectStageDTO projectStage);

    public InvokeResult updateProjectStage(ProjectStageDTO projectStage);

    public InvokeResult removeProjectStage(Long id);

    public InvokeResult removeProjectStages(Long[] ids);

    public List<ProjectStageDTO> findAllProjectStage();

    public Page<ProjectStageDTO> pageQueryProjectStage(ProjectStageDTO projectStage, int currentPage, int pageSize);

    public ProjectTypeDTO findProjectTypeByProjectStage(Long id);

    public List<ProjectStageDTO> findProjectStageListByTypeId(Long id);

    public InvokeResult getByProjectId(Long projectId);

}

