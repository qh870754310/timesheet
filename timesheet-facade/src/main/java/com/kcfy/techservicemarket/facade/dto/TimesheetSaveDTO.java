package com.kcfy.techservicemarket.facade.dto;

import java.util.List;

/**
 * Created by zhouxc on 2016/6/28.
 */
public class TimesheetSaveDTO {
    private Long timesheetId;
    private Long userId;
    private String date;
    private Integer status;
    private List<TimesheetDetailSaveDTO> timesheetDetailList;

    public Long getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(Long timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TimesheetDetailSaveDTO> getTimesheetDetailList() {
        return timesheetDetailList;
    }

    public void setTimesheetDetailList(List<TimesheetDetailSaveDTO> timesheetDetailList) {
        this.timesheetDetailList = timesheetDetailList;
    }
}
