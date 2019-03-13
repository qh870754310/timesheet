package com.kcfy.techservicemarket.application;

import com.kcfy.techservicemarket.facade.dto.weixin.UserInfo;

/**
 * Created by zhouxc on 2016/6/23.
 */
public interface HomeApplication {
    UserInfo getInfoByUserId(Long userId);
}
