package com.example.back2.service;

import com.example.back2.entity.Leaderworkorderall;

import java.util.List;

/**
 * (Leaderworkorderall)表服务接口
 *
 * @author makejava
 * @since 2022-01-10 20:56:42
 */
public interface LeaderworkorderallService {


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Leaderworkorderall> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param leaderworkorderall 实例对象
     * @return 实例对象
     */
    Leaderworkorderall insert(Leaderworkorderall leaderworkorderall);


    List<Leaderworkorderall> selectAll();
}
