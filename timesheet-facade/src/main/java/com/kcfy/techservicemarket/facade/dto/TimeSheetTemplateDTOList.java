package com.kcfy.techservicemarket.facade.dto;

import java.util.List;

/**
 * Created by daidong on 16/6/28.
 */
public class TimeSheetTemplateDTOList {
    private List<TimeSheetTemplateDTO> templateList;
    public List<TimeSheetTemplateDTO> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TimeSheetTemplateDTO> templateList) {
        this.templateList = templateList;
    }
}
