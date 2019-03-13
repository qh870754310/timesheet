package com.kcfy.techservicemarket.application;


import com.kcfy.techservicemarket.core.domain.timesetting.TimeSetting;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TimeSettingApplication {

    public TimeSetting getTimeSetting(String id);

    public void createTimeSetting(TimeSetting timeSetting);

    public void updateTimeSetting(TimeSetting timeSetting);

    public void removeTimeSetting(TimeSetting timeSetting);

    public void removeTimeSettings(Set<TimeSetting> timeSettings);

    public List<TimeSetting> findAllTimeSetting();

    public List<TimeSetting> getCurrentPageData(String date);

    public List<TimeSetting> getListByDateRange(String dateStart, String dateEnd);

    public TimeSetting getTimeSettingByDate(String date);

    Map<String,TimeSetting> getTimeSettingMapFromYearFirstDayToNow();
}

