package com.example.back2.controller.staff;

import com.alibaba.fastjson.JSON;
import com.example.back2.entity.table.*;
import com.example.back2.service.table.AllocatedComService;
import com.example.back2.service.table.PhysicsComResourceService;
import com.example.back2.service.table.VmSpecificationsService;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("applyTickets")
public class applyTicket {
    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private VmSpecificationsService vmSpecificationsService;
    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private AllocatedComService allocatedComService;

    /**
     * 查询所有未分配的物理机
     * @return
     */
    @GetMapping("selectAllPc")
    public List<PhysicsComResource> selectAllpc(){
        return this.physicsComResourceService.selectAllpc();
    }

    /**
     * 查询所有虚拟机规格
     * @return
     */
    @GetMapping("selectAllVm")
    public List<VmSpecifications> selectAllvm(){
        return this.vmSpecificationsService.selectAllvm();
    }
//申请工单接口
    @PostMapping("intsertApplyTicket")
    public String intsertApplyTicket(String workOrderName,Date expirationTime,String reason,Integer workernum,String file,Double price){
//        生成工单号，并传入
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String workOrderNum = df.format(d);
        double t1 = Math.random();
        t1 *= 10000;
        int t2 = (int) t1;
        workOrderNum += t2+ "";
//        System.out.println(workOrderNum);

        WorkOrder workOrder = new WorkOrder();
//        修改工单号
        workOrder.setWorkOrderNum(workOrderNum);

//传入时间
        workOrder.setExpirationTime(expirationTime);
//传入工单标题
        workOrder.setWorkOrderName(workOrderName);
//  传入理由描述
        workOrder.setReason(reason);
// 传入申请人工号
        workOrder.setWorkerNum(workernum);
//传入工单类型
        workOrder.setWorkOrderType("申请工单");
//传入附件
        workOrder.setFile(file);
//传入工单总价
        workOrder.setPrice(price);
// 修改工单状态
        workOrder.setWorkOrderState("待审批");
//
        this.workOrderService.insert(workOrder);
//
        return workOrder.getWorkOrderNum();
    }

    @PostMapping("insertAllocatedCom")
    public boolean insertAllocatedCom(String qs){
        Object m = JSON.parse(qs);
        System.out.println(qs);
        return true;
    }

    @PostMapping("insertAllocationVm")
    public boolean insertAllocatedVm(String workOrderNum){
        return true;
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    @PostMapping("/upload")
    public Map<String, Object> fileUpload(MultipartFile file, HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();

        // 得到上传时的文件名
        String originName = file.getOriginalFilename();
        assert originName != null;
        if (!originName.endsWith(".pdf")) {
            resultMap.put("status", "error");
            resultMap.put("msg", "文件类型不对，必须为pdf");

            return resultMap;
        }

        String strFormat = simpleDateFormat.format(new Date());
        //文件保存路径
        String realPath = req.getServletContext().getRealPath("/") + strFormat;

        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 保存文件对象，加上uuid是为了防止文件重名
        String strNewFileName = UUID.randomUUID().toString().replaceAll("-", "") + ".pdf";
        try {
            file.transferTo(new File(folder, strNewFileName));
            String strUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + strFormat + strNewFileName;
            resultMap.put("status", "success");
            resultMap.put("url", strUrl);
        } catch (IOException e) {
            e.printStackTrace();

            resultMap.put("status", "error");
            resultMap.put("msg", e.getMessage());
        }

        return resultMap;
    }
    }
