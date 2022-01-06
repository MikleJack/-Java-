package com.example.demo.dao;

import com.example.demo.entity.WorkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (WorkOrder)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-04 20:57:36
 */
@Mapper
public interface WorkOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrder queryById(String workOrderNum);

    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<WorkOrder> queryAllByLimit(@Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     * @return 总行数
     */
    long count();

    /**
     * 新增数据
     *
     * @param workOrder 实例对象
     * @return 影响行数
     */
    int insert(WorkOrder workOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WorkOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WorkOrder> entities);

    /**
     * 修改数据
     *
     * @param workOrder 实例对象
     * @return 影响行数
     */
    int update(WorkOrder workOrder);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 影响行数
     */
    int deleteById(String workOrderNum);

    /**
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    List<WorkOrder> queryAll();


    List<WorkOrder> selectByWorkNum(String WorkNum);

    List<WorkOrder> selectByWorkOrderType(String WorkOrderType);

    List<WorkOrder> selectByWorkOrderReason(String WorkOrderReason);
}

