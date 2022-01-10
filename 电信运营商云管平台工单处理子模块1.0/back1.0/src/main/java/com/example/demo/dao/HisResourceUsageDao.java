package com.example.demo.dao;

import com.example.demo.entity.HisResourceUsage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (HisResourceUsage)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-05 21:19:21
 */
@Mapper
public interface HisResourceUsageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    HisResourceUsage queryById(String workOrderNum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HisResourceUsage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param hisResourceUsage 实例对象
     * @return 对象列表
     */
    List<HisResourceUsage> queryAll(HisResourceUsage hisResourceUsage);

    /**
     * 新增数据
     *
     * @param hisResourceUsage 实例对象
     * @return 影响行数
     */
    int insert(HisResourceUsage hisResourceUsage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HisResourceUsage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HisResourceUsage> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<HisResourceUsage> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<HisResourceUsage> entities);

    /**
     * 修改数据
     *
     * @param hisResourceUsage 实例对象
     * @return 影响行数
     */
    int update(HisResourceUsage hisResourceUsage);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 影响行数
     */
    int deleteById(String workOrderNum);

}

