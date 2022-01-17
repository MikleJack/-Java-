package com.example.back2.service.view;

import com.example.back2.entity.view.Leaderworkorderall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.concurrent.Future;

/**
 * (Leaderworkorderall)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 11:28:34
 */
public interface LeaderworkorderallService {


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Future<Page<Leaderworkorderall>> queryByPage(Integer second_leader_num,
                                                Integer first_leader_num,
                                                String orderState,
                                                PageRequest pageRequest);

    Integer getSecondNum(String workOrderNum);


}
