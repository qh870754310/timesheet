package com.kcfy.techservicemarket.web.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kcfy.techservicemarket.facade.TemplateMsgIndustryFacade;
import com.kcfy.techservicemarket.facade.dto.TemplateMsgIndustryDTO;
import com.kcfy.techservicemarket.infra.util.IndustryCode;
import com.kcfy.techservicemarket.infra.wso2.HttpHelper;
import com.kcy.techservicemarket.infra.message.HTTPClient;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/TemplateMsgIndustry")
public class TemplateMsgIndustryController {

    @Inject
    private TemplateMsgIndustryFacade templateMsgIndustryFacade;

    @ResponseBody
    @RequestMapping("/add")
    public InvokeResult add(TemplateMsgIndustryDTO templateMsgIndustryDTO) {
        return templateMsgIndustryFacade.creatTemplateMsgIndustry(templateMsgIndustryDTO);
    }

    @ResponseBody
    @RequestMapping("/update")
    public InvokeResult update(TemplateMsgIndustryDTO templateMsgIndustryDTO) {
        return templateMsgIndustryFacade.updateTemplateMsgIndustry(templateMsgIndustryDTO);
    }

    @ResponseBody
    @RequestMapping("/pageJson")
    public Page pageJson(TemplateMsgIndustryDTO templateMsgIndustryDTO, @RequestParam int page, @RequestParam int pagesize) {
        Page<TemplateMsgIndustryDTO> all = templateMsgIndustryFacade.pageQueryTemplateMsgIndustry(templateMsgIndustryDTO, page, pagesize);
        return all;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public InvokeResult remove(@RequestParam String ids) {
        String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            idArrs[i] = Long.valueOf(value[i]);
        }
        return templateMsgIndustryFacade.removeTemplateMsgIndustrys(idArrs);
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public InvokeResult get(@PathVariable Long id) {
        return templateMsgIndustryFacade.getTemplateMsgIndustry(id);
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
    @RequestMapping("/setWeixin/{id}")
    public InvokeResult setWeixin(@PathVariable Long id) throws IOException {
        InvokeResult invokeResult =  templateMsgIndustryFacade.getTemplateMsgIndustry(id);
        TemplateMsgIndustryDTO dto =  (TemplateMsgIndustryDTO)invokeResult.getData();
        String postStr = "{\"industry_id1\":\""+dto.getCodeOne()+"\",\"industry_id2\":\""+dto.getCodeTwo()+"\"}";
        HttpResponse response = new HTTPClient().doPost("http://localhost:8280/weixin/1.0/templatemsgIndustryController/setWeixin",
                "Bearer " + "81ff4e92da4f1b9e615deaf432fcbb68",postStr,"application/json");
        String result = IOUtils.toString(response.getEntity().getContent(), "utf-8");
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        if (Boolean.valueOf(jsonObject.get("hasErrors").toString())) {
            invokeResult = InvokeResult.failure(jsonObject.get("errorMessage").toString().replaceAll("\"", ""));
        } else {
            invokeResult = InvokeResult.success();
        }
        return invokeResult;
    }

}
