package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.TimeSettingApplication;
import com.kcfy.techservicemarket.core.domain.timesetting.TimeSetting;
import com.kcfy.techservicemarket.infra.util.DateTool;
import com.kcfy.techservicemarket.infra.util.TimeSettingDayType;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.*;

@Named
@Transactional
public class TimeSettingApplicationImpl implements TimeSettingApplication {

    public TimeSetting getTimeSetting(String id) {
        return TimeSetting.get(TimeSetting.class, id);
    }

    public void createTimeSetting(TimeSetting timeSetting) {
        timeSetting.save();
    }

    public void updateTimeSetting(TimeSetting timeSetting) {
        timeSetting.save();
    }

    public void removeTimeSetting(TimeSetting timeSetting) {
        if (timeSetting != null) {
            timeSetting.remove();
        }
    }

    public void removeTimeSettings(Set<TimeSetting> timeSettings) {
        for (TimeSetting timeSetting : timeSettings) {
            timeSetting.remove();
        }
    }

    public List<TimeSetting> findAllTimeSetting() {
        return TimeSetting.findAll(TimeSetting.class);
    }

    @Override
    public List<TimeSetting> getCurrentPageData(String date) {
        // 用来显示fullcalendar上个月或下个月在本月的填充信息
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateTool.convertStringToDate("yyyy-MM-dd", date));
        calendar.add(Calendar.DAY_OF_MONTH, 41);
        Date dateEnd = calendar.getTime();
        return this.getListByDateRange(date, DateTool.convertDateToString("yyyy-MM-dd", dateEnd));
    }

    public List<TimeSetting> getListByDateRange(String dateStart, String dateEnd) {
        List<TimeSetting> timeSettingList = TimeSetting.getByDateRange(dateStart, dateEnd);
        List<String> dbDateList = new ArrayList<String>();
        for(TimeSetting timeSetting : timeSettingList) {
            dbDateList.add(timeSetting.getDate());
        }
        // 添加自动补全日期类型功能，数据库为空，区分出周末和工作日。数据库数据不为空，以数据库数据为准。
        Long startTime;
        Long endTime;

        startTime = DateTool.convertStringToDate("yyyy-MM-dd", dateStart).getTime();
        endTime = DateTool.convertStringToDate("yyyy-MM-dd", dateEnd).getTime();
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTime;
        Calendar calendar = Calendar.getInstance();
        while (time <= endTime) {
            // 判断timeSettingList中是否有数据，有跳到下一次循环，没有则继续。
            //System.out.println(DateTool.convertLongToString("yyyy-MM-dd", time));
            calendar.setTimeInMillis(time);
            //System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                String date = DateTool.convertLongToString("yyyy-MM-dd", time);
                if (!dbDateList.contains(date)) {
                    TimeSetting timeSetting = new TimeSetting();
                    timeSetting.setDate(date);
                    calendar.set(Calendar.HOUR, 9);
                    timeSetting.setStartTime(calendar.getTime());
                    calendar.set(Calendar.HOUR, 17);
//                        calendar.set(Calendar.MINUTE , 30);
                    timeSetting.setEndTime(calendar.getTime());
                    timeSetting.setDayType(TimeSettingDayType.WEEKEND.getValue());
                    timeSettingList.add(timeSetting);
                }
            }
            time += oneDay;
        }

        return timeSettingList;
    }

    public TimeSetting getTimeSettingByDate(String date) {
        TimeSetting timeSetting = TimeSetting.getByDate(date);
        if (timeSetting == null) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(DateTool.convertStringToDate("yyyy-MM-dd", date));
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                timeSetting = new TimeSetting();
                timeSetting.setDate(date);
                calendar.set(Calendar.HOUR, 9);
                timeSetting.setStartTime(calendar.getTime());
                calendar.set(Calendar.HOUR, 17);
//                    calendar.set(Calendar.MINUTE , 30);
                timeSetting.setEndTime(calendar.getTime());
                timeSetting.setDayType(TimeSettingDayType.WEEKEND.getValue());
            }

        }
        return timeSetting;
    }

    @Override
    public Map<String, TimeSetting> getTimeSettingMapFromYearFirstDayToNow() {
        // 获取今年日期的所有配置
        List<TimeSetting> timeSettingList = getListByDateRange(
                DateTool.convertDateToString("yyyy-MM-dd", DateTool.getFirstDayOfThisYear()),
                DateTool.convertDateToString("yyyy-MM-dd", new Date()));
        Map<String, TimeSetting> timeSettingMap = new HashMap<>();
        for (TimeSetting timeSetting : timeSettingList) {
            timeSettingMap.put(timeSetting.getDate(), timeSetting);
        }
        return timeSettingMap;
    }
}
