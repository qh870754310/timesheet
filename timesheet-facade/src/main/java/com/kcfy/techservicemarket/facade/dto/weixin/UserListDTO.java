package com.kcfy.techservicemarket.facade.dto.weixin;

import java.util.List;

/**
 * Created by Qiaohong on 2016/7/5.
 */
public class UserListDTO {

    private List<UserGetDTO> userList;

    public List<UserGetDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserGetDTO> userList) {
        this.userList = userList;
    }
}
