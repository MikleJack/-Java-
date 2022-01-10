package com.example.back2.controller.login;

import com.example.back2.entity.table.Staff;
import com.example.back2.service.impl.table.AdminServiceImpl;
import com.example.back2.service.impl.table.StaffServiceImpl;
import com.example.back2.service.table.AdminService;
import com.example.back2.service.table.StaffService;
import com.example.back2.utils.SHA_256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("login")
public class login {

    @Resource
    private AdminService adminService;
    private StaffService staffService;

    private String initPassword="brccq123456";


    //必须通过Autowired注解来生成其他服务类
    @Autowired
    AdminService temp =new AdminServiceImpl();
    @Autowired
    StaffService staff_temp = new StaffServiceImpl();

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

    @GetMapping("user")
    public int userLogin(Integer work_num, String password){
        if(!password.equals("") ) {
            Staff staff = this.staff_temp.queryById(work_num);
            if (staff.getState() && staff.getInService())
            {
                password = SHA_256.getSHA256(password);
                if(password.equals(staff.getPassword())){
                    if(staff.getDepNum()==1)
                        return 1;
                    else
                        return 2;
                }
                else
                    return 0;
            }
            else
                return 0;
        }
        else
            return 0;
    }

}
