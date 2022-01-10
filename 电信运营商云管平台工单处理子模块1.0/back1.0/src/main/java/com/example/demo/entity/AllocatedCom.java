package com.example.demo.entity;

import java.io.Serializable;

/**
 * (AllocatedCom)实体类
 *
 * @author makejava
 * @since 2022-01-07 16:58:45
 */
public class AllocatedCom implements Serializable {
    private static final long serialVersionUID = -54828863710258847L;

    private String comNum;

    private String workOrderNum;

    private String comType;

    private Integer cpuCore;

    private Integer ram;

    private Integer storage;

    private String ip;

    private String os;


    public String getComNum() {
        return comNum;
    }

    public void setComNum(String comNum) {
        this.comNum = comNum;
    }

    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
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

}

