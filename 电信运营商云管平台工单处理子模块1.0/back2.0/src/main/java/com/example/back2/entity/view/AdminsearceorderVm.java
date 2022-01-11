package com.example.back2.entity.view;

import java.io.Serializable;

/**
 * (AdminsearceorderVm)实体类
 *
 * @author makejava
 * @since 2022-01-11 18:53:15
 */
public class AdminsearceorderVm implements Serializable {
    private static final long serialVersionUID = -26550463565499331L;
    
    private String workOrderNum;
    
    private Integer comNum;
    
    private Integer cpuCore;
    
    private Integer ram;
    
    private String processorModel;
    
    private Double processorFrequency;
    
    private Double allprice;
    
    private Double perprice;
    
    private String ip;
    
    private String os;
    
    private Integer storage;

    private String description;

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public Integer getComNum() {
        return comNum;
    }

    public void setComNum(Integer comNum) {
        this.comNum = comNum;
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

    public String getProcessorModel() {
        return processorModel;
    }

    public void setProcessorModel(String processorModel) {
        this.processorModel = processorModel;
    }

    public Double getProcessorFrequency() {
        return processorFrequency;
    }

    public void setProcessorFrequency(Double processorFrequency) {
        this.processorFrequency = processorFrequency;
    }

    public Double getAllprice() {
        return allprice;
    }

    public void setAllprice(Double allprice) {
        this.allprice = allprice;
    }

    public Double getPerprice() {
        return perprice;
    }

    public void setPerprice(Double perprice) {
        this.perprice = perprice;
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

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

}

