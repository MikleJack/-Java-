package com.example.back2.service.view;

import com.example.back2.entity.view.FlowStaff;

import java.util.List;

/**
 * (FlowStaff)表服务接口
 *
 * @author makejava
 * @since 2022-01-13 19:24:10
 */
public interface FlowStaffService {


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FlowStaff> queryAllByLimit(int offset, int limit);

//通过工单号查找所有的流转过程
    List<FlowStaff> selectByWorkOrderNum(String workOrderNum);

}
