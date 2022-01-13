package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (AllocatedVm)实体类
 *
 * @author makejava
 * @since 2022-01-11 19:44:49
 */
public class AllocatedVm implements Serializable {
    private static final long serialVersionUID = 478707250218245776L;

    private Integer account_virtual;

    private Double diskPrice;

    private String description;

    private String processorFrequency;

    private String processorModel;

    private Integer row_index;

    private Integer comNum;

    private String workOrderNum;

    private Integer cpuCore;

    private Integer ram;

    private Integer storage;

    private String ip;

    private String os;

    private Double price;

    private Integer quantity;

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public Integer getQuantity(){
        return this.quantity;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
