package com.example.demo.service;

import com.example.demo.entity.FlowProcess;

import java.util.List;

/**
 * (FlowProcess)表服务接口
 *
 * @author makejava
 * @since 2022-01-06 20:28:30
 */
public interface FlowProcessService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    FlowProcess queryById(String workOrderNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FlowProcess> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param flowProcess 实例对象
     * @return 实例对象
     */
    FlowProcess insert(FlowProcess flowProcess);

    /**
     * 修改数据
     *
     * @param flowProcess 实例对象
     * @return 实例对象
     */
    FlowProcess update(FlowProcess flowProcess);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);

//    通过工号查询操作过的工单
    List<FlowProcess> selectOrderNumByWorkNum(Integer WorkNum,int page,int size);

//    通过工号查询记录
    List<FlowProcess> selectByWorkNum(Integer WorkNum);
}
