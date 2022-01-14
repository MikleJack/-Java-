package com.example.back2.service.impl.table;

import com.example.back2.entity.table.WorkOrderDelay;
import com.example.back2.dao.table.WorkOrderDelayDao;
import com.example.back2.service.table.WorkOrderDelayService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (WorkOrderDelay)表服务实现类
 *
 * @author makejava
 * @since 2022-01-14 14:18:37
 */
@Service("workOrderDelayService")
public class WorkOrderDelayServiceImpl implements WorkOrderDelayService {
    @Resource
    private WorkOrderDelayDao workOrderDelayDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    @Override
    public WorkOrderDelay queryById(String workOrderNum) {
        return this.workOrderDelayDao.queryById(workOrderNum);
    }

    /**
     * 分页查询
     *
     * @param workOrderDelay 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<WorkOrderDelay> queryByPage(WorkOrderDelay workOrderDelay, PageRequest pageRequest) {
        long total = this.workOrderDelayDao.count(workOrderDelay);
        return new PageImpl<>(this.workOrderDelayDao.queryAllByLimit(workOrderDelay, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param workOrderDelay 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOrderDelay insert(WorkOrderDelay workOrderDelay) {
        this.workOrderDelayDao.insert(workOrderDelay);
        return workOrderDelay;
    }

    /**
     * 修改数据
     *
     * @param workOrderDelay 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOrderDelay update(WorkOrderDelay workOrderDelay) {
        this.workOrderDelayDao.update(workOrderDelay);
        return this.queryById(workOrderDelay.getWorkOrderNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String workOrderNum) {
        return this.workOrderDelayDao.deleteById(workOrderNum) > 0;
    }
}
