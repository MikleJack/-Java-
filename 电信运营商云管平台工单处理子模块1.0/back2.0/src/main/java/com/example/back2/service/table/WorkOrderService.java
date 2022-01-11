package com.example.back2.service.table;

import com.example.back2.entity.table.WorkOrder;

import java.util.List;

/**
 * (WorkOrder)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 15:23:50
 */
public interface WorkOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrder queryById(String workOrderNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WorkOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param workOrder 实例对象
     * @return 实例对象
     */
    WorkOrder insert(WorkOrder workOrder);

    /**
     * 修改数据
     *
     * @param workOrder 实例对象
     * @return 实例对象
     */
    WorkOrder update(WorkOrder workOrder);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);

}
