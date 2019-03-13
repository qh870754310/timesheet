package com.kcfy.techservicemarket.application;


import com.kcfy.techservicemarket.core.domain.timesheet.Timesheet;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.facade.dto.DayFillMissingDTO;
import com.kcfy.techservicemarket.facade.dto.DayFillStatus;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TimesheetApplication {

    Timesheet getTimesheet(String id);
    void createTimesheet(Timesheet timesheet);
    void updateTimesheet(Timesheet timesheet);
    void removeTimesheet(Timesheet timesheet);
    void removeTimesheets(Set<Timesheet> timesheets);
    void deleteDetails(List<TimesheetDetail> detailOldForDelete);

    List<Timesheet> findAllTimesheet();
    Timesheet getByUserIdAndDate(Long userId, Date date);
    List<Timesheet> getByUserId(String userId);
    List<Timesheet> findTimesheetBetween(Long userId, Date startDate, Date endDate);

    /**
     * 查找该用户的日志草稿
     * @param userId
     * @return
     */
    List<Timesheet> getDraftList(Long userId);

    /**
     * 查找该用户缺失的日志
     * @param userId
     * @return
     */
    List<DayFillStatus> getMissingDateList(Long userId);

    /**
     * 查找该用户被驳回的日志
     * @param userId
     * @return
     */
    List<TimesheetDetail> getRejectedDetailList(Long userId);

    /**
     * 根据项目经理用户id，查找该项目经理的待办列表。
     * @param userId
     * @return
     */
    List<TimesheetDetail> getWaitApproveDetailsByPMUserId(Long userId);

    /**
     * 根据项目经理用户id，查找该项目经理管理的项目团队中成员缺失的日志
     * @param userId
     * @return
     */
    List<DayFillMissingDTO> getTeamMissingDateListByPMUserId(Long userId);

    /**
     * 根据项目经理用户id，查找该项目经理管理的项目团队中成员被驳回的日志
     * @param userId
     * @return
     */
    List<TimesheetDetail> getTeamRejectedDetaiListByPMUserId(Long userId);

    /**
     * 根据type发送不同的消息给团队用户
     * @param userId
     * @param remindType
     * @return
     */
    String sendRemindMsgToTeam(Long userId, Integer remindType);
}

