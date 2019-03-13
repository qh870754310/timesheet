package com.kcfy.techservicemarket.facade.dto;

/**
 * Created by zhouxc on 2016/6/22.
 */
public class DayFillStatusAndType {
    private String date;
    private int dateType;
    private int fillStatus;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDateType() {
        return dateType;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }

    public int getFillStatus() {
        return fillStatus;
    }

    public void setFillStatus(int fillStatus) {
        this.fillStatus = fillStatus;
    }
}
