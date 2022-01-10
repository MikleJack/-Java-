package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (AdminWorkOrderInform)实体类
 *
 * @author makejava
 * @since 2022-01-07 10:47:48
 */
public class AdminWorkOrderInform implements Serializable {
    private static final long serialVersionUID = -13598274706398266L;

    private String rollbackOldOrder;

    private String changedOldOrder;

    private String workOrderNum;

    private String workOrderName;

    private String workOrderType;

    private Date applyTime;

    private Integer workerNum;

    private String file;

    private String workOrderState;

    private String reason;

    private String name;

    private String depNum;

    private String phone;

    private Boolean state;

    private Boolean inService;


    public String getRollbackOldOrder() {
        return rollbackOldOrder;
    }

    public void setRollbackOldOrder(String rollbackOldOrder) {
        this.rollbackOldOrder = rollbackOldOrder;
    }

    public String getChangedOldOrder() {
        return changedOldOrder;
    }

    public void setChangedOldOrder(String changedOldOrder) {
        this.changedOldOrder = changedOldOrder;
    }

    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public String getWorkOrderName() {
        return workOrderName;
    }

    public void setWorkOrderName(String workOrderName) {
        this.workOrderName = workOrderName;
    }

    public String getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(String workOrderType) {
        this.workOrderType = workOrderType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

}

