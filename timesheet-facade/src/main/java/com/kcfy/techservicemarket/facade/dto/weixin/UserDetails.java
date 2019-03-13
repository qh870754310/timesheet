package com.kcfy.techservicemarket.facade.dto.weixin;

/**
 * Created by daidong on 16/6/24.
 */
public class UserDetails {
    private String deptName;
    private String userId;
    private String userName;
    private String userPost;
    private String roleName;
    private String phone;
    private String email;

    public UserDetails(){};
    public UserDetails(String userId,String userName,String roleName,String email,String phone,String deptName,String userPost) {
        this.userId = userId;
        this.userName = userName;
        this.roleName = roleName;
        this.email = email;
        this.phone = phone;
        this.deptName = deptName;
        this.userPost = userPost;
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
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String org) {
        this.deptName = org;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

}

