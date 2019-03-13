
package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.TimeSettingFacade;
import com.kcfy.techservicemarket.facade.dto.TimeSettingDTO;
import com.kcfy.techservicemarket.infra.util.TimeSettingDayType;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.koalacommons.resourceloader.util.StringUtils;
import org.openkoala.organisation.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/TimeSetting")
public class TimeSettingController extends BaseController {
    @Inject
    private TimeSettingFacade timeSettingFacade;

    /**
     * 日历显示页面
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/query-for-calendar")
    public String queryForCalender() {
        return "/timesetting/timesetting-calendar";
    }

    @ResponseBody
    @RequestMapping(value = "/getDayType", method = {RequestMethod.GET})
    public Map<String, Integer> getDayType() {
        return TimeSettingDayType.getAllTimeSettingDayTypeMap();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public InvokeResult add(TimeSettingDTO timeSettingDTO) {
        InvokeResult invokeResult = null;
        if(StringUtils.isEmpty(timeSettingDTO.getId())) {
            invokeResult = timeSettingFacade.createTimeSetting(timeSettingDTO);
        } else {
            invokeResult = timeSettingFacade.updateTimeSetting(timeSettingDTO);
        }
        return invokeResult;
    }

    @ResponseBody
    @RequestMapping(value = "/getByDate", method = {RequestMethod.GET})
    public InvokeResult getByDate(@RequestParam("date") String date) {
        InvokeResult invokeResult = timeSettingFacade.getTimeSettingByDate(date);
        return invokeResult;
    }

    @ResponseBody
    @RequestMapping(value = "/getCurrentPageData", method = {RequestMethod.GET})
    public List<TimeSettingDTO> getCurrentPageData(@RequestParam("date") String date) {

        return timeSettingFacade.getCurrentPageData(date);
    }
}
