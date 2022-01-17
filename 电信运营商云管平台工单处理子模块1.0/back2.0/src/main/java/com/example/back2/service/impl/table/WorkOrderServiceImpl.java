package com.example.back2.service.impl.table;

import com.example.back2.entity.table.WorkOrder;
import com.example.back2.dao.table.WorkOrderDao;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (WorkOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 20:33:16
 */
@Service("workOrderService")
public class WorkOrderServiceImpl implements WorkOrderService {
    @Resource
    private WorkOrderDao workOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    @Override
    public WorkOrder queryById(String workOrderNum) {
        return this.workOrderDao.queryById(workOrderNum);
    }

    /**
     * 分页查询
     *
     * @param workOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<WorkOrder> queryByPage(WorkOrder workOrder, PageRequest pageRequest) {
        long total = this.workOrderDao.count(workOrder);
        return new PageImpl<>(this.workOrderDao.queryAllByLimit(workOrder, pageRequest), pageRequest, total);
    }

    /**
     * 无条件的分页查询
     *
     * @param workerNum 员工编号
     * @param pageRequest 分页请求
     * @return 用户的全部工单
     */
    @Async
    @Override
    public Future<Page<WorkOrder>> criteriaQueryByPage(Integer workerNum, PageRequest pageRequest){
        long total = this.workOrderDao.criteriaCount(workerNum);
        return new AsyncResult<>(new PageImpl<>(this.workOrderDao.criteriaQueryAllByLimit(workerNum, pageRequest), pageRequest, total));
    }

    /**
     * 新增数据
     *
     * @param workOrder 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOrder insert(WorkOrder workOrder) {
        this.workOrderDao.insert(workOrder);
        return workOrder;
    }

    /**
     * 修改数据
     *
     * @param workOrder 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOrder update(WorkOrder workOrder) {
        this.workOrderDao.update(workOrder);
        return this.queryById(workOrder.getWorkOrderNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String workOrderNum) {
        return this.workOrderDao.deleteById(workOrderNum) > 0;
    }

    /**
     * 通过员工编号分页查询
     *
     * @param workOrderNum 工单编号
     * @param offlineReason  下线原因
     * @return 用户的全部工单
     */
    @Override
    public Boolean offline(String workOrderNum, String offlineReason) {
        return this.workOrderDao.offline(workOrderNum,offlineReason);
    }

    /**
     * 通过工单编号进行延期操作
     *
     * @param newWorkOrderNum 新工单编号
     * @param workOrderNum 工单编号
     * @param delayReason  延期原因
     * @param delayTime  延期日期
     * @return 是否发起延期请求成功
     */
    @Override
    public Boolean delay(String workOrderNum,String newWorkOrderNum, Date delayTime, String delayReason,Double nowPrice) {
        WorkOrder preWorkOrder = this.workOrderDao.queryById(workOrderNum);
        preWorkOrder.setExpirationTime(delayTime);
        preWorkOrder.setPrice(nowPrice);
        preWorkOrder.setWorkOrderNum(newWorkOrderNum);
        preWorkOrder.setReason(delayReason);
        preWorkOrder.setWorkOrderType("延期工单");
        return this.workOrderDao.insertDelayWorkOrder(preWorkOrder);
    }

    /**
     * 通过工单编号查询该工单的价格
     *
     * @param workOrderNum 主键
     * @return 该工单总价
     */
    @Async
    @Override
    public Future<Double> queryPriceById(String workOrderNum){
        return new AsyncResult<>(this.workOrderDao.queryById(workOrderNum).getPrice());
    }

//-------------------员工全部工单查询界面--查询按钮-顶部----------------------------
    /**
     * 带条件的分页查询：只传参数
     *
     * @param workerTitle 工单名
     * @param workOrderType 工单类型
     * @param workerNum 工人编号
     * @return 查询结果
     */
    @Async
    @Override
    public Future<Page<WorkOrder>> parameterQueryByPage(String workOrderType, String workerTitle,Integer workerNum,PageRequest pageRequest){
        long total = this.workOrderDao.parameterCount(workOrderType, workerTitle, workerNum);
        return new AsyncResult<>(new PageImpl<>(this.workOrderDao.parameterQueryAllByLimit(workOrderType, workerTitle, workerNum, pageRequest), pageRequest, total));
    }
//------------------------员工全部工单查询界面----查询按钮-底部----------------------------

//-----------------------员工变更工单界面
    @Override
    public List<WorkOrder> selectWorkOrderByworkNum(String workerNum){
        return this.workOrderDao.selectWorkOrderByworkNum(workerNum);
    }
}
