package com.example.back2.controller.admin;


import com.example.back2.entity.VirtualComResource;
import com.example.back2.entity.table.Admin;
import com.example.back2.entity.table.Department;
import com.example.back2.entity.table.VmSpecifications;
import com.example.back2.service.impl.table.AdminServiceImpl;
import com.example.back2.service.table.*;
import com.example.back2.utils.SHA_256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("adminHome")
public class AdminHome {

    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private VmSpecificationsService vmSpecificationsService;
    private String initPassword="brccq123456";
    //-----------------------------------主页配置预算，虚拟机，物理机资源模块---------------------------
    @PostMapping("insertPhysics")
    public boolean insertPhysics(int cpuCore, int ram, int storage, int price){
        this.physicsComResourceService.insertPhysics(cpuCore,ram,storage,price);
        return true;
    }

    @PostMapping("insertVirtual")
    public boolean insertVirtual(VmSpecifications vmSpecifications){
        vmSpecifications.setDiskPrice(0.5);
        this.vmSpecificationsService.insert(vmSpecifications);
        return true;
    }

    @Resource
    private VirtualComResourceService virtualComResourceService;
    @Resource

    @Autowired
    AdminService temp =new AdminServiceImpl();

    /**
     * 改变虚拟机总资源
     *
     * @return 返回
     */


    @PutMapping("updateVm")
    public Boolean updateResource(Integer cpuCore ,Integer ram,Integer storage){
        return this.virtualComResourceService.updateVmResource( cpuCore , ram, storage,"update");
    }

    /**
     * 验证管理员密码
     * @param password 密码
     * @return 正确错误
     */
    @PostMapping("confirmPassword")
    public Boolean confirm(String password){
        password = SHA_256.getSHA256(password);

        Admin admin = this.temp.queryById("root");

        if (password.equals(admin.getPassword())){
            return true;
        }
        else
            return false;
    }

    /**
     * 得到总的虚拟机资源
     * @return 返回
     */
    @GetMapping("getVm")
    public VirtualComResource getVm(){
        return this.virtualComResourceService.selectOne();
    }

    @Resource
    private DepartmentService departmentService;

    @GetMapping("getDepBudget")
    public List<Department> getDepBudget(){
        return this.departmentService.getDepBudget();
    }
}
