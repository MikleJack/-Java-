package com.example.back2.dao.view;

import com.example.back2.entity.view.FlowWorkorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (FlowWorkorder)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-14 09:33:37
 */
@Mapper
public interface FlowWorkorderDao {


    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<FlowWorkorder> queryAllByLimit(String applyName,Integer dealNum, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(String applyName,Integer dealNum);

}

