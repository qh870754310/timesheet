package com.kcfy.techservicemarket.facade.dto;

/**
 * Created by zhouxc on 2016/7/4.
 */
public class UserFitDTO {
    private Long id;
    private String name;
    private String email;

    public UserFitDTO() {

    }
    public UserFitDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
