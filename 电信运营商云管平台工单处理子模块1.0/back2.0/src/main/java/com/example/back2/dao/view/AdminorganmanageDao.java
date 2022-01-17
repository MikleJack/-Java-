package com.example.back2.dao.view;

import com.example.back2.entity.view.Adminorganmanage;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Adminorganmanage)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-17 20:23:06
 */
public interface AdminorganmanageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param  depNum 主键
     * @return 实例对象
     */
    List<Adminorganmanage> queryById(Integer depNum );

    /**
     * 查询指定行数据
     *
     * @param adminorganmanage 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Adminorganmanage> queryAllByLimit(Adminorganmanage adminorganmanage, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param adminorganmanage 查询条件
     * @return 总行数
     */
    long count(Adminorganmanage adminorganmanage);


}

