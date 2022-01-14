package com.example.back2.entity;

import java.io.Serializable;

/**
 * (VirtualComResource)实体类
 *
 * @author makejava
 * @since 2022-01-14 11:33:07
 */
public class VirtualComResource implements Serializable {
    private static final long serialVersionUID = 203271616493302296L;

    private Integer cpuCore;

    private Integer ram;

    private Integer storage;


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

}

