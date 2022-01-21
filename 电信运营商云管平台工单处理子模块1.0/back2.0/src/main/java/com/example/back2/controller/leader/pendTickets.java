package com.example.back2.controller.leader;

import com.example.back2.controller.admin.searchOrder;
import com.example.back2.entity.VirtualComResource;
import com.example.back2.entity.table.*;
import com.example.back2.entity.view.*;
import com.example.back2.exception.GlobalException;
import com.example.back2.service.table.*;
import com.example.back2.service.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("pendtickets")
public class pendTickets {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //--------------查询某个领导未审批的工单信息----------------------
    @Resource
    private LeaderworkorderallService leaderworkorderallService;
    @Resource
    private AdminordertowleaderService adminordertowleaderService;

    /**
     * 分页查询
     *
     * @param second_leader_num 二级领导人工号
     * @param first_leader_num  一级领导人工号
     * @param orderState        工单状态
     * @param page              页面
     * @param size              页面大小
     * @return 查询结果
     */
    @GetMapping("selectTicketsByState")
    public ResponseEntity<Page<Leaderworkorderall>> queryByPage(Integer second_leader_num,
                                                                 Integer first_leader_num,
                                                                 String orderState,
                                                                 int page, int size) throws Exception {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.leaderworkorderallService.queryByPage(second_leader_num,
                first_leader_num, orderState, pageRequest).get());
    }

    @GetMapping("selectTow")
    public ResponseEntity<Page<Adminordertowleader>> selectTow(int page, int size,
                                                               String orderState)throws Exception{
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.adminordertowleaderService.queryByPage(orderState,pageRequest).get());
    }

    //    根据工号查询工人信息
    @Resource
    AdminsearchorderDetailpersonService adminsearchorderDetailpersonService;

    @GetMapping("queryWorkOrderDetailTop")
    public ResponseEntity<AdminsearchorderDetailperson> queryWorkOrderDetailTop(String workOrderNum) throws Exception {
        return ResponseEntity.ok(this.adminsearchorderDetailpersonService.queryWorkOrderDetailTop(workOrderNum).get());
    }

//  根据工单号返回该工单的物理机信息

    @Resource
    private AdminsearchorderComService adminsearchorderComService;

    @GetMapping("getOrderCom")
    public List<AdminsearchorderCom> getOrderCom(String workOrderNum) throws Exception {
        List<AdminsearchorderCom> a = this.adminsearchorderComService.getOrderCom(workOrderNum).get();
        return this.adminsearchorderComService.getOrderCom(workOrderNum).get();
    }

//根据工单号返回该工单的虚拟机信息

    @Resource
    private AdminsearceorderVmService adminsearceorderVmService;

    @GetMapping("getOrderVm")
    public List<AdminsearceorderVm> getOrderVm(String workOrderNum) throws Exception {
        return this.adminsearceorderVmService.getOrderVm(workOrderNum).get();
    }


    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private LeadershipService leadershipService;
    @Resource
    private FlowProcessService flowProcessService;

    @Resource
    private InformService informService;

    /**
     * @param workOrderNum 要审批的工单号
     * @param firstWorkerNum      审批人工号
     * @param state        审批状态
     * @return
     */
    @PostMapping("oneExamine")
    public Boolean Examine(String workOrderNum, Integer firstWorkerNum, String state) throws GlobalException{
        logger.info("有一级领导对工单" + workOrderNum + "发起了审批");

        WorkOrder workOrder  = workOrderService.queryById(workOrderNum);
        if(state.equals("审批不通过")){
            workOrder.setWorkOrderState(state);
            workOrderService.update(workOrder);
            this.informService.firstLeaderInsertInform(workOrderNum, firstWorkerNum, "未通过一级审批");
        } else if (state.equals("审批通过")) {
            //得到申请这个工单人的姓名
            Integer applyNum = workOrder.getWorkerNum();
            //得到这个人的所有上级领导
            List<Leadership> leaderList = leadershipService.getLeaderNum(applyNum);

            if (leaderList == null) {
                throw new GlobalException("在审批通过后查询员工上级领导时发生错误      员工编号为", applyNum);
            }

            Boolean success = true;
            //查询申请工单的员工的所有领导有无全部通过这个工单
            for (Leadership i : leaderList) {
                if (flowProcessService.selectByOrderNumLeaderNum(workOrderNum,
                        i.getLederNum(), "审批通过") == null)
                    success = false;
            }
            if (success) {
                workOrder.setWorkOrderState("一级审批通过");
                workOrderService.update(workOrder);
                this.informService.firstLeaderInsertInform(workOrderNum, firstWorkerNum, "通过一级审批");
            } else {
                throw new GlobalException("在一级审批时发生错误   工单信息为", workOrder);
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
    @Resource
    private AllocatedVmService allocatedVmService;
    @Resource
    private VirtualComResourceService virtualComResourceService;

    @PostMapping("towExamine")
    public Boolean towExamine(String workOrderNum, Integer secondNum, String state) throws Exception{
        logger.info("有二级领导对工单" + workOrderNum + "发起了审批");

        WorkOrder workOrder  = workOrderService.queryById(workOrderNum);
        if(state.equals("审批通过")){
            workOrder.setWorkOrderState("二级审批通过");
            workOrderService.update(workOrder);
            //分配物理机资源
            List<AllocatedCom> allocatedComs = this.allocatedComService.queryByWorkOrderNum(workOrderNum).get();
            List<Integer> comNums = new ArrayList<Integer>();
            for (int i = 0; i < allocatedComs.size(); i++) {
                comNums.add(allocatedComs.get(i).getComNum());
            }
            if (!this.physicsComResourceService.setComAssign(comNums, false)) {
                throw new GlobalException("在分配物理机资源时发生错误    选中的物理机的编号为", comNums);
            }
            //分配虚拟机资源

            List<AllocatedVm> allocatedVms = this.allocatedVmService.queryByWorkOrderNum(workOrderNum).get();
            Integer ram = 0, storage = 0, cpuCore = 0;
            for (int i = 0; i < allocatedVms.size(); i++) {
                AllocatedVm tempVm = allocatedVms.get(i);
                ram += tempVm.getRam();
                storage = tempVm.getStorage();
                cpuCore += tempVm.getCpuCore();
            }
            if (!this.virtualComResourceService.updateVmResource(cpuCore, ram, storage, "down")) {
                throw new GlobalException("在分配虚拟机资源时发生错误    生成的虚拟机资源为", allocatedVms);
            }

            //更新部门使用预算
            Staff staff = staffService.queryById(workOrder.getWorkerNum());
            UsedBudget usedBudget = usedBudgetService.queryById(staff.getDepNum());
            usedBudget.setDepUsedBudget(usedBudget.getDepUsedBudget() + workOrder.getPrice());
            if (usedBudgetService.update(usedBudget) == null) {
                throw new GlobalException("更新部门预算时发生错误     计划插入的预算为", usedBudget);
            }

            this.informService.secondLeaderInsertInform(workOrderNum,secondNum, "通过二级审批");

        } else if (state.equals("挂起") || state.equals("审批不通过")) {
            workOrder.setWorkOrderState(state);
            workOrderService.update(workOrder);
            this.informService.secondLeaderInsertInform(workOrderNum, secondNum, state);
        } else
            throw new GlobalException("申请二级审批的工单未通过一级审批    工单编号为", workOrderNum);
        return true;
    }
//查询当前剩余资源
    @GetMapping("checkresource")
    public boolean checkresource(String workOrderNum) throws Exception{


//      对比当前申请的物理机资源
        List<PhysicsComResource> unresource = this.physicsComResourceService.selectAllpc();
        List<AllocatedCom> usedcom = this.allocatedComService.queryByWorkOrderNum(workOrderNum).get();

        List<Integer> unresourcecomnum = new LinkedList<Integer>();
        for (PhysicsComResource i:unresource){
            unresourcecomnum.add(i.getComNum());
        }

        for (AllocatedCom i:usedcom){
            if (!unresourcecomnum.contains(i.getComNum())){
                return false;
            }
        }

//        获取申请的虚拟机资源
        List<AllocatedVm> allocatedVms = this.allocatedVmService.queryByWorkOrderNum(workOrderNum).get();
        Integer ram = 0, storage = 0, cpuCore = 0;
        for (int i = 0; i < allocatedVms.size(); i++) {
            AllocatedVm tempVm = allocatedVms.get(i);
            ram += tempVm.getRam();
            storage = tempVm.getStorage();
            cpuCore += tempVm.getCpuCore();
        }
        VirtualComResource vmresource = this.virtualComResourceService.selectOne();

        if(ram > vmresource.getRam() || storage > vmresource.getStorage() || cpuCore > vmresource.getCpuCore()){
            return false;
        }

        return true;
    }
//    通过工单号查找工单申请时间

    @GetMapping("getordertime")
    public String getordertime(String workOrderNum){
        List<FlowProcess> a = this.flowProcessService.selectApplyTime(workOrderNum);


        Date applytime = a.get(0).getDealDate();
        for (FlowProcess i:a){
//            System.out.println(i.getDealDate());
            if (i.getDealDate().compareTo(applytime) == -1){
                applytime = i.getDealDate();
            }
        }

        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ans;
        ans = sp.format(applytime);
        return ans;
    }
}
