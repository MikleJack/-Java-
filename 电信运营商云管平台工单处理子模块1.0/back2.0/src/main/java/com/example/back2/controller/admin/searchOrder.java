package com.example.back2.controller.admin;


import com.example.back2.dao.view.AdminsearchorderComDao;
import com.example.back2.entity.view.AdminsearceorderVm;
import com.example.back2.entity.view.AdminsearchorderCom;
import com.example.back2.entity.view.AdminsearchorderDetailperson;
import com.example.back2.entity.view.AdminsearchorderTable;
import com.example.back2.service.table.DepartmentService;
import com.example.back2.service.view.AdminsearceorderVmService;
import com.example.back2.service.view.AdminsearchorderComService;
import com.example.back2.service.view.AdminsearchorderDetailpersonService;
import com.example.back2.service.view.AdminsearchorderTableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("adminSearchOrder")
public class searchOrder {

//----------------首页表单显示-顶部--------------------------------------------------------

    @Resource
    private AdminsearchorderTableService adminsearchorderTableService;

        /**
     * 带条件的分页查询
     *
     * @param adminsearchorderTable
     * @return 查询结果+
     */
    @PostMapping("criteriaQueryByPage")
    public ResponseEntity<Page<AdminsearchorderTable>> criteriaQueryByPage(AdminsearchorderTable adminsearchorderTable) throws Exception{
        PageRequest pageRequest = PageRequest.of(0,8);
        return ResponseEntity.ok(this.adminsearchorderTableService.queryByPage(adminsearchorderTable, pageRequest).get());
    }

    /**
     * 带条件的分页查询：只传参数
     *
     * @param workOrderType 工单类型
     * @param workerName 工人姓名
     * @return 查询结果
     */
    @GetMapping("parameterQueryByPage")
    public ResponseEntity<Page<AdminsearchorderTable>> parameterQueryByPage(String workOrderType,String workerName,int page,int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.adminsearchorderTableService.parameterQueryByPage(workOrderType,workerName, pageRequest));
    }

    /**
     * 无条件的分页查询
     * @param page 分页页面
     * @param size 分页大小
     *
     * @return 查询结果
     */
    @GetMapping("normalQueryByPage")
    public ResponseEntity<Page<AdminsearchorderTable>> normalQueryByPage(int page,int size) throws  Exception{
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.adminsearchorderTableService.normalQueryByPage(pageRequest).get());
    }


//---------------------------首页表单显示-底部-----------------------------------------------------------



//-----------------------------详情按钮-顶部---------------------------
    @Resource
    AdminsearchorderDetailpersonService adminsearchorderDetailpersonService;

    /**
     * 管理员工单界面的详情按钮
     *此处只返回上半部分信息，下半部分信息调用其他接口
     *
     * @param workOrderNum 工单编号
     * @return 每个工单详情页面上的员工信息、部门信息、工单信息
     */
    @GetMapping("queryWorkOrderDetailTop")
    public ResponseEntity<AdminsearchorderDetailperson> queryWorkOrderDetailTop(String workOrderNum) throws Exception{

        return ResponseEntity.ok(this.adminsearchorderDetailpersonService.queryWorkOrderDetailTop(workOrderNum).get());

    }

    @Resource
    private AdminsearchorderComService adminsearchorderComService;

    /**
     *
     * @param workOrderNum 工单号
     * @return 返回该工单申请的物理机信息
     */
    @GetMapping("getOrderCom")
    public List<AdminsearchorderCom> getOrderCom(String workOrderNum) throws Exception{
        return this.adminsearchorderComService.getOrderCom(workOrderNum).get();
    }

    @Resource
    private AdminsearceorderVmService adminsearceorderVmService;

    /**
     *
     * @param workOrderNum 工单号
     * @return 返回该工单申请的虚拟机信息
     */
    @GetMapping("getOrderVm")
    public List<AdminsearceorderVm> getOrderVm(String workOrderNum) throws Exception{
        return this.adminsearceorderVmService.getOrderVm(workOrderNum).get();
    }


    @Resource
    private DepartmentService departmentService;

    /**
     *
     * @param depNum 部门号
     * @return 返回该部门预算
     */
    @GetMapping("getDepBudget")
    public Double getDepBudget(Integer depNum){
        return departmentService.queryById(depNum).getDepBudget();
    }




//-----------------------------详情按钮-底部---------------------------
}
