package com.example.back2.controller.leader;

import com.example.back2.entity.view.Leaderworkorderall;
import com.example.back2.service.view.LeaderworkorderallService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

public class pendTickets {
    //--------------查询某个领导未审批的工单信息----------------------
    @Resource
    private LeaderworkorderallService leaderworkorderallService;

    /**
     * 分页查询
     *
     * @param second_leader_num 二级领导人工号
     * @param first_leader_num      一级领导人工号
     * @param orderState 工单状态
     * @param page 页面
     * @param size 页面大小
     * @return 查询结果
     */
    @GetMapping("selectTicketsByNum")
    public ResponseEntity<Page<Leaderworkorderall>> queryByPage(Integer second_leader_num,
                                                                Integer first_leader_num,
                                                                String orderState,
                                                                int page, int size) {
        Leaderworkorderall leaderworkorderall = new Leaderworkorderall();
        if(second_leader_num!=null)
            leaderworkorderall.setFirstLeaderNum(first_leader_num);
        else
            leaderworkorderall.setSecondLeaderNum(second_leader_num);
        leaderworkorderall.setWorkOrderState(orderState);
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.leaderworkorderallService.queryByPage(leaderworkorderall, pageRequest));
    }
}
