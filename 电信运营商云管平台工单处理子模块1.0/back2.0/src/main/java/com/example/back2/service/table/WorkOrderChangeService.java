package com.example.back2.service.table;

import com.example.back2.entity.table.WorkOrderChange;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (WorkOrderChange)表服务接口
 *
 * @author makejava
 * @since 2022-01-12 18:39:16
 */
public interface WorkOrderChangeService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrderChange queryById(String workOrderNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WorkOrderChange> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param workOrderChange 实例对象
     * @return 实例对象
     */
    WorkOrderChange insert(WorkOrderChange workOrderChange);

    /**
     * 修改数据
     *
     * @param workOrderChange 实例对象
     * @return 实例对象
     */
    WorkOrderChange update(WorkOrderChange workOrderChange);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);

}
