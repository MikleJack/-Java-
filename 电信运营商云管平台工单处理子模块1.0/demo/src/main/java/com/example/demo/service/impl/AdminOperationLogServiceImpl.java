package com.example.demo.service.impl;

import com.example.demo.entity.AdminOperationLog;
import com.example.demo.dao.AdminOperationLogDao;
import com.example.demo.service.AdminOperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (AdminOperationLog)表服务实现类
 *
 * @author makejava
 * @since 2022-01-07 09:39:06
 */
@Service("adminOperationLogService")
public class AdminOperationLogServiceImpl implements AdminOperationLogService {
    @Resource
    private AdminOperationLogDao adminOperationLogDao;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AdminOperationLog> queryByPage(PageRequest pageRequest) {
        long total = this.adminOperationLogDao.count();
        return new PageImpl<>(this.adminOperationLogDao.queryAllByLimit(pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param adminOperationLog 实例对象
     * @return 实例对象
     */
    @Override
    public AdminOperationLog insert(AdminOperationLog adminOperationLog) {
        this.adminOperationLogDao.insert(adminOperationLog);
        return adminOperationLog;
    }



}
