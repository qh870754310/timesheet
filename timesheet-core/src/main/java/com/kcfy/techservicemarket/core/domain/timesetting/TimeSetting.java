package com.kcfy.techservicemarket.core.domain.timesetting;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "t_time_setting")
public class TimeSetting extends CustomKoalaAbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "date")
    private String date;

    @Column(name = "day_type")
    private int dayType;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "content")
    private String content;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDayType() {
        return dayType;
    }

    public void setDayType(int dayType) {
        this.dayType = dayType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean existed() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean notExisted() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean existed(String propertyName, Object propertyValue) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String[] businessKeys() {
        // TODO Auto-generated method stub
        return null;
    }

    public static TimeSetting getByDate(String date) {
        List<TimeSetting> timeSettingList = getRepository().createCriteriaQuery(TimeSetting.class).eq("date", date).list();
        return timeSettingList.size() > 0 ? timeSettingList.get(0) : null;
    }

    public static List<TimeSetting> getByDateRange(String startDate, String endDate) {
        return getRepository().createCriteriaQuery(TimeSetting.class).between("date", startDate, endDate).list();
    }
}