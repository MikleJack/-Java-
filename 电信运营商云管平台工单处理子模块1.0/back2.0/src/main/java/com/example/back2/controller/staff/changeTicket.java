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

    @PostMapping("intsertChangeTicket")
    public String intsertApplyTicket(String workOrderName, Date expirationTime, String reason, Integer workNum,String workOrderType, String file, Double price){
        applyTicket applyticket = new applyTicket();
        String workOrderNum = applyticket.intsertApplyTicket(workOrderType,workOrderName,expirationTime,reason,workNum,file,price);
        return workOrderNum;
    }

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
