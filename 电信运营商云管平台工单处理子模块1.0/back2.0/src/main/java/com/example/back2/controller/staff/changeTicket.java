package com.example.back2.controller.staff;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.back2.entity.table.AllocatedCom;
import com.example.back2.entity.table.AllocatedVm;
import com.example.back2.entity.table.WorkOrder;
import com.example.back2.entity.table.WorkOrderChange;
import com.example.back2.entity.view.AdminsearchorderDetailperson;
import com.example.back2.entity.view.AllocatedVmSpecifications;
import com.example.back2.exception.GlobalException;
import com.example.back2.service.table.AllocatedComService;
import com.example.back2.service.table.AllocatedVmService;
import com.example.back2.service.table.WorkOrderChangeService;
import com.example.back2.service.table.WorkOrderService;
import com.example.back2.service.view.AdminsearchorderDetailpersonService;
import com.example.back2.service.view.AdminsearchorderTableService;
import com.example.back2.service.view.AllocatedVmSpecificationsService;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("changeTickets")
public class changeTicket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WorkOrderChangeService workOrderChangeService;


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

//创建原工单和变更工单的关联
    @PostMapping("insertWorkOrderChange")
    public WorkOrderChange insertWorkOrderChange(String workOrderNum,String changedOldOrder){
        WorkOrderChange workOrderChange = new WorkOrderChange();
        workOrderChange.setWorkOrderNum(workOrderNum);
        workOrderChange.setChangedOldOrder(changedOldOrder);
        return this.workOrderChangeService.insert(workOrderChange);
    }

//通过原工单号查找工单内容

    @Resource
    AdminsearchorderDetailpersonService adminsearchorderDetailpersonService;

    @GetMapping("selectByWorkOrderNum")
    public ResponseEntity<AdminsearchorderDetailperson> selectByWorkOrderNum(String workOrderNum) throws Exception{
        return ResponseEntity.ok(this.adminsearchorderDetailpersonService.queryWorkOrderDetailTop(workOrderNum).get());
    }

//    提交变更之后的工单内容

    @Resource
    private WorkOrderService workOrderService;

    @PostMapping("insertChangedTickets")
    public String insertChangedTickets(WorkOrder workorder) throws GlobalException{
        logger.info("员工编号为" + workorder.getWorkerNum() + "的员工进行了变更工单申请");

        //        生成工单号，并传入
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String workOrderNum = df.format(d);
        int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
        workOrderNum += randomNum+ "";

        if(workOrderNum.length() != 18){
            throw new GlobalException("插入变更工单时，生成新的工单号不为18位    date为新生成的工单号",workOrderNum);
        }

//        填入新的工单号
        workorder.setWorkOrderNum(workOrderNum);

        return this.workOrderService.insert(workorder).getWorkOrderNum();
    }


//  获取原工单申请的物理机资源
    @Resource
    private AllocatedComService allocatedComService;

    @GetMapping("selectComByWorkOrderNum")
    public List<AllocatedCom> selectComByWorkOrderNum(String workOrderNum) throws Exception{
        return this.allocatedComService.queryByWorkOrderNum(workOrderNum).get();
    }

//    获取原工单申请的虚拟机资源
    @Resource
    private AllocatedVmSpecificationsService allocatedVmSpecificationsService;

    @GetMapping("selectVmByWorkOrderNum")
    public List<AllocatedVmSpecifications> selectVmByWorkOrderNum(String workOrderNum) throws Exception{
        return this.allocatedVmSpecificationsService.queryVmByWorkOrderNum(workOrderNum).get();
    }


//   查询相应工号的二级审批通过的工单

    @GetMapping("selectWorkOrderByworkNum")
    public List<String> selectWorkOrderByworkNum(String workerNum){

        List<WorkOrder> getWorkOrertNum = this.workOrderService.selectWorkOrderByworkNum(workerNum);
        List<String> ans = new LinkedList<String>();

        for (WorkOrder i:getWorkOrertNum){
            ans.add(i.getWorkOrderNum());
        }
        return ans;
    }

    @PutMapping("OrderStateChange")
    public Boolean OrderStateChange(String workOrderNum,String state) throws GlobalException{
        WorkOrder workOrder  = this.workOrderService.queryById(workOrderNum);
        if(state.equals("已变更")){
            workOrder.setWorkOrderState(state);
            this.workOrderService.update(workOrder);
            return true;
        }
        else
            throw new GlobalException("在想把工单状态改为已变更时需要输入新的工单状态‘已变更’     date为试图设置的状态", state);
    }
}
