package com.example.back2.entity.view;

import java.io.Serializable;

/**
 * (StaffhomePersoninform)实体类
 *
 * @author makejava
 * @since 2022-01-11 15:31:55
 */
public class StaffhomePersoninform implements Serializable {
    private static final long serialVersionUID = -83221710270318595L;

    private Integer workNum;

    private String name;

    private Integer depNum;

    private String depName;

    private Integer depLevel;

    private Integer depBudget;

    private String phone;

    private Boolean state;


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

    public Integer getDepBudget() {
        return depBudget;
    }

    public void setDepBudget(Integer depBudget) {
        this.depBudget = depBudget;
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

}

