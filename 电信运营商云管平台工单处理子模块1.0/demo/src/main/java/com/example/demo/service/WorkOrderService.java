package com.example.demo.service;

import com.example.demo.entity.WorkOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (WorkOrder)表服务接口
 *
 * @author makejava
 * @since 2022-01-04 20:57:42
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
     * 分页查询
     *
     * @param workOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<WorkOrder> queryByPage(WorkOrder workOrder, PageRequest pageRequest);

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

    /**
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    List<WorkOrder> queryAll();

}
