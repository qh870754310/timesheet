package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by zhouxc on 2016/6/27.
 */
public class TimesheetDetailRejectedListForPM {
    private List<TimesheetDetailRejectedForPM> rejectedList;

    public List<TimesheetDetailRejectedForPM> getRejectedList() {
        return rejectedList;
    }

    public void setRejectedList(List<TimesheetDetailRejectedForPM> rejectedList) {
        this.rejectedList = rejectedList;
    }
}
