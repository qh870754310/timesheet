package com.kcfy.techservicemarket.facade;

import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Named;

/**
 * Created by zhouxc on 2016/6/22.
 */
public interface CalendarFacade {

    InvokeResult getDateAndTimesheetStatus(Long userId, String startDate, String endDate);

}
