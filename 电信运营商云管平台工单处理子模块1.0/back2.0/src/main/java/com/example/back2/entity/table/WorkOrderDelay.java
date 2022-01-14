package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (WorkOrderDelay)实体类
 *
 * @author makejava
 * @since 2022-01-14 14:18:37
 */
public class WorkOrderDelay implements Serializable {
    private static final long serialVersionUID = 224809341403750912L;

    private String workOrderNum;

    private String oldOrder;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public String getOldOrder() {
        return oldOrder;
    }

    public void setOldOrder(String oldOrder) {
        this.oldOrder = oldOrder;
    }

}

