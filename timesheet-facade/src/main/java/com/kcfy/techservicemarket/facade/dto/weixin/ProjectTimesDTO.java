package com.kcfy.techservicemarket.facade.dto.weixin;

/**
 * Created by Qiaohong on 2016/7/5.
 */
public class ProjectTimesDTO {

    private String projectId;

    private String projectName;

    private String hour;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
