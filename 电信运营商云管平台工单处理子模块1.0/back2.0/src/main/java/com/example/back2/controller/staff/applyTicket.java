package com.example.back2.controller.staff;

import com.example.back2.entity.table.*;
import com.example.back2.service.table.PhysicsComResourceService;
import com.example.back2.service.table.VmSpecificationsService;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("applyTickets")
public class applyTicket {
    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private VmSpecificationsService vmSpecificationsService;
    @Resource
    private WorkOrderService workOrderService;

    /**
     * 查询所有未分配的物理机
     * @return
     */
    @GetMapping("selectAllpc")
    public List<PhysicsComResource> selectAllpc(){
        return this.physicsComResourceService.selectAllpc();
    }

    /**
     * 查询所有虚拟机规格
     * @return
     */
    @GetMapping("selectAllvm")
    public List<VmSpecifications> selectAllvm(){
        return this.vmSpecificationsService.selectAllvm();
    }
//申请工单接口
    @PostMapping("intsertApplyTicket")
    public WorkOrder intsertApplyTicket(String workOrderName,Date expirationTime,String reason,Integer workernum,String file,Double price){
//        生成工单号，并传入
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String workOrderNum = df.format(d);
        double t1 = Math.random();
        t1 *= 10000;
        int t2 = (int) t1;
        workOrderNum += t2+ "";
//        System.out.println(workOrderNum);

        WorkOrder workOrder = new WorkOrder();
//        修改工单号
        workOrder.setWorkOrderNum(workOrderNum);

//传入时间
        workOrder.setExpirationTime(expirationTime);
//传入工单标题
        workOrder.setWorkOrderName(workOrderName);
//  传入理由描述
        workOrder.setReason(reason);
// 传入申请人工号
        workOrder.setWorkerNum(workernum);
//传入工单类型
        workOrder.setWorkOrderType("申请工单");
//传入附件
        workOrder.setFile(file);
//传入工单总价
        workOrder.setPrice(price);
// 修改工单状态
        workOrder.setWorkOrderState("待审批");
//
        return workOrder;
    }

//    @PostMapping("insertAllocatedCom")
//    public AllocatedCom insertAllocatedCom(String workOrderNum,String comNum){
//
//    }
//
//    @PostMapping("insertAllocationVm")
//    public AllocatedVm insertAllocatedVm(String workOrderNum){
//    }
}
