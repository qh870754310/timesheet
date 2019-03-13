package com.kcfy.techservicemarket.facade;

import org.openkoala.koala.commons.InvokeResult;

/**
 * Created by Qiaohong on 2016/6/30.
 */
public interface TimeSheetDetailFacade {

    InvokeResult getTimeSheetDetail(String id);

    InvokeResult getTeamRejectedDetailListByPMUserId(Long userId);
}
