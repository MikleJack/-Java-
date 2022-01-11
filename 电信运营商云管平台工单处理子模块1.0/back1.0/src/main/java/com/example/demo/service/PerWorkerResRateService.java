package com.example.demo.service;

import com.example.demo.entity.PerWorkerResRate;
import com.example.demo.entity.WorkOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Perworkerresrate)表服务接口
 *
 * @author makejava
 * @since 2022-01-05 10:59:07
 */
public interface PerWorkerResRateService {

    /**
     * 分页查询
     *
     * @param perworkerresrate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<PerWorkerResRate> queryByPage(PerWorkerResRate perworkerresrate, PageRequest pageRequest);

    /**
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    List<WorkOrder> queryAll();
}
