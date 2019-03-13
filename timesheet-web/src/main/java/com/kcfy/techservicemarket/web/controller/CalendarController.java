package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.CalendarFacade;
import com.kcfy.techservicemarket.facade.TimeSheetFacade;
import com.kcfy.techservicemarket.facade.dto.TimesheetDTO;
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
import java.util.List;

/**
 * Created by zhouxc on 2016/6/22.
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @Inject
    private CalendarFacade calendarFacade;

    @RequestMapping(value = "/getDateAndTimesheetStatus")
    @ResponseBody
    public InvokeResult getDateAndTimesheetStatus(Long userId, String startDate ,String endDate, HttpServletRequest request) {
        return calendarFacade.getDateAndTimesheetStatus(userId,startDate,endDate);
    }

}
