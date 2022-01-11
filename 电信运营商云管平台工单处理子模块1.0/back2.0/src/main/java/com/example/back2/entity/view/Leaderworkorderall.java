package com.example.back2.entity.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * (Leaderworkorderall)实体类
 *
 * @author makejava
 * @since 2022-01-11 11:28:34
 */
public class Leaderworkorderall implements Serializable {
    private static final long serialVersionUID = -25455824979785705L;
    
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
    
    private Integer depNum;
    
    private String phone;
    
    private Boolean state;
    
    private Boolean inService;
    
    private String firstLeaderName;
    
    private Integer firstLeaderNum;
    
    private Integer secondLeaderNum;
    
    private String secondLeaderName;
    
    private String depName;
    
    private Integer depBudget;
    
    private Integer depLevel;


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

    public String  getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
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

    public Integer getDepNum() {
        return depNum;
    }

    public void setDepNum(Integer depNum) {
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

    public String getFirstLeaderName() {
        return firstLeaderName;
    }

    public void setFirstLeaderName(String firstLeaderName) {
        this.firstLeaderName = firstLeaderName;
    }

    public Integer getFirstLeaderNum() {
        return firstLeaderNum;
    }

    public void setFirstLeaderNum(Integer firstLeaderNum) {
        this.firstLeaderNum = firstLeaderNum;
    }

    public Integer getSecondLeaderNum() {
        return secondLeaderNum;
    }

    public void setSecondLeaderNum(Integer secondLeaderNum) {
        this.secondLeaderNum = secondLeaderNum;
    }

    public String getSecondLeaderName() {
        return secondLeaderName;
    }

    public void setSecondLeaderName(String secondLeaderName) {
        this.secondLeaderName = secondLeaderName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getDepBudget() {
        return depBudget;
    }

    public void setDepBudget(Integer depBudget) {
        this.depBudget = depBudget;
    }

    public Integer getDepLevel() {
        return depLevel;
    }

    public void setDepLevel(Integer depLevel) {
        this.depLevel = depLevel;
    }

}

