package com.kcfy.techservicemarket.application.impl;

import com.kcfy.techservicemarket.application.HomeApplication;
import com.kcfy.techservicemarket.application.TimesheetApplication;
import com.kcfy.techservicemarket.core.domain.timesheet.TimesheetDetail;
import com.kcfy.techservicemarket.facade.dto.DayFillMissingDTO;
import com.kcfy.techservicemarket.facade.dto.DayFillStatus;
import com.kcfy.techservicemarket.facade.dto.weixin.UserInfo;
import org.openkoala.security.core.domain.Authorization;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by zhouxc on 2016/6/23.
 */
@Named
@Transactional
public class HomeApplicationImpl implements HomeApplication {
    @Inject
    private TimesheetApplication timesheetApplication;
    @Override
    public UserInfo getInfoByUserId(Long userId) {
        List<Authorization> list = Authorization.getRepository().createCriteriaQuery(Authorization.class)
                .eq("actor.id", userId).list();
        String roleId = "";
        String roleName = "";
        for(Authorization authorization: list) {
            roleId =  authorization.getAuthority().getId() + ",";
            roleName = authorization.getAuthority().getName() + ",";
        }
        UserInfo userInfo = new UserInfo();
        if(list.size() > 0) {
            userInfo.setName(list.get(0).getActor().getName());
            userInfo.setRoleId(roleId.substring(0, roleId.length() - 1));
            userInfo.setRoleName(roleName.substring(0, roleName.length() - 1));
        }

        List<DayFillStatus> missingList = timesheetApplication.getMissingDateList(userId);
        List<TimesheetDetail> rejectedList = timesheetApplication.getRejectedDetailList(userId);
        List<TimesheetDetail> waitApproveList = timesheetApplication.getWaitApproveDetailsByPMUserId(userId);
        List<DayFillMissingDTO> teamMissingList = timesheetApplication.getTeamMissingDateListByPMUserId(userId);
        List<TimesheetDetail> teamRejectedList = timesheetApplication.getTeamRejectedDetaiListByPMUserId(userId);
        userInfo.setMissingNum(missingList.size());
        userInfo.setRejectedNum(rejectedList.size());
        userInfo.setApprovingNum(waitApproveList.size());
        userInfo.setAllPeopleNum(teamMissingList.size() + teamRejectedList.size());
        return userInfo;
    }
}
