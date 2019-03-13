package com.kcfy.techservicemarket.facade.dto;

import com.kcfy.techservicemarket.facade.dto.weixin.TimeSheetDetailBaseDTO;

/**
 * Created by daidong on 16/6/29.
 */
public class TimeSheetDetailTemplateDTO extends TimeSheetDetailBaseDTO{
        private String templateDetailId;
        public String getTemplateDetailId() {
            return templateDetailId;
        }

        public void setTemplateDetailId(String templateDetailId) {
            this.templateDetailId = templateDetailId;
        }
    }

