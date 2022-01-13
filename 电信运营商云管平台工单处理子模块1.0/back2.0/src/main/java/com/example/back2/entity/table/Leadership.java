package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (Leadership)实体类
 *
 * @author makejava
 * @since 2022-01-13 16:05:43
 */
public class Leadership implements Serializable {
    private static final long serialVersionUID = 143278257322401861L;
    
    private Integer lederNum;
    
    private Integer underNum;


    public Integer getLederNum() {
        return lederNum;
    }

    public void setLederNum(Integer lederNum) {
        this.lederNum = lederNum;
    }

    public Integer getUnderNum() {
        return underNum;
    }

    public void setUnderNum(Integer underNum) {
        this.underNum = underNum;
    }

}

