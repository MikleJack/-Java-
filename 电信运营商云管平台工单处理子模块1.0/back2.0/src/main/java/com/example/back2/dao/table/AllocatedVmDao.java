package com.example.back2.dao.table;

import com.example.back2.entity.table.AllocatedVm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (AllocatedVm)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 19:44:49
 */
@Mapper
public interface AllocatedVmDao {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    AllocatedVm queryById(Integer comNum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AllocatedVm> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param allocatedVm 实例对象
     * @return 对象列表
     */
    List<AllocatedVm> queryAll(AllocatedVm allocatedVm);

    /**
     * 新增数据
     *
     * @param allocatedVm 实例对象
     * @return 影响行数
     */
    int insert(AllocatedVm allocatedVm);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AllocatedVm> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AllocatedVm> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AllocatedVm> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AllocatedVm> entities);

    /**
     * 修改数据
     *
     * @param allocatedVm 实例对象
     * @return 影响行数
     */
    int update(AllocatedVm allocatedVm);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 影响行数
     */
    int deleteById(Integer comNum);

}

