package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by Qiaohong on 2016/7/5.
 */
public class ProjectTimeDetailList {

    private List<ProjectTimesDTO> projectTimeList;

    public List<ProjectTimesDTO> getProjectTimeList() {
        return projectTimeList;
    }

    public void setProjectTimeList(List<ProjectTimesDTO> projectTimeList) {
        this.projectTimeList = projectTimeList;
    }
}
