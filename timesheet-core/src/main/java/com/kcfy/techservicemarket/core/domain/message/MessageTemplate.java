package com.kcfy.techservicemarket.core.domain.message;

import org.openkoala.common.core.domain.CustomKoalaAbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_message_template")
public class MessageTemplate extends CustomKoalaAbstractEntity {

    @Column(name = "template_id")
    private String templateId;
    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String[] businessKeys() {
        // TODO Auto-generated method stub
        return null;
    }

}
