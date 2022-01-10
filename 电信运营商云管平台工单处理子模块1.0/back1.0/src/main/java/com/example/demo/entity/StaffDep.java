package com.example.demo.entity;


import java.io.Serializable;

/**
 * (StaffDep)实体类
 *
 * @author makejava
 * @since 2022-01-07 18:22:49
 */
public class StaffDep implements Serializable {
    private static final long serialVersionUID = -59015498658074179L;
    
    private String depNum;
    
    private String depName;
    
    private Integer depLevel;
    
    private Integer workNum;
    
    private String name;
    
    private String phone;
    
    private Boolean state;
    
    private Boolean inService;

    private String state_string;


    public void setState_string(String state_string){
        this.state_string = state_string;
    }

    public String getState_string(){
        return this.state_string;
    }

    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
        this.depNum = depNum;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getDepLevel() {
        return depLevel;
    }

    public void setDepLevel(Integer depLevel) {
        this.depLevel = depLevel;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

