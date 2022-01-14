package com.example.back2.service.table;

import com.example.back2.entity.table.AllocatedCom;

import java.util.List;

/**
 * (AllocatedCom)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 19:33:19
 */
public interface AllocatedComService {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    AllocatedCom queryById(Integer comNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AllocatedCom> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param allocatedCom 实例对象
     * @return 实例对象
     */
    AllocatedCom insert(AllocatedCom allocatedCom);

    /**
     * 修改数据
     *
     * @param allocatedCom 实例对象
     * @return 实例对象
     */
    AllocatedCom update(AllocatedCom allocatedCom);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer comNum);

    /**
     * 通过工单编号查询该工单所有物理机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有物理机资源
     */
    List<AllocatedCom> queryByWorkOrderNum(String workOrderNum);


}
