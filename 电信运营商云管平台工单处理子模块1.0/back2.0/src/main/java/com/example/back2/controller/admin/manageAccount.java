package com.example.back2.controller.admin;

import com.example.back2.entity.table.Admin;
import com.example.back2.entity.table.Staff;
import com.example.back2.entity.view.Adminaccountmanage;
import com.example.back2.service.impl.table.AdminServiceImpl;
import com.example.back2.service.impl.table.StaffServiceImpl;
import com.example.back2.service.table.AdminService;
import com.example.back2.service.table.StaffService;
import com.example.back2.service.view.AdminaccountmanageService;
import com.example.back2.utils.SHA_256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("account")
public class manageAccount {

    @Resource
    private StaffService staffService;
    @Resource
    private AdminaccountmanageService adminaccountmanageService;

    @Autowired
    AdminService temp =new AdminServiceImpl();
    @Autowired
    StaffService staff_temp = new StaffServiceImpl();
    private String initPassword="brccq123456";

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页大小
     * @return 查询结果
     */
    @GetMapping("all")
    public ResponseEntity<Page<Adminaccountmanage>> queryByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.adminaccountmanageService.queryByPage( pageRequest));
    }

    @GetMapping("reset")
    public ResponseEntity<Boolean> ResetStaffPassword(Integer work_num,String root_num, String password){
        System.out.println(work_num+" "+root_num+" "+password);
        if (!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                staffService.updatePassword(work_num,SHA_256.getSHA256(initPassword));
                return ResponseEntity.ok(true);
            }
            else
                return ResponseEntity.ok(false);
        }else
            return ResponseEntity.ok(false);
    }

    /**
     * 锁定当前账户，将账户状态从正常改为锁定
     *
     */
    @GetMapping("lockAccount")
    public ResponseEntity<Boolean> lockAccount(Integer work_num){
        if(staffService.lockAccount(work_num)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    /**
     * 解锁当前账户，将账户状态从false改为true
     *
     */
    @GetMapping("unlockAccount")
    public ResponseEntity<Boolean> unlockAccount(Integer work_num,String root_num, String password){

        if (!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                staffService.unlockAccount(work_num);
                return ResponseEntity.ok(true);
            }
            else
                return ResponseEntity.ok(false);
        }else
            return ResponseEntity.ok(false);
    }

    /**
     * 删除制定账户,将用户是否在公司的状态改为false
     *
     */
    @GetMapping("deleteAccount")
    public ResponseEntity<Boolean> deleteAccount(Integer work_num, String root_num, String password){
        if (!work_num.equals("")&&!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                staffService.deleteAccount(work_num);
                staffService.lockAccount(work_num);
                return ResponseEntity.ok(true);
            }
            else
                return ResponseEntity.ok(false);
        }else
            return ResponseEntity.ok(false);
    }

    /**
     * 新增账户
     *
     */
    @GetMapping("addAccount")
    public ResponseEntity<Boolean> addAccount(String root_num, String admin_password,String name,String depNum,
                                              String phone,String work_password){
        if (!admin_password.equals("")&&!root_num.equals("")){
            admin_password = SHA_256.getSHA256(admin_password);

            Admin admin = this.temp.queryById("root");

            if (admin_password.equals(admin.getPassword())){
                staffService.addAccount(name,depNum,phone,SHA_256.getSHA256(work_password));
                return ResponseEntity.ok(true);
            }
            else
                return ResponseEntity.ok(false);
        }else
            return ResponseEntity.ok(false);
    }
}
