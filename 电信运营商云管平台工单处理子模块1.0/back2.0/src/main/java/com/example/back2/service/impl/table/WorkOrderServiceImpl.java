package com.example.back2.service.impl.table;

import com.example.back2.entity.table.WorkOrder;
import com.example.back2.dao.table.WorkOrderDao;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

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
    @Override
    public Page<WorkOrder> criteriaQueryByPage(Integer workerNum,PageRequest pageRequest){
        long total = this.workOrderDao.criteriaCount(workerNum);
        return new PageImpl<>(this.workOrderDao.criteriaQueryAllByLimit(workerNum, pageRequest), pageRequest, total);
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
}
