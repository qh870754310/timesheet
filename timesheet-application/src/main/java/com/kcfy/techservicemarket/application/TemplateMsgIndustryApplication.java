package com.kcfy.techservicemarket.application;


import com.kcfy.techservicemarket.core.domain.message.TemplateMsgIndustry;

import java.util.List;
import java.util.Set;

public interface TemplateMsgIndustryApplication {

    public TemplateMsgIndustry getTemplateMsgIndustry(Long id);

    public void creatTemplateMsgIndustry(TemplateMsgIndustry templateMsgIndustry);

    public void updateTemplateMsgIndustry(TemplateMsgIndustry templateMsgIndustry);

    public void removeTemplateMsgIndustry(TemplateMsgIndustry templateMsgIndustry);

    public void removeTemplateMsgIndustrys(Set<TemplateMsgIndustry> templateMsgIndustrys);

    public List<TemplateMsgIndustry> findAllTemplateMsgIndustry();

}

