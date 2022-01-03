package com.example.demo.service;

import com.example.demo.entity.OperationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (OperationLog)表服务接口
 *
 * @author makejava
 * @since 2022-01-03 10:27:45
 */
public interface OperationLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param workNum 主键
     * @return 实例对象
     */
    OperationLog queryById(String workNum);

    /**
     * 分页查询
     *
     * @param operationLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<OperationLog> queryByPage(OperationLog operationLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param operationLog 实例对象
     * @return 实例对象
     */
    OperationLog insert(OperationLog operationLog);

    /**
     * 修改数据
     *
     * @param operationLog 实例对象
     * @return 实例对象
     */
    OperationLog update(OperationLog operationLog);

    /**
     * 通过主键删除数据
     *
     * @param workNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workNum);

}
