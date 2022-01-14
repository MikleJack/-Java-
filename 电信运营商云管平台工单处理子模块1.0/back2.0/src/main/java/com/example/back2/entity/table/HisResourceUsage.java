package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (HisResourceUsage)实体类
 *
 * @author makejava
 * @since 2022-01-14 15:35:14
 */
public class HisResourceUsage implements Serializable {
    private static final long serialVersionUID = -23421693410377056L;

    private String workOrderNum;

    private Double resUtilization;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public Double getResUtilization() {
        return resUtilization;
    }

    public void setResUtilization(Double resUtilization) {
        this.resUtilization = resUtilization;
    }

}

