package com.example.back2.entity.table;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (OperationLog)实体类
 *
 * @author makejava
 * @since 2022-01-03 10:27:45
 */
public class OperationLog implements Serializable {
    private static final long serialVersionUID = -48180332884626691L;

    private Integer work_num;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operate_time;

    private String operate;

    private String ip;

    private String address;


    public Integer getWork_num() {
        return work_num;
    }

    public void setWork_num(Integer work_num) {
        this.work_num = work_num;
    }

    public Date getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
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

}

