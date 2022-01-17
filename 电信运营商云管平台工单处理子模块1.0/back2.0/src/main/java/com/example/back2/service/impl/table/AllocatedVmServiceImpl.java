package com.example.back2.service.impl.table;

import com.example.back2.entity.table.AllocatedCom;
import com.example.back2.entity.table.AllocatedVm;
import com.example.back2.dao.table.AllocatedVmDao;
import com.example.back2.service.table.AllocatedVmService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (AllocatedVm)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 19:44:49
 */
@Service("allocatedVmService")
public class AllocatedVmServiceImpl implements AllocatedVmService {
    @Resource
    private AllocatedVmDao allocatedVmDao;

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    @Override
    public AllocatedVm queryById(Integer comNum) {
        return this.allocatedVmDao.queryById(comNum);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AllocatedVm> queryAllByLimit(int offset, int limit) {
        return this.allocatedVmDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param allocatedVm 实例对象
     * @return 实例对象
     */
    @Override
    public AllocatedVm insert(AllocatedVm allocatedVm) {
        this.allocatedVmDao.insert(allocatedVm);
        return allocatedVm;
    }

    /**
     * 修改数据
     *
     * @param allocatedVm 实例对象
     * @return 实例对象
     */
    @Override
    public AllocatedVm update(AllocatedVm allocatedVm) {
        this.allocatedVmDao.update(allocatedVm);
        return this.queryById(allocatedVm.getComNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer comNum) {
        return this.allocatedVmDao.deleteById(comNum) > 0;
    }

    /**
     * 通过工单编号查询该工单所有虚拟机机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    @Async
    @Override
    public Future<List<AllocatedVm>> queryByWorkOrderNum(String workOrderNum){
        return new AsyncResult<>(this.allocatedVmDao.queryByWorkOrderNum(workOrderNum));
    }
}
