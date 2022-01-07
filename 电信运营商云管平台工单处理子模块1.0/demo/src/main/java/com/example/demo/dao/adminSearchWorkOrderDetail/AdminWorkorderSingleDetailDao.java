package com.example.demo.dao.adminSearchWorkOrderDetail;

import com.example.demo.entity.adminSearchWorkOrderDetail.AdminWorkorderSingleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (AdminWorkorderSingleDetail)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-07 16:04:41
 */
@Mapper
public interface AdminWorkorderSingleDetailDao {


    /**
     * 查询指定行数据
     *
     * @param adminWorkorderSingleDetail 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminWorkorderSingleDetail> queryAllByLimit(AdminWorkorderSingleDetail adminWorkorderSingleDetail, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param adminWorkorderSingleDetail 查询条件
     * @return 总行数
     */
    long count(AdminWorkorderSingleDetail adminWorkorderSingleDetail);

    /**
     * 新增数据
     *
     * @param adminWorkorderSingleDetail 实例对象
     * @return 影响行数
     */
    int insert(AdminWorkorderSingleDetail adminWorkorderSingleDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminWorkorderSingleDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdminWorkorderSingleDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminWorkorderSingleDetail> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AdminWorkorderSingleDetail> entities);

    /**
     * 修改数据
     *
     * @param adminWorkorderSingleDetail 实例对象
     * @return 影响行数
     */
    int update(AdminWorkorderSingleDetail adminWorkorderSingleDetail);

    /**
     * 管理员工单界面的详情按钮
     *
     * @param workOrderNum 工单编号
     * @return 每个工单详情页面上的员工信息、部门信息、工单信息
     */
    AdminWorkorderSingleDetail queryWorkOrderDetail(String workOrderNum);


}

