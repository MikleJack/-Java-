package com.example.back2.dao.table;

import com.example.back2.entity.table.WorkOrderDelay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (WorkOrderDelay)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-14 14:18:37
 */
@Mapper
public interface WorkOrderDelayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrderDelay queryById(String workOrderNum);

    /**
     * 查询指定行数据
     *
     * @param workOrderDelay 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<WorkOrderDelay> queryAllByLimit(WorkOrderDelay workOrderDelay, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param workOrderDelay 查询条件
     * @return 总行数
     */
    long count(WorkOrderDelay workOrderDelay);

    /**
     * 新增数据
     *
     * @param workOrderDelay 实例对象
     * @return 影响行数
     */
    int insert(WorkOrderDelay workOrderDelay);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrderDelay> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WorkOrderDelay> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrderDelay> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WorkOrderDelay> entities);

    /**
     * 修改数据
     *
     * @param workOrderDelay 实例对象
     * @return 影响行数
     */
    int update(WorkOrderDelay workOrderDelay);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 影响行数
     */
    int deleteById(String workOrderNum);

}

