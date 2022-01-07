package com.example.demo.dao;

import com.example.demo.entity.AdminWorkOrderInform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AdminWorkOrderInform)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-07 10:47:48
 */
@Mapper
public interface AdminWorkOrderInformDao {


    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminWorkOrderInform> queryAllByLimit(@Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count();

    /**
     * 新增数据
     *
     * @param adminWorkOrderInform 实例对象
     * @return 影响行数
     */
    int insert(AdminWorkOrderInform adminWorkOrderInform);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminWorkOrderInform> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdminWorkOrderInform> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminWorkOrderInform> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AdminWorkOrderInform> entities);

}

