package com.example.back2.controller.admin;


import com.example.back2.entity.VirtualComResource;
import com.example.back2.entity.table.Admin;
import com.example.back2.entity.table.Department;
import com.example.back2.entity.table.PhysicsComResource;
import com.example.back2.entity.table.VmSpecifications;
import com.example.back2.entity.view.Adminaccountmanage;
import com.example.back2.service.impl.table.AdminServiceImpl;
import com.example.back2.service.table.*;
import com.example.back2.utils.SHA_256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("adminHome")
public class AdminHome {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PhysicsComResourceService physicsComResourceService;
    @Resource
    private VmSpecificationsService vmSpecificationsService;
    private String initPassword="brccq123456";
    //-----------------------------------主页配置预算，虚拟机，物理机资源模块---------------------------
    @PostMapping("insertPhysics")
    public boolean insertPhysics(int cpuCore, int ram, int storage, int price){
        logger.info("有管理员新增了物理机资源，cup核心、内存、存储、价格 分别为" + cpuCore + ram + storage + price);

        this.physicsComResourceService.insertPhysics(cpuCore,ram,storage,price);
        return true;
    }

    @PostMapping("insertVirtual")
    public boolean insertVirtual(VmSpecifications vmSpecifications){
        logger.info("有管理员新增了虚拟机资源" , vmSpecifications);

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
        logger.info("有管理员重置了虚拟机资源，cup核心、内存、存储 分别为" + cpuCore + ram + storage );

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

    /**
     * 得到部门预算
     * @return
     */
    @GetMapping("getDepBudget")
    public List<Department> getDepBudget(){
        return this.departmentService.getDepBudget();
    }


    @PutMapping("setDiskPrice")
    public Boolean setDiskPrice(Double diskPrice){
        return this.vmSpecificationsService.updateDiskPrice(diskPrice);
    }


    @GetMapping("getAllPhy")
    public ResponseEntity<Page<PhysicsComResource>> getAllPhy(int page,int size) throws Exception{
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.physicsComResourceService.queryAllByLimit(pageRequest).get());
    }

    @GetMapping("getAllVm")
    public ResponseEntity<Page<VmSpecifications>> getAllVm(int page,int size) throws Exception{
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.vmSpecificationsService.queryAllByLimit(pageRequest).get());
    }

    //    改变部门预算
    @PutMapping("setDepBudget")
    public boolean setDepBudget(Integer DepNum,double DepBudget){
        if(this.departmentService.setDepBudget(DepNum,DepBudget) != 0){
            return true;
        }
        return false;
    }


//    改变虚拟机每月定价
    @PutMapping("setVmPrice")
    public boolean setVmPrice(Integer comNum,double price){
        if(this.vmSpecificationsService.setVmPrice(comNum,price) != 0){
            return true;
        }
        return false;
    }
//    改变物理机每月定价
    @PutMapping("setPhyPrice")
    public boolean setPhyPrice(Integer comNum,Integer price){
        if (this.physicsComResourceService.setPhyPrice(comNum,price) != 0){
            return true;
        }
        return false;
    }
}
