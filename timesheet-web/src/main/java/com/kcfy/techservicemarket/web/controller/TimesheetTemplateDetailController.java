package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.TimeSheetTemplateFacade;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by daidong on 16/6/30.
 */
@Controller
@RequestMapping("/templateDetail")
public class TimesheetTemplateDetailController {
    @Inject
    private TimeSheetTemplateFacade timeSheetTemplateFacade;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InvokeResult getTemplateById(@PathVariable Long id){
        InvokeResult invokeResult = null;
        if(id != null) {
            invokeResult = timeSheetTemplateFacade.getTemplateDetailById(id);
        }else{
            invokeResult = InvokeResult.failure("模板ID为空");
        }
        return invokeResult;
    }
}
