package com.example.back2.entity.view;

import java.io.Serializable;

/**
 * (Adminorganmanage)实体类
 *
 * @author makejava
 * @since 2022-01-17 20:23:06
 */
public class Adminorganmanage implements Serializable {
    private static final long serialVersionUID = 637269330936535973L;
    
    private Integer depNum;
    
    private String depName;
    
    private Integer depLevel;
    
    private Integer depNumJunior;
    
    private String lowName;
    
    private Integer lowNum;
    
    private Integer lowLevel;


    public Integer getDepNum() {
        return depNum;
    }

    public void setDepNum(Integer depNum) {
        this.depNum = depNum;
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

    public Integer getDepNumJunior() {
        return depNumJunior;
    }

    public void setDepNumJunior(Integer depNumJunior) {
        this.depNumJunior = depNumJunior;
    }

    public String getLowName() {
        return lowName;
    }

    public void setLowName(String lowName) {
        this.lowName = lowName;
    }

    public Integer getLowNum() {
        return lowNum;
    }

    public void setLowNum(Integer lowNum) {
        this.lowNum = lowNum;
    }

    public Integer getLowLevel() {
        return lowLevel;
    }

    public void setLowLevel(Integer lowLevel) {
        this.lowLevel = lowLevel;
    }

}

