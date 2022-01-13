package com.example.back2.entity.view;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderBeginEndTime)实体类
 *
 * @author makejava
 * @since 2022-01-12 18:34:29
 */
public class OrderBeginEndTime implements Serializable {
    private static final long serialVersionUID = -45006107197653240L;

    private String workOrderNum;

    private Date dealDate;

    private Date expirationTime;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

}

