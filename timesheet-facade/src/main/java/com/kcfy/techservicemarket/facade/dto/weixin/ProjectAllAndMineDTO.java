package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by zhouxc on 2016/6/29.
 */
public class ProjectAllAndMineDTO {
    private List<ProjectFitDTO> projectList;
    private String myProjectId;
    private String myProjectCurrentStageId;
    private List<ProjectStageFitDTO> myProjectStageList;

    public List<ProjectFitDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectFitDTO> projectList) {
        this.projectList = projectList;
    }

    public String getMyProjectId() {
        return myProjectId;
    }

    public void setMyProjectId(String myProjectId) {
        this.myProjectId = myProjectId;
    }

    public String getMyProjectCurrentStageId() {
        return myProjectCurrentStageId;
    }

    public void setMyProjectCurrentStageId(String myProjectCurrentStageId) {
        this.myProjectCurrentStageId = myProjectCurrentStageId;
    }

    public List<ProjectStageFitDTO> getMyProjectStageList() {
        return myProjectStageList;
    }

    public void setMyProjectStageList(List<ProjectStageFitDTO> myProjectStageList) {
        this.myProjectStageList = myProjectStageList;
    }
}
