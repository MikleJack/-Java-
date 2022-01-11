package com.example.back2.service.impl;

import com.example.back2.entity.Leaderworkorderall;
import com.example.back2.dao.LeaderworkorderallDao;
import com.example.back2.service.LeaderworkorderallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Leaderworkorderall)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 09:23:42
 */
@Service("leaderworkorderallService")
public class LeaderworkorderallServiceImpl implements LeaderworkorderallService {
    @Resource
    private LeaderworkorderallDao leaderworkorderallDao;

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Leaderworkorderall> queryAllByLimit(int offset, int limit) {
        return this.leaderworkorderallDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param leaderworkorderall 实例对象
     * @return 实例对象
     */
    @Override
    public Leaderworkorderall insert(Leaderworkorderall leaderworkorderall) {
        this.leaderworkorderallDao.insert(leaderworkorderall);
        return leaderworkorderall;
    }

    @Override
    public List<Leaderworkorderall> selectAll(){

        return this.leaderworkorderallDao.selectAll();
    }
}
