package com.example.back2.dao;

import com.example.back2.entity.AdminsearchorderDetailperson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AdminsearchorderDetailperson)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 09:50:13
 */
@Mapper
public interface AdminsearchorderDetailpersonDao {

    /**
     * 查询指定行数据
     *
     * @param adminsearchorderDetailperson 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminsearchorderDetailperson> queryAllByLimit(AdminsearchorderDetailperson adminsearchorderDetailperson, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param adminsearchorderDetailperson 查询条件
     * @return 总行数
     */
    long count(AdminsearchorderDetailperson adminsearchorderDetailperson);

    /**
     * 新增数据
     *
     * @param adminsearchorderDetailperson 实例对象
     * @return 影响行数
     */
    int insert(AdminsearchorderDetailperson adminsearchorderDetailperson);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminsearchorderDetailperson> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdminsearchorderDetailperson> entities);

    /**
     * 管理员工单界面的详情按钮
     *此处只返回上半部分信息，下半部分信息调用其他接口
     *
     * @param workOrderNum 工单编号
     * @return 每个工单详情页面上的员工信息、部门信息、工单信息
     */
    AdminsearchorderDetailperson queryWorkOrderDetailTop(@Param("workOrderNum") String workOrderNum);

}

