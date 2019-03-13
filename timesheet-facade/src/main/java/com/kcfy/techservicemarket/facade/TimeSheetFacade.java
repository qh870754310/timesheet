package com.kcfy.techservicemarket.facade;

import com.kcfy.techservicemarket.facade.dto.TimesheetDTO;
import com.kcfy.techservicemarket.facade.dto.TimesheetSaveWrapDTO;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import java.util.Date;
import java.util.List;

public interface TimeSheetFacade {

    InvokeResult createTimesheet(TimesheetSaveWrapDTO timeSetting);
    InvokeResult updateTimesheet(TimesheetSaveWrapDTO timeSetting);
    InvokeResult removeTimesheet(String id);

    List<TimesheetDTO> getByUserId(String userId);
    InvokeResult getTimesheet(String id);
    InvokeResult getByUserIdAndDate(Long userId, Date date);

    InvokeResult getDraftList(Long userId);
    InvokeResult getMissingDateList(Long userId);
    InvokeResult getRejectedDetailList(Long userId);
    InvokeResult getTeamMissingDateListByPMUserId(Long userId);

    InvokeResult sendRemindMsgToTeam(Long userId, Integer remindType);
}

