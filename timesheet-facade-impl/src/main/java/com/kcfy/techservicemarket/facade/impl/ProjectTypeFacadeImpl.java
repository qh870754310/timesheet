package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.ProjectTypeApplication;
import com.kcfy.techservicemarket.core.domain.project.ProjectType;
import com.kcfy.techservicemarket.facade.ProjectTypeFacade;
import com.kcfy.techservicemarket.facade.dto.ProjectTypeDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.ProjectTypeAssembler;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
public class ProjectTypeFacadeImpl implements ProjectTypeFacade {

    @Inject
    private ProjectTypeApplication application;

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    public InvokeResult getProjectType(Long id) {
        return InvokeResult.success(ProjectTypeAssembler.toDTO(application.getProjectType(id)));
    }

    public InvokeResult creatProjectType(ProjectTypeDTO projectTypeDTO) throws ParseException {
        application.creatProjectType(ProjectTypeAssembler.toEntity(projectTypeDTO));
        return InvokeResult.success();
    }

    public InvokeResult updateProjectType(ProjectTypeDTO projectTypeDTO) throws ParseException {
        application.updateProjectType(ProjectTypeAssembler.toEntity(projectTypeDTO));
        return InvokeResult.success();
    }

    public InvokeResult removeProjectType(Long id) {
        application.removeProjectType(application.getProjectType(id));
        return InvokeResult.success();
    }

    public InvokeResult removeProjectTypes(Long[] ids) {
        Set<ProjectType> projectTypes = new HashSet<ProjectType>();
        for (Long id : ids) {
            projectTypes.add(application.getProjectType(id));
        }
        application.removeProjectTypes(projectTypes);
        return InvokeResult.success();
    }

    public List<ProjectTypeDTO> findAllProjectType() {
        return ProjectTypeAssembler.toDTOs(application.findAllProjectType());
    }

    public Page<ProjectTypeDTO> pageQueryProjectType(ProjectTypeDTO queryVo, int currentPage, int pageSize) {
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _projectType from ProjectType _projectType   where 1=1 ");
        if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
            jpql.append(" and _projectType.name like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
        }
        if (queryVo.getMemo() != null && !"".equals(queryVo.getMemo())) {
            jpql.append(" and _projectType.memo like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getMemo()));
        }
        Page<ProjectType> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(currentPage, pageSize)
                .pagedList();

        return new Page<ProjectTypeDTO>(pages.getStart(), pages.getResultCount(), pageSize, ProjectTypeAssembler.toDTOs(pages.getData()));
    }


}
