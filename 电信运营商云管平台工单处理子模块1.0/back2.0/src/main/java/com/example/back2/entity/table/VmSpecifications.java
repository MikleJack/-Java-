package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (VmSpecifications)实体类
 *
 * @author makejava
 * @since 2022-01-11 14:35:54
 */
public class VmSpecifications implements Serializable {
    private static final long serialVersionUID = 619054058014818672L;

    private Integer comNum;

    private Integer cpuCore;

    private Integer ram;

    private String processorModel;

    private Double processorFrequency;

    private Double price;

    private Double diskPrice;

    private String description;


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

}
