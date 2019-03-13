package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.application.TimeSheetDetailApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Qiaohong on 2016/6/30.
 */
@Named
@Transactional
public class TimeSheetDetailApplicationImpl implements TimeSheetDetailApplication{

    @Override
    public TimesheetDetail getTimeSheetDetail(String id) {
        return TimesheetDetail.get(TimesheetDetail.class, Long.valueOf(id));
    }
}
