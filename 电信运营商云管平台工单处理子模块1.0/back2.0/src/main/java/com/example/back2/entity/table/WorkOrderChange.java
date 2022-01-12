package com.example.back2.entity.table;

import java.io.Serializable;

/**
 * (WorkOrderChange)实体类
 *
 * @author makejava
 * @since 2022-01-12 18:39:16
 */
public class WorkOrderChange implements Serializable {
    private static final long serialVersionUID = -30748957202056771L;

    private String workOrderNum;

    private String changedOldOrder;


    public String getWorkOrderNum() {
        return workOrderNum;
    }

    public void setWorkOrderNum(String workOrderNum) {
        this.workOrderNum = workOrderNum;
    }

    public String getChangedOldOrder() {
        return changedOldOrder;
    }

    public void setChangedOldOrder(String changedOldOrder) {
        this.changedOldOrder = changedOldOrder;
    }

}
