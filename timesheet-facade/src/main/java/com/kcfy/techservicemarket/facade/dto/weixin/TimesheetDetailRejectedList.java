package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by zhouxc on 2016/6/27.
 */
public class TimesheetDetailRejectedList {
    private List<TimesheetDetailRejected> rejectedList;

    public List<TimesheetDetailRejected> getRejectedList() {
        return rejectedList;
    }

    public void setRejectedList(List<TimesheetDetailRejected> rejectedList) {
        this.rejectedList = rejectedList;
    }
}
