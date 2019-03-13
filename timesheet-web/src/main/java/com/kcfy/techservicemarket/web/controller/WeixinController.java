package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.UserFacade;
import com.kcfy.techservicemarket.facade.dto.weixin.UserRole;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by zhouxc on 2016/5/7.
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {

    @Inject
    private UserFacade userFacade;

    @RequestMapping(value = "/bindUser")
    @ResponseBody
    public InvokeResult bindWeixinUser(String openId, String weixinUserId, String email) {
        Long userId = userFacade.bindWeixinUser(openId, weixinUserId, email);
        InvokeResult invokeResult = null;
        if(userId != null) {
            UserRole userRole = userFacade.getRoleNameByUserId(userId);
            invokeResult = InvokeResult.success(userRole);
        } else {
            invokeResult = InvokeResult.failure("此邮箱无对应员工信息。");
        }
        return invokeResult;
    }

    @RequestMapping(value = "/getUserRole")
    @ResponseBody
    public UserRole getUserRole(String userId) {
        UserRole userRole = userFacade.getRoleNameByUserId(Long.valueOf(userId));
        return userRole;
    }

    @RequestMapping(value = "/getBindStatus")
    @ResponseBody
    public InvokeResult getBindStatus(String openId, String weixinUserId) {
        return userFacade.getBindStatus(openId, weixinUserId);
    }

    @RequestMapping(value = "/unBindUser")
    @ResponseBody
    public InvokeResult unBindUser(Long userId) {
        return userFacade.unBindUser(userId);
    }
}
