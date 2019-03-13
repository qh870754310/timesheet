package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.TimesheetApplication;
import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import com.kcfy.techservicemarket.core.domain.timesheet.Timesheet;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.facade.TimeSheetFacade;
import com.kcfy.techservicemarket.facade.dto.*;
import com.kcfy.techservicemarket.facade.dto.weixin.DraftDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.DraftListDTO;
import com.kcfy.techservicemarket.facade.dto.weixin.TimesheetDetailRejected;
import com.kcfy.techservicemarket.facade.dto.weixin.TimesheetDetailRejectedList;
import com.kcfy.techservicemarket.facade.impl.assembler.TimesheetAssembler;
import com.kcfy.techservicemarket.infra.util.DateTool;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.core.domain.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
public class TimesheetFacadeImpl implements TimeSheetFacade {

    @Inject
    private TimesheetApplication application;

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    public InvokeResult getTimesheet(String id) {
        return InvokeResult.success(TimesheetAssembler.toDTO(application.getTimesheet(id)));
    }

    public InvokeResult createTimesheet(TimesheetSaveWrapDTO timesheetDTO) {
        TimesheetSaveDTO timesheetSaveDTO = timesheetDTO.getTimesheet();
        Timesheet timesheet = new Timesheet();
        timesheet.setDate(DateTool.convertStringToDate("yyyy-MM-dd", timesheetSaveDTO.getDate()));
        timesheet.setStatus(timesheetSaveDTO.getStatus());
        User user = new User();
        user.setId(timesheetSaveDTO.getUserId());
        timesheet.setUser(user);
        timesheet.setTimesheetDetailList(getDetailListFromVO(timesheetSaveDTO, timesheet));
        application.createTimesheet(timesheet);
        IdReturn idReturn = new IdReturn();
        idReturn.setId(String.valueOf(timesheet.getId()));
        return InvokeResult.success(idReturn);
    }

    public InvokeResult updateTimesheet(TimesheetSaveWrapDTO timesheetDTO) {
        TimesheetSaveDTO timesheetSaveDTO = timesheetDTO.getTimesheet();
        Timesheet timesheet = Timesheet.get(Timesheet.class, timesheetSaveDTO.getTimesheetId());
        List<TimesheetDetail> detailOldForDelete = timesheet.getTimesheetDetailList();
        timesheet.setStatus(timesheetSaveDTO.getStatus());
        timesheet.setTimesheetDetailList(getDetailListFromVO(timesheetSaveDTO, timesheet));
        application.deleteDetails(detailOldForDelete);
        application.updateTimesheet(timesheet);
        IdReturn idReturn = new IdReturn();
        idReturn.setId(String.valueOf(timesheet.getId()));
        return InvokeResult.success(idReturn);
    }

    private List<TimesheetDetail> getDetailListFromVO(TimesheetSaveDTO timesheetSaveDTO, Timesheet timesheet) {
        List<TimesheetDetail> detailList = new ArrayList<>();
        for(TimesheetDetailSaveDTO timesheetDetailSaveDTO : timesheetSaveDTO.getTimesheetDetailList()) {
            TimesheetDetail timesheetDetail = new TimesheetDetail();
            timesheetDetail.setTaskDesc(timesheetDetailSaveDTO.getTaskDesc());
            timesheetDetail.setHour(Long.valueOf(timesheetDetailSaveDTO.getHour()));
            timesheetDetail.setStatus(timesheetSaveDTO.getStatus().toString());
            Project project = new Project();
            project.setId(timesheetDetailSaveDTO.getProjectId());
            timesheetDetail.setProject(project);
            ProjectStage projectStage = new ProjectStage();
            projectStage.setId(timesheetDetailSaveDTO.getStageId());
            timesheetDetail.setProjectStage(projectStage);
            timesheetDetail.setTimesheet(timesheet);
            detailList.add(timesheetDetail);
        }
        return detailList;
    }

    public InvokeResult removeTimesheet(String id) {
        application.removeTimesheet(application.getTimesheet(id));
        return InvokeResult.success();
    }

    @Override
    public List<TimesheetDTO> getByUserId(String userId) {
        return TimesheetAssembler.toDTOs(application.getByUserId(userId));
    }
    @Override
    public InvokeResult getByUserIdAndDate(Long userId, Date date) {
        return InvokeResult.success(TimesheetAssembler.toDTO(application.getByUserIdAndDate(userId, date)));
    }
    @Override
    public InvokeResult getMissingDateList(Long userId) {
        List<DayFillStatus> missingDateList = application.getMissingDateList(userId);
        DayFillStatusList dayFillStatusList = new DayFillStatusList();
        dayFillStatusList.setMissingDateList(missingDateList);
        return InvokeResult.success(dayFillStatusList);
    }
    @Override
    public InvokeResult getRejectedDetailList(Long userId) {
        List<TimesheetDetail> list = application.getRejectedDetailList(userId);
        List<TimesheetDetailRejected> rejectedList = new ArrayList<>();
        for(TimesheetDetail timesheetDetail : list) {
            TimesheetDetailRejected timesheetDetailRejected = new TimesheetDetailRejected();
            timesheetDetailRejected.setTimesheetId(String.valueOf(timesheetDetail.getTimesheet().getId()));
            timesheetDetailRejected.setDate(DateTool.convertDateToString("yyyy-MM-dd", timesheetDetail.getTimesheet().getDate()));
            timesheetDetailRejected.setProjectName(timesheetDetail.getProject().getName());
            timesheetDetailRejected.setStageName(timesheetDetail.getProjectStage().getName());
            timesheetDetailRejected.setHour(String.valueOf(timesheetDetail.getHour()));
            timesheetDetailRejected.setTaskDesc(timesheetDetail.getTaskDesc());
            timesheetDetailRejected.setRejectedReason(timesheetDetail.getRejectedReason());
            rejectedList.add(timesheetDetailRejected);
        }
        TimesheetDetailRejectedList timesheetDetailRejectedList = new TimesheetDetailRejectedList();
        timesheetDetailRejectedList.setRejectedList(rejectedList);
        return InvokeResult.success(timesheetDetailRejectedList);
    }
    @Override
    public InvokeResult getDraftList(Long userId) {
        List<Timesheet> list = application.getDraftList(userId);
        List<DraftDTO> draftList = new ArrayList<>();
        for(Timesheet timesheet: list) {
            DraftDTO draftDTO = new DraftDTO();
            draftDTO.setTimesheetId(String.valueOf(timesheet.getId()));
            draftDTO.setDate(DateTool.convertDateToString("yyyy-MM-dd",timesheet.getDate()));
            if(timesheet.getTimesheetDetailList().size() > 0) {
                TimesheetDetail timesheetDetail = timesheet.getTimesheetDetailList().get(0);
                draftDTO.setFirstProjectName(timesheetDetail.getProject().getName());
                draftDTO.setStageName(timesheetDetail.getProjectStage().getName());
                draftDTO.setHour(String.valueOf(timesheetDetail.getHour()));
                draftDTO.setTaskDesc(timesheetDetail.getTaskDesc());
            }
            draftList.add(draftDTO);
        }
        DraftListDTO draftListDTO = new DraftListDTO();
        draftListDTO.setTimesheetDraftList(draftList);
        return InvokeResult.success(draftListDTO);
    }
    @Override
    public InvokeResult getTeamMissingDateListByPMUserId(Long userId) {
        List<DayFillMissingDTO> teamMissingDateList = application.getTeamMissingDateListByPMUserId(userId);
        DayFillMissingDTOList list = new DayFillMissingDTOList();
        list.setMissingList(teamMissingDateList);
        return InvokeResult.success(list);
    }

    @Override
    public InvokeResult sendRemindMsgToTeam(Long userId, Integer remindType) {
        String result = application.sendRemindMsgToTeam(userId, remindType);
        if(result.contains("200")) {
            return InvokeResult.success();
        } else {
            return InvokeResult.failure(result);
        }
    }
}
