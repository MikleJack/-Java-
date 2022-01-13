package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (UsedBudget)实体类
 *
 * @author makejava
 * @since 2022-01-13 14:37:28
 */
public class UsedBudget implements Serializable {
    private static final long serialVersionUID = -91631751365456238L;
    
    private Integer depNum;
    
    private Double depUsedBudget;


    public Integer getDepNum() {
        return depNum;
    }

    public void setDepNum(Integer depNum) {
        this.depNum = depNum;
    }

    public Double getDepUsedBudget() {
        return depUsedBudget;
    }

    public void setDepUsedBudget(Double depUsedBudget) {
        this.depUsedBudget = depUsedBudget;
    }

}

