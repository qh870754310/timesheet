package com.kcfy.techservicemarket.facade.dto;

import java.util.List;

/**
 * Created by zhouxc on 2016/6/22.
 */
public class DayFillStatusList {
    private List<DayFillStatus> missingDateList;

    public List<DayFillStatus> getMissingDateList() {
        return missingDateList;
    }

    public void setMissingDateList(List<DayFillStatus> missingDateList) {
        this.missingDateList = missingDateList;
    }
}
