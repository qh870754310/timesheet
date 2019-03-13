package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.domain.message.TemplateMsgIndustry;
import com.kcfy.techservicemarket.facade.dto.TemplateMsgIndustryDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TemplateMsgIndustryAssembler {

    public static TemplateMsgIndustryDTO toDTO(TemplateMsgIndustry templateMsgIndustry) {
        if (templateMsgIndustry == null) {
            return null;
        }
        TemplateMsgIndustryDTO result = new TemplateMsgIndustryDTO();
        result.setId(templateMsgIndustry.getId());
        result.setNameOneFirst(templateMsgIndustry.getNameOneFirst());
        result.setNameOneSecond(templateMsgIndustry.getNameOneSecond());
        result.setCodeOne(templateMsgIndustry.getCodeOne());
        result.setNameTwoFirst(templateMsgIndustry.getNameTwoFirst());
        result.setNameTwoSecond(templateMsgIndustry.getNameTwoSecond());
        result.setCodeTwo(templateMsgIndustry.getCodeTwo());
        result.setVersion(templateMsgIndustry.getVersion());
        return result;
    }

    public static List<TemplateMsgIndustryDTO> toDTOs(Collection<TemplateMsgIndustry> templateMsgIndustrys) {
        if (templateMsgIndustrys == null) {
            return null;
        }
        List<TemplateMsgIndustryDTO> results = new ArrayList<TemplateMsgIndustryDTO>();
        for (TemplateMsgIndustry each : templateMsgIndustrys) {
            results.add(toDTO(each));
        }
        return results;
    }

    public static TemplateMsgIndustry toEntity(TemplateMsgIndustryDTO templateMsgIndustryDTO) {
        if (templateMsgIndustryDTO == null) {
            return null;
        }
        TemplateMsgIndustry result = new TemplateMsgIndustry();
        result.setId(templateMsgIndustryDTO.getId());
        result.setNameOneFirst(templateMsgIndustryDTO.getNameOneFirst());
        result.setNameOneSecond(templateMsgIndustryDTO.getNameOneSecond());
        result.setCodeOne(templateMsgIndustryDTO.getCodeOne());
        result.setNameTwoFirst(templateMsgIndustryDTO.getNameTwoFirst());
        result.setNameTwoSecond(templateMsgIndustryDTO.getNameTwoSecond());
        result.setCodeTwo(templateMsgIndustryDTO.getCodeTwo());
        result.setVersion(templateMsgIndustryDTO.getVersion());
        return result;
    }

    public static List<TemplateMsgIndustry> toEntities(Collection<TemplateMsgIndustryDTO> templateMsgIndustryDTOs) {
        if (templateMsgIndustryDTOs == null) {
            return null;
        }

        List<TemplateMsgIndustry> results = new ArrayList<TemplateMsgIndustry>();
        for (TemplateMsgIndustryDTO each : templateMsgIndustryDTOs) {
            results.add(toEntity(each));
        }
        return results;
    }
}
