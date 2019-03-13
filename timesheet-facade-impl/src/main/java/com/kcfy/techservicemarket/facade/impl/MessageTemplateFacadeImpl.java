package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.MessageTemplateApplication;
import com.kcfy.techservicemarket.core.domain.message.MessageTemplate;
import com.kcfy.techservicemarket.facade.MessageTemplateFacade;
import com.kcfy.techservicemarket.facade.dto.MessageTemplateDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.TemplateMsgDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.MessageTemplateAssembler;
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
public class MessageTemplateFacadeImpl implements MessageTemplateFacade {

    @Inject
    private MessageTemplateApplication application;

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    public InvokeResult getMessageTemplate(Long id) {
        return InvokeResult.success(MessageTemplateAssembler.toDTO(application.getMessageTemplate(id)));
    }

    public InvokeResult creatMessageTemplate(MessageTemplateDTO messageTemplateDTO) {
        application.creatMessageTemplate(MessageTemplateAssembler.toEntity(messageTemplateDTO));
        return InvokeResult.success();
    }

    public InvokeResult updateMessageTemplate(MessageTemplateDTO messageTemplateDTO) {
        application.updateMessageTemplate(MessageTemplateAssembler.toEntity(messageTemplateDTO));
        return InvokeResult.success();
    }

    public InvokeResult removeMessageTemplate(Long id) {
        application.removeMessageTemplate(application.getMessageTemplate(id));
        return InvokeResult.success();
    }

    public InvokeResult removeMessageTemplates(Long[] ids) {
        Set<MessageTemplate> messageTemplates = new HashSet<MessageTemplate>();
        for (Long id : ids) {
            messageTemplates.add(application.getMessageTemplate(id));
        }
        application.removeMessageTemplates(messageTemplates);
        return InvokeResult.success();
    }

    public List<MessageTemplateDTO> findAllMessageTemplate() {
        return MessageTemplateAssembler.toDTOs(application.findAllMessageTemplate());
    }

    public Page<MessageTemplateDTO> pageQueryMessageTemplate(MessageTemplateDTO queryVo, int currentPage, int pageSize) {
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _messageTemplate from MessageTemplate _messageTemplate   where 1=1 ");
        if (queryVo.getTemplateId() != null && !"".equals(queryVo.getTemplateId())) {
            jpql.append(" and _messageTemplate.templateId like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getTemplateId()));
        }
        if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
            jpql.append(" and _messageTemplate.name like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
        }
        if (queryVo.getContent() != null && !"".equals(queryVo.getContent())) {
            jpql.append(" and _messageTemplate.content like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getContent()));
        }
        Page<MessageTemplate> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(currentPage, pageSize)
                .pagedList();

        return new Page<MessageTemplateDTO>(pages.getStart(), pages.getResultCount(), pageSize, MessageTemplateAssembler.toDTOs(pages.getData()));
    }

    @Override
    public InvokeResult deleteAndCreateMessageTemplate(List<TemplateMsgDTO> templateMsgDTOList) {
        application.deleteAndCreateMessageTemplate(MessageTemplateAssembler.entitiesFromWeixin(templateMsgDTOList));
        return null;
    }

    @Override
    public void sendMsgToWeixin(Long userId, String templateId, String url, String... data) {
        application.sendMsgToWeixin(userId, templateId, url, data);
    }


}
