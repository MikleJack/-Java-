package com.example.demo.service.leader;

import com.example.demo.entity.leader.LeaderOrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (LeaderOrderDetail)表服务接口
 *
 * @author makejava
 * @since 2022-01-08 11:08:04
 */
public interface LeaderOrderDetailService {


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<LeaderOrderDetail> queryByPage(String leader_num,PageRequest pageRequest);

}
