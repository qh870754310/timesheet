package com.kcfy.techservicemarket.facade.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouxc on 2016/5/10.
 */
public class TimesheetDTO {

    private String date;

    private List<TimesheetDetailDTO> timesheetDetailList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TimesheetDetailDTO> getTimesheetDetailList() {
        return timesheetDetailList;
    }

    public void setTimesheetDetailList(List<TimesheetDetailDTO> timesheetDetailList) {
        this.timesheetDetailList = timesheetDetailList;
    }
}
