package com.example.back2.entity.view;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (InformSenderRecipentName)实体类
 *
 * @author makejava
 * @since 2022-01-19 09:50:54
 */
public class InformSenderRecipentName implements Serializable {
    private static final long serialVersionUID = 177164279676444581L;

    private Integer informNum;

    private Integer senderNum;

    private String details;

    private String workOrderNum;

    private Integer recipient;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sendTime;

    private Boolean readState;

    private String senderName;

    private String recipentName;


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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipentName() {
        return recipentName;
    }

    public void setRecipentName(String recipentName) {
        this.recipentName = recipentName;
    }

}

