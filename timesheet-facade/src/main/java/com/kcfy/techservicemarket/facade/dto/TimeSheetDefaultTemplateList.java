package com.kcfy.techservicemarket.facade.dto;

import java.util.List;

/**
 * Created by daidong on 16/6/27.
 */
public class TimeSheetDefaultTemplateList extends TimeSheetDefaultTemplateListBase{

    private List<TimeSheetDefaultTemplateDTO> templateDetailList;
    public List<TimeSheetDefaultTemplateDTO> getTemplateDetailList() {
        return templateDetailList;
    }
    public void setTemplateDetailList(List<TimeSheetDefaultTemplateDTO> templateDetailList) {
        this.templateDetailList = templateDetailList;
    }
}
