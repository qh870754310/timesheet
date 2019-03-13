package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.TimeSheetFacade;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhouxc on 2016/7/7.
 */
@Controller
@RequestMapping("/remind")
public class RemindController {

    @Inject
    private TimeSheetFacade timesheetFacede;

    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    @ResponseBody
    public InvokeResult sendMsg(Long userId, Integer remindType) {
        return timesheetFacede.sendRemindMsgToTeam(userId, remindType);
    }
}
