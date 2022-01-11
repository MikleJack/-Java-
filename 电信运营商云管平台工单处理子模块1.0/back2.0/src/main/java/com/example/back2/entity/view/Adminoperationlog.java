package com.example.back2.entity.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

/**
 * (Adminoperationlog)实体类
 *
 * @author makejava
 * @since 2022-01-11 09:42:32
 */
public class Adminoperationlog implements Serializable {
    private static final long serialVersionUID = 395996043303671170L;
    
    private Integer workNum;

    private String operateTime;
    
    private String operate;
    
    private String ip;
    
    private String address;
    
    private String name;


    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        this.operateTime = ft.format(operateTime);
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

