package com.example.back2.controller.staff;

import com.example.back2.entity.table.WorkOrder;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("staffAllTickets")
public class StaffAllTickets {

    @Resource
    private WorkOrderService workOrderService;

//----------------首页表单显示-顶部-------------------------------------------------------
    /**
     * 通过员工编号分页查询
     *
     * @param workerNum 员工编号
     * @param page  分页所在页面
     * @param size 分页大小
     * @return 是否下线成功
     */
    @GetMapping("criteriaQueryByPage")
    public ResponseEntity<Page<WorkOrder>> criteriaQueryByPage(Integer workerNum, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.workOrderService.criteriaQueryByPage(workerNum,pageRequest));
    }

//----------------首页表单显示-底部-------------------------------------------------------


//----------------------------下线按钮-顶部----------------------------
    /**
     * 通过员工编号分页查询
     *
     * @param workOrderNum 工单编号
     * @param offlineReason  下线原因
     * @param workOrderState  工单状态
     * @return 用户的全部工单
     */
    @GetMapping("offline")
    public ResponseEntity<Boolean> offline(String workOrderNum,String workOrderState, String offlineReason) {
        if(workOrderState.equals("二级审批通过")) {
            return ResponseEntity.ok(this.workOrderService.offline(workOrderNum, offlineReason));
        }else{
            return ResponseEntity.ok(false);
        }
    }

//----------------------------下线按钮-底部----------------------------


}
