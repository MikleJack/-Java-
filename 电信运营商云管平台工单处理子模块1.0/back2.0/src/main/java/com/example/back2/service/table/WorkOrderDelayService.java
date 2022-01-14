package com.example.back2.service.table;

import com.example.back2.entity.table.WorkOrderDelay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (WorkOrderDelay)表服务接口
 *
 * @author makejava
 * @since 2022-01-14 14:18:37
 */
public interface WorkOrderDelayService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrderDelay queryById(String workOrderNum);

    /**
     * 分页查询
     *
     * @param workOrderDelay 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<WorkOrderDelay> queryByPage(WorkOrderDelay workOrderDelay, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param workOrderDelay 实例对象
     * @return 实例对象
     */
    WorkOrderDelay insert(WorkOrderDelay workOrderDelay);

    /**
     * 修改数据
     *
     * @param workOrderDelay 实例对象
     * @return 实例对象
     */
    WorkOrderDelay update(WorkOrderDelay workOrderDelay);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);

}
