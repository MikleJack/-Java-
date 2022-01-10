package com.example.demo.dao;

import com.example.demo.entity.FlowProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (FlowProcess)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-06 20:28:29
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
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FlowProcess> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param flowProcess 实例对象
     * @return 对象列表
     */
    List<FlowProcess> queryAll(FlowProcess flowProcess);

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

//    通过工号查询工单
    List<FlowProcess> selectOrderNumByWorkNum(Integer WorkNum,int page,int size);
// 通过工号查询操作过的工单
    List<FlowProcess> selectByWorkNum(Integer WorkNum);

    /**
     * 管理员工单查询界面，详情按钮
     * 通过工单编号查询该工单的所有流转过程
     *
     * @param workOrderNum 主键
     * @return 该工单的所有流转过程
     */
    List<FlowProcess> queryByWorkOrderNum(String workOrderNum);

}

