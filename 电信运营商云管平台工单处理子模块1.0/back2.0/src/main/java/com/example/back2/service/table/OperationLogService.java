package com.example.back2.service.table;

import com.example.back2.entity.table.OperationLog;
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
    OperationLog queryById(Integer workNum);

    /**
     * 分页查询
     *
     * @param operationLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<OperationLog> queryByPage(PageRequest pageRequest);

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
    boolean deleteById(Integer workNum);

}
