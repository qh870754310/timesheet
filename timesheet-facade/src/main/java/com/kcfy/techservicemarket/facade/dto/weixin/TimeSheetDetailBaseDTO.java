package com.kcfy.techservicemarket.facade.dto.weixin;

/**
 * Created by daidong on 16/6/29.
 */
public class TimeSheetDetailBaseDTO {
    private String projectName;
    private String stageName;
    private String hour;
    private String taskDesc;
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
}
