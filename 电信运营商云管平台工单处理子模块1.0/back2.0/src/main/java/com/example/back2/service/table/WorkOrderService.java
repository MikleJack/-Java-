package com.example.back2.service.table;

import com.example.back2.entity.table.WorkOrder;
import com.example.back2.entity.view.AdminsearchorderTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (WorkOrder)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 20:33:15
 */
public interface WorkOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrder queryById(String workOrderNum);

    /**
     * 分页查询
     *
     * @param workOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<WorkOrder> queryByPage(WorkOrder workOrder, PageRequest pageRequest);

    /**
     * 无条件的分页查询
     *
     * @param workerNum 员工编号
     * @param pageRequest 分页请求
     * @return 用户的全部工单
     */
    Future<Page<WorkOrder>> criteriaQueryByPage(Integer workerNum, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param workOrder 实例对象
     * @return 实例对象
     */
    WorkOrder insert(WorkOrder workOrder);

    /**
     * 修改数据
     *
     * @param workOrder 实例对象
     * @return 实例对象
     */
    WorkOrder update(WorkOrder workOrder);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);

    /**
     * 通过员工编号分页查询
     *
     * @param workOrderNum 工单编号
     * @param offlineReason  下线原因
     * @return 是否下线成功
     */
    Boolean offline(String workOrderNum, String offlineReason);

    /**
     * 通过工单编号进行延期操作
     *
     * @param workOrderNum 工单编号
     * @param delayReason  延期原因
     * @param delayTime  延期日期
     * @return 是否发起延期请求成功
     */
     Boolean delay(String workOrderNum,String newWorkOrderNum, Date delayTime, String delayReason,Double nowPrice);

    /**
     * 通过工单编号查询该工单的价格
     *
     * @param workOrderNum 主键
     * @return 该工单总价
     */
    Future<Double> queryPriceById(String workOrderNum);

//-------------------员工全部工单查询界面--查询按钮-顶部----------------------------
    /**
     * 带条件的分页查询：只传参数
     *
     * @param workerTitle 工单名
     * @param workOrderType 工单类型
     * @param workerNum 工人编号
     * @return 查询结果
     */
    Future<Page<WorkOrder>> parameterQueryByPage(String workOrderType, String workerTitle,Integer workerNum,PageRequest pageRequest);
//------------------------员工全部工单查询界面----查询按钮-底部----------------------------

//------------------------员工变更工单界面
    List<WorkOrder> selectWorkOrderByworkNum(String workerNum);

    long getNumPendticket(Integer workerNum);

    long getNumFailedtickets(Integer workerNum);
}




