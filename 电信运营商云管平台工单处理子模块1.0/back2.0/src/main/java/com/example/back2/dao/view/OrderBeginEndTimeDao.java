package com.example.back2.dao.view;

import com.example.back2.entity.view.OrderBeginEndTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (OrderBeginEndTime)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-12 18:34:29
 */
@Mapper
public interface OrderBeginEndTimeDao {


    /**
     * 查询指定行数据
     *
     * @param orderBeginEndTime 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<OrderBeginEndTime> queryAllByLimit(OrderBeginEndTime orderBeginEndTime, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param orderBeginEndTime 查询条件
     * @return 总行数
     */
    long count(OrderBeginEndTime orderBeginEndTime);

    /**
     * 新增数据
     *
     * @param orderBeginEndTime 实例对象
     * @return 影响行数
     */
    int insert(OrderBeginEndTime orderBeginEndTime);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderBeginEndTime> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderBeginEndTime> entities);


    /**
     * 通过工单编号查询数据
     *
     * @param workOrderNum 工单编号
     * @return 该工单编号对应的记录，可获取申请时间和结束时间
     */
     OrderBeginEndTime queryById(String workOrderNum);

}

