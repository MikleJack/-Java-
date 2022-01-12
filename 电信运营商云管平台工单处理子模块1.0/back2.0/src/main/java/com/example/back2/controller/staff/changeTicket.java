package com.example.back2.controller.staff;

import com.example.back2.entity.table.WorkOrderChange;
import com.example.back2.service.table.WorkOrderChangeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("changeTickets")
public class changeTicket {

    @Resource
    private WorkOrderChangeService workOrderChangeService;

//    创建变更工单
    @PostMapping("intsertChangeTicket")
    public String intsertApplyTicket( String workOrderName,Date expirationTime,String reason,Integer workNum,String file
            ,Double price,String workOrderType) {
        applyTicket applyticket = new applyTicket();
        String workOrderNum = applyticket.intsertApplyTicket(workOrderName,expirationTime,reason,workNum,file,price,workOrderType);
        return workOrderNum;
    }
//创建插入已申请资源
    @PostMapping("insertAllocatedCom")
    public boolean insertAllocatedCom(String qs,String workOrderNum){
        applyTicket applyticket = new applyTicket();
        return applyticket.insertAllocatedCom(qs,workOrderNum);
    }

//创建已经申请变更工单
    @PostMapping("insertWorkOrderChange")
    public WorkOrderChange insertWorkOrderChange(String workOrderNum,String changedOldOrder){
        System.out.println(workOrderNum);
        System.out.println(changedOldOrder);

        WorkOrderChange workOrderChange = new WorkOrderChange();

        workOrderChange.setWorkOrderNum(workOrderNum);
        workOrderChange.setChangedOldOrder(changedOldOrder);
        return this.workOrderChangeService.insert(workOrderChange);


    }


}
