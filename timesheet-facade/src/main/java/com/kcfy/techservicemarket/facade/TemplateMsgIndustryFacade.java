package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.TemplateMsgIndustryDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.util.List;

public interface TemplateMsgIndustryFacade {

    public InvokeResult getTemplateMsgIndustry(Long id);

    public InvokeResult creatTemplateMsgIndustry(TemplateMsgIndustryDTO templateMsgIndustry);

    public InvokeResult updateTemplateMsgIndustry(TemplateMsgIndustryDTO templateMsgIndustry);

    public InvokeResult removeTemplateMsgIndustry(Long id);

    public InvokeResult removeTemplateMsgIndustrys(Long[] ids);

    public List<TemplateMsgIndustryDTO> findAllTemplateMsgIndustry();

    public Page<TemplateMsgIndustryDTO> pageQueryTemplateMsgIndustry(TemplateMsgIndustryDTO templateMsgIndustry, int currentPage, int pageSize);


}

