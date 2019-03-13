package com.kcfy.techservicemarket.application;


import com.kcfy.techservicemarket.core.domain.message.MessageTemplate;
import org.openkoala.organisation.core.domain.Employee;

import java.util.List;
import java.util.Set;

public interface MessageTemplateApplication {

    public MessageTemplate getMessageTemplate(Long id);

    public void creatMessageTemplate(MessageTemplate messageTemplate);

    public void updateMessageTemplate(MessageTemplate messageTemplate);

    public void removeMessageTemplate(MessageTemplate messageTemplate);

    public void removeMessageTemplates(Set<MessageTemplate> messageTemplates);

    public List<MessageTemplate> findAllMessageTemplate();

    void deleteAndCreateMessageTemplate(List<MessageTemplate> messageTemplates);

    void sendMsgToWeixin(Long userId, String templateId, String url, String... data);
}

