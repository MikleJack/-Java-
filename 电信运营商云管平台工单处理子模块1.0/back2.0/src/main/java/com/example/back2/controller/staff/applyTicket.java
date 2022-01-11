package com.example.back2.controller.staff;

import com.example.back2.entity.table.PhysicsComResource;
import com.example.back2.entity.table.VmSpecifications;
import com.example.back2.entity.table.WorkOrder;
import com.example.back2.service.table.PhysicsComResourceService;
import com.example.back2.service.table.VmSpecificationsService;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("applyTickets")
public class applyTicket {
    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private VmSpecificationsService vmSpecificationsService;
    @Resource
    private WorkOrderService workOrderService;

    @GetMapping("selectAllpc")
    public List<PhysicsComResource> selectAllpc(){
        return this.physicsComResourceService.selectAllpc();
    }

    @GetMapping("selectAllvm")
    public List<VmSpecifications> selectAllvm(){
        return this.vmSpecificationsService.selectAllvm();
    }

    @PostMapping("intsertApplyTicket")
    public boolean intsertApplyTicket(String workOrderName,String expirationTime,String reason){
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String workOrderNum = df.format(d);
        double t1 = Math.random();
        t1 *= 10000;
        int t2 = (int) t1;
        workOrderNum += t2+ "";
//        System.out.println(workOrderNum);




        WorkOrder workOrder = new WorkOrder();
        workOrder.setWorkOrderNum(workOrderNum);
        workOrder.setWorkOrderName(workOrderName);

        workOrder.setReason(reason);
        return true;
    }

}
