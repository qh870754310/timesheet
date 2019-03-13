package com.kcfy.techservicemarket.application.impl;


import com.kcfy.techservicemarket.application.TemplateMsgIndustryApplication;
import com.kcfy.techservicemarket.core.domain.message.TemplateMsgIndustry;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;
import java.util.Set;

@Named
@Transactional
public class TemplateMsgIndustryApplicationImpl implements TemplateMsgIndustryApplication {

    public TemplateMsgIndustry getTemplateMsgIndustry(Long id) {
        return TemplateMsgIndustry.get(TemplateMsgIndustry.class, id);
    }

    public void creatTemplateMsgIndustry(TemplateMsgIndustry templateMsgIndustry) {
        templateMsgIndustry.save();
    }

    public void updateTemplateMsgIndustry(TemplateMsgIndustry templateMsgIndustry) {
        templateMsgIndustry.save();
    }

    public void removeTemplateMsgIndustry(TemplateMsgIndustry templateMsgIndustry) {
        if (templateMsgIndustry != null) {
            templateMsgIndustry.remove();
        }
    }

    public void removeTemplateMsgIndustrys(Set<TemplateMsgIndustry> templateMsgIndustrys) {
        for (TemplateMsgIndustry templateMsgIndustry : templateMsgIndustrys) {
            templateMsgIndustry.remove();
        }
    }

    public List<TemplateMsgIndustry> findAllTemplateMsgIndustry() {
        return TemplateMsgIndustry.findAll(TemplateMsgIndustry.class);
    }

}
