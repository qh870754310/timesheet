package com.kcfy.techservicemarket.facade.dto;

/**
 * Created by zhouxc on 2016/5/10.
 */
public class TimesheetDetailSaveDTO {
    private Long timesheetDetailId;
    private Integer hour;
    private Long projectId;
    private Long stageId;
    private String taskDesc;

    public Long getTimesheetDetailId() {
        return timesheetDetailId;
    }

    public void setTimesheetDetailId(Long timesheetDetailId) {
        this.timesheetDetailId = timesheetDetailId;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
}
