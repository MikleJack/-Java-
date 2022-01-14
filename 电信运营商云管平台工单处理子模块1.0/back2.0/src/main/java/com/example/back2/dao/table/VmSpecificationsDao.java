package com.example.back2.dao.table;

import com.example.back2.entity.table.VmSpecifications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (VmSpecifications)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 14:35:55
 */
@Mapper
public interface VmSpecificationsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    VmSpecifications queryById(Integer comNum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<VmSpecifications> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param vmSpecifications 实例对象
     * @return 对象列表
     */
    List<VmSpecifications> queryAll(VmSpecifications vmSpecifications);

    /**
     * 新增数据
     *
     * @param vmSpecifications 实例对象
     * @return 影响行数
     */
    int insert(VmSpecifications vmSpecifications);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VmSpecifications> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VmSpecifications> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VmSpecifications> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<VmSpecifications> entities);

    /**
     * 修改数据
     *
     * @param vmSpecifications 实例对象
     * @return 影响行数
     */
    int update(VmSpecifications vmSpecifications);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 影响行数
     */
    int deleteById(Integer comNum);

    List<VmSpecifications> selectAllvm();

    boolean insertVirtual(int cpuCore, int ram, int price,String processorModel,String processorFrequency,String description);

    boolean updateDiskPrice(Double diskPrice);
}

