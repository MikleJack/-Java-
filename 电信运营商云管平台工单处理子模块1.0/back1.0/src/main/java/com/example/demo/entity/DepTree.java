package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (DepTree)实体类
 *
 * @author makejava
 * @since 2022-01-05 15:20:46
 */
public class DepTree implements Serializable {
    private static final long serialVersionUID = 886646191153559672L;
    
    private String depNum;
    
    private String depName;
    
    private Integer depLevel;
    
    private String depNumSuperior;
    
    private String depNumJunior;

    private List<DepTree> childList = new ArrayList<DepTree>();


    public String getDepNum() {
        return depNum;
    }

    public void setDepNum(String depNum) {
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

    public String getDepNumSuperior() {
        return depNumSuperior;
    }

    public void setDepNumSuperior(String depNumSuperior) {
        this.depNumSuperior = depNumSuperior;
    }

    public String getDepNumJunior() {
        return depNumJunior;
    }

    public void setDepNumJunior(String depNumJunior) {
        this.depNumJunior = depNumJunior;
    }

    public void setChildList(ArrayList<DepTree> childList) {
        this.childList = childList;
    }

    public List<DepTree> getChildList() {
        return this.childList;
    }
}

