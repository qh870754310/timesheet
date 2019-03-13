package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.UserFacade;
import com.kcfy.techservicemarket.facade.dto.weixin.UserDetails;
import com.kcfy.techservicemarket.facade.dto.weixin.UserRole;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by daidong on 16/6/23.
 */
@Controller
@RequestMapping("/user")
public class UserInterfaceController {

     @Inject
    private UserFacade userFacade;
    @RequestMapping(value = "/getUserDetails")
    @ResponseBody
    public InvokeResult getUserDetails(Long userId){
         InvokeResult invokeResult = null;
        if(userId != null) {
            UserDetails userRole= userFacade.getUserDeatilsByUserId(userId);
            if(userRole != null) {
                invokeResult = InvokeResult.success(userRole);
            }else{invokeResult = InvokeResult.failure("此用户信息有误.");}
        }else{
            invokeResult = InvokeResult.failure("此用户信息为空.");
        }
        return invokeResult;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.PUT)
    public InvokeResult updateUserInfo(Long userId, String phone){
        return userFacade.updateUserInfoByUserId(userId,phone);
    }
}
