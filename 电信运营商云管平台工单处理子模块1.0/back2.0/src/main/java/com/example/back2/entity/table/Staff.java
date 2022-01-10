package com.example.back2.entity.table;

import java.util.Boolean;
import java.io.Serializable;

/**
 * (Staff)实体类
 *
 * @author makejava
 * @since 2022-01-10 19:34:37
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = 399890825581205702L;
    
    private Integer workNum;
    
    private String name;
    
    private Integer depNum;
    
    private String phone;
    
    private String password;
    
    private Boolean state;
    
    private Boolean inService;


    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepNum() {
        return depNum;
    }

    public void setDepNum(Integer depNum) {
        this.depNum = depNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getInService() {
        return inService;
    }

    public void setInService(Boolean inService) {
        this.inService = inService;
    }

}

