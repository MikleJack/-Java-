package com.example.back2.entity.table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * (WorkOrder)实体类
 *
 * @author makejava
 * @since 2022-01-11 15:23:50
 */
public class WorkOrder implements Serializable {
    private static final long serialVersionUID = 558641441992181263L;

    private String workOrderNum;

    private String workOrderType;

    private String workOrderName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date expirationTime;

    private Integer workerNum;

    private String file;

    private String workOrderState;

    private String reason;

    private Double price;

    private String offlineReason;


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
//        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//        this.expirationTime = ft.format(expirationTime);
        this.expirationTime = expirationTime;
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
