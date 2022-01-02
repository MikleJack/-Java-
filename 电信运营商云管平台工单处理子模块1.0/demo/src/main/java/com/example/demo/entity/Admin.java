package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2022-01-02 16:31:38
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = -37183949994305702L;
    
    private String adminNum;
    
    private String password;


    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

