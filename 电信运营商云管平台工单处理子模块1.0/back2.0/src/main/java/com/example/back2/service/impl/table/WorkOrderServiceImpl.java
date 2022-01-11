package com.example.back2.service.impl.table;

import com.example.back2.entity.table.WorkOrder;
import com.example.back2.dao.table.WorkOrderDao;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WorkOrder)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 15:23:50
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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WorkOrder> queryAllByLimit(int offset, int limit) {
        return this.workOrderDao.queryAllByLimit(offset, limit);
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
