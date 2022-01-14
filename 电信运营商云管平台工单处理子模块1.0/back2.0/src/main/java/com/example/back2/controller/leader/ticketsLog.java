package com.example.back2.controller.leader;

import com.example.back2.entity.view.FlowWorkorder;
import com.example.back2.service.view.FlowWorkorderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("flowWorkOrder")
public class ticketsLog {

    @Resource
    private FlowWorkorderService flowWorkorderService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("getLog")
    public ResponseEntity<Page<FlowWorkorder>> queryByPage(String applyName,Integer dealNum,Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.flowWorkorderService.queryByPage(applyName,dealNum, pageRequest));
    }

}
