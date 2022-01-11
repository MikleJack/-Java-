package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2022-01-10 19:24:58
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 524072524066553568L;
    
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

