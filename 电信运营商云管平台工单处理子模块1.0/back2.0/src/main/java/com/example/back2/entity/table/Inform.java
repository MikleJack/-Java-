package com.example.back2.entity.table;

import java.util.Date;
import java.io.Serializable;

/**
 * (Inform)实体类
 *
 * @author makejava
 * @since 2022-01-18 14:35:14
 */
public class Inform implements Serializable {
    private static final long serialVersionUID = -42618633169135130L;

    private Integer informNum;

    private Integer senderNum;

    private String details;

    private String workOrderNum;

    private Integer recipient;

    private Date sendTime;

    private Boolean readState;


    public Integer getInformNum() {
        return informNum;
    }

    public void setInformNum(Integer informNum) {
        this.informNum = informNum;
    }

    public Integer getSenderNum() {
        return senderNum;
    }

    public void setSenderNum(Integer senderNum) {
        this.senderNum = senderNum;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getReadState() {
        return readState;
    }

    public void setReadState(Boolean readState) {
        this.readState = readState;
    }

}

