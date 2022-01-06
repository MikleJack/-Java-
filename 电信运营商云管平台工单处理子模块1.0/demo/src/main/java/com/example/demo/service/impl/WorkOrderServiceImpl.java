package com.example.demo.service.impl;

import com.example.demo.entity.WorkOrder;
import com.example.demo.dao.WorkOrderDao;
import com.example.demo.service.WorkOrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WorkOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-01-04 20:57:42
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
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<WorkOrder> queryByPage(PageRequest pageRequest) {
        long total = this.workOrderDao.count();
        return new PageImpl<>(this.workOrderDao.queryAllByLimit(pageRequest), pageRequest, total);
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
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    @Override
    public List<WorkOrder> queryAll(){
        return this.workOrderDao.queryAll();
    }

    @Override
    public List<WorkOrder> selectByWorkNum(String WorkNum){
        return this.workOrderDao.selectByWorkNum(WorkNum);
    }

    @Override
    public List<WorkOrder> selectByWorkOrderType(String WorkOrderType){
        return this.workOrderDao.selectByWorkOrderType(WorkOrderType);
    }

    @Override
    public List<WorkOrder> selectByWorkOrderReason(String WorkOrderReason){
        return this.workOrderDao.selectByWorkOrderReason(WorkOrderReason);
    }
}
