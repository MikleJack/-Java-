package com.example.back2.service.view;

import com.example.back2.entity.view.HistoryUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (HistoryUsage)表服务接口
 *
 * @author makejava
 * @since 2022-01-20 16:46:37
 */
public interface HistoryUsageService {

    /**
     * 分页查询
     *
     * @param historyUsage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HistoryUsage> queryByPage(HistoryUsage historyUsage, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param historyUsage 实例对象
     * @return 实例对象
     */
    HistoryUsage insert(HistoryUsage historyUsage);

    /**
     * 根据员工编号返回其所有工单
     *
     * @param workerNum 员工编号
     * @return 该员工的所有工单
     */
    Double getStaffUsage(Integer workerNum);


}
