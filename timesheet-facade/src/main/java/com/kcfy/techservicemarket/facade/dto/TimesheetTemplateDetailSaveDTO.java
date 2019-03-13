package com.kcfy.techservicemarket.facade.dto;

/**
 * Created by zhouxc on 2016/7/1.
 */
public class TimesheetTemplateDetailSaveDTO {
    private Long templateDetailId;
    private String hour;
    private Long projectId;
    private Long stageId;
    private String taskDesc;

    public Long getTemplateDetailId() {
        return templateDetailId;
    }

    public void setTemplateDetailId(Long templateDetailId) {
        this.templateDetailId = templateDetailId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
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
