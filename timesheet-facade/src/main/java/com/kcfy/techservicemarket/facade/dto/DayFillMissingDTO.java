package com.kcfy.techservicemarket.facade.dto;

/**
 * Created by zhouxc on 2016/7/5.
 */
public class DayFillMissingDTO {
    private Long userId;
    private String userName;
    private String date;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
