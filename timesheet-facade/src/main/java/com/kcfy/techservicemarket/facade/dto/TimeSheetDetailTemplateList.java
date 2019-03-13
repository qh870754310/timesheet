package com.kcfy.techservicemarket.facade.dto;

import java.util.List;

/**
 * Created by daidong on 16/6/29.
 */
public class TimeSheetDetailTemplateList extends TimeSheetDefaultTemplateListBase{
    private List<TimeSheetDetailTemplateDTO> templateDetailList;

    public List<TimeSheetDetailTemplateDTO> getTemplateDetailList() {
        return templateDetailList;
    }

    public void setTemplateDetailList(List<TimeSheetDetailTemplateDTO> templateDetailList) {
        this.templateDetailList = templateDetailList;
    }
}
