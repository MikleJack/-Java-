package com.example.demo.service.impl.leader;

import com.example.demo.entity.leader.LeaderOrderDetail;
import com.example.demo.dao.leader.LeaderOrderDetailDao;
import com.example.demo.service.leader.LeaderOrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (LeaderOrderDetail)表服务实现类
 *
 * @author makejava
 * @since 2022-01-08 11:08:04
 */
@Service("leaderOrderDetailService")
public class LeaderOrderDetailServiceImpl implements LeaderOrderDetailService {
    @Resource
    private LeaderOrderDetailDao leaderOrderDetailDao;


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<LeaderOrderDetail> queryByPage(String leader_num, PageRequest pageRequest) {
        long total = this.leaderOrderDetailDao.count(leader_num);
        return new PageImpl<>(this.leaderOrderDetailDao.queryAllByLimit(leader_num,pageRequest), pageRequest, total);
    }


}
