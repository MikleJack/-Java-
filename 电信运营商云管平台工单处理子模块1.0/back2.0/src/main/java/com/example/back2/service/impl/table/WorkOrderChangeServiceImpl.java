package com.example.back2.service.impl.table;

import com.example.back2.entity.table.WorkOrderChange;
import com.example.back2.dao.table.WorkOrderChangeDao;
import com.example.back2.service.table.WorkOrderChangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WorkOrderChange)表服务实现类
 *
 * @author makejava
 * @since 2022-01-12 18:39:16
 */
@Service("workOrderChangeService")
public class WorkOrderChangeServiceImpl implements WorkOrderChangeService {
    @Resource
    private WorkOrderChangeDao workOrderChangeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    @Override
    public WorkOrderChange queryById(String workOrderNum) {
        return this.workOrderChangeDao.queryById(workOrderNum);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WorkOrderChange> queryAllByLimit(int offset, int limit) {
        return this.workOrderChangeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param workOrderChange 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOrderChange insert(WorkOrderChange workOrderChange) {
        this.workOrderChangeDao.insert(workOrderChange);
        return workOrderChange;
    }

    /**
     * 修改数据
     *
     * @param workOrderChange 实例对象
     * @return 实例对象
     */
    @Override
    public WorkOrderChange update(WorkOrderChange workOrderChange) {
        this.workOrderChangeDao.update(workOrderChange);
        return this.queryById(workOrderChange.getWorkOrderNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String workOrderNum) {
        return this.workOrderChangeDao.deleteById(workOrderNum) > 0;
    }
}
