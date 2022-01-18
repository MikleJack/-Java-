package com.example.back2.entity.table;

import java.io.Serializable;
import java.util.List;

/**
 * (Department)实体类
 *
 * @author makejava
 * @since 2022-01-11 15:19:28
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 116660775774922741L;

    private String id;

    private String label;

    private Integer depNum;
    
    private String depName;
    
    private Integer depLevel;
    
    private Double depBudget;

    private List<Department> children;

    public void setId(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setChildren(List<Department> children){
        this.children=children;
    }

    public List<Department> getChildren(){
        return this.children;
    }


    public Integer getDepNum() {
        return depNum;
    }

    public void setDepNum(Integer depNum) {
        setId(depNum.toString());
        this.depNum = depNum;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        setLabel(depName);
        this.depName = depName;
    }

    public Integer getDepLevel() {
        return depLevel;
    }

    public void setDepLevel(Integer depLevel) {
        this.depLevel = depLevel;
    }

    public Double getDepBudget() {
        return depBudget;
    }

    public void setDepBudget(Double depBudget) {
        this.depBudget = depBudget;
    }

}

