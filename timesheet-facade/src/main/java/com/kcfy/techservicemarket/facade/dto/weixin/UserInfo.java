package com.kcfy.techservicemarket.facade.dto.weixin;

/**
 * Created by zhouxc on 2016/6/23.
 */
public class UserInfo {
    private String name;
    private String roleId;
    private String roleName;
    private int missingNum;
    private int rejectedNum;
    private int approvingNum;
    private int allPeopleNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getMissingNum() {
        return missingNum;
    }

    public void setMissingNum(int missingNum) {
        this.missingNum = missingNum;
    }

    public int getRejectedNum() {
        return rejectedNum;
    }

    public void setRejectedNum(int rejectedNum) {
        this.rejectedNum = rejectedNum;
    }

    public int getApprovingNum() {
        return approvingNum;
    }

    public void setApprovingNum(int approvingNum) {
        this.approvingNum = approvingNum;
    }

    public int getAllPeopleNum() {
        return allPeopleNum;
    }

    public void setAllPeopleNum(int allPeopleNum) {
        this.allPeopleNum = allPeopleNum;
    }
}
