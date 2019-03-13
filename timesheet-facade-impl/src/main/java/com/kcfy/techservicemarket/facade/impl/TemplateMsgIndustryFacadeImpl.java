package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.TemplateMsgIndustryApplication;
import com.kcfy.techservicemarket.core.domain.message.TemplateMsgIndustry;
import com.kcfy.techservicemarket.facade.TemplateMsgIndustryFacade;
import com.kcfy.techservicemarket.facade.dto.TemplateMsgIndustryDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.TemplateMsgIndustryAssembler;
import com.kcfy.techservicemarket.infra.util.IndustryCode;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Named
public class TemplateMsgIndustryFacadeImpl implements TemplateMsgIndustryFacade {

    @Inject
    private TemplateMsgIndustryApplication application;

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    public InvokeResult getTemplateMsgIndustry(Long id) {
        return InvokeResult.success(TemplateMsgIndustryAssembler.toDTO(application.getTemplateMsgIndustry(id)));
    }

    public InvokeResult creatTemplateMsgIndustry(TemplateMsgIndustryDTO templateMsgIndustryDTO) {
        IndustryCode one = IndustryCode.valueOf(templateMsgIndustryDTO.getCodeOne());
        templateMsgIndustryDTO.setNameOneFirst(one.getPrimary());
        templateMsgIndustryDTO.setNameOneSecond(one.getSecond());
        IndustryCode two = IndustryCode.valueOf(templateMsgIndustryDTO.getCodeTwo());
        templateMsgIndustryDTO.setNameTwoFirst(two.getPrimary());
        templateMsgIndustryDTO.setNameTwoSecond(two.getSecond());
        application.creatTemplateMsgIndustry(TemplateMsgIndustryAssembler.toEntity(templateMsgIndustryDTO));
        return InvokeResult.success();
    }

    public InvokeResult updateTemplateMsgIndustry(TemplateMsgIndustryDTO templateMsgIndustryDTO) {
        IndustryCode one = IndustryCode.valueOf(templateMsgIndustryDTO.getCodeOne());
        templateMsgIndustryDTO.setNameOneFirst(one.getPrimary());
        templateMsgIndustryDTO.setNameOneSecond(one.getSecond());
        IndustryCode two = IndustryCode.valueOf(templateMsgIndustryDTO.getCodeTwo());
        templateMsgIndustryDTO.setNameTwoFirst(two.getPrimary());
        templateMsgIndustryDTO.setNameTwoSecond(two.getSecond());
        application.updateTemplateMsgIndustry(TemplateMsgIndustryAssembler.toEntity(templateMsgIndustryDTO));
        return InvokeResult.success();
    }

    public InvokeResult removeTemplateMsgIndustry(Long id) {
        application.removeTemplateMsgIndustry(application.getTemplateMsgIndustry(id));
        return InvokeResult.success();
    }

    public InvokeResult removeTemplateMsgIndustrys(Long[] ids) {
        Set<TemplateMsgIndustry> templateMsgIndustrys = new HashSet<TemplateMsgIndustry>();
        for (Long id : ids) {
            templateMsgIndustrys.add(application.getTemplateMsgIndustry(id));
        }
        application.removeTemplateMsgIndustrys(templateMsgIndustrys);
        return InvokeResult.success();
    }

    public List<TemplateMsgIndustryDTO> findAllTemplateMsgIndustry() {
        return TemplateMsgIndustryAssembler.toDTOs(application.findAllTemplateMsgIndustry());
    }

    public Page<TemplateMsgIndustryDTO> pageQueryTemplateMsgIndustry(TemplateMsgIndustryDTO queryVo, int currentPage, int pageSize) {
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _templateMsgIndustry from TemplateMsgIndustry _templateMsgIndustry   where 1=1 ");
        if (queryVo.getNameOneFirst() != null && !"".equals(queryVo.getNameOneFirst())) {
            jpql.append(" and _templateMsgIndustry.nameOneFirst like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getNameOneFirst()));
        }
        if (queryVo.getNameOneSecond() != null && !"".equals(queryVo.getNameOneSecond())) {
            jpql.append(" and _templateMsgIndustry.nameOneSecond like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getNameOneSecond()));
        }
        if (queryVo.getNameTwoFirst() != null && !"".equals(queryVo.getNameTwoFirst())) {
            jpql.append(" and _templateMsgIndustry.nameTwoFirst like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getNameTwoFirst()));
        }
        if (queryVo.getNameTwoSecond() != null && !"".equals(queryVo.getNameTwoSecond())) {
            jpql.append(" and _templateMsgIndustry.nameTwoSecond like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getNameTwoSecond()));
        }
        Page<TemplateMsgIndustry> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(currentPage, pageSize)
                .pagedList();

        return new Page<TemplateMsgIndustryDTO>(pages.getStart(), pages.getResultCount(), pageSize, TemplateMsgIndustryAssembler.toDTOs(pages.getData()));
    }


}
