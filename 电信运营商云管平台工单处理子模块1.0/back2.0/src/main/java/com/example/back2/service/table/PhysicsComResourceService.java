package com.example.back2.service.table;

import com.example.back2.entity.table.PhysicsComResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (PhysicsComResource)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 14:23:56
 */
public interface PhysicsComResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    PhysicsComResource queryById(Integer comNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PhysicsComResource> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param physicsComResource 实例对象
     * @return 实例对象
     */
    PhysicsComResource insert(PhysicsComResource physicsComResource);

    /**
     * 修改数据
     *
     * @param physicsComResource 实例对象
     * @return 实例对象
     */
    PhysicsComResource update(PhysicsComResource physicsComResource);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer comNum);


    List<PhysicsComResource> selectAllpc();

    /**
     * 改变物理机分配状态
     *
     * @param comNum  即将下线的物理机编号
     * @param assign  选择分配状态
     * @return 是否修改成功
     */
    boolean setComAssign(List<Integer> comNum,boolean assign);

}
