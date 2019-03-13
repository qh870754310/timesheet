package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.TimeSheetTemplateFacade;
import com.kcfy.techservicemarket.facade.dto.TimesheetTemplateSaveWrapDTO;
import com.kcfy.techservicemarket.infra.util.JacksonUtil;
import org.apache.commons.io.IOUtils;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by daidong on 16/6/27.
 */
@Controller
@RequestMapping("/template")
public class TimesheetTemplateController {
    @Inject
    private TimeSheetTemplateFacade timeSheetTemplateFacade;

    @RequestMapping(value = "/getDefault")
     @ResponseBody
    public InvokeResult getDefaultTemplate(Long userId){
        InvokeResult invokeResult = null;
        if(userId != null) {
            invokeResult = timeSheetTemplateFacade.getDefaultTemplate(userId);

        }else {
            invokeResult = InvokeResult.failure("此用户信息为空.");
        }
        return invokeResult;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public InvokeResult getTemplateList(Long userId){
        InvokeResult invokeResult = null;
        if(userId != null) {
        invokeResult = timeSheetTemplateFacade.getTemplateList(userId);
        }else {
            invokeResult = InvokeResult.failure("此用户信息为空.");
        }
        return invokeResult;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InvokeResult getTemplateById(@PathVariable Long id){
        InvokeResult invokeResult = null;
        if(id != null) {
        invokeResult = timeSheetTemplateFacade.getTemplateById(id);
        }else {
            invokeResult = InvokeResult.failure("模板id为空");
        }
        return invokeResult;
    }

    @RequestMapping(value="/setDefault")
    @ResponseBody
    public InvokeResult setDefault(Long userId,Long id){
        InvokeResult invokeResult = null;
        if(userId != null & id != null){
            invokeResult = timeSheetTemplateFacade.setDefault(id, userId);
        } else{invokeResult = InvokeResult.failure("模板id或者用户id为空");}
        return invokeResult;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public InvokeResult deleteTemplateById(@PathVariable Long id){
        InvokeResult invokeResult = null;
        if(id != null) {
            timeSheetTemplateFacade.deleteTemplateById(id);
        }else{invokeResult = InvokeResult.failure("用户id为空");}
        return invokeResult;
    }

    @RequestMapping(value = "", method = RequestMethod.POST )
    @ResponseBody
    public InvokeResult add(HttpServletRequest request) {
        InvokeResult invokeResult;
        try {
            String json = IOUtils.toString(request.getInputStream(),"utf-8");
            TimesheetTemplateSaveWrapDTO timesheetTemplateSaveWrapDTO = JacksonUtil.fromObject(json, TimesheetTemplateSaveWrapDTO.class);
            if(timesheetTemplateSaveWrapDTO.getTemplate().getTemplateId() != null) {
                // 草稿提交会用到，更新也可以用，但是约定用下面的方法来更新
                invokeResult = timeSheetTemplateFacade.update(timesheetTemplateSaveWrapDTO);
            } else {
                // 保存新增或提高新的
                invokeResult = timeSheetTemplateFacade.create(timesheetTemplateSaveWrapDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
            invokeResult = InvokeResult.failure(e.getMessage());
        }
        return invokeResult;
    }
    @RequestMapping(value = "", method = RequestMethod.PUT )
    @ResponseBody
    public InvokeResult update(HttpServletRequest request) {
        InvokeResult invokeResult;
        try {
            String json = IOUtils.toString(request.getInputStream(),"utf-8");
            TimesheetTemplateSaveWrapDTO timesheetTemplateSaveWrapDTO = JacksonUtil.fromObject(json, TimesheetTemplateSaveWrapDTO.class);
            invokeResult = timeSheetTemplateFacade.update(timesheetTemplateSaveWrapDTO);
        } catch (IOException e) {
            e.printStackTrace();
            invokeResult = InvokeResult.failure(e.getMessage());
        }
        return invokeResult;
    }

}
