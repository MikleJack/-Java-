package com.example.back2.controller.staff;

import com.example.back2.entity.view.AdminsearchorderTable;
import com.example.back2.entity.view.StaffhomePersoninform;
import com.example.back2.service.table.WorkOrderService;
import com.example.back2.service.view.StaffhomePersoninformService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("staffHome")
public class StaffHome {

//----------------左上角员工信息界面-顶部-------------------------------
    @Resource
    private StaffhomePersoninformService staffhomePersoninformService;

    /**
     * 通过员工编号查询员工信息
     *
     * @param workerNum 员工编号
     * @return 查询结果
     */
    @GetMapping("queryPersonInformById")
    public ResponseEntity<StaffhomePersoninform> queryPersonInformById(Integer workerNum) {
        return ResponseEntity.ok(this.staffhomePersoninformService.queryPersonInformById(workerNum));
    }

//----------------左上角员工信息界面-底部部-------------------------------

// 获取该工号人的待审批工单数量
    @Resource
    WorkOrderService workOrderService;
    @GetMapping("getNumPendticket")
    public long getNumPendticket(Integer workerNum) throws Exception{
        return this.workOrderService.getNumPendticket(workerNum);
    }
//获取该工号人的审批不通过工单数量
    @GetMapping("getNumFailedtickets")
    public long getNumFailedtickets(Integer workerNum) {
        return this.workOrderService.getNumFailedtickets(workerNum);
    }
//   即将过期工单数量
    @GetMapping("getAboutTicket")
    public long getAboutTicket(Integer workerNum){
        Date now = new Date();
        Date ddl = new Date();
        Calendar c = new GregorianCalendar();
        c.add(c.DATE,15);
        ddl = c.getTime();

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(s.format(now));

        return this.workOrderService.getAboutTicket(workerNum,now);
    }
}
