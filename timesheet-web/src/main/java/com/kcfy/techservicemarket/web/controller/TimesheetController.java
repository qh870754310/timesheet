package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.TimeSheetFacade;
import com.kcfy.techservicemarket.facade.dto.TimesheetDTO;
import com.kcfy.techservicemarket.facade.dto.TimesheetSaveWrapDTO;
import com.kcfy.techservicemarket.infra.util.DateTool;
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
 * Created by zhouxc on 2016/5/10.
 */
@Controller
@RequestMapping("/timesheet")
public class TimesheetController {
    @Inject
    private TimeSheetFacade timesheetFacede;

    @RequestMapping(value = "", method = RequestMethod.POST )
    @ResponseBody
    public InvokeResult add(HttpServletRequest request) {
        InvokeResult invokeResult;
        try {
            String json = IOUtils.toString(request.getInputStream(),"utf-8");
            TimesheetSaveWrapDTO timesheetDTO = JacksonUtil.fromObject(json, TimesheetSaveWrapDTO.class);
            if(timesheetDTO.getTimesheet().getTimesheetId() != null) {
                // 草稿提交会用到，更新也可以用，但是约定用下面的方法来更新
                invokeResult = timesheetFacede.updateTimesheet(timesheetDTO);
            } else {
                // 保存新增或提高新的
                invokeResult = timesheetFacede.createTimesheet(timesheetDTO);
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
            TimesheetSaveWrapDTO timesheetDTO = JacksonUtil.fromObject(json, TimesheetSaveWrapDTO.class);
            invokeResult = timesheetFacede.updateTimesheet(timesheetDTO);
        } catch (IOException e) {
            e.printStackTrace();
            invokeResult = InvokeResult.failure(e.getMessage());
        }
        return invokeResult;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public InvokeResult add(@PathVariable String id) {
        return timesheetFacede.removeTimesheet(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InvokeResult get(@PathVariable String id) {
        InvokeResult invokeResult = timesheetFacede.getTimesheet(id);
        return invokeResult;
    }

    @RequestMapping(value = "/getByUserIdAndDate", method = RequestMethod.GET)
    @ResponseBody
    public InvokeResult getByUserIdAndDate(Long userId, String date) {
        InvokeResult invokeResult = timesheetFacede.getByUserIdAndDate(userId, DateTool.convertStringToDate("yyyy-MM-dd", date));
        return invokeResult;
    }

    @RequestMapping(value = "/getByUserId")
    @ResponseBody
    public List<TimesheetDTO> getByUserId(String userId) {
        return timesheetFacede.getByUserId(userId);
    }


    @RequestMapping(value = "/draft/list")
    @ResponseBody
    public InvokeResult draftList(Long userId) {
        return timesheetFacede.getDraftList(userId);
    }
    @RequestMapping(value = "/missing/list")
    @ResponseBody
    public InvokeResult missingList(Long userId) {
        return timesheetFacede.getMissingDateList(userId);
    }

    @RequestMapping(value = "/rejected/list")
    @ResponseBody
    public InvokeResult rejectedList(Long userId) {
        return timesheetFacede.getRejectedDetailList(userId);
    }

    @RequestMapping(value = "/team/missing/list")
    @ResponseBody
    public InvokeResult teamMissingList(Long userId) {
        return timesheetFacede.getTeamMissingDateListByPMUserId(userId);
    }

}
