package com.example.demo.dao;

import com.example.demo.entity.AllocatedCom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AllocatedCom)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-07 16:58:45
 */
@Mapper
public interface AllocatedComDao {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    AllocatedCom queryById(String comNum);

    /**
     * 查询指定行数据
     *
     * @param allocatedCom 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AllocatedCom> queryAllByLimit(AllocatedCom allocatedCom, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param allocatedCom 查询条件
     * @return 总行数
     */
    long count(AllocatedCom allocatedCom);

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
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
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
    int deleteById(String comNum);

    /**
     * 管理员工单查询界面，详细按钮
     * 通过工单编号查询该工单利用所有的资源
     *
     * @param workOrderNum 主键
     * @return 该工单利用的所有资源
     */
    List<AllocatedCom> queryByWorkOrderNum(String workOrderNum);

}

