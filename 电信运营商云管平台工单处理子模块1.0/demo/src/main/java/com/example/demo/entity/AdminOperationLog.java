package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (AdminOperationLog)实体类
 *
 * @author makejava
 * @since 2022-01-07 09:39:06
 */
public class AdminOperationLog implements Serializable {
    private static final long serialVersionUID = -30701167850050447L;

    private Date operateTime;

    private String operate;

    private String ip;

    private String address;

    private String name;

    private String depNum;

    private String phone;

    private Boolean state;

    private Boolean inService;

    private Integer workNum;


    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
        this.depNum = depNum;
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

    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

}

