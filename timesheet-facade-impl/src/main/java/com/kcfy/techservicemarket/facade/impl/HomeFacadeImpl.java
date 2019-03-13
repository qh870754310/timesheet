package com.kcfy.techservicemarket.facade.impl;

import com.kcfy.techservicemarket.application.HomeApplication;
import com.kcfy.techservicemarket.facade.HomeFacade;
import com.kcfy.techservicemarket.facade.dto.weixin.UserInfo;
import org.openkoala.koala.commons.InvokeResult;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by zhouxc on 2016/6/23.
 */
@Named
public class HomeFacadeImpl implements HomeFacade {

    @Inject
    private HomeApplication homeApplication;

    @Override
    public InvokeResult getInfo(Long userId) {
        UserInfo userInfo = homeApplication.getInfoByUserId(userId);
        return InvokeResult.success(userInfo);
    }
}
