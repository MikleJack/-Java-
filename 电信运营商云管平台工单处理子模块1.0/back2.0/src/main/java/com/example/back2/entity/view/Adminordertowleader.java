package com.example.back2.entity.view;

import java.util.Date;
import java.io.Serializable;

/**
 * (Adminordertowleader)实体类
 *
 * @author makejava
 * @since 2022-01-18 16:54:29
 */
public class Adminordertowleader implements Serializable {
    private static final long serialVersionUID = -86127819612400619L;
    
    private String workOrderNum;
    
    private String workOrderType;
    
    private String workOrderName;
    
    private Date expirationTime;
    
    private String workOrderState;
    
    private Integer workerNum;
    
    private String name;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public String getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(String workOrderType) {
        this.workOrderType = workOrderType;
    }

    public String getWorkOrderName() {
        return workOrderName;
    }

    public void setWorkOrderName(String workOrderName) {
        this.workOrderName = workOrderName;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getWorkOrderState() {
        return workOrderState;
    }

    public void setWorkOrderState(String workOrderState) {
        this.workOrderState = workOrderState;
    }

    public Integer getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(Integer workerNum) {
        this.workerNum = workerNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

