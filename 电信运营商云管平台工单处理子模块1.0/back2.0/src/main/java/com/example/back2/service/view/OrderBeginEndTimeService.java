package com.example.back2.service.view;

import com.example.back2.entity.view.OrderBeginEndTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * (OrderBeginEndTime)表服务接口
 *
 * @author makejava
 * @since 2022-01-12 18:34:29
 */
public interface OrderBeginEndTimeService {

    /**
     * 分页查询
     *
     * @param orderBeginEndTime 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<OrderBeginEndTime> queryByPage(OrderBeginEndTime orderBeginEndTime, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderBeginEndTime 实例对象
     * @return 实例对象
     */
    OrderBeginEndTime insert(OrderBeginEndTime orderBeginEndTime);

    /**
     * 通过工单编号查询数据
     *
     * @param workOrderNum 工单编号
     * @return 该工单编号对应的记录，可获取申请时间和结束时间
     */
    Future<OrderBeginEndTime> queryById(String workOrderNum) throws Exception;

    /**
     * 通过工单编号得到结束日期
     *
     * @param workOrderNum 工单编号
     * @return 该工单编号对应的结束日期
     */
    Date queryEndTimeByOrderNum(String workOrderNum) throws Exception;

    /**
     * 通过工单编号得到申请日期
     *
     * @param workOrderNum 工单编号
     * @return 该工单编号对应的申请日期
     */
    Date queryBeginTimeByOrderNum(String workOrderNum) throws Exception;

}
