package com.example.back2.dao.table;

import com.example.back2.entity.table.WorkOrderChange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (WorkOrderChange)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-12 18:39:16
 */
@Mapper
public interface WorkOrderChangeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrderChange queryById(String workOrderNum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WorkOrderChange> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param workOrderChange 实例对象
     * @return 对象列表
     */
    List<WorkOrderChange> queryAll(WorkOrderChange workOrderChange);

    /**
     * 新增数据
     *
     * @param workOrderChange 实例对象
     * @return 影响行数
     */
    int insert(WorkOrderChange workOrderChange);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrderChange> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WorkOrderChange> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrderChange> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<WorkOrderChange> entities);

    /**
     * 修改数据
     *
     * @param workOrderChange 实例对象
     * @return 影响行数
     */
    int update(WorkOrderChange workOrderChange);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 影响行数
     */
    int deleteById(String workOrderNum);

}

