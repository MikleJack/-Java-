package com.example.back2.controller.login;

import com.example.back2.service.table.AdminService;
import com.example.back2.utils.SHA_256;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("login")
public class login {

    @Resource
    private AdminService adminService;

    @GetMapping("admin")
    public ResponseEntity<Boolean> adminLogin(String work_num, String password){
        if(!work_num.equals("") && !password.equals("") ) {
            if (adminService.queryById(work_num) != null)
            {
                password = SHA_256.getSHA256(password);
                return ResponseEntity.ok(password.equals(this.adminService.queryById(work_num).getPassword()));
            }
            else
                return ResponseEntity.ok(false);
        }
        else
            return ResponseEntity.ok(false);
    }

}
