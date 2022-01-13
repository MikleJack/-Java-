package com.example.back2.dao.table;

import com.example.back2.entity.table.FlowProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * (FlowProcess)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-13 09:41:13
 */
@Mapper
public interface FlowProcessDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    FlowProcess queryById(String workOrderNum);

    /**
     * 查询指定行数据
     *
     * @param flowProcess 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<FlowProcess> queryAllByLimit(FlowProcess flowProcess, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param flowProcess 查询条件
     * @return 总行数
     */
    long count(FlowProcess flowProcess);

    /**
     * 新增数据
     *
     * @param flowProcess 实例对象
     * @return 影响行数
     */
    int insert(FlowProcess flowProcess);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FlowProcess> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FlowProcess> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FlowProcess> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<FlowProcess> entities);

    /**
     * 修改数据
     *
     * @param flowProcess 实例对象
     * @return 影响行数
     */
    int update(FlowProcess flowProcess);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 影响行数
     */
    int deleteById(String workOrderNum);

    /**
     * 插入申请延期的流转过程
     *
     * @param DealDate 流转过程日期
     * @param workOrderNum 工单编号
     * @param workerNum 员工编号
     * @return 是否插入流转过程
     */
    Boolean DelayInsert(String workOrderNum, Integer workerNum, Date DealDate);

}

