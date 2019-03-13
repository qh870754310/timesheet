package com.kcfy.techservicemarket.application;

import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;

/**
 * Created by Qiaohong on 2016/6/30.
 */

public interface TimeSheetDetailApplication {

    TimesheetDetail getTimeSheetDetail(String id);
}
