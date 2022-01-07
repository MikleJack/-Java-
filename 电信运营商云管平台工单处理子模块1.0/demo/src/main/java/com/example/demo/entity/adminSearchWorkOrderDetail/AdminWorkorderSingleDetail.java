package com.example.demo.entity.adminSearchWorkOrderDetail;

import java.io.Serializable;
import java.util.Date;

/**
 * (AdminWorkorderSingleDetail)实体类
 *
 * @author makejava
 * @since 2022-01-07 16:04:41
 */
public class AdminWorkorderSingleDetail implements Serializable {
    private static final long serialVersionUID = 774490151332663653L;

    private String workOrderNum;

    private String workOrderType;

    private String workOrderName;

    private Date applyTime;

    private String file;

    private String workOrderState;

    private String reason;

    private String changedOldOrder;

    private String rollbackOldOrder;

    private Integer workerNum;

    private String name;

    private String depNum;

    private String phone;

    private Boolean state;

    private Boolean inService;

    private String depName;

    private Integer depLevel;


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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public String getChangedOldOrder() {
        return changedOldOrder;
    }

    public void setChangedOldOrder(String changedOldOrder) {
        this.changedOldOrder = changedOldOrder;
    }

    public String getRollbackOldOrder() {
        return rollbackOldOrder;
    }

    public void setRollbackOldOrder(String rollbackOldOrder) {
        this.rollbackOldOrder = rollbackOldOrder;
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

}

