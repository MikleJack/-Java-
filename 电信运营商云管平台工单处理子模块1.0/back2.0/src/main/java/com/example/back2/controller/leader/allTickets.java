package com.example.back2.controller.leader;


import com.example.back2.entity.Leaderworkorderall;
import com.example.back2.service.LeaderworkorderallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("leader")
public class allTickets {

    //--------------查询所有工单信息----------------------
    @Resource
    private LeaderworkorderallService leaderworkorderallService;

    @GetMapping("selectAll")
    public List<Leaderworkorderall> selectAll(){
        return this.leaderworkorderallService.selectAll();
    }


}
