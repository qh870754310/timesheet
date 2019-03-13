package com.kcfy.techservicemarket.facade.dto.weixin;

/**
 * Created by zhouxc on 2016/6/30.
 */
public class StatusReturn {
    private String userId;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
