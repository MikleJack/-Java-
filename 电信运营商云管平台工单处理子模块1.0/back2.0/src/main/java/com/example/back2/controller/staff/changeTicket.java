package com.example.back2.controller.staff;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.back2.entity.table.AllocatedCom;
import com.example.back2.entity.table.WorkOrder;
import com.example.back2.entity.table.WorkOrderChange;
import com.example.back2.entity.view.AdminsearchorderDetailperson;
import com.example.back2.service.table.AllocatedComService;
import com.example.back2.service.table.WorkOrderChangeService;
import com.example.back2.service.table.WorkOrderService;
import com.example.back2.service.view.AdminsearchorderDetailpersonService;
import com.example.back2.service.view.AdminsearchorderTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("changeTickets")
public class changeTicket {

    @Resource
    private WorkOrderChangeService workOrderChangeService;

//    创建变更工单
//    @PostMapping("intsertChangeTicket")
//    public String intsertApplyTicket( String workOrderName,Date expirationTime,String reason,Integer workNum,String file
//            ,Double price,String workOrderType) {
//        applyTicket applyticket = new applyTicket();
//        String workOrderNum = applyticket.intsertApplyTicket(workOrderName,expirationTime,reason,workNum,file,price,workOrderType);
//        return workOrderNum;
//    }

//创建插入已申请物理机资源
    @PostMapping("insertAllocatedCom")
    public boolean insertAllocatedCom(String qs,String workOrderNum){
        applyTicket applyticket = new applyTicket();
        return applyticket.insertAllocatedCom(qs,workOrderNum);
    }
//创建插入已申请虚拟机资源
    @PostMapping("insertAllocatedVm")
    public boolean insertAllocatedVm(String qs, String workOrderNum, Integer storage,String os){
        applyTicket applyticket = new applyTicket();
        return applyticket.insertAllocatedVm(qs,workOrderNum,storage,os);
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
//通过原工单号查找工单内容

    @Resource
    AdminsearchorderDetailpersonService adminsearchorderDetailpersonService;

    @GetMapping("selectByWorkOrderNum")
    public ResponseEntity<AdminsearchorderDetailperson> selectByWorkOrderNum(String workOrderNum){
        return ResponseEntity.ok(this.adminsearchorderDetailpersonService.queryWorkOrderDetailTop(workOrderNum));
    }

//    提交变更之后的工单内容

    @Resource
    private WorkOrderService workOrderService;

    @PostMapping("insertChangedTickets")
    public String insertChangedTickets(WorkOrder workorder){
        //        生成工单号，并传入
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String workOrderNum = df.format(d);
        int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
        workOrderNum += randomNum+ "";

//        填入新的工单号
        workorder.setWorkOrderNum(workOrderNum);

        return this.workOrderService.insert(workorder).getWorkOrderNum();
    }






}
