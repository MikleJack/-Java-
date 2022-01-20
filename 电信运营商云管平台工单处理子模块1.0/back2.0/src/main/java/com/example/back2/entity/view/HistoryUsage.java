package com.example.back2.entity.view;

import java.io.Serializable;

/**
 * (HistoryUsage)实体类
 *
 * @author makejava
 * @since 2022-01-20 16:46:37
 */
public class HistoryUsage implements Serializable {
    private static final long serialVersionUID = 914806872035176154L;

    private String workOrderNum;

    private Integer workerNum;

    private Double resUtilization;

    private Double price;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public Integer getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(Integer workerNum) {
        this.workerNum = workerNum;
    }

    public Double getResUtilization() {
        return resUtilization;
    }

    public void setResUtilization(Double resUtilization) {
        this.resUtilization = resUtilization;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}

