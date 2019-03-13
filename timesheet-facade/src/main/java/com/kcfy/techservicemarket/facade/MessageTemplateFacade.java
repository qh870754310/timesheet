package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.MessageTemplateDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.TemplateMsgDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;

public interface MessageTemplateFacade {

    public InvokeResult getMessageTemplate(Long id);

    public InvokeResult creatMessageTemplate(MessageTemplateDTO messageTemplate);

    public InvokeResult updateMessageTemplate(MessageTemplateDTO messageTemplate);

    public InvokeResult removeMessageTemplate(Long id);

    public InvokeResult removeMessageTemplates(Long[] ids);

    public List<MessageTemplateDTO> findAllMessageTemplate();

    public Page<MessageTemplateDTO> pageQueryMessageTemplate(MessageTemplateDTO messageTemplate, int currentPage, int pageSize);


    InvokeResult deleteAndCreateMessageTemplate(List<TemplateMsgDTO> templateMsgDTOList);

    void sendMsgToWeixin(Long userId, String templateId, String url, String... data);
}

