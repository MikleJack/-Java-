package com.example.back2.controller.staff;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.back2.entity.table.*;
import com.example.back2.service.table.*;
import com.example.back2.utils.GenerateIP;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("applyTickets")
public class applyTicket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private VmSpecificationsService vmSpecificationsService;
    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private AllocatedComService allocatedComService;
    @Resource
    private AllocatedVmService allocatedVmService;

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
    public String intsertApplyTicket(WorkOrder workorder){
        logger.info("员工编号为" + workorder.getWorkerNum() + "的员工进行了工单申请");

//        生成工单号，并传入
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String workOrderNum = df.format(d);
        int randomNum = (int) ((Math.random() * 9 + 1) * 1000);
        workOrderNum += randomNum+ "";
//        修改工单号
        workorder.setWorkOrderNum(workOrderNum);
//        System.out.println(workOrder.getWorkOrderNum());
        return this.workOrderService.insert(workorder).getWorkOrderNum();

    }

    @PostMapping("insertAllocatedCom")
    public boolean insertAllocatedCom(String qs,String workOrderNum){
        JSONArray m = JSON.parseArray(qs);
        for (int i =0;i<m.size();i++){
            AllocatedCom allocatedCom = m.getObject(i,AllocatedCom.class);
            allocatedCom.setWorkOrderNum(workOrderNum);
            this.allocatedComService.insert(allocatedCom);
        }
        return true;
    }

    @PostMapping("insertAllocatedVm")
    public boolean insertAllocatedVm(String qs, String workOrderNum, Integer storage,String os){
        JSONArray m = JSON.parseArray(qs);
        for (int i =0;i<m.size();i++){
            GenerateIP generateIP = new GenerateIP();
            AllocatedVm allocatedVm = m.getObject(i,AllocatedVm.class);
            allocatedVm.setWorkOrderNum(workOrderNum);
            allocatedVm.setStorage(storage);
            allocatedVm.setOs(os);
            allocatedVm.setIp(generateIP.createip());
            this.allocatedVmService.insert(allocatedVm);
        }
        return true;
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    @PostMapping("/upload")
    public Map<String, Object> fileUpload(MultipartFile file, HttpServletRequest req) throws IOException{
        Map<String, Object> resultMap = new HashMap<>();

        logger.info("有文件被上传",file);
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
        System.out.println("保存文件的路径：" +realPath);

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
