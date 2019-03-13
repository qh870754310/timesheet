package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.domain.timesetting.TimeSetting;
import com.kcfy.techservicemarket.facade.dto.TimeSettingDTO;
import com.kcfy.techservicemarket.infra.util.DateTool;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TimeSettingAssembler {

    public static TimeSettingDTO toDTO(TimeSetting timeSetting) {
        if (timeSetting == null) {
            return null;
        }
        TimeSettingDTO result = new TimeSettingDTO();
        result.setId(timeSetting.getId());
        result.setVersion(timeSetting.getVersion());
        result.setDayType(timeSetting.getDayType());
        result.setStartTime(DateTool.convertDateToString("yyyy-MM-dd HH:mm:ss", timeSetting.getStartTime()));
        result.setEndTime(DateTool.convertDateToString("yyyy-MM-dd HH:mm:ss", timeSetting.getEndTime()));
        result.setContent(timeSetting.getContent());
        result.setCreateTime(DateTool.convertDateToString("yyyy-MM-dd HH:mm:ss", timeSetting.getCreateDate()));
        result.setCreatorId(timeSetting.getCreatorId());
        result.setDate(timeSetting.getDate());
        result.setIsAvailable(timeSetting.getIsAvailable());
        return result;
    }

    public static List<TimeSettingDTO> toDTOs(Collection<TimeSetting> timeSettings) {
        if (timeSettings == null) {
            return null;
        }
        List<TimeSettingDTO> results = new ArrayList<TimeSettingDTO>();
        for (TimeSetting each : timeSettings) {
            results.add(toDTO(each));
        }
        return results;
    }

    public static TimeSetting toEntity(TimeSettingDTO timeSettingDTO) throws ParseException {
        if (timeSettingDTO == null) {
            return null;
        }
        TimeSetting result = new TimeSetting();
        result.setId(timeSettingDTO.getId());
        result.setVersion(timeSettingDTO.getVersion() == null ? 0 : timeSettingDTO.getVersion());
        result.setDayType(timeSettingDTO.getDayType() == null ? 0 : timeSettingDTO.getDayType());
        result.setStartTime(DateTool.convertStringToDate("yyyy-MM-dd HH:mm:ss", timeSettingDTO.getStartTime()));
        result.setEndTime(DateTool.convertStringToDate("yyyy-MM-dd HH:mm:ss", timeSettingDTO.getEndTime()));
        result.setContent(timeSettingDTO.getContent());
        result.setCreateDate(DateTool.convertStringToDate("yyyy-MM-dd HH:mm:ss", timeSettingDTO.getCreateTime()));
        result.setCreatorId(timeSettingDTO.getCreatorId());
        result.setDate(timeSettingDTO.getDate());
        result.setIsAvailable(timeSettingDTO.getIsAvailable());
        return result;
    }

    public static List<TimeSetting> toEntities(Collection<TimeSettingDTO> TimeSettingDTOs) throws ParseException {
        if (TimeSettingDTOs == null) {
            return null;
        }

        List<TimeSetting> results = new ArrayList<TimeSetting>();
        for (TimeSettingDTO each : TimeSettingDTOs) {
            results.add(toEntity(each));
        }
        return results;
    }
}
