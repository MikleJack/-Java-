package com.example.back2.controller.leader;

import com.example.back2.entity.view.Leaderworkorderall;
import com.example.back2.service.view.LeaderworkorderallService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("leader")
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
    @GetMapping("selectTicketsByState")
    public ResponseEntity<Page<Leaderworkorderall>> queryByPage(Integer second_leader_num,
                                                                Integer first_leader_num,
                                                                String orderState,
                                                                int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.leaderworkorderallService.queryByPage(second_leader_num,
                first_leader_num,orderState, pageRequest));
    }
}
