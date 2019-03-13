package com.kcfy.techservicemarket.facade.dto;

import com.kcfy.techservicemarket.facade.dto.weixin.TimeSheetDetailBaseDTO;

/**
 * Created by zhouxc on 2016/5/10.
 */
public class TimesheetDetailDTO extends TimeSheetDetailBaseDTO{

    private String timesheetDetailId;

    public String getTimesheetDetailId() {
        return timesheetDetailId;
    }

    public void setTimesheetDetailId(String timesheetDetailId) {
        this.timesheetDetailId = timesheetDetailId;
    }
}
