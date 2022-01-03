package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (OperationLog)实体类
 *
 * @author makejava
 * @since 2022-01-02 20:19:26
 */
public class OperationLog implements Serializable {
    private static final long serialVersionUID = -83964879838195902L;
    
    private String workNum;
    
    private Date operateTime;
    
    private String operate;


    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

}

