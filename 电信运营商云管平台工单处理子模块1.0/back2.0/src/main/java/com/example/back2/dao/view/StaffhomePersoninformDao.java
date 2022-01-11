package com.example.back2.dao.view;

import com.example.back2.entity.view.StaffhomePersoninform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (StaffhomePersoninform)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 15:31:55
 */
@Mapper
public interface StaffhomePersoninformDao {

    /**
     * 查询指定行数据
     *
     * @param staffhomePersoninform 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<StaffhomePersoninform> queryAllByLimit(StaffhomePersoninform staffhomePersoninform, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param staffhomePersoninform 查询条件
     * @return 总行数
     */
    long count(StaffhomePersoninform staffhomePersoninform);

    /**
     * 新增数据
     *
     * @param staffhomePersoninform 实例对象
     * @return 影响行数
     */
    int insert(StaffhomePersoninform staffhomePersoninform);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<StaffhomePersoninform> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<StaffhomePersoninform> entities);

    /**
     * 通过员工编号查询员工信息
     *
     * @param workerNum 员工编号
     * @return 查询结果
     */
    StaffhomePersoninform queryPersonInformById(@Param("workerNum") Integer workerNum);


}

