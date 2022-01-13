package com.example.back2.service.table;

import com.example.back2.entity.table.FlowProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (FlowProcess)表服务接口
 *
 * @author makejava
 * @since 2022-01-13 09:41:13
 */
public interface FlowProcessService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    FlowProcess queryById(String workOrderNum);

    /**
     * 分页查询
     *
     * @param flowProcess 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<FlowProcess> queryByPage(FlowProcess flowProcess, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param flowProcess 实例对象
     * @return 实例对象
     */
    FlowProcess insert(FlowProcess flowProcess);

    /**
     * 修改数据
     *
     * @param flowProcess 实例对象
     * @return 实例对象
     */
    FlowProcess update(FlowProcess flowProcess);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);


    FlowProcess selectByOrderNumLeaderNum(String workOrderNum, Integer leaderNum, String operation_type);

}
