package com.example.back2.controller.admin;


import com.example.back2.entity.table.VmSpecifications;
import com.example.back2.service.table.PhysicsComResourceService;
import com.example.back2.service.table.VmSpecificationsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    /**
     * 改变虚拟机总资源
     *
     * @param virtualComResource 参数
     * @return 返回
     */


    @PutMapping("updateVm")
    public VirtualComResource updateResource(VirtualComResource virtualComResource){
        return this.virtualComResourceService.update(virtualComResource);
    }
}
