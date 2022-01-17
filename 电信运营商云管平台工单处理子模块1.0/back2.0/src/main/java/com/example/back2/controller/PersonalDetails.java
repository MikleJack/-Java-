package com.example.back2.controller;

import com.example.back2.entity.table.Staff;
import com.example.back2.entity.view.StaffhomePersoninform;
import com.example.back2.service.impl.table.StaffServiceImpl;
import com.example.back2.service.table.StaffService;
import com.example.back2.service.view.StaffhomePersoninformService;
import com.example.back2.utils.SHA_256;
import com.sun.org.apache.xpath.internal.axes.ChildTestIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("personaldetails")
public class PersonalDetails {
    @Autowired
//    @Resource
    private StaffService staffService = new StaffServiceImpl();


//员工修改密码接口
    @PostMapping("changepw")
    public boolean changepw(Integer workernum,String oldpw,String newpw){
//        取出原来的旧密码进行对比
        Staff p = this.staffService.queryById(workernum);

        if(p == null){
            System.out.println("不存在该员工");
        }

        String realoldpw = p.getPassword();
        String inputoldpw = SHA_256.getSHA256(oldpw);
        if(realoldpw.equals(inputoldpw)){
//            比对成功将新密码进行加密然后修改
            String pw = SHA_256.getSHA256(newpw);
            this.staffService.changepw(pw,workernum);
            System.out.println("修改成功");
            return true;
        }
        System.out.println("修改失败");
        return false;
    }
//修改电话号码
    @PostMapping("changephone")
    public boolean changephone(Integer workernum,String phone){
        return this.staffService.changephone(workernum,phone);
    }
//    个人信息
    @Resource
    private StaffhomePersoninformService staffhomePersoninformService;
    @GetMapping("queryPersonInformById")
    public ResponseEntity<StaffhomePersoninform> queryPersonInformById(Integer workernum) {
        return ResponseEntity.ok(this.staffhomePersoninformService.queryPersonInformById(workernum));
    }


}
