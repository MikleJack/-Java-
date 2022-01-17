package com.example.back2.service.table;

import com.example.back2.entity.table.HisResourceUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * (HisResourceUsage)表服务接口
 *
 * @author makejava
 * @since 2022-01-14 15:35:14
 */
public interface HisResourceUsageService {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    Future<HisResourceUsage> queryById(String workOrderNum);

    /**
     * 分页查询
     *
     * @param hisResourceUsage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<HisResourceUsage> queryByPage(HisResourceUsage hisResourceUsage, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param hisResourceUsage 实例对象
     * @return 实例对象
     */
    HisResourceUsage insert(HisResourceUsage hisResourceUsage);

    /**
     * 修改数据
     *
     * @param hisResourceUsage 实例对象
     * @return 实例对象
     */
    HisResourceUsage update(HisResourceUsage hisResourceUsage) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workOrderNum);

}
