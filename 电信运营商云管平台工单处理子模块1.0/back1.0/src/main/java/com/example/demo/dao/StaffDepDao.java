package com.example.demo.dao;

import com.example.demo.entity.StaffDep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (StaffDep)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-07 18:22:49
 */
@Mapper
public interface StaffDepDao {


    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<StaffDep> queryAllByLimit( @Param("pageable")Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count();



}

