package com.kcfy.techservicemarket.facade.dto;

import com.kcfy.techservicemarket.facade.dto.weixin.TimeSheetDetailBaseDTO;

/**
 * Created by Qiaohong on 2016/6/30.
 */
public class TimesheetDetailGetDTO extends TimeSheetDetailBaseDTO{

    private String timesheetDetailId;

    private String projectId;

    private String projectStageId;

    public String getTimesheetDetailId() {
        return timesheetDetailId;
    }

    public void setTimesheetDetailId(String timesheetDetailId) {
        this.timesheetDetailId = timesheetDetailId;
    }

    public String getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(String projectStageId) {
        this.projectStageId = projectStageId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
