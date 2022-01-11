package com.example.back2.entity.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * (AdminsearchorderTable)实体类
 *
 * @author makejava
 * @since 2022-01-10 19:56:49
 */
public class AdminsearchorderTable implements Serializable {
    private static final long serialVersionUID = 439776942297058272L;

    private String workOrderNum;

    private String workOrderType;

    private String workOrderName;

    private Integer workerNum;

    private String workOrderState;

    private String expirationTime;

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

    public Integer getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(Integer workerNum) {
        this.workerNum = workerNum;
    }

    public String getWorkOrderState() {
        return workOrderState;
    }

    public void setWorkOrderState(String workOrderState) {
        this.workOrderState = workOrderState;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        this.expirationTime = ft.format(expirationTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

