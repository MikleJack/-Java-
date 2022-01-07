package com.example.demo.dao;

import com.example.demo.entity.AdminOperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AdminOperationLog)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-07 09:39:06
 */
@Mapper
public interface AdminOperationLogDao {


    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminOperationLog> queryAllByLimit(@Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count();

    /**
     * 新增数据
     *
     * @param adminOperationLog 实例对象
     * @return 影响行数
     */
    int insert(AdminOperationLog adminOperationLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminOperationLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdminOperationLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminOperationLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AdminOperationLog> entities);



}

