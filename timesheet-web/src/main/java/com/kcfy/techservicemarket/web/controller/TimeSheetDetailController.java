package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.TimeSheetDetailFacade;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by Qiaohong on 2016/6/30.
 */
@Controller
@RequestMapping("/timesheetDetail")
public class TimeSheetDetailController {

    @Inject
    private TimeSheetDetailFacade timeSheetDetailFacade;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public InvokeResult get(@PathVariable String id) {
        return timeSheetDetailFacade.getTimeSheetDetail(id);
    }

    @RequestMapping(value = "/team/rejected/list")
    @ResponseBody
    public InvokeResult teamRejectedList(Long userId) {
        return timeSheetDetailFacade.getTeamRejectedDetailListByPMUserId(userId);
    }
}
