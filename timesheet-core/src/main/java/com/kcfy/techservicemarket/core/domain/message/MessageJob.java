package com.kcfy.techservicemarket.core.domain.message;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;
import org.openkoala.security.core.domain.Authority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_message_job")
public class MessageJob extends CustomKoalaAbstractEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "memo")
    private String memo;
    @Column(name = "cron_expression")
    private String cronExpression;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "message_template_id")
    private MessageTemplate messageTemplate;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "t_message_job_role", inverseJoinColumns = {@JoinColumn(name = "role_id")}, joinColumns = {
            @JoinColumn(name = "message_job_id")})
    private List<Authority> authorityList;
    @Column(name = "status")
    private boolean status;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getMemo() {
        return memo;
    }


    public void setMemo(String memo) {
        this.memo = memo;
    }


    public String getCronExpression() {
        return cronExpression;
    }


    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public MessageTemplate getMessageTemplate() {
        return messageTemplate;
    }


    public void setMessageTemplate(MessageTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }


    public List<Authority> getAuthorityList() {
        return authorityList;
    }


    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }


    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String[] businessKeys() {
        // TODO Auto-generated method stub
        return null;
    }

}
