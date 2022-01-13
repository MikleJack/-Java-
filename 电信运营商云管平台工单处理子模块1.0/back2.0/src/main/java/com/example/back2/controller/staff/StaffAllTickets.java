package com.example.back2.controller.staff;

import com.example.back2.controller.FlowProcessController;
import com.example.back2.entity.table.*;
import com.example.back2.entity.view.AdminsearchorderTable;
import com.example.back2.entity.view.AllocatedVmSpecifications;
import com.example.back2.entity.view.FlowStaff;
import com.example.back2.entity.view.OrderBeginEndTime;
import com.example.back2.service.table.*;
import com.example.back2.service.view.AllocatedVmSpecificationsService;
import com.example.back2.service.view.OrderBeginEndTimeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("staffAllTickets")
public class StaffAllTickets {

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


//----------------------------延期按钮-顶部----------------------------
    /**
     * 通过工单编号进行延期操作
     *
     * @param workOrderNum 工单编号
     * @param delayReason  延期原因
     * @param delayTime  延期日期
     * @return 是否发起延期请求成功
     */
    @GetMapping("delay")
    public ResponseEntity<String> delay(String workOrderNum,
                                        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date delayTime,
                                         String delayReason) {
        //计算工单的持续时间: 单位月
        Long preBeginTime = this.orderBeginEndTimeService.queryBeginTimeByOrderNum(workOrderNum).getTime();
        Long preEndTime = this.orderBeginEndTimeService.queryEndTimeByOrderNum(workOrderNum).getTime();
        Long preDurationTime = ((preEndTime - preBeginTime)/((long)24*60*60*1000*30));

        //计算当前工单持续时间： 单位月
        Date nowDate = new Date();
        Long nowBeginTime = nowDate.getTime();
        Long nowEndTime = delayTime.getTime();
        Long nowDurationTime = ((nowEndTime - nowBeginTime)/(24*60*60*1000));

        //通过 原工单总价/持续时间 * 当前工单持续时间 得到当前工单的总价
        Double prePrice = this.workOrderService.queryPriceById(workOrderNum);
        Double nowPrice = (prePrice / preDurationTime) * nowDurationTime;
        Double nowPricePrecision = Double.valueOf(String.format("%.2f", nowPrice ));

        //        通过时间和随机数生成工单号，并传入
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newWorkOrderNum = df.format(new Date());
        double t1 = Math.random();
        t1 *= 10000;
        String t2 = (int) t1 + "";
        if(t2.length() != 4){
            for(int i = 0;i < 4 - t2.length();i++){
                t2 += '0';
            }
        }else{
            newWorkOrderNum += t2+ "";
        }

        if(this.workOrderService.delay(workOrderNum,newWorkOrderNum,delayTime, delayReason,nowPricePrecision)){
            Integer workerNum = this.workOrderService.queryById(workOrderNum).getWorkerNum();
            this.flowProcessService.DelayInsert(newWorkOrderNum, workerNum, nowDate);
            return ResponseEntity.ok(newWorkOrderNum);
        }else{
            return ResponseEntity.ok("false");
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
    public ResponseEntity<Page<WorkOrder>> parameterQueryByPage(String workOrderType,String workOrderTile, Integer workerNum, int page, int size) {
        System.out.println(workOrderType);
        System.out.println(workOrderTile);
        System.out.println(workerNum);
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.workOrderService.parameterQueryByPage(workOrderType, workOrderTile, workerNum, pageRequest));
    }
//----------------------------查询按钮-底部----------------------------


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


//----------------详情按钮-顶部-------------------------------------------------------
    /**
     * 通过工单编号查询该工单所有物理机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有物理机资源
     */
    @GetMapping("allocatedCom")
    public ResponseEntity<List<AllocatedCom>> allocatedCom(String workOrderNum){
        return ResponseEntity.ok(this.allocatedComService.queryByWorkOrderNum(workOrderNum));
    }

    /**
     * 通过工单编号查询该工单所有虚拟机机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    @GetMapping("allocatedVir")
    public ResponseEntity<List<AllocatedVmSpecifications>> allocatedVir(String workOrderNum){
        return ResponseEntity.ok(this.allocatedVmSpecificationsService.queryVmByWorkOrderNum(workOrderNum));
    }

    /**
     * 通过工单编号查询该工单所有虚拟机机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    @GetMapping("queryBeginAndEndTime")
    public ResponseEntity<OrderBeginEndTime> queryBeginTime(String workOrderNum){
        return ResponseEntity.ok(this.orderBeginEndTimeService.queryById(workOrderNum));
    }

//----------------详情按钮-底部-------------------------------------------------------



}
