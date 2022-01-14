package com.example.back2.dao.table;

import com.example.back2.entity.table.AllocatedCom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (AllocatedCom)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 19:33:19
 */
@Mapper
public interface AllocatedComDao {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    AllocatedCom queryById(Integer comNum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AllocatedCom> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param allocatedCom 实例对象
     * @return 对象列表
     */
    List<AllocatedCom> queryAll(AllocatedCom allocatedCom);

    /**
     * 新增数据
     *
     * @param allocatedCom 实例对象
     * @return 影响行数
     */
    int insert(AllocatedCom allocatedCom);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AllocatedCom> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AllocatedCom> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AllocatedCom> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AllocatedCom> entities);

    /**
     * 修改数据
     *
     * @param allocatedCom 实例对象
     * @return 影响行数
     */
    int update(AllocatedCom allocatedCom);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 影响行数
     */
    int deleteById(Integer comNum);

    /**
     * 通过工单编号查询该工单所有物理机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有物理机资源
     */
    List<AllocatedCom> queryByWorkOrderNum(String workOrderNum);

}

