package com.example.back2.dao.table;

import com.example.back2.entity.table.WorkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (WorkOrder)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 20:33:15
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
     * @param workOrder 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<WorkOrder> queryAllByLimit(WorkOrder workOrder, @Param("pageable") Pageable pageable);

    /**
     * 无条件的分页查询
     *
     * @param workerNum 员工编号
     * @param pageable 分页请求
     * @return 用户的全部工单
     */
    List<WorkOrder> criteriaQueryAllByLimit(@Param("workerNum") Integer workerNum, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param workOrder 查询条件
     * @return 总行数
     */
    long count(WorkOrder workOrder);
    long criteriaCount(Integer workerNum);

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

}

