package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.TimeSettingApplication;
import com.kcfy.techservicemarket.core.domain.timesetting.TimeSetting;
import com.kcfy.techservicemarket.facade.TimeSettingFacade;
import com.kcfy.techservicemarket.facade.dto.TimeSettingDTO;
import com.kcfy.techservicemarket.facade.impl.assembler.TimeSettingAssembler;
import com.kcfy.techservicemarket.infra.util.DateTool;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.common.core.constants.Availability;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.util.*;

@Named
public class TimeSettingFacadeImpl implements TimeSettingFacade {

    @Inject
    private TimeSettingApplication application;

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    public InvokeResult getTimeSetting(String id) {
        return InvokeResult.success(TimeSettingAssembler.toDTO(application.getTimeSetting(id)));
    }

    public InvokeResult getTimeSettingByDate(String date) {
        TimeSetting timeSetting = application.getTimeSettingByDate(date);
        if (null != timeSetting) {
            return InvokeResult.success(TimeSettingAssembler.toDTO(timeSetting));
        } else {
            return InvokeResult.failure("no timeSetting");
        }
    }

    public InvokeResult createTimeSetting(TimeSettingDTO timeSettingDTO) {
        String dateStart = timeSettingDTO.getStartTime().substring(0, 10);
        timeSettingDTO.setDate(dateStart);
        timeSettingDTO.setIsAvailable(Availability.available);
        //TODO batch
        try {
            application.createTimeSetting(TimeSettingAssembler.toEntity(timeSettingDTO));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return InvokeResult.success();
    }

    public InvokeResult updateTimeSetting(TimeSettingDTO timeSettingDTO) {
        String dateStart = timeSettingDTO.getStartTime().substring(0, 10);
        timeSettingDTO.setDate(dateStart);
        timeSettingDTO.setIsAvailable(Availability.available);
        //TODO batch
        try {
            application.updateTimeSetting(TimeSettingAssembler.toEntity(timeSettingDTO));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return InvokeResult.success();
    }

    public InvokeResult removeTimeSetting(String id) {
        application.removeTimeSetting(application.getTimeSetting(id));
        return InvokeResult.success();
    }

    public InvokeResult removeTimeSettings(String[] ids) {
        Set<TimeSetting> timeSettings = new HashSet<TimeSetting>();
        for (String id : ids) {
            timeSettings.add(application.getTimeSetting(id));
        }
        application.removeTimeSettings(timeSettings);
        return InvokeResult.success();
    }

    public List<TimeSettingDTO> findAllTimeSetting() {
        return TimeSettingAssembler.toDTOs(application.findAllTimeSetting());
    }

    @SuppressWarnings("unchecked")
    public Page<TimeSettingDTO> pageQueryTimeSetting(TimeSettingDTO queryVo, int currentPage, int pageSize) {
        /*List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _timeSetting from TimeSetting _timeSetting   where 1=1 ");
        if (queryVo.getTitle() != null && !"".equals(queryVo.getTitle())) {
            jpql.append(" and _timeSetting.title like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getTitle()));
        }
        if (queryVo.getContent() != null && !"".equals(queryVo.getContent())) {
            jpql.append(" and _timeSetting.content like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getContent()));
        }
        if (queryVo.getCreateTime() != null) {
            jpql.append(" and _timeSetting.createTime between ? and ? ");
            conditionVals.add(queryVo.getCreateTime());
            conditionVals.add(queryVo.getCreateTimeEnd());
        }
        if (queryVo.getCreatorId() != null && !"".equals(queryVo.getCreatorId())) {
            jpql.append(" and _timeSetting.creator_id like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreatorId()));
        }
        if (queryVo.getFlag() != null) {
            jpql.append(" and _timeSetting.flag=?");
            conditionVals.add(queryVo.getFlag());
        }
        Page<TimeSetting> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString())
                .setParameters(conditionVals)
                .setPage(currentPage, pageSize)
                .pagedList();

        return new Page<TimeSettingDTO>(pages.getStart(), pages.getResultCount(), pageSize, TimeSettingAssembler.toDTOs(pages.getData()));*/
        return new Page<TimeSettingDTO>(0, 0, 0, new ArrayList<TimeSettingDTO>());
    }

    @Override
    public List<TimeSettingDTO> getCurrentPageData(String date) {
        return TimeSettingAssembler.toDTOs(application.getCurrentPageData(date));
    }

    @Override
    public List<TimeSettingDTO> getListByDateRange(String dateStart, String dateEnd) {
        return TimeSettingAssembler.toDTOs(application.getListByDateRange(dateStart,dateEnd));
    }



    public InvokeResult getTimeSetting(Long arg0) {
        // TODO Auto-generated method stub
        return null;
    }


}
