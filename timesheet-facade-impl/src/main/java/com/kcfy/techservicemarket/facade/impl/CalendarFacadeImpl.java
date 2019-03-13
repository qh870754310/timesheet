package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.TimeSettingApplication;
import com.kcfy.techservicemarket.application.TimesheetApplication;
import com.kcfy.techservicemarket.core.Constants;
import com.kcfy.techservicemarket.core.domain.timesetting.TimeSetting;
import com.kcfy.techservicemarket.core.domain.timesheet.Timesheet;
import com.kcfy.techservicemarket.facade.CalendarFacade;
import com.kcfy.techservicemarket.facade.dto.DayFillStatusAndType;
import com.kcfy.techservicemarket.facade.dto.DayFillStatusAndTypeList;
import com.kcfy.techservicemarket.infra.util.DateTool;
import com.kcfy.techservicemarket.infra.util.TimeSettingDayType;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * Created by zhouxc on 2016/6/22.
 */
@Named
public class CalendarFacadeImpl implements CalendarFacade {
    @Inject
    private TimesheetApplication application;
    @Inject
    private TimeSettingApplication timeSettingApplication;

    @Override
    public InvokeResult getDateAndTimesheetStatus(Long userId, String startDate, String endDate) {
        // 获取所有日期
        List<Date> allDate = DateTool.findDatesBetween(startDate, endDate);
        // 获取所有配置
        List<TimeSetting> timeSettingList = timeSettingApplication.getListByDateRange(startDate, endDate);
        Map<String, TimeSetting> timeSettingMap = new HashMap<>();
        for (TimeSetting timeSetting : timeSettingList) {
            timeSettingMap.put(timeSetting.getDate(), timeSetting);
        }
        // 获取填报的日期。
        List<Timesheet> timesheetList = application.findTimesheetBetween(userId, DateTool.convertStringToDate("yyyy-MM-dd", startDate), DateTool.convertStringToDate("yyyy-MM-dd", endDate));
        Map<String, Timesheet> timesheetMap = new HashMap<>();
        for (Timesheet timesheet : timesheetList) {
            timesheetMap.put(DateTool.convertDateToString("yyyy-MM-dd", timesheet.getDate()), timesheet);
        }

        // 缺失的列表
        List<DayFillStatusAndType> resultList = new ArrayList<>();
        for (Date date : allDate) {
            String dateTmp = DateTool.convertDateToString("yyyy-MM-dd", date);

            DayFillStatusAndType dayFillStatusAndType = new DayFillStatusAndType();
            dayFillStatusAndType.setDate(dateTmp);

            if (timeSettingMap.containsKey(dateTmp)) {
                // 有设置
                TimeSetting timeSetting = timeSettingMap.get(dateTmp);
                dayFillStatusAndType.setDateType(timeSetting.getDayType());
            } else {
                // 无设置，看周末
                if (DateTool.isWorkDay(dateTmp)) {
                    dayFillStatusAndType.setDateType(TimeSettingDayType.WORKDAY.getValue());
                } else {
                    dayFillStatusAndType.setDateType(TimeSettingDayType.WEEKEND.getValue());
                }
            }
            if (timesheetMap.containsKey(dateTmp)) {
                Timesheet timesheet = timesheetMap.get(dateTmp);
                dayFillStatusAndType.setFillStatus(timesheet.getStatus());
            } else {
                dayFillStatusAndType.setFillStatus(Constants.TimesheetStatus.NONE);
            }
            resultList.add(dayFillStatusAndType);
        }
        DayFillStatusAndTypeList dayFillStatusAndTypeList = new DayFillStatusAndTypeList();
        dayFillStatusAndTypeList.setDateList(resultList);
        return InvokeResult.success(dayFillStatusAndTypeList);
    }
}
