package com.example.back2.entity.view;

import java.io.Serializable;

/**
 * (AdminsearchorderCom)实体类
 *
 * @author makejava
 * @since 2022-01-11 18:13:47
 */
public class AdminsearchorderCom implements Serializable {
    private static final long serialVersionUID = 854145404765353177L;
    
    private Integer comNum;
    
    private String workOrderNum;
    
    private Integer cpuCore;
    
    private Integer ram;
    
    private Integer storage;
    
    private Integer price;
    
    private Boolean assign;


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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAssign() {
        return assign;
    }

    public void setAssign(Boolean assign) {
        this.assign = assign;
    }

}

