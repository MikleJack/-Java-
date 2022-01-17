package com.example.back2.controller.admin;

import com.example.back2.entity.table.Admin;
import com.example.back2.service.table.AdminService;
import com.example.back2.utils.SHA_256;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("admindetails")
public class AdminDetails {

    @Resource
    private AdminService adminService;

    @PostMapping("changepw")
    public boolean changepw(String admin_num,String oldpw,String newpw){
//        验证输入的原密码是否正确

        String encryptoldpw = SHA_256.getSHA256(oldpw);


        boolean f = encryptoldpw.equals(this.adminService.queryById(admin_num).getPassword());

        if(f){
//            验证通过进行密码修改
//            对新密码进行加密

            String npw = SHA_256.getSHA256(newpw);
//            进行修改
            this.adminService.changepw(admin_num,npw);
            return true;
        }
        return false;
    }
}
