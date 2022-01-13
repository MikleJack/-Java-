package com.example.back2.controller.staff;

import com.example.back2.entity.table.WorkOrder;
import com.example.back2.entity.view.OrderBeginEndTime;
import com.example.back2.service.table.WorkOrderService;
import com.example.back2.service.view.OrderBeginEndTimeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("staffAllTickets")
public class StaffAllTickets {

    @Resource
    private WorkOrderService workOrderService;

    @Resource
    private OrderBeginEndTimeService orderBeginEndTimeService;


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
    public ResponseEntity<Boolean> delay(String workOrderNum,
                                         @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date delayTime,
                                         String delayReason) {
        //计算工单的持续时间: 单位月
        Long preBeginTime = this.orderBeginEndTimeService.queryBeginTimeByOrderNum(workOrderNum).getTime();
        Long preEndTime = this.orderBeginEndTimeService.queryEndTimeByOrderNum(workOrderNum).getTime();
        Long preDurationTime = ((preEndTime - preBeginTime)/((long)24*60*60*1000*30));

        //计算当前工单持续时间： 单位月
        Long nowBeginTime = (new Date()).getTime();
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

        return ResponseEntity.ok(this.workOrderService.delay(workOrderNum,newWorkOrderNum,delayTime, delayReason,nowPricePrecision));
    }

//----------------------------延期按钮-底部----------------------------


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


}
