package com.example.back2.controller.admin;

import com.example.back2.entity.table.OperationLog;
import com.example.back2.entity.view.Adminoperationlog;
import com.example.back2.service.table.OperationLogService;
import com.example.back2.service.view.AdminoperationlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("Log")
public class manageLog {

    @Resource
    private AdminoperationlogService adminoperationlogService;


    @GetMapping("query")
    public ResponseEntity<Page<Adminoperationlog>> queryByPage2(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of( page,size);
        return ResponseEntity.ok(this.adminoperationlogService.queryByPage( name, pageRequest));
    }
}
