package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.ProjectApplication;
import com.kcfy.techservicemarket.application.TimeSettingApplication;
import com.kcfy.techservicemarket.application.TimesheetApplication;
import com.kcfy.techservicemarket.core.Constants;
import com.kcfy.techservicemarket.core.domain.project.Project;
import com.kcfy.techservicemarket.core.domain.timesetting.TimeSetting;
import com.kcfy.techservicemarket.core.domain.timesheet.Timesheet;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.facade.dto.DayFillMissingDTO;
import com.kcfy.techservicemarket.facade.dto.DayFillStatus;
import com.kcfy.techservicemarket.infra.util.DateTool;
import com.kcfy.techservicemarket.infra.util.TimeSettingDayType;
import com.kcfy.techservicemarket.infra.wso2.WSO2Util;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openkoala.koala.commons.InvokeResult;
import org.openkoala.security.core.domain.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
@Transactional
public class TimesheetApplicationImpl implements TimesheetApplication {
    private Log log = LogFactory.getLog(TimesheetApplicationImpl.class);
    @Inject
    private ProjectApplication projectApplication;
    @Inject
    private TimeSettingApplication timeSettingApplication;

    @Override
    public Timesheet getTimesheet(String id) {
        return Timesheet.get(Timesheet.class, Long.valueOf(id));
    }

    @Override
    public void createTimesheet(Timesheet timesheet) {
        timesheet.save();
    }

    @Override
    public void updateTimesheet(Timesheet timesheet) {
        timesheet.save();
    }
    @Override
    public void removeTimesheet(Timesheet timesheet) {
        if (timesheet != null) {
            timesheet.remove();
        }
    }

    @Override
    public void removeTimesheets(Set<Timesheet> timesheets) {
        for (Timesheet timesheet : timesheets) {
            timesheet.remove();
        }
    }

    @Override
    public void deleteDetails(List<TimesheetDetail> detailOldForDelete) {
        for (TimesheetDetail detail : detailOldForDelete) {
            detail.remove();
        }
    }

    @Override
    public List<Timesheet> findAllTimesheet() {
        return Timesheet.findAll(Timesheet.class);
    }
    @Override
    public Timesheet getByUserIdAndDate(Long userId, Date date) {
        List<Timesheet> list = Timesheet.getRepository().createCriteriaQuery(Timesheet.class)
                .eq("user.id", Long.valueOf(userId)).eq("date", date).list();
        return list.size() > 0 ? list.get(0) : null;
    }
    @Override
    public List<Timesheet> getByUserId(String userId) {
        return Timesheet.getRepository().createCriteriaQuery(Timesheet.class).eq("user.id", Long.valueOf(userId)).list();
    }
    @Override
    public List<Timesheet> findTimesheetBetween(Long userId, Date startDate, Date endDate) {
        return Timesheet.getRepository().createCriteriaQuery(Timesheet.class)
                .eq("user.id", userId).ge("date", startDate).le("date", endDate).list();
    }

    private void addMissingDayFat(User user, Map<String, Timesheet> timeMap, List<DayFillMissingDTO> missingDateList, String dateTmp) {
        if (timeMap != null && timeMap.containsKey(dateTmp)) {
            // 草稿态的添加到缺失列表
            Timesheet timesheet = timeMap.get(dateTmp);
            if (timesheet.getStatus() == Constants.TimesheetStatus.DRAFT) {
                DayFillMissingDTO dayFillMissingDTO = new DayFillMissingDTO();
                dayFillMissingDTO.setUserId(user.getId());
                dayFillMissingDTO.setUserName(user.getName());
                dayFillMissingDTO.setDate(dateTmp);
                missingDateList.add(dayFillMissingDTO);
            }
        } else {
            // 添加到缺失列表
            DayFillMissingDTO dayFillMissingDTO = new DayFillMissingDTO();
            dayFillMissingDTO.setUserId(user.getId());
            dayFillMissingDTO.setUserName(user.getName());
            dayFillMissingDTO.setDate(dateTmp);
            missingDateList.add(dayFillMissingDTO);
        }
    }

    private void addMissingDay(Map<String, Timesheet> timesheetMap, List<DayFillStatus> missingDateList, String dateTmp) {
        if (timesheetMap.containsKey(dateTmp)) {
            // 草稿态的添加到缺失列表
            Timesheet timesheet = timesheetMap.get(dateTmp);
            if (timesheet.getStatus() == Constants.TimesheetStatus.DRAFT) {
                DayFillStatus dayFillStatus = new DayFillStatus();
                dayFillStatus.setDate(dateTmp);
                dayFillStatus.setStatus(Constants.TimesheetStatus.DRAFT);
                missingDateList.add(dayFillStatus);
            }
        } else {
            // 添加到缺失列表
            DayFillStatus dayFillStatus = new DayFillStatus();
            dayFillStatus.setDate(dateTmp);
            dayFillStatus.setStatus(Constants.TimesheetStatus.NONE);
            missingDateList.add(dayFillStatus);
        }
    }

    @Override
    public List<Timesheet> getDraftList(Long userId) {
        return Timesheet.getRepository().createCriteriaQuery(Timesheet.class)
                .eq("user.id", userId)
                .eq("status", Constants.TimesheetStatus.DRAFT).list();
    }
    @Override
    public List<DayFillStatus> getMissingDateList(Long userId) {
        // 填的
        List<Timesheet> timesheetList = Timesheet.getRepository().createCriteriaQuery(Timesheet.class)
                .eq("user.id", userId).ge("date", DateTool.getFirstDayOfThisYear()).list();
        Map<String, Timesheet> timesheetMap = new HashMap<>();
        for (Timesheet timesheet : timesheetList) {
            timesheetMap.put(DateTool.convertDateToString("yyyy-MM-dd", timesheet.getDate()), timesheet);
        }
        // 日期设置
        Map<String, TimeSetting> timeSettingMap = timeSettingApplication.getTimeSettingMapFromYearFirstDayToNow();
        // 找出缺的
        List<DayFillStatus> missingDateList = new ArrayList<>();
        for (Date date : DateTool.getAllDateOfThisYearToNow()) {
            String dateTmp = DateTool.convertDateToString("yyyy-MM-dd", date);
            if (timeSettingMap.containsKey(dateTmp)) {
                // 有设置看是否为工作日，工作日则判断是否填报。
                TimeSetting timeSetting = timeSettingMap.get(dateTmp);
                if (timeSetting.getDayType() == TimeSettingDayType.WORKDAY.getValue()) {
                    // 判断是否填报，没有填报或者填报状态为草稿的要加入到缺失列表里
                    addMissingDay(timesheetMap, missingDateList, dateTmp);
                }
            } else {
                // 没有设置的话判断是否为周末，不为周末的话要看是否填报
                if (DateTool.isWorkDay(date)) {
                    addMissingDay(timesheetMap, missingDateList, dateTmp);
                }
            }
        }
        return missingDateList;
    }

    @Override
    public List<TimesheetDetail> getRejectedDetailList(Long userId) {
        return TimesheetDetail.getRepository().createCriteriaQuery(TimesheetDetail.class)
                .eq("timesheet.user.id", userId)
                .ge("timesheet.date", DateTool.getFirstDayOfThisYear())
                .eq("status", String.valueOf(Constants.TimesheetStatus.REJECTED)).list();
    }

    @Override
    public List<TimesheetDetail> getWaitApproveDetailsByPMUserId(Long userId) {
        // 获取项目经理所有的项目。
        List<Project> projectList = projectApplication.getProjectsByPmUserId(userId);
        // 获取这些项目下所有的timesheetDetails
        List<TimesheetDetail> timesheetDetailList = getTimesheetDetailsByProjects(projectList, Constants.TimesheetStatus.COMMITTED);
        return timesheetDetailList;
    }
    @Override
    public List<DayFillMissingDTO> getTeamMissingDateListByPMUserId(Long userId) {
        // 填的
        List<Project> projectList = projectApplication.getProjectsByPmUserId(userId);
        List<User> userList = new ArrayList<>();
        for (Project project : projectList) {
            for (User user : project.getUsers()) {
                userList.add(user);
            }
        }
        List<Long> userIds = new ArrayList<>();
        for (User user : userList) {
            userIds.add(user.getId());
        }
        List<Timesheet> timesheetList = Timesheet.getRepository().createCriteriaQuery(Timesheet.class)
                .in("user.id", userIds).ge("date", DateTool.getFirstDayOfThisYear()).list();
        Map<Long, Map<String, Timesheet>> timesheetMap = new HashMap<>();
        for (Timesheet timesheet : timesheetList) {
            if (timesheetMap.containsKey(timesheet.getUser().getId())) {
                timesheetMap.get(timesheet.getUser().getId()).put(DateTool.convertDateToString("yyyy-MM-dd", timesheet.getDate()), timesheet);
            } else {
                Map<String, Timesheet> timeMap = new HashMap<>();
                timeMap.put(DateTool.convertDateToString("yyyy-MM-dd", timesheet.getDate()), timesheet);
                timesheetMap.put(timesheet.getUser().getId(), timeMap);
            }
        }
        // 日期设置
        Map<String, TimeSetting> timeSettingMap = timeSettingApplication.getTimeSettingMapFromYearFirstDayToNow();
        // 缺失的列表
        List<DayFillMissingDTO> missingDateList = new ArrayList<>();
        for (Date date : DateTool.getAllDateOfThisYearToNow()) {
            String dateTmp = DateTool.convertDateToString("yyyy-MM-dd", date);
            for (User user : userList) {
                Map<String, Timesheet> timeMap = timesheetMap.get(user.getId());
                if (timeSettingMap.containsKey(dateTmp)) {
                    // 有设置看是否为工作日，工作日则判断是否填报。
                    TimeSetting timeSetting = timeSettingMap.get(dateTmp);
                    if (timeSetting.getDayType() == TimeSettingDayType.WORKDAY.getValue()) {
                        // 判断是否填报，没有填报或者填报状态为草稿的要加入到缺失列表里
                        addMissingDayFat(user, timeMap, missingDateList, dateTmp);
                    }
                } else {
                    // 没有设置的话判断是否为周末，不为周末的话要看是否填报
                    if (DateTool.isWorkDay(date)) {
                        addMissingDayFat(user, timeMap, missingDateList, dateTmp);
                    }
                }
            }
        }
        return missingDateList;
    }

    @Override
    public List<TimesheetDetail> getTeamRejectedDetaiListByPMUserId(Long userId) {
        List<Project> projectList = projectApplication.getProjectsByPmUserId(userId);
        return getTimesheetDetailsByProjects(projectList, Constants.TimesheetStatus.REJECTED);
    }

    private List<TimesheetDetail> getTimesheetDetailsByProjects(List<Project> projectList, int status) {
        List<Long> list = new ArrayList<>();
        for (Project project : projectList) {
            list.add(project.getId());
        }
        List<TimesheetDetail> timesheetDetailList = TimesheetDetail.getRepository().createCriteriaQuery(TimesheetDetail.class)
                .ge("timesheet.date", DateTool.getFirstDayOfThisYear()).eq("status", String.valueOf(status)).in("project.id", list).list();
        return timesheetDetailList;
    }

    @Override
    public String sendRemindMsgToTeam(Long userId, Integer remindType) {
        String result = "";
        if (remindType != null && remindType == Constants.RemindType.NONE) {
            Map<Long, List<String>> userMissDateMap = new HashMap<>();
            List<DayFillMissingDTO> missingDTOList = getTeamMissingDateListByPMUserId(userId);
            for (DayFillMissingDTO dayFillMissingDTO : missingDTOList) {
                if (userMissDateMap.containsKey(dayFillMissingDTO.getUserId())) {
                    userMissDateMap.get(dayFillMissingDTO.getUserId()).add(dayFillMissingDTO.getDate());
                } else {
                    List<String> listMissDate = new ArrayList<>();
                    listMissDate.add(dayFillMissingDTO.getDate());
                    userMissDateMap.put(dayFillMissingDTO.getUserId(), listMissDate);
                }
            }
            result = sendMsgToTeam(" 没有填写日志，请重新填写。", userMissDateMap, "http://iwork.fengyuntec.com/IWork/resources/html/index.html#/log/lost");
        } else if (remindType != null && remindType == Constants.RemindType.REJECTED) {
            Map<Long, List<String>> userRejectDateMap = new HashMap<>();
            List<TimesheetDetail> rejectedList = getTeamRejectedDetaiListByPMUserId(userId);
            for(TimesheetDetail detail : rejectedList) {
                Long userIdTmp = detail.getTimesheet().getUser().getId();
                String dateTmp = DateTool.convertDateToString("yyyy-MM-dd", detail.getTimesheet().getDate());
                if (userRejectDateMap.containsKey(userIdTmp) && !userRejectDateMap.get(userIdTmp).contains(dateTmp)) {
                    userRejectDateMap.get(userIdTmp).add(dateTmp);
                } else {
                    List<String> listMissDate = new ArrayList<>();
                    listMissDate.add(dateTmp);
                    userRejectDateMap.put(userIdTmp, listMissDate);
                }
            }
            result = sendMsgToTeam(" 日志被退回，请重新填写。", userRejectDateMap, "http://iwork.fengyuntec.com/IWork/resources/html/index.html#/approval/wrong");
        } else {
            result = "缺失参数：remindType";
        }
        return result;
    }

    private String sendMsgToTeam(String contentEnd, Map<Long, List<String>> userDateMap, String callbackUrl) {
        String result = "";
        for (Long teamUserId : userDateMap.keySet()) {
            User user = User.get(User.class, teamUserId);
            String content = user.getName() + "你好，你有以下日期：";
            List<String> dateList = userDateMap.get(teamUserId);
            for (int i = 0; i < dateList.size(); i++) {
                content += dateList.get(i) + " ";
                if (i >= 30) {
                    content = content.substring(0, content.length() - 1) + "......";
                    break;
                }
            }
            content +=  contentEnd;
            log.info(content);
            if(StringUtils.isNotEmpty(user.getWeixinUserId())) {
                InvokeResult invokeResult = WSO2Util.sendMsg(user.getWeixinUserId(), content, callbackUrl + "?userId=" + teamUserId);
                if(invokeResult.isSuccess()) {
                    result = "200";
                } else {
                    result = invokeResult.getErrorMessage();
                }
            }
        }
        return result;
    }
}
