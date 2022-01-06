package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (FlowProcess)实体类
 *
 * @author makejava
 * @since 2022-01-06 20:28:28
 */
public class FlowProcess implements Serializable {
    private static final long serialVersionUID = 619303572320817628L;

    private String workOrderNum;

    private Integer dealNum;

    private String operationType;

    private Date dealDate;

    private String dealComment;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealComment() {
        return dealComment;
    }

    public void setDealComment(String dealComment) {
        this.dealComment = dealComment;
    }

}
