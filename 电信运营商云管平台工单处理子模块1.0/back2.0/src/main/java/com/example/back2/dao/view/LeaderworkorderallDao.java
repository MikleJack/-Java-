package com.example.back2.dao.view;

import com.example.back2.entity.view.Leaderworkorderall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Leaderworkorderall)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 11:28:34
 */
@Mapper
public interface LeaderworkorderallDao {


    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Leaderworkorderall> queryAllByLimit(Integer secondLeaderNum,Integer firstLeaderNum, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param leader_num 查询条件
     * @return 总行数
     */
    long count(Integer secondLeaderNum,Integer firstLeaderNum);


}

