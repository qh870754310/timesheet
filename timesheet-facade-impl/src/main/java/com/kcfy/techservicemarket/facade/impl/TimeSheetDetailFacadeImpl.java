package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.TimeSheetDetailApplication;
import com.kcfy.techservicemarket.application.TimesheetApplication;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.facade.TimeSheetDetailFacade;
import com.kcfy.techservicemarket.facade.dto.TimesheetDetailDTO;
import com.kcfy.techservicemarket.facade.dto.TimesheetDetailGetDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.TimesheetDetailRejectedForPM;
import com.kcfy.techservicemarket.facade.dto.weixin.TimesheetDetailRejectedListForPM;
import com.kcfy.techservicemarket.infra.util.DateTool;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiaohong on 2016/6/30.
 */

@Named
public class TimeSheetDetailFacadeImpl implements TimeSheetDetailFacade{
    @Inject
    private TimesheetApplication timesheetApplication;
    @Inject
    private TimeSheetDetailApplication timeSheetDetailApplication;

    @Override
    public InvokeResult getTimeSheetDetail(String id) {
        TimesheetDetail timeSheetDetail = timeSheetDetailApplication.getTimeSheetDetail(id);
        TimesheetDetailGetDTO timesheetDetailGetDTO = new TimesheetDetailGetDTO();
        if(timeSheetDetail != null) {
            timesheetDetailGetDTO.setTimesheetDetailId(String.valueOf(timeSheetDetail.getId()));
            timesheetDetailGetDTO.setProjectId(String.valueOf(timeSheetDetail.getProject().getId()));
            timesheetDetailGetDTO.setProjectName(timeSheetDetail.getProject().getName());
            timesheetDetailGetDTO.setProjectStageId(String.valueOf(timeSheetDetail.getProjectStage().getId()));
            timesheetDetailGetDTO.setStageName(timeSheetDetail.getProjectStage().getName());
            timesheetDetailGetDTO.setHour(String.valueOf(timeSheetDetail.getHour()));
            timesheetDetailGetDTO.setTaskDesc(timeSheetDetail.getTaskDesc());
        }
        return InvokeResult.success(timesheetDetailGetDTO);
    }

    @Override
    public InvokeResult getTeamRejectedDetailListByPMUserId(Long userId) {
        List<TimesheetDetail> timesheetDetailList = timesheetApplication.getTeamRejectedDetaiListByPMUserId(userId);
        List<TimesheetDetailRejectedForPM> list = new ArrayList<>();
        for(TimesheetDetail detail : timesheetDetailList) {
            TimesheetDetailRejectedForPM detailRejectedForPM = new TimesheetDetailRejectedForPM();
            detailRejectedForPM.setUserId(detail.getTimesheet().getUser().getId());
            detailRejectedForPM.setUserName(detail.getTimesheet().getUser().getName());
            detailRejectedForPM.setProjectName(detail.getProject().getName());
            detailRejectedForPM.setDate(DateTool.convertDateToString("yyyy-MM-dd",detail.getTimesheet().getDate()));
            detailRejectedForPM.setHour(detail.getHour());
            detailRejectedForPM.setTaskDesc(detail.getTaskDesc());
            detailRejectedForPM.setRejectedReason(detail.getRejectedReason());
            list.add(detailRejectedForPM);
        }
        TimesheetDetailRejectedListForPM timesheetDetailRejectedList = new TimesheetDetailRejectedListForPM();
        timesheetDetailRejectedList.setRejectedList(list);
        return InvokeResult.success(timesheetDetailRejectedList);
    }
}
