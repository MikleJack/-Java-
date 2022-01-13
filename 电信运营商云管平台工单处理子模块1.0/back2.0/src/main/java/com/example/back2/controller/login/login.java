package com.example.back2.controller.login;

import com.example.back2.entity.table.OperationLog;
import com.example.back2.entity.table.Staff;
import com.example.back2.service.impl.table.AdminServiceImpl;
import com.example.back2.service.impl.table.StaffServiceImpl;
import com.example.back2.service.table.AdminService;
import com.example.back2.service.table.OperationLogService;
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
//    @Resource
//    private StaffService staffService;
    @Resource
    private OperationLogService operationLogService;

    private String initPassword="brccq123456";


    //必须通过Autowired注解来生成其他服务类
    @Autowired
    AdminService temp =new AdminServiceImpl();
    @Autowired
    StaffService staff_temp = new StaffServiceImpl();

    /**
     *  管理员登录接口
        @ work_num 管理员工号
        @ password 管理员登录密码
     */
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

    /**
     *  员工及领导登录接口
     *  @param  work_num 工号
     *  @param password 登录密码
     *  @return 0 登录失败 1 普通员工 2 领导
     */
    @GetMapping("user")
    public int userLogin(Integer work_num, String password){
        if(!password.equals("") ) {
            Staff staff = this.staff_temp.queryById(work_num);
            if (staff.getState() && staff.getInService())
            {
                password = SHA_256.getSHA256(password);
                if(password.equals(staff.getPassword())){
                    if(staff.getDepNum()==4||staff.getDepNum()==3)
                        return 1;
                    else if(staff.getDepNum()==2)
                        return 2;
                    else
                        return 3;
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

    /**
     * 新增登录记录
     *
     * @param operationLog 实体
     * @return 新增结果
     */
    @PostMapping("addLog")
    public ResponseEntity<OperationLog> add(OperationLog operationLog) {
        return ResponseEntity.ok(this.operationLogService.insert(operationLog));
    }

}
