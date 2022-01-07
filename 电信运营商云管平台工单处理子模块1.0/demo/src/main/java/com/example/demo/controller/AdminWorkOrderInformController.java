package com.example.demo.controller;

import com.example.demo.entity.AdminWorkOrderInform;
import com.example.demo.entity.adminSearchWorkOrderDetail.AdminWorkorderSingleDetail;
import com.example.demo.service.AdminWorkOrderInformService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (AdminWorkOrderInform)表控制层
 *
 * @author makejava
 * @since 2022-01-07 10:47:48
 */
@RestController
@RequestMapping("adminWorkOrderInform")
public class AdminWorkOrderInformController {
    /**
     * 服务对象
     */
    @Resource
    private AdminWorkOrderInformService adminWorkOrderInformService;

    /**
     * 分页查询
     *
     *@param  page 初始页面号
     * @param size 分页大小
     * @return 查询结果
     */
    @GetMapping("queryByPage")
    public ResponseEntity<Page<AdminWorkOrderInform>> queryByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.adminWorkOrderInformService.queryByPage(pageRequest));
    }

    /**
     * 新增数据
     *
     * @param adminWorkOrderInform 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<AdminWorkOrderInform> add(AdminWorkOrderInform adminWorkOrderInform) {
        return ResponseEntity.ok(this.adminWorkOrderInformService.insert(adminWorkOrderInform));
    }

    /**
     * 管理员工单界面的详情按钮
     *此处只返回上半部分信息，下半部分信息调用其他接口
     *
     * @param workOrderNum 工单编号
     * @return 每个工单详情页面上的员工信息、部门信息、工单信息
     */
    @GetMapping("queryWorkOrderDetail")
    public ResponseEntity<AdminWorkorderSingleDetail> queryWorkOrderDetail(String workOrderNum) {
        return ResponseEntity.ok(this.adminWorkOrderInformService.queryWorkOrderDetail(workOrderNum));
    }

}

