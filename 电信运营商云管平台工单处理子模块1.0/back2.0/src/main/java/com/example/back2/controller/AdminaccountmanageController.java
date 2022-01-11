package com.example.back2.controller;

import com.example.back2.entity.view.Adminaccountmanage;
import com.example.back2.service.view.AdminaccountmanageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Adminaccountmanage)表控制层
 *
 * @author makejava
 * @since 2022-01-11 10:09:09
 */
@RestController
@RequestMapping("adminaccountmanage")
public class AdminaccountmanageController {
    /**
     * 服务对象
     */
    @Resource
    private AdminaccountmanageService adminaccountmanageService;

    /**
     * 分页查询
     *

     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Adminaccountmanage>> queryByPage( PageRequest pageRequest) {
        return ResponseEntity.ok(this.adminaccountmanageService.queryByPage( pageRequest));
    }


}

