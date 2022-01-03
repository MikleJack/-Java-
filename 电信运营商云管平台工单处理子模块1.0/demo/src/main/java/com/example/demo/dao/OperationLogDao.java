package com.example.demo.dao;

import com.example.demo.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (OperationLog)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-02 20:19:26
 */
@Mapper
public interface OperationLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workNum 主键
     * @return 实例对象
     */
    OperationLog queryById(String workNum);

    /**
     * 查询指定行数据
     *
     * @param operationLog 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<OperationLog> queryAllByLimit(OperationLog operationLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param operationLog 查询条件
     * @return 总行数
     */
    long count(OperationLog operationLog);

    /**
     * 新增数据
     *
     * @param operationLog 实例对象
     * @return 影响行数
     */
    int insert(OperationLog operationLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OperationLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OperationLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OperationLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<OperationLog> entities);

    /**
     * 修改数据
     *
     * @param operationLog 实例对象
     * @return 影响行数
     */
    int update(OperationLog operationLog);

    /**
     * 通过主键删除数据
     *
     * @param workNum 主键
     * @return 影响行数
     */
    int deleteById(String workNum);

}

