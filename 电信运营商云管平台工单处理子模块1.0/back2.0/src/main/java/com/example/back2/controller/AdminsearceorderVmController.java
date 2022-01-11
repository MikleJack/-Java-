package com.example.back2.controller;

import com.example.back2.entity.view.AdminsearceorderVm;
import com.example.back2.service.view.AdminsearceorderVmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (AdminsearceorderVm)表控制层
 *
 * @author makejava
 * @since 2022-01-11 18:53:15
 */
@RestController
@RequestMapping("adminsearceorderVm")
public class AdminsearceorderVmController {
    /**
     * 服务对象
     */
    @Resource
    private AdminsearceorderVmService adminsearceorderVmService;

    /**
     * 分页查询
     *
     * @param adminsearceorderVm 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<AdminsearceorderVm>> queryByPage(AdminsearceorderVm adminsearceorderVm, PageRequest pageRequest) {
        return ResponseEntity.ok(this.adminsearceorderVmService.queryByPage(adminsearceorderVm, pageRequest));
    }
}

