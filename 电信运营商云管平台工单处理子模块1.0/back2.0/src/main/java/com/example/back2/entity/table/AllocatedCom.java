package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (AllocatedCom)实体类
 *
 * @author makejava
 * @since 2022-01-11 19:33:19
 */
public class AllocatedCom implements Serializable {
    private static final long serialVersionUID = 686874999876906148L;

    private Integer comNum;

    private String workOrderNum;

    private Integer cpuCore;

    private Integer ram;

    private Integer storage;

    private Double price;


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
