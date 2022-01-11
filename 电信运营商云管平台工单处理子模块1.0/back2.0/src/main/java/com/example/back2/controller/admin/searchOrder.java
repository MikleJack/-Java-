package com.example.back2.controller.admin;


import com.example.back2.entity.view.AdminsearchorderDetailperson;
import com.example.back2.entity.view.AdminsearchorderTable;
import com.example.back2.service.view.AdminsearchorderDetailpersonService;
import com.example.back2.service.view.AdminsearchorderTableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * @return 查询结果
     */
    @PostMapping("criteriaQueryByPage")
    public ResponseEntity<Page<AdminsearchorderTable>> criteriaQueryByPage(@RequestBody AdminsearchorderTable adminsearchorderTable) {
        PageRequest pageRequest = PageRequest.of(0,8);
        return ResponseEntity.ok(this.adminsearchorderTableService.queryByPage(adminsearchorderTable, pageRequest));
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
    public ResponseEntity<Page<AdminsearchorderTable>> normalQueryByPage(int page,int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.adminsearchorderTableService.normalQueryByPage(pageRequest));
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
    public ResponseEntity<AdminsearchorderDetailperson> queryWorkOrderDetailTop(String workOrderNum) {
        return ResponseEntity.ok(this.adminsearchorderDetailpersonService.queryWorkOrderDetailTop(workOrderNum));
    }





//-----------------------------详情按钮-底部---------------------------
}
