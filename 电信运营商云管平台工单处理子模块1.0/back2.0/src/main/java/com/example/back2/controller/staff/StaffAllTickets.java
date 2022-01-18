package com.example.back2.controller.staff;

import com.example.back2.entity.table.WorkOrderDelay;
import com.example.back2.entity.table.*;
import com.example.back2.entity.view.AllocatedVmSpecifications;
import com.example.back2.entity.view.OrderBeginEndTime;
import com.example.back2.exception.GlobalException;
import com.example.back2.service.table.*;
import com.example.back2.service.view.AllocatedVmSpecificationsService;
import com.example.back2.service.view.OrderBeginEndTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;

@RestController
@RequestMapping("staffAllTickets")
public class StaffAllTickets {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WorkOrderService workOrderService;

    @Resource
    private OrderBeginEndTimeService orderBeginEndTimeService;

    @Resource
    private FlowProcessService flowProcessService;

    @Resource
    private AllocatedComService allocatedComService;
    @Resource
    private AllocatedVmSpecificationsService allocatedVmSpecificationsService;
    @Resource
    private AllocatedVmService allocatedVmService;

    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private VirtualComResourceService virtualComResourceService;
    @Resource
    private HisResourceUsageService hisResourceUsageService;

    @Resource
    private InformService informService;

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
    public ResponseEntity<Page<WorkOrder>> criteriaQueryByPage(Integer workerNum, int page, int size) throws Exception {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.workOrderService.criteriaQueryByPage(workerNum,pageRequest).get());
    }

//----------------首页表单显示-底部-------------------------------------------------------


//----------------------------延期按钮-顶部----------------------------
    /**
     * 通过工单编号进行延期操作
     *
     * @param workOrderNum 工单编号
     * @param delayReason  延期原因
     * @param delayTime  延期日期
     * @return 是否发起延期请求成功
     */
    @PostMapping("delay")
    public ResponseEntity<String> delay(String workOrderNum,
                                        Date delayTime,
                                         String delayReason)throws Exception  {
        logger.info("持有工单编号" + workOrderNum + "的员工对该工单发起了延期要求");

        //计算工单的持续时间: 单位月
        Long preBeginTime = this.orderBeginEndTimeService.queryBeginTimeByOrderNum(workOrderNum).getTime();
        Long preEndTime = this.orderBeginEndTimeService.queryEndTimeByOrderNum(workOrderNum).getTime();
        Long tempTime = preEndTime - preBeginTime;
        Long preDurationTime = tempTime / (24*60*60*1000);
//        Long preDurationTime = ((preEndTime - preBeginTime)/((long)24*60*60*1000)/(long)30);

        if(preDurationTime <= 0 ){
            throw new GlobalException("发起延期申请，计算原工单持续时间发生错误,可能错误出于数据库的持续时间表     计算的持续时间", preDurationTime);
        }

        //计算当前工单持续时间： 单位月
        Date nowDate = new Date();
        Long nowBeginTime = nowDate.getTime();
        Long nowEndTime = delayTime.getTime();
        Long nowDurationTime = ((nowEndTime - nowBeginTime)/(24*60*60*1000));

        //通过 原工单总价/持续时间 * 当前工单持续时间 得到当前工单的总价
        Double prePrice = this.workOrderService.queryPriceById(workOrderNum).get();
        Double nowPrice = (prePrice / preDurationTime) * nowDurationTime;
        Double nowPricePrecision = Double.valueOf(String.format("%.2f", nowPrice));

        if(nowPricePrecision <= 0 ){
            throw new GlobalException("发起延期申请，计算延期工单总价时发生错误     date为计算的持续时间", preDurationTime);
        }

        //        生成工单号，并传入
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newWorkOrderNum = df.format(d);
        int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
        newWorkOrderNum += randomNum+ "";
//        修改工单号
//        workorder.setWorkOrderNum(workOrderNum);

        if(newWorkOrderNum.length() != 18){
            throw new GlobalException("准备新生成的新工单号不为18位    date为生成的工单编号为", newWorkOrderNum);
        }

        if(this.workOrderService.delay(workOrderNum,newWorkOrderNum,delayTime, delayReason,nowPricePrecision)){
            //插入流转过程
            Integer workerNum = this.workOrderService.queryById(workOrderNum).getWorkerNum();
            this.flowProcessService.DelayInsert(newWorkOrderNum, workerNum, nowDate);

            //记录延期工单和原工单的关系
            WorkOrderDelay workOrderDelay = new WorkOrderDelay();
            workOrderDelay.setOldOrder(workOrderNum);
            workOrderDelay.setWorkOrderNum(newWorkOrderNum);

            this.informService.staffApplyInsertInform(newWorkOrderNum,
                    this.workOrderService.queryById(workOrderNum).getWorkerNum(),"发起了延期工单");
            return ResponseEntity.ok(newWorkOrderNum);
        }else{
            throw new GlobalException("发起延期申请时后台生成新工单或生成流转过程发生错误    date为新生成的工单号为",newWorkOrderNum);
        }
    }

//----------------------------延期按钮-底部----------------------------


//----------------------------查询按钮-顶部----------------------------
    /**
     * 带条件的分页查询：只传参数
     *
     * @param workOrderType 工单类型
     * @param workerNum 工人编号
     * @return 查询结果
     */
    @GetMapping("parameterQueryByPage")
    public ResponseEntity<Page<WorkOrder>> parameterQueryByPage(String workOrderType,String workOrderTile, Integer workerNum, int page, int size) throws Exception{
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.workOrderService.parameterQueryByPage(workOrderType, workOrderTile, workerNum, pageRequest).get());
    }
//----------------------------查询按钮-底部----------------------------


//----------------------------下线按钮-顶部----------------------------
    /**
     * 通过工单编号分页查询
     *
     * @param workOrderNum 工单编号
     * @param offlineReason  下线原因
     * @param workOrderState  工单状态
     * @return 用户的全部工单
     */
    @GetMapping("offline")
    public ResponseEntity<Boolean> offline(String workOrderNum,
                                           String workOrderState,
                                           String offlineReason) throws Exception {
        if(workOrderState.equals("二级审批通过")) {
            //批量下线物理机资源
            List<AllocatedCom> allocatedComs = this.allocatedComService.queryByWorkOrderNum(workOrderNum).get();
            List<Integer> comNums = new ArrayList<Integer>();
            for(int i = 0; i <allocatedComs.size();i++) {
                comNums.add(allocatedComs.get(i).getComNum());
            }
            this.physicsComResourceService.setComAssign(comNums,true);

            //批量下线虚拟机资源
            List<AllocatedVm> allocatedVms = this.allocatedVmService.queryByWorkOrderNum(workOrderNum).get();
            Integer ram = 0,storage = 0 ,cpuCore = 0;
            for (int i = 0; i < allocatedVms.size(); i++){
                AllocatedVm tempVm = allocatedVms.get(i);
                ram += tempVm.getRam();
                storage = tempVm.getStorage();
                cpuCore += tempVm.getCpuCore();
            }
            this.virtualComResourceService.updateVmResource(cpuCore,ram,storage,"up");

            HisResourceUsage hisResourceUsage = new HisResourceUsage();
            hisResourceUsage.setResUtilization(Double.valueOf(String.format("%.2f", Math.random() * 100)));
            hisResourceUsage.setWorkOrderNum(workOrderNum);
            if(hisResourceUsageService.insert(hisResourceUsage) == null){
                throw new GlobalException("在生成工单的历史资源利用率时发生错误   date为试图插入的记录", hisResourceUsage);
            }

            if(this.workOrderService.offline(workOrderNum, offlineReason)){
                return ResponseEntity.ok(true);
            }else{
                throw new GlobalException("插入延期工单时发生错误  date为新生成的工单号", workOrderNum);
            }
        }else{
            throw new GlobalException("项目未通过二级审核    date为工单类型" , workOrderState);
        }
    }

//----------------------------下线按钮-底部----------------------------


//----------------详情按钮-顶部-------------------------------------------------------
    /**
     * 通过工单编号查询该工单所有物理机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有物理机资源
     */
    @GetMapping("allocatedCom")
    public ResponseEntity<List<AllocatedCom>> allocatedCom(String workOrderNum) throws Exception{
        return ResponseEntity.ok(this.allocatedComService.queryByWorkOrderNum(workOrderNum).get());
    }

    /**
     * 通过工单编号查询该工单所有虚拟机机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    @GetMapping("allocatedVir")
    public ResponseEntity<List<AllocatedVmSpecifications>> allocatedVir(String workOrderNum) throws Exception{
        return ResponseEntity.ok(this.allocatedVmSpecificationsService.queryVmByWorkOrderNum(workOrderNum).get());
    }

    /**
     * 通过工单编号查询该工单的开始和结束时间
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    @GetMapping("queryBeginAndEndTime")
    public ResponseEntity<OrderBeginEndTime> queryBeginTime(String workOrderNum) throws Exception{
        return ResponseEntity.ok(this.orderBeginEndTimeService.queryById(workOrderNum).get());
    }

    /**
     * 通过工单编号查询该工单的资源利用率
     *
     * @param workOrderNum 工单编号
     * @return 该工单的资源利用率
     */
    @GetMapping("queryResourceUsage")
    public ResponseEntity<HisResourceUsage> queryResourceUsage(String workOrderNum) throws Exception{
        return ResponseEntity.ok(this.hisResourceUsageService.queryById(workOrderNum).get());
    }

//----------------详情按钮-底部-------------------------------------------------------



}
