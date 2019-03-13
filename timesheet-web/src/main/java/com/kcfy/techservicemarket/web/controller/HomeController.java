package com.kcfy.techservicemarket.web.controller;

import com.kcfy.techservicemarket.facade.HomeFacade;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by zhouxc on 2016/6/23.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Inject
    private HomeFacade homeFacade;

    @RequestMapping(value = "/info/get")
    @ResponseBody
    public InvokeResult getInfo(Long userId){
        return homeFacade.getInfo(userId);
    }
}
