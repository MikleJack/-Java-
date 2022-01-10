package com.example.demo.controller;

import com.example.demo.entity.AdminOperationLog;
import com.example.demo.service.AdminOperationLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (AdminOperationLog)表控制层
 *
 * @author makejava
 * @since 2022-01-07 09:39:06
 */
@RestController
@RequestMapping("adminOperationLog")
public class AdminOperationLogController {
    /**
     * 服务对象
     */
    @Resource
    private AdminOperationLogService adminOperationLogService;


    /**
     * 分页查询
     *
     *@param  page 初始页面号
     * @param size 分页大小
     * @return 查询结果
     */
    @GetMapping("queryByPage")
    public ResponseEntity<Page<AdminOperationLog>> queryByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.adminOperationLogService.queryByPage(pageRequest));
    }


    /**
     * 新增数据
     *
     * @param adminOperationLog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<AdminOperationLog> add(AdminOperationLog adminOperationLog) {
        return ResponseEntity.ok(this.adminOperationLogService.insert(adminOperationLog));
    }


}

