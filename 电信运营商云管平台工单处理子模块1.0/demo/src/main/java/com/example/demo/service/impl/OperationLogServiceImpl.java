package com.example.demo.service.impl;

import com.example.demo.entity.OperationLog;
import com.example.demo.dao.OperationLogDao;
import com.example.demo.service.OperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (OperationLog)表服务实现类
 *
 * @author makejava
 * @since 2022-01-03 10:27:45
 */
@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService {
    @Resource
    private OperationLogDao operationLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workNum 主键
     * @return 实例对象
     */
    @Override
    public OperationLog queryById(Integer workNum) {
        return this.operationLogDao.queryById(workNum);
    }

    /**
     * 分页查询
     *
     * @param operationLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<OperationLog> queryByPage(OperationLog operationLog, PageRequest pageRequest) {
        long total = this.operationLogDao.count(operationLog);
        return new PageImpl<>(this.operationLogDao.queryAllByLimit(operationLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param operationLog 实例对象
     * @return 实例对象
     */
    @Override
    public OperationLog insert(OperationLog operationLog) {
        this.operationLogDao.insert(operationLog);
        return operationLog;
    }

    /**
     * 修改数据
     *
     * @param operationLog 实例对象
     * @return 实例对象
     */
    @Override
    public OperationLog update(OperationLog operationLog) {
        this.operationLogDao.update(operationLog);
        return this.queryById(operationLog.getWork_num());
    }

    /**
     * 通过主键删除数据
     *
     * @param workNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workNum) {
        return this.operationLogDao.deleteById(workNum) > 0;
    }
}
