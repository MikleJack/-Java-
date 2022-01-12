package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (PhysicsComResource)实体类
 *
 * @author makejava
 * @since 2022-01-11 14:23:56
 */
public class PhysicsComResource implements Serializable {
    private static final long serialVersionUID = -26351837738401331L;

    private Integer comNum;

    private Integer row_index;

    private Integer cpuCore;

    private Integer ram;

    private Integer storage;

    private Boolean assign;

    private Integer price;


    public void setRow_index(Integer row_index){
        this.row_index = row_index;
    }

    public Integer getRow_index(){
        return this.row_index;
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

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Boolean getAssign() {
        return assign;
    }

    public void setAssign(Boolean assign) {
        this.assign = assign;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
