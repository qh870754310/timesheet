package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.domain.message.MessageJob;
import com.kcfy.techservicemarket.core.domain.message.MessageTemplate;
import com.kcfy.techservicemarket.facade.dto.MessageJobDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MessageJobAssembler {

    public static MessageJobDTO toDTO(MessageJob messageJob) {
        if (messageJob == null) {
            return null;
        }
        MessageJobDTO result = new MessageJobDTO();
        result.setId(messageJob.getId());
        result.setVersion(messageJob.getVersion());
        result.setId(messageJob.getId());
        result.setName(messageJob.getName());
        result.setMemo(messageJob.getMemo());
        result.setCronExpression(messageJob.getCronExpression());
    	result.setMessageTemplateId(messageJob.getMessageTemplate().getId());
        result.setMessageTemplateName(messageJob.getMessageTemplate().getName());
//     	    	result.setAuthorityList (messageJob.getAuthorityList());
        result.setStatus(messageJob.isStatus());
        return result;
    }

    public static List<MessageJobDTO> toDTOs(Collection<MessageJob> messageJobs) {
        if (messageJobs == null) {
            return null;
        }
        List<MessageJobDTO> results = new ArrayList<MessageJobDTO>();
        for (MessageJob each : messageJobs) {
            results.add(toDTO(each));
        }
        return results;
    }

    public static MessageJob toEntity(MessageJobDTO messageJobDTO) {
        if (messageJobDTO == null) {
            return null;
        }
        MessageJob result = new MessageJob();
        result.setId(messageJobDTO.getId());
        result.setVersion(messageJobDTO.getVersion());
        result.setId(messageJobDTO.getId());
        result.setName(messageJobDTO.getName());
        result.setMemo(messageJobDTO.getMemo());
        result.setCronExpression(messageJobDTO.getCronExpression());
        if(messageJobDTO.getMessageTemplateId()!=null){
            MessageTemplate messageTemplate = new MessageTemplate();
            messageTemplate.setId(messageJobDTO.getMessageTemplateId());
            result.setMessageTemplate(messageTemplate);
        }

//         result.setAuthorityList (messageJobDTO.getAuthorityList());
        result.setStatus(messageJobDTO.getStatus());
        return result;
    }

    public static List<MessageJob> toEntities(Collection<MessageJobDTO> messageJobDTOs) {
        if (messageJobDTOs == null) {
            return null;
        }

        List<MessageJob> results = new ArrayList<MessageJob>();
        for (MessageJobDTO each : messageJobDTOs) {
            results.add(toEntity(each));
        }
        return results;
    }
}
