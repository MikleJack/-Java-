package com.example.demo.entity.leader;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * (LeaderOrderDetail)实体类
 *
 * @author makejava
 * @since 2022-01-08 11:08:04
 */
public class LeaderOrderDetail implements Serializable {
    private static final long serialVersionUID = -78723730326225924L;
    
    private String workOrderNum;
    
    private String workOrderType;
    
    private String workOrderName;

    private String applyTime;
    
    private Integer workerNum;
    
    private String name;
    
    private String workOrderState;
    
    private Integer lederNum;


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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

        this.applyTime = ft.format(applyTime);
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

    public String getWorkOrderState() {
        return workOrderState;
    }

    public void setWorkOrderState(String workOrderState) {
        this.workOrderState = workOrderState;
    }

    public Integer getLederNum() {
        return lederNum;
    }

    public void setLederNum(Integer lederNum) {
        this.lederNum = lederNum;
    }

}

