package com.example.back2.dao.view;

import com.example.back2.entity.view.HistoryUsage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (HistoryUsage)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-20 16:46:37
 */
@Mapper
public interface HistoryUsageDao {

    /**
     * 查询指定行数据
     *
     * @param historyUsage 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<HistoryUsage> queryAllByLimit(HistoryUsage historyUsage, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param historyUsage 查询条件
     * @return 总行数
     */
    long count(HistoryUsage historyUsage);

    /**
     * 新增数据
     *
     * @param historyUsage 实例对象
     * @return 影响行数
     */
    int insert(HistoryUsage historyUsage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<HistoryUsage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<HistoryUsage> entities);

    /**
     * 根据员工编号返回其所有工单
     *
     * @param workerNum 员工编号
     * @return 该员工的所有工单
     */
    List<HistoryUsage> getStaffUsage(Integer workerNum);

}

