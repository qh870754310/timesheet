package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by Qiaohong on 2016/7/4.
 */
public class ProjectStageHourDTO {
    private String projectId;

    private String projectName;

    private List<ProjectStageHour> timeList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<ProjectStageHour> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<ProjectStageHour> timeList) {
        this.timeList = timeList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
