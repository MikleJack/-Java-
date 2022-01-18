package com.example.back2.service.impl.table;

import com.example.back2.entity.table.Leadership;
import com.example.back2.dao.table.LeadershipDao;
import com.example.back2.service.table.LeadershipService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Leadership)表服务实现类
 *
 * @author makejava
 * @since 2022-01-13 16:05:43
 */
@Service("leadershipService")
public class LeadershipServiceImpl implements LeadershipService {
    @Resource
    private LeadershipDao leadershipDao;

    /**
     * 通过ID查询单条数据
     *
     * @param lederNum 主键
     * @return 实例对象
     */
    @Override
    public List<Leadership> getUnderNum(Integer lederNum) {
        return this.leadershipDao.getUnderNum(lederNum);
    }

    /**
     * 分页查询
     *
     * @param leadership 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Leadership> queryByPage(Leadership leadership, PageRequest pageRequest) {
        long total = this.leadershipDao.count(leadership);
        return new PageImpl<>(this.leadershipDao.queryAllByLimit(leadership, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param leadership 实例对象
     * @return 实例对象
     */
    @Override
    public Leadership insert(Leadership leadership) {
        this.leadershipDao.insert(leadership);
        return leadership;
    }

    /**
     * 修改数据
     *
     * @param leadership 实例对象
     * @return 实例对象
     */
    @Override
    public List<Leadership> update(Leadership leadership) {
        this.leadershipDao.update(leadership);
        return this.getUnderNum(leadership.getLederNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param lederNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer lederNum) {
        return this.leadershipDao.deleteById(lederNum) > 0;
    }

    @Override
    public List<Leadership> getLeaderNum(Integer underNum){
        return this.leadershipDao.getLeaderNum(underNum);
    }

}
