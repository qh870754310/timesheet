package com.kcfy.techservicemarket.facade.dto;

/**
 * Created by daidong on 16/6/27.
 */
public class TimeSheetDefaultTemplateDTO extends TimeSheetDetailTemplateDTO {
    private String projectId;
    private String projectStageId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectStageId() {
        return projectStageId;
    }

    public void setProjectStageId(String projectStageId) {
        this.projectStageId = projectStageId;
    }
}
