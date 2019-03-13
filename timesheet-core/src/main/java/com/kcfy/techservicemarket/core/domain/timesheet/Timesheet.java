package com.kcfy.techservicemarket.core.domain.timesheet;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;
import org.openkoala.security.core.domain.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by zhouxc on 2016/5/10.
 */
@Entity
@Table(name = "t_timesheet")
public class Timesheet extends CustomKoalaAbstractEntity {

    private static final long serialVersionUID = 1L;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name ="USER_ID")
    private User user;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade={CascadeType.ALL},
            fetch = FetchType.LAZY, mappedBy="timesheet")
    private List<TimesheetDetail> timesheetDetailList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TimesheetDetail> getTimesheetDetailList() {
        return timesheetDetailList;
    }

    public void setTimesheetDetailList(List<TimesheetDetail> timesheetDetailList) {
        this.timesheetDetailList = timesheetDetailList;
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


}
