package com.kcfy.techservicemarket.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kcfy.techservicemarket.facade.MessageTemplateFacade;
import com.kcfy.techservicemarket.facade.dto.MessageTemplateDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.TemplateMsgDTO;
import com.kcy.techservicemarket.infra.message.HTTPClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/MessageTemplate")
public class MessageTemplateController {
    private static final Log LOG = LogFactory.getLog(MessageTemplateController.class);
    @Inject
    private MessageTemplateFacade messageTemplateFacade;

    @ResponseBody
    @RequestMapping("/add")
    public InvokeResult add(MessageTemplateDTO messageTemplateDTO) {
        return messageTemplateFacade.creatMessageTemplate(messageTemplateDTO);
    }

    @ResponseBody
    @RequestMapping("/update")
    public InvokeResult update(MessageTemplateDTO messageTemplateDTO) {
        return messageTemplateFacade.updateMessageTemplate(messageTemplateDTO);
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(MessageTemplateDTO messageTemplateDTO, @RequestParam int page, @RequestParam int pagesize) {
        Page<MessageTemplateDTO> all = messageTemplateFacade.pageQueryMessageTemplate(messageTemplateDTO, page, pagesize);
        return all;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public InvokeResult remove(@RequestParam String ids) {
        String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.parseLong(value[i]);
        }
        return messageTemplateFacade.removeMessageTemplates(idArrs);
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public InvokeResult get(@PathVariable Long id) {
        return messageTemplateFacade.getMessageTemplate(id);
    }

    @ResponseBody
    @RequestMapping("/getWeixin")
    public InvokeResult getWeixin() throws IOException {
        InvokeResult invokeResult = null;
        HttpResponse response = new HTTPClient().doGet("http://localhost:8280/weixin/1.0/templateMsg/getTemplate",
                "Bearer " + "81ff4e92da4f1b9e615deaf432fcbb68");
        String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
        result = result.substring(1, result.length() - 1).replace("\\", "");
        LOG.info(result);
        JsonParser parser = new JsonParser();
        JsonObject rootObejct = parser.parse(result).getAsJsonObject();
        JsonElement jsonElement = rootObejct.get("template_list");

        Gson gson = new Gson();
        List<TemplateMsgDTO> templateMsgDTOList = new ArrayList<>();

        if (jsonElement.isJsonObject()) {
            //The returned list has only 1 element
            TemplateMsgDTO project = gson.fromJson(jsonElement, TemplateMsgDTO.class);
            templateMsgDTOList.add(project);
        }
        else if (jsonElement.isJsonArray()) {
            //The returned list has >1 elements
            Type type = new TypeToken<List<TemplateMsgDTO>>() {}.getType();
            templateMsgDTOList = gson.fromJson(jsonElement, type);
        }
        messageTemplateFacade.deleteAndCreateMessageTemplate(templateMsgDTOList);
        invokeResult = InvokeResult.success();
        return invokeResult;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }

    @ResponseBody
    @RequestMapping("/sendTest")
    public InvokeResult sendTest() throws IOException {
        messageTemplateFacade.sendMsgToWeixin(600L , "rJBNTtzQbqMdC-v6bMelBOvKgtX8i8SWyNAG-C4CZ2o","http://www.baidu.com", "周晓晨");
        return InvokeResult.success();
    }

}
