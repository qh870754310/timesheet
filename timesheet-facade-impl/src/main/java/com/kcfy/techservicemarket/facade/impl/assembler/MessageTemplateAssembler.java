package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.domain.message.MessageTemplate;
import com.kcfy.techservicemarket.facade.dto.MessageTemplateDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.TemplateMsgDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MessageTemplateAssembler {

    public static MessageTemplateDTO toDTO(MessageTemplate messageTemplate) {
        if (messageTemplate == null) {
            return null;
        }
        MessageTemplateDTO result = new MessageTemplateDTO();
        result.setId(messageTemplate.getId());
        result.setVersion(messageTemplate.getVersion());
        result.setId(messageTemplate.getId());
        result.setTemplateId(messageTemplate.getTemplateId());
        result.setName(messageTemplate.getName());
        result.setContent(messageTemplate.getContent());
        return result;
    }

    public static List<MessageTemplateDTO> toDTOs(Collection<MessageTemplate> messageTemplates) {
        if (messageTemplates == null) {
            return null;
        }
        List<MessageTemplateDTO> results = new ArrayList<MessageTemplateDTO>();
        for (MessageTemplate each : messageTemplates) {
            results.add(toDTO(each));
        }
        return results;
    }

    public static MessageTemplate toEntity(MessageTemplateDTO messageTemplateDTO) {
        if (messageTemplateDTO == null) {
            return null;
        }
        MessageTemplate result = new MessageTemplate();
        result.setId(messageTemplateDTO.getId());
        result.setVersion(messageTemplateDTO.getVersion());
        result.setTemplateId(messageTemplateDTO.getTemplateId());
        result.setName(messageTemplateDTO.getName());
        result.setContent(messageTemplateDTO.getContent());
        return result;
    }

    public static List<MessageTemplate> toEntities(Collection<MessageTemplateDTO> messageTemplateDTOs) {
        if (messageTemplateDTOs == null) {
            return null;
        }

        List<MessageTemplate> results = new ArrayList<MessageTemplate>();
        for (MessageTemplateDTO each : messageTemplateDTOs) {
            results.add(toEntity(each));
        }
        return results;
    }
    public static MessageTemplate fromWeixin(TemplateMsgDTO messageTemplateDTO) {
        if (messageTemplateDTO == null) {
            return null;
        }
        MessageTemplate result = new MessageTemplate();
        //result.setId(messageTemplateDTO.getId());
        //result.setVersion(messageTemplateDTO.getVersion());
        //result.setId(messageTemplateDTO.getId());
        result.setTemplateId(messageTemplateDTO.getTemplate_id());
        result.setName(messageTemplateDTO.getTitle());
        result.setContent(messageTemplateDTO.getContent());
        return result;
    }
    public static List<MessageTemplate> entitiesFromWeixin(Collection<TemplateMsgDTO> messageTemplateDTOs) {
        if (messageTemplateDTOs == null) {
            return null;
        }

        List<MessageTemplate> results = new ArrayList<MessageTemplate>();
        for (TemplateMsgDTO each : messageTemplateDTOs) {
            results.add(fromWeixin(each));
        }
        return results;
    }
}
