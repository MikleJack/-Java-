package com.example.back2.service.impl.table;

import com.example.back2.entity.table.FlowProcess;
import com.example.back2.dao.table.FlowProcessDao;
import com.example.back2.service.table.FlowProcessService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (FlowProcess)表服务实现类
 *
 * @author makejava
 * @since 2022-01-13 09:41:13
 */
@Service("flowProcessService")
public class FlowProcessServiceImpl implements FlowProcessService {
    @Resource
    private FlowProcessDao flowProcessDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    @Override
    public FlowProcess queryById(String workOrderNum) {
        return this.flowProcessDao.queryById(workOrderNum);
    }

    /**
     * 分页查询
     *
     * @param flowProcess 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<FlowProcess> queryByPage(FlowProcess flowProcess, PageRequest pageRequest) {
        long total = this.flowProcessDao.count(flowProcess);
        return new PageImpl<>(this.flowProcessDao.queryAllByLimit(flowProcess, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param flowProcess 实例对象
     * @return 实例对象
     */
    @Override
    public FlowProcess insert(FlowProcess flowProcess) {
        this.flowProcessDao.insert(flowProcess);
        return flowProcess;
    }

    /**
     * 修改数据
     *
     * @param flowProcess 实例对象
     * @return 实例对象
     */
    @Override
    public FlowProcess update(FlowProcess flowProcess) {
        this.flowProcessDao.update(flowProcess);
        return this.queryById(flowProcess.getWorkOrderNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String workOrderNum) {
        return this.flowProcessDao.deleteById(workOrderNum) > 0;
    }
}
