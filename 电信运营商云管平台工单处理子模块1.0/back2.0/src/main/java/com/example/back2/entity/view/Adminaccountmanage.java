package com.example.back2.entity.view;

import java.io.Serializable;

/**
 * (Adminaccountmanage)实体类
 *
 * @author makejava
 * @since 2022-01-11 10:09:09
 */
public class Adminaccountmanage implements Serializable {
    private static final long serialVersionUID = -25989897406812579L;
    
    private Integer workNum;
    
    private String name;
    
    private String phone;
    
    private Boolean state;
    
    private String depName;
    
    private Integer depLevel;

    private String state_string;

    private Boolean inService;

    private String service_string;

    public Boolean getInService() {
        return inService;
    }

    public void setInService(Boolean inService) {
        this.inService = inService;
        if(inService)
            this.service_string="未离职";
        else
            this.service_string="已离职";
    }

    public String getService_string() {
        return service_string;
    }

    public void setService_string(String service_string) {
        this.service_string = service_string;
    }

    public String getState_string(){
        return state_string;
    }


    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
        if(this.state)
            this.state_string = "未锁定";
        else
            this.state_string = "已锁定";
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getDepLevel() {
        return depLevel;
    }

    public void setDepLevel(Integer depLevel) {
        this.depLevel = depLevel;
    }

}

