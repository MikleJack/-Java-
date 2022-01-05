package com.example.demo.dao;

import com.example.demo.entity.PerWorkerResRate;
import com.example.demo.entity.WorkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Perworkerresrate)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-05 10:59:07
 */
@Mapper
public interface PerWorkerResRateDao {

    /**
     * 查询指定行数据
     *
     * @param perworkerresrate 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<PerWorkerResRate> queryAllByLimit(PerWorkerResRate perworkerresrate, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param perworkerresrate 查询条件
     * @return 总行数
     */
    long count(PerWorkerResRate perworkerresrate);

    /**
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    List<WorkOrder> queryAll();

}

