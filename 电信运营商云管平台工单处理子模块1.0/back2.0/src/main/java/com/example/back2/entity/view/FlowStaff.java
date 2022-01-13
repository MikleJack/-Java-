package com.example.back2.entity.view;

import java.util.Date;
import java.io.Serializable;

/**
 * (FlowStaff)实体类
 *
 * @author makejava
 * @since 2022-01-13 19:24:10
 */
public class FlowStaff implements Serializable {
    private static final long serialVersionUID = 370555806334821675L;

    private String name;

    private String workOrderNum;

    private Integer dealNum;

    private String operationType;

    private Date dealDate;

    private String dealComment;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
