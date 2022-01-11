package com.example.back2.service.table;

import com.example.back2.entity.table.AllocatedVm;

import java.util.List;

/**
 * (AllocatedVm)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 19:44:49
 */
public interface AllocatedVmService {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    AllocatedVm queryById(Integer comNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AllocatedVm> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param allocatedVm 实例对象
     * @return 实例对象
     */
    AllocatedVm insert(AllocatedVm allocatedVm);

    /**
     * 修改数据
     *
     * @param allocatedVm 实例对象
     * @return 实例对象
     */
    AllocatedVm update(AllocatedVm allocatedVm);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer comNum);

}
