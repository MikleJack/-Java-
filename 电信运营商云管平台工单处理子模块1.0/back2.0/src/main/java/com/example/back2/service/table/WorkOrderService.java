package com.example.back2.service.table;

import com.example.back2.entity.table.WorkOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    Page<WorkOrder> criteriaQueryByPage(Integer workerNum,PageRequest pageRequest);

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

}
