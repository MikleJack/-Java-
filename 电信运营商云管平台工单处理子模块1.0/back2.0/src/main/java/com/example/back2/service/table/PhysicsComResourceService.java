package com.example.back2.service.table;

import com.example.back2.entity.table.PhysicsComResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.Future;

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
     * @return 对象列表
     */
    Future<Page<PhysicsComResource>> queryAllByLimit(PageRequest pageRequest);

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

    boolean insertPhysics(int cpuCore, int ram, int storage, int price);

}
