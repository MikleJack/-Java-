package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (DepRelate)实体类
 *
 * @author makejava
 * @since 2022-01-18 10:31:34
 */
public class DepRelate implements Serializable {
    private static final long serialVersionUID = 923515656910660866L;
    
    private Integer depNumSuperior;
    
    private Integer depNumJunior;


    public Integer getDepNumSuperior() {
        return depNumSuperior;
    }

    public void setDepNumSuperior(Integer depNumSuperior) {
        this.depNumSuperior = depNumSuperior;
    }

    public Integer getDepNumJunior() {
        return depNumJunior;
    }

    public void setDepNumJunior(Integer depNumJunior) {
        this.depNumJunior = depNumJunior;
    }

}

