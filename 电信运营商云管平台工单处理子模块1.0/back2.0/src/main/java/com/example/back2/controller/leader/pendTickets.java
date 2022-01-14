package com.example.back2.controller.leader;

import com.example.back2.controller.admin.searchOrder;
import com.example.back2.entity.table.*;
import com.example.back2.entity.view.AdminsearceorderVm;
import com.example.back2.entity.view.AdminsearchorderCom;
import com.example.back2.entity.view.AdminsearchorderDetailperson;
import com.example.back2.entity.view.Leaderworkorderall;
import com.example.back2.service.table.*;
import com.example.back2.service.view.AdminsearceorderVmService;
import com.example.back2.service.view.AdminsearchorderComService;
import com.example.back2.service.view.AdminsearchorderDetailpersonService;
import com.example.back2.service.view.LeaderworkorderallService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pendtickets")
public class pendTickets {
    //--------------查询某个领导未审批的工单信息----------------------
    @Resource
    private LeaderworkorderallService leaderworkorderallService;

    /**
     * 分页查询
     *
     * @param second_leader_num 二级领导人工号
     * @param first_leader_num      一级领导人工号
     * @param orderState 工单状态
     * @param page 页面
     * @param size 页面大小
     * @return 查询结果
     */
    @GetMapping("selectTicketsByState")
    public ResponseEntity<Page<Leaderworkorderall>> queryByPage(Integer second_leader_num,
                                                                Integer first_leader_num,
                                                                String orderState,
                                                                int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.leaderworkorderallService.queryByPage(second_leader_num,
                first_leader_num,orderState, pageRequest));
    }

    //    根据工号查询工人信息
    @Resource
    AdminsearchorderDetailpersonService adminsearchorderDetailpersonService;

    @GetMapping("queryWorkOrderDetailTop")
    public ResponseEntity<AdminsearchorderDetailperson> queryWorkOrderDetailTop(String workOrderNum){
        return ResponseEntity.ok(this.adminsearchorderDetailpersonService.queryWorkOrderDetailTop(workOrderNum));
    }

//  根据工单号返回该工单的物理机信息

    @Resource
    private AdminsearchorderComService adminsearchorderComService;

    @GetMapping("getOrderCom")
    public List<AdminsearchorderCom> getOrderCom(String workOrderNum){
        return this.adminsearchorderComService.getOrderCom(workOrderNum);
    }
//根据工单号返回该工单的虚拟机信息

    @Resource
    private AdminsearceorderVmService adminsearceorderVmService;

    @GetMapping("getOrderVm")
    public List<AdminsearceorderVm> getOrderVm(String workOrderNum){
        return this.adminsearceorderVmService.getOrderVm(workOrderNum);
    }


    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private LeadershipService leadershipService;
    @Resource
    private FlowProcessService flowProcessService;

    /**
     *
     * @param workOrderNum  要审批的工单号
     * @param workNum       审批人工号
     * @param state         审批状态
     * @return
     */
    @PostMapping("oneExamine")
    public Boolean Examine(String workOrderNum, String workNum, String state){
        WorkOrder workOrder  = workOrderService.queryById(workOrderNum);
        if(state.equals("审批不通过")){
            workOrder.setWorkOrderState(state);
            workOrderService.update(workOrder);
        }
        else if(state.equals("审批通过")){
            //得到申请这个工单人的姓名
            Integer applyNum = workOrder.getWorkerNum();
            //得到这个人的所有上级领导
            List<Leadership> leaderList = leadershipService.getLeaderNum(applyNum);
            Boolean success=true;
            //查询申请工单的员工的所有领导有无全部通过这个工单
            for (Leadership i :leaderList){
                if(flowProcessService.selectByOrderNumLeaderNum(workOrderNum,
                        i.getLederNum(),"审批通过")==null)
                    success=false;
            }
            if(success){
                workOrder.setWorkOrderState("一级审批通过");
                workOrderService.update(workOrder);
            }

        }
        return true;
    }

    @Resource
    private AllocatedComService allocatedComService;
    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private UsedBudgetService usedBudgetService;
    @Resource
    private StaffService staffService;

    @PostMapping("towExamine")
    public Boolean towExamine(String workOrderNum, String workNum, String state){
        WorkOrder workOrder  = workOrderService.queryById(workOrderNum);
        if(state.equals("审批通过")){
            workOrder.setWorkOrderState("二级审批通过");
            workOrderService.update(workOrder);
            //分配物理机资源
            List<AllocatedCom> allocatedComs = this.allocatedComService.queryByWorkOrderNum(workOrderNum);
            List<Integer> comNums = new ArrayList<Integer>();
            for(int i = 0; i <allocatedComs.size();i++) {
                comNums.add(allocatedComs.get(i).getComNum());
            }
            this.physicsComResourceService.setComAssign(comNums,false);
            //分配虚拟机资源

            //更新部门使用预算
            Staff staff=staffService.queryById(workOrder.getWorkerNum());
            UsedBudget usedBudget = usedBudgetService.queryById(staff.getDepNum());
            usedBudget.setDepUsedBudget(usedBudget.getDepUsedBudget()+workOrder.getPrice());
            usedBudgetService.update(usedBudget);

        }
        else if(state.equals("挂起")||state.equals("审批不通过")){
            workOrder.setWorkOrderState(state);
            workOrderService.update(workOrder);
        }
        else
            return false;
        return true;
    }
}
