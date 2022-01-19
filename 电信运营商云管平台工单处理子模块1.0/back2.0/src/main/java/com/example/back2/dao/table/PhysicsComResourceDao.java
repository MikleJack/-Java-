package com.example.back2.dao.table;

import com.example.back2.entity.table.PhysicsComResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (PhysicsComResource)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 14:23:56
 */
@Mapper
public interface PhysicsComResourceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    PhysicsComResource queryById(Integer comNum);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<PhysicsComResource> queryAllByLimit(@Param("pageable") Pageable pageable);

    long count();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param physicsComResource 实例对象
     * @return 对象列表
     */
    List<PhysicsComResource> queryAll(PhysicsComResource physicsComResource);

    /**
     * 新增数据
     *
     * @param physicsComResource 实例对象
     * @return 影响行数
     */
    int insert(PhysicsComResource physicsComResource);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PhysicsComResource> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PhysicsComResource> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PhysicsComResource> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<PhysicsComResource> entities);

    /**
     * 修改数据
     *
     * @param physicsComResource 实例对象
     * @return 影响行数
     */
    int update(PhysicsComResource physicsComResource);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 影响行数
     */
    int deleteById(Integer comNum);


    List<PhysicsComResource> selectAllpc();

    /**
     * 改变物理机分配状态
     *
     * @param comNum  即将下线的物理机编号
     * @param assign  选择分配状态
     * @return 是否修改成功
     */
    boolean setComAssign(Integer comNum,boolean assign);


    boolean insertPhysics(int cpuCore, int ram, int storage, int price);

}

