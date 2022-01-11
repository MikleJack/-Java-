package com.example.demo.service;

import com.example.demo.entity.HisResourceUsage;

import java.util.List;

/**
 * (HisResourceUsage)表服务接口
 *
 * @author makejava
 * @since 2022-01-05 21:19:22
 */
public interface HisResourceUsageService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    HisResourceUsage queryById(String workOrderNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HisResourceUsage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param hisResourceUsage 实例对象
     * @return 实例对象
     */
    HisResourceUsage insert(HisResourceUsage hisResourceUsage);

    /**
     * 修改数据
     *
     * @param hisResourceUsage 实例对象
     * @return 实例对象
     */
    HisResourceUsage update(HisResourceUsage hisResourceUsage);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);

}
