package com.kcfy.techservicemarket.facade.dto.weixin;

/**
 * Created by Qiaohong on 2016/7/4.
 */
public class ProjectStageHour {

    private String projectStageId;

    private String stageName;

    private String hour;

    public String getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(String projectStageId) {
        this.projectStageId = projectStageId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
