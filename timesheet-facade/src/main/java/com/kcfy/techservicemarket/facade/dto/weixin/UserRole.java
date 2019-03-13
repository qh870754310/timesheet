package com.kcfy.techservicemarket.facade.dto.weixin;

/**
 * Created by zhouxc on 2016/5/9.
 */
public class UserRole {
    private String userId;
    private String userName;
    private String roleName;

    public UserRole(){};
    public UserRole(String userId,String userName,String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
