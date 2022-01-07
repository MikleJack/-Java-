package com.example.demo.service;

import com.example.demo.entity.AdminOperationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AdminOperationLog)表服务接口
 *
 * @author makejava
 * @since 2022-01-07 09:39:06
 */
public interface AdminOperationLogService {


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminOperationLog> queryByPage( PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param adminOperationLog 实例对象
     * @return 实例对象
     */
    AdminOperationLog insert(AdminOperationLog adminOperationLog);



}
