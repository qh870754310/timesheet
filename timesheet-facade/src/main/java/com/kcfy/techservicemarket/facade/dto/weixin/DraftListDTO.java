package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by zhouxc on 2016/6/28.
 */
public class DraftListDTO {
    private List<DraftDTO> timesheetDraftList;

    public List<DraftDTO> getTimesheetDraftList() {
        return timesheetDraftList;
    }

    public void setTimesheetDraftList(List<DraftDTO> timesheetDraftList) {
        this.timesheetDraftList = timesheetDraftList;
    }
}
