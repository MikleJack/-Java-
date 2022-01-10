package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Perworkerresrate)实体类
 *
 * @author makejava
 * @since 2022-01-05 10:59:07
 */
public class PerWorkerResRate implements Serializable {
    private static final long serialVersionUID = -51684232900742776L;

    private String workNum;

    private String name;

    private Integer ram;

    private Integer storage;

    private String ip;

    private String os;

    private String workOrderNum;

    private String comNum;

    private String comType;

    private Integer cpuCore;

    private String workOrderName;

    private String workOrderType;

    private Date applyTime;

    private Float resUtilization;


    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public String getComNum() {
        return comNum;
    }

    public void setComNum(String comNum) {
        this.comNum = comNum;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public Integer getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(Integer cpuCore) {
        this.cpuCore = cpuCore;
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

    public Float getResUtilization() {
        return resUtilization;
    }

    public void setResUtilization(Float resUtilization) {
        this.resUtilization = resUtilization;
    }

}

