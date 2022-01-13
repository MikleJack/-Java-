package com.example.back2.entity.view;

import java.io.Serializable;

/**
 * (AllocatedVmSpecifications)实体类
 *
 * @author makejava
 * @since 2022-01-13 19:52:29
 */
public class AllocatedVmSpecifications implements Serializable {
    private static final long serialVersionUID = -73170068836305605L;

    private Integer comNum;

    private String workOrderNum;

    private Integer cpuCore;

    private Integer ram;

    private String processorModel;

    private Double processorFrequency;

    private Double price;

    private Double diskPrice;

    private String description;

    private Integer quantity;

    private String os;

    private String ip;

    private Integer storage;


    public Integer getComNum() {
        return comNum;
    }

    public void setComNum(Integer comNum) {
        this.comNum = comNum;
    }

    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiskPrice() {
        return diskPrice;
    }

    public void setDiskPrice(Double diskPrice) {
        this.diskPrice = diskPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

}

