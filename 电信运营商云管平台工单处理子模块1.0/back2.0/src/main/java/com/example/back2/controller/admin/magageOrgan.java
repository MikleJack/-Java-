package com.example.back2.controller.admin;

import com.example.back2.entity.table.Department;
import com.example.back2.entity.view.Adminorganmanage;
import com.example.back2.service.table.DepartmentService;
import com.example.back2.service.view.AdminorganmanageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Organ")
public class magageOrgan {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private AdminorganmanageService adminorganmanageService;

    @GetMapping("getTree")
    public Department getTree(){
        Department one = this.departmentService.queryById(1);
        List<Adminorganmanage> temp = this.adminorganmanageService.queryById(1);

        return one;
    }
}
