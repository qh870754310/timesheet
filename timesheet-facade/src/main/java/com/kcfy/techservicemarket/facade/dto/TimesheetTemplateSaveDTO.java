package com.kcfy.techservicemarket.facade.dto;

import java.util.List;

/**
 * Created by zhouxc on 2016/7/1.
 */
public class TimesheetTemplateSaveDTO {
    private Long templateId;
    private String templateName;
    private Long userId;
    private String status;
    private List<TimesheetTemplateDetailSaveDTO> templateDetailList;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TimesheetTemplateDetailSaveDTO> getTemplateDetailList() {
        return templateDetailList;
    }

    public void setTemplateDetailList(List<TimesheetTemplateDetailSaveDTO> templateDetailList) {
        this.templateDetailList = templateDetailList;
    }
}
