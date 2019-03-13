package com.kcfy.techservicemarket.facade.dto;

import org.openkoala.common.core.constants.Availability;

import java.io.Serializable;

public class TemplateMsgIndustryDTO implements Serializable {
    private Long id;
    private String nameOneFirst;
    private Integer codeTwo;
    private Integer codeOne;
    private String nameOneSecond;
    private String nameTwoFirst;
    private String nameTwoSecond;
    private int version;

    private Availability isAvailable;

    private String creatorId;

    private String createDate;

    private String modifierId;

    private String modifyDate;
    public void setNameOneFirst(String nameOneFirst) {
        this.nameOneFirst = nameOneFirst;
    }
    public String getNameOneFirst() {
        return this.nameOneFirst;
    }
    public void setCodeTwo(Integer codeTwo) {
        this.codeTwo = codeTwo;
    }
    public Integer getCodeTwo() {
        return this.codeTwo;
    }
    public void setCodeOne(Integer codeOne) {
        this.codeOne = codeOne;
    }
    public Integer getCodeOne() {
        return this.codeOne;
    }
    public void setNameOneSecond(String nameOneSecond) {
        this.nameOneSecond = nameOneSecond;
    }
    public String getNameOneSecond() {
        return this.nameOneSecond;
    }
    public void setNameTwoFirst(String nameTwoFirst) {
        this.nameTwoFirst = nameTwoFirst;
    }
    public String getNameTwoFirst() {
        return this.nameTwoFirst;
    }
    public void setNameTwoSecond(String nameTwoSecond) {
        this.nameTwoSecond = nameTwoSecond;
    }
    public String getNameTwoSecond() {
        return this.nameTwoSecond;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Availability getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Availability isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TemplateMsgIndustryDTO other = (TemplateMsgIndustryDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}