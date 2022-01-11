package com.example.back2.entity;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * (Leaderworkorderall)实体类
 *
 * @author makejava
 * @since 2022-01-10 20:56:41
 */
public class Leaderworkorderall implements Serializable {
    private static final long serialVersionUID = 178610334239150040L;

    private String name;

    private String workOrderNum;

    private String workOrderType;

    private String expirationTime;

    private String workOrderName;

    private Integer workerNum;

    private String file;

    private String workOrderState;

    private String reason;

    private Double price;

    private String offlineReason;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.expirationTime = ft.format(expirationTime);
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getWorkOrderState() {
        return workOrderState;
    }

    public void setWorkOrderState(String workOrderState) {
        this.workOrderState = workOrderState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOfflineReason() {
        return offlineReason;
    }

    public void setOfflineReason(String offlineReason) {
        this.offlineReason = offlineReason;
    }

}
