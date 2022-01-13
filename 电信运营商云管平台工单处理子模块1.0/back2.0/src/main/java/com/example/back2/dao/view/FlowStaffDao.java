package com.example.back2.dao.view;

import com.example.back2.entity.view.FlowStaff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (FlowStaff)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-13 19:24:10
 */

@Mapper
public interface FlowStaffDao {


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FlowStaff> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param flowStaff 实例对象
     * @return 对象列表
     */
    List<FlowStaff> queryAll(FlowStaff flowStaff);



//    通过工单号查找相应的流转过程
    List<FlowStaff> selectByWorkOrderNum(String workOrderNum);

}

