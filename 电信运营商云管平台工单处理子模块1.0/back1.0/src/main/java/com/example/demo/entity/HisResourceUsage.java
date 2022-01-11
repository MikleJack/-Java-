package com.example.demo.entity;

import java.io.Serializable;

/**
 * (HisResourceUsage)实体类
 *
 * @author makejava
 * @since 2022-01-05 21:19:20
 */
public class HisResourceUsage implements Serializable {
    private static final long serialVersionUID = -63265053097018080L;

    private String workOrderNum;

    private double resUtilization;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public double getResUtilization() {
        return resUtilization;
    }

    public void setResUtilization(Double resUtilization) {
        this.resUtilization = resUtilization;
    }

}
