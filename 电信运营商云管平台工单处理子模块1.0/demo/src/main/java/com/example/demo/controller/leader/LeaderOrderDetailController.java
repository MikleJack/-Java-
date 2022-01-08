package com.example.demo.controller.leader;

import com.example.demo.entity.leader.LeaderOrderDetail;
import com.example.demo.service.leader.LeaderOrderDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (LeaderOrderDetail)表控制层
 *
 * @author makejava
 * @since 2022-01-08 11:08:04
 */
@RestController
@RequestMapping("leaderOrder")
public class LeaderOrderDetailController {
    /**
     * 服务对象
     */
    @Resource
    private LeaderOrderDetailService leaderOrderDetailService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("selectByLeader")
    public ResponseEntity<Page<LeaderOrderDetail>> queryByPage(String leader_num, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.leaderOrderDetailService.queryByPage( leader_num,pageRequest));
    }






}

