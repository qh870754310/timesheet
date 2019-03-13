package com.kcfy.techservicemarket.core.domain.timesheet;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;
import org.openkoala.security.core.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daidong on 16/6/24.
 */
@Entity
@Table(name = "t_template")
public class TimeSheetTemplate extends CustomKoalaAbstractEntity {
    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name ="USER_ID")
    private User user;
    @Column(name = "status")
    private Integer status;

    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy="timeSheetTemplate")
    private List<TimeSheetTemplateDetail> timeSheetTemplateDetailList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public List<TimeSheetTemplateDetail> getTimeSheetTemplateDetailList(){
        return timeSheetTemplateDetailList;
    }
    public void setTimeSheetTemplateDetailList(List<TimeSheetTemplateDetail> timeSheetTemplateDetailList) {
        this.timeSheetTemplateDetailList = timeSheetTemplateDetailList;
    }
    @Override
    public String[] businessKeys() {
        return null;
    }
}
