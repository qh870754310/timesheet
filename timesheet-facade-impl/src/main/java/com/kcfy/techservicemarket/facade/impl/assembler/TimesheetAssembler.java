package com.kcfy.techservicemarket.facade.impl.assembler;

import com.kcfy.techservicemarket.core.domain.timesheet.Timesheet;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.facade.dto.TimesheetDTO;
import com.kcfy.techservicemarket.facade.dto.TimesheetDetailDTO;
import org.openkoala.common.core.constants.Availability;
import org.openkoala.security.core.domain.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TimesheetAssembler {

    public static TimesheetDTO toDTO(Timesheet timesheet) {
        TimesheetDTO timesheetDTO = new TimesheetDTO();
        if(timesheet != null && timesheet.getId() != null) {
            timesheetDTO.setDate(new SimpleDateFormat("yyyy-MM-dd").format(timesheet.getDate()));
            List<TimesheetDetailDTO> list = new ArrayList<>();
            for (TimesheetDetail timesheetDetail : timesheet.getTimesheetDetailList()) {
                TimesheetDetailDTO timesheetDetailDTO = new TimesheetDetailDTO();
                timesheetDetailDTO.setTimesheetDetailId(String.valueOf(timesheetDetail.getId()));
                timesheetDetailDTO.setProjectName(timesheetDetail.getProject().getName());
                timesheetDetailDTO.setStageName(timesheetDetail.getProjectStage().getName());
                timesheetDetailDTO.setHour(String.valueOf(timesheetDetail.getHour()));
                timesheetDetailDTO.setTaskDesc(timesheetDetail.getTaskDesc());
                list.add(timesheetDetailDTO);
            }
            timesheetDTO.setTimesheetDetailList(list);
        }
        return timesheetDTO;
    }

    public static List<TimesheetDTO> toDTOs(Collection<Timesheet> timesheets) {
        List<TimesheetDTO> list = new ArrayList<>();
        for(Timesheet t : timesheets) {
            list.add(toDTO(t));
        }
        return list;
    }

    public static Timesheet toEntity(TimesheetDTO timesheetDTO) throws ParseException {
        Timesheet timesheet = new Timesheet();
        //timesheet.setId(timesheetDTO.getId());
        timesheet.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(timesheetDTO.getDate()));
        //timesheet.setStatus(timesheetDTO.getStatus());
//        User user = new User();
//        user.setId(timesheetDTO.getUserId());
//        timesheet.setUser(user);
//        timesheet.setVersion(1);
//        timesheet.setIsAvailable(Availability.available);
//        timesheet.setModifyDate(new Date());
        return timesheet;
    }

    public static List<Timesheet> toEntities(Collection<TimesheetDTO> TimesheetDTOs) throws ParseException {
        List<Timesheet> list = new ArrayList<>();
        for(TimesheetDTO t : TimesheetDTOs) {
            list.add(toEntity(t));
        }
        return list;
    }
}
