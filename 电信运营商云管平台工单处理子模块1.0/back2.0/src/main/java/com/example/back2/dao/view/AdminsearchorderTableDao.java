package com.example.back2.dao.view;

import com.example.back2.entity.view.AdminsearchorderTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AdminsearchorderTable)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-10 19:56:49
 */
@Mapper
public interface AdminsearchorderTableDao {

    /**
     * 对象查询指定行数据
     *
     * @param adminsearchorderTable 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminsearchorderTable> queryAllByLimit(@Param("adminsearchorderTable") AdminsearchorderTable adminsearchorderTable, @Param("pageable") Pageable pageable);

    /**
     * 无条件查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminsearchorderTable> normalQueryAllByLimit(@Param("pageable") Pageable pageable);

    /**
     * 参数查询指定行数据
     *
     * @param pageable         分页对象
     * @param workerName 员工姓名
     * @return 对象列表
     */
    List<AdminsearchorderTable> parameterQueryByPage(@Param("workOrderType") String workOrderType,
                                                     @Param("workerName") String workerName ,@Param("pageable") Pageable pageable);


    /**
     * 统计总行数
     *
     * @param adminsearchorderTable 查询条件
     * @return 总行数
     */
    long count(@Param("adminsearchorderTable") AdminsearchorderTable adminsearchorderTable);
    long normalCount();
    long paramCount(@Param("workOrderType") String workOrderType, @Param("workerName") String workerName);

    /**
     * 新增数据
     *
     * @param adminsearchorderTable 实例对象
     * @return 影响行数
     */
    int insert(AdminsearchorderTable adminsearchorderTable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminsearchorderTable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdminsearchorderTable> entities);




}

