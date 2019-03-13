package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by Qiaohong on 2016/7/6.
 */
public class ProjectListDTO {

    private List<ProjectFitDTO> projectList;

    public List<ProjectFitDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectFitDTO> projectList) {
        this.projectList = projectList;
    }
}

