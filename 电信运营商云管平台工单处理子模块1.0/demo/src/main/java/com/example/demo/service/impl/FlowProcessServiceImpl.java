package com.example.demo.service.impl;

import com.example.demo.entity.FlowProcess;
import com.example.demo.dao.FlowProcessDao;
import com.example.demo.service.FlowProcessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FlowProcess)表服务实现类
 *
 * @author makejava
 * @since 2022-01-06 20:28:31
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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<FlowProcess> queryAllByLimit(int offset, int limit) {
        return this.flowProcessDao.queryAllByLimit(offset, limit);
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

//    通过工号查询操作过的工单分页
    @Override
    public List<FlowProcess> selectOrderNumByWorkNum(Integer WorkNum,int page,int size){
        return this.flowProcessDao.selectOrderNumByWorkNum(WorkNum,page,size);
    }
//    通过工号查询操作过的工单
    public List<FlowProcess> selectByWorkNum(Integer WorkNum){
        return this.flowProcessDao.selectByWorkNum(WorkNum);
    }
}
