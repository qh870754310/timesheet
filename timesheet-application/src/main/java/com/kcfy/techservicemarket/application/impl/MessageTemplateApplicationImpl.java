package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.MessageTemplateApplication;
import com.kcfy.techservicemarket.core.domain.message.MessageTemplate;
import com.kcy.techservicemarket.infra.message.HTTPClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.core.domain.Actor;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Named
@Transactional
public class MessageTemplateApplicationImpl implements MessageTemplateApplication {
    private static final Log LOG = LogFactory.getLog(MessageTemplateApplication.class);

    public MessageTemplate getMessageTemplate(Long id) {
        return MessageTemplate.get(MessageTemplate.class, id);
    }

    public void creatMessageTemplate(MessageTemplate messageTemplate) {
        messageTemplate.save();
    }

    public void updateMessageTemplate(MessageTemplate messageTemplate) {
        messageTemplate.save();
    }

    public void removeMessageTemplate(MessageTemplate messageTemplate) {
        if (messageTemplate != null) {
            messageTemplate.remove();
        }
    }

    public void removeMessageTemplates(Set<MessageTemplate> messageTemplates) {
        for (MessageTemplate messageTemplate : messageTemplates) {
            messageTemplate.remove();
        }
    }

    public List<MessageTemplate> findAllMessageTemplate() {
        return MessageTemplate.findAll(MessageTemplate.class);
    }

    @Override
    public void deleteAndCreateMessageTemplate(List<MessageTemplate> messageTemplates) {
        List<MessageTemplate> list = this.findAllMessageTemplate();
        for (MessageTemplate messageTemplate : list) {
            messageTemplate.remove();
        }
        for (MessageTemplate messageTemplate : messageTemplates) {
            creatMessageTemplate(messageTemplate);
        }
    }

    @Override
    public void sendMsgToWeixin(Long userId, String templateId, String url, String... data) {
        String openId = Actor.get(Actor.class,userId).getOpenid();
        String postStr = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"" + url + "\",\n" +
                "           \"data\":{\n" +
                "                   \"person\": {\n" +
                "                       \"value\":\"" + data[0] + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }\n";
        try {
            HttpResponse response = new HTTPClient().doPost("http://localhost:8280/weixin/1.0/templateMsg/send",
                    "Bearer " + "81ff4e92da4f1b9e615deaf432fcbb68", postStr, "application/json");
            String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
            LOG.info("template message send result:" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
