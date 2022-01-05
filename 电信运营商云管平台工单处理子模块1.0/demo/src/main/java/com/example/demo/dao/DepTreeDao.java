package com.example.demo.dao;

import com.example.demo.entity.DepTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (DepTree)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-05 15:20:46
 */
@Mapper
public interface DepTreeDao {

    /**
     * 查询指定行数据
     *
     * @param depTree 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<DepTree> queryAllByLimit(DepTree depTree, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param depTree 查询条件
     * @return 总行数
     */
    long count(DepTree depTree);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<DepTree> queryAll();

}

