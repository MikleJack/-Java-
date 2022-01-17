package com.example.back2.service.impl.view;

import com.example.back2.entity.view.OrderBeginEndTime;
import com.example.back2.dao.view.OrderBeginEndTimeDao;
import com.example.back2.service.view.OrderBeginEndTimeService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * (OrderBeginEndTime)表服务实现类
 *
 * @author makejava
 * @since 2022-01-12 18:34:29
 */
@Service("orderBeginEndTimeService")
public class OrderBeginEndTimeServiceImpl implements OrderBeginEndTimeService {
    @Resource
    private OrderBeginEndTimeDao orderBeginEndTimeDao;

    /**
     * 分页查询
     *
     * @param orderBeginEndTime 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<OrderBeginEndTime> queryByPage(OrderBeginEndTime orderBeginEndTime, PageRequest pageRequest) {
        long total = this.orderBeginEndTimeDao.count(orderBeginEndTime);
        return new PageImpl<>(this.orderBeginEndTimeDao.queryAllByLimit(orderBeginEndTime, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param orderBeginEndTime 实例对象
     * @return 实例对象
     */
    @Override
    public OrderBeginEndTime insert(OrderBeginEndTime orderBeginEndTime) {
        this.orderBeginEndTimeDao.insert(orderBeginEndTime);
        return orderBeginEndTime;
    }

    /**
     * 通过工单编号查询数据
     *
     * @param workOrderNum 工单编号
     * @return 该工单编号对应的记录，可获取申请时间和结束时间
     */
    @Async
    @Override
    public Future<OrderBeginEndTime> queryById(String workOrderNum)throws Exception{
        return new AsyncResult<>(this.orderBeginEndTimeDao.queryById(workOrderNum));
    }


    /**
     * 这个方法尽量不用，在同一个类中调用线程，不实现并发
     *
     * 通过工单编号得到结束日期
     *
     * @param workOrderNum 工单编号
     * @return 该工单编号对应的记录，可获取申请时间和结束时间
     */
    @Override
    public Date queryEndTimeByOrderNum(String workOrderNum) throws Exception{
        return queryById(workOrderNum).get().getExpirationTime();
    }

    /**
     * 这个方法尽量不用，在同一个类中调用线程，不实现并发
     *
     * 通过工单编号得到申请日期
     *
     * @param workOrderNum 工单编号
     * @return 该工单编号对应的申请日期
     */
    @Override
    public Date queryBeginTimeByOrderNum(String workOrderNum)throws Exception{
        return queryById(workOrderNum).get().getDealDate();
    }
}
