package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.TimeSheetTemplateApplication;
import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.project.ProjectStage;
import com.kcfy.techservicemarket.core.domain.timesheet.TimeSheetTemplate;
import com.kcfy.techservicemarket.core.domain.timesheet.TimeSheetTemplateDetail;
import com.kcfy.techservicemarket.facade.TimeSheetTemplateFacade;
import com.kcfy.techservicemarket.facade.dto.*;
import org.apache.commons.lang.StringUtils;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.core.domain.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daidong on 16/6/27.
 */
@Named
public class TimeSheetTemplateFacadeImpl implements TimeSheetTemplateFacade {

    @Inject
    TimeSheetTemplateApplication application;

    @Override
    public InvokeResult getDefaultTemplate(Long userId) {
        TimeSheetTemplate timeSheetTemplate = application.getTimeSheetDefaultTemplate(userId);
        TimeSheetDefaultTemplateList timeSheetDefaultTemplateList = new TimeSheetDefaultTemplateList();
        List<TimeSheetDefaultTemplateDTO> resultList = new ArrayList<>();
        if(timeSheetTemplate != null) {
            List<TimeSheetTemplateDetail> timeSheetTemplateDetailList = timeSheetTemplate.getTimeSheetTemplateDetailList();
            for (TimeSheetTemplateDetail details : timeSheetTemplateDetailList) {
                TimeSheetDefaultTemplateDTO timeSheetDefaultTemplateDTO = new TimeSheetDefaultTemplateDTO();
                timeSheetDefaultTemplateDTO.setTemplateDetailId(details.getId().toString());
                timeSheetDefaultTemplateDTO.setProjectId(details.getProject().getId().toString());
                timeSheetDefaultTemplateDTO.setHour(String.valueOf(details.getHour()));
                timeSheetDefaultTemplateDTO.setProjectName(details.getProject().getName());
                timeSheetDefaultTemplateDTO.setProjectStageId(details.getProjectStage().getId().toString());
                timeSheetDefaultTemplateDTO.setStageName(details.getProjectStage().getName());
                timeSheetDefaultTemplateDTO.setTaskDesc(details.getTaskDesc());
                resultList.add(timeSheetDefaultTemplateDTO);
            }

        timeSheetDefaultTemplateList.setId(timeSheetTemplate.getId().toString());
        timeSheetDefaultTemplateList.setName(timeSheetTemplate.getName());
        timeSheetDefaultTemplateList.setTemplateDetailList(resultList);
        }else{
            return InvokeResult.failure("用户信息有误");
        }
        return InvokeResult.success(timeSheetDefaultTemplateList);
    }

    @Override
    public InvokeResult getTemplateList(Long userId) {
        List<TimeSheetTemplate> timeSheetTemplate = application.getTimeSheetTemplateList(userId);
        TimeSheetTemplateDTOList timeSheetTemplateDTOList = new TimeSheetTemplateDTOList();
        List<TimeSheetTemplateDTO> resultList = new ArrayList<>();
        if (timeSheetTemplate != null) {
            for (TimeSheetTemplate details : timeSheetTemplate) {
                TimeSheetTemplateDTO timeSheetTemplateDTO = new TimeSheetTemplateDTO();
                timeSheetTemplateDTO.setId(details.getId().toString());
                timeSheetTemplateDTO.setName(details.getName().toString());
                if (details.getStatus() != null) {
                    timeSheetTemplateDTO.setStatus(details.getStatus().toString());
                }
                timeSheetTemplateDTO.setCreateTime(details.getCreateDate().toString());
                resultList.add(timeSheetTemplateDTO);
            }
        }else{
            return InvokeResult.failure("用户信息有误");
        }
        return InvokeResult.success(timeSheetTemplateDTOList);
    }

    @Override
    public InvokeResult getTemplateById(Long id) {
        TimeSheetTemplate timeSheetDefaultTemplateDetails = application.getTimeSheetDefaultTemplateDetails(id);
        TimeSheetDetailTemplateList timeSheetDetailTemplateList = new TimeSheetDetailTemplateList();
        List<TimeSheetDetailTemplateDTO> list = new ArrayList<>();
        if(timeSheetDefaultTemplateDetails != null) {
           for (TimeSheetTemplateDetail details : timeSheetDefaultTemplateDetails.getTimeSheetTemplateDetailList()) {
                TimeSheetDetailTemplateDTO timeSheetDetailTemplateDTO = new TimeSheetDetailTemplateDTO();
                timeSheetDetailTemplateDTO.setTemplateDetailId(details.getId().toString());
                timeSheetDetailTemplateDTO.setProjectName(details.getProject().getName());
                timeSheetDetailTemplateDTO.setStageName(details.getProjectStage().getName());
                timeSheetDetailTemplateDTO.setHour(String.valueOf(details.getHour()));
                timeSheetDetailTemplateDTO.setTaskDesc(details.getTaskDesc());
                list.add(timeSheetDetailTemplateDTO);
            }

        timeSheetDetailTemplateList.setId(timeSheetDefaultTemplateDetails.getId().toString());
        timeSheetDetailTemplateList.setName(timeSheetDefaultTemplateDetails.getName().toString());
        timeSheetDetailTemplateList.setTemplateDetailList(list);
        }else {
            return InvokeResult.failure("此模板ID有误");
        }
        return InvokeResult.success(timeSheetDetailTemplateList);
    }

    @Override
    public InvokeResult getTemplateDetailById(Long id) {
        TimeSheetTemplateDetail timeSheetTemplateDetail = application.getTemplateDetailById(id);
        TimeSheetDefaultTemplateDTO timeSheetDefaultTemplateDTO = new TimeSheetDefaultTemplateDTO();
        if(timeSheetTemplateDetail != null) {
            timeSheetDefaultTemplateDTO.setTemplateDetailId(timeSheetTemplateDetail.getId().toString());
            timeSheetDefaultTemplateDTO.setProjectId(timeSheetTemplateDetail.getProject().getId().toString());
            timeSheetDefaultTemplateDTO.setHour(String.valueOf(timeSheetTemplateDetail.getHour()));
            timeSheetDefaultTemplateDTO.setProjectName(timeSheetTemplateDetail.getProject().getName());
            timeSheetDefaultTemplateDTO.setProjectStageId(timeSheetTemplateDetail.getProjectStage().getId().toString());
            timeSheetDefaultTemplateDTO.setStageName(timeSheetTemplateDetail.getProjectStage().getName());
            timeSheetDefaultTemplateDTO.setTaskDesc(timeSheetTemplateDetail.getTaskDesc());
        }else{
            return InvokeResult.failure("任务ID有误");
        }
        return InvokeResult.success(timeSheetDefaultTemplateDTO);
    }

    @Override
    public InvokeResult setDefault(Long id, Long userId) {
        int result = application.setDefault(id,userId);
       if(result == 1) {
           return InvokeResult.failure("用户ID或者模板ID有误.");
       }

        IdReturn idReturn = new IdReturn();
        idReturn.setId(id.toString());
        return InvokeResult.success(idReturn);
    }

    @Override
    public InvokeResult deleteTemplateById(Long id) {
        int result = application.deleteTemplateById(id);
        if(result == 1) {
            return InvokeResult.failure("用户ID有误.");
        }
        return InvokeResult.success();
    }

    @Override
    public InvokeResult update(TimesheetTemplateSaveWrapDTO timesheetTemplateSaveWrapDTO) {
        TimesheetTemplateSaveDTO timesheetTemplateSaveDTO = timesheetTemplateSaveWrapDTO.getTemplate();
        TimeSheetTemplate timeSheetTemplate = TimeSheetTemplate.get(TimeSheetTemplate.class, timesheetTemplateSaveDTO.getTemplateId());
        List<TimeSheetTemplateDetail> detailsOldForDelete = timeSheetTemplate.getTimeSheetTemplateDetailList();
        timeSheetTemplate.setName(timesheetTemplateSaveDTO.getTemplateName());
        if(StringUtils.isNotEmpty(timesheetTemplateSaveDTO.getStatus())) {
            timeSheetTemplate.setStatus(Integer.valueOf(timesheetTemplateSaveDTO.getStatus()));
        }
        timeSheetTemplate.setTimeSheetTemplateDetailList(getDetailListFromVO(timesheetTemplateSaveDTO, timeSheetTemplate));
        application.deleteDetails(detailsOldForDelete);
        application.update(timeSheetTemplate);
        IdReturn idReturn = new IdReturn();
        idReturn.setId(String.valueOf(timeSheetTemplate.getId()));
        return InvokeResult.success(idReturn);
    }

    @Override
    public InvokeResult create(TimesheetTemplateSaveWrapDTO timesheetTemplateSaveWrapDTO) {
        TimesheetTemplateSaveDTO timesheetTemplateSaveDTO = timesheetTemplateSaveWrapDTO.getTemplate();
        TimeSheetTemplate timeSheetTemplate = new TimeSheetTemplate();
        timeSheetTemplate.setName(timesheetTemplateSaveDTO.getTemplateName());
        if(StringUtils.isNotEmpty(timesheetTemplateSaveDTO.getStatus())) {
            timeSheetTemplate.setStatus(Integer.valueOf(timesheetTemplateSaveDTO.getStatus()));
        }
        User user = new User();
        user.setId(timesheetTemplateSaveDTO.getUserId());
        timeSheetTemplate.setUser(user);
        timeSheetTemplate.setTimeSheetTemplateDetailList(getDetailListFromVO(timesheetTemplateSaveDTO, timeSheetTemplate));
        application.save(timeSheetTemplate);
        IdReturn idReturn = new IdReturn();
        idReturn.setId(String.valueOf(timeSheetTemplate.getId()));
        return InvokeResult.success(idReturn);
    }

    private List<TimeSheetTemplateDetail> getDetailListFromVO(TimesheetTemplateSaveDTO timesheetTemplateSaveDTO, TimeSheetTemplate timesheetTemplate) {
        List<TimeSheetTemplateDetail> detailList = new ArrayList<>();
        for(TimesheetTemplateDetailSaveDTO timesheetTemplateDetailSaveDTO : timesheetTemplateSaveDTO.getTemplateDetailList()) {
            TimeSheetTemplateDetail timeSheetTemplateDetail = new TimeSheetTemplateDetail();
            timeSheetTemplateDetail.setTaskDesc(timesheetTemplateDetailSaveDTO.getTaskDesc());
            timeSheetTemplateDetail.setHour(Long.valueOf(timesheetTemplateDetailSaveDTO.getHour()));
            Project project = new Project();
            project.setId(timesheetTemplateDetailSaveDTO.getProjectId());
            timeSheetTemplateDetail.setProject(project);
            ProjectStage projectStage = new ProjectStage();
            projectStage.setId(timesheetTemplateDetailSaveDTO.getStageId());
            timeSheetTemplateDetail.setProjectStage(projectStage);
            timeSheetTemplateDetail.setTimeSheetTemplate(timesheetTemplate);
            detailList.add(timeSheetTemplateDetail);
        }
        return detailList;
    }

}

