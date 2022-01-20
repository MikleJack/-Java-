package com.example.back2.controller.login;

import com.example.back2.entity.table.OperationLog;
import com.example.back2.entity.table.Staff;
import com.example.back2.exception.GlobalException;
import com.example.back2.service.impl.table.AdminServiceImpl;
import com.example.back2.service.impl.table.StaffServiceImpl;
import com.example.back2.service.table.AdminService;
import com.example.back2.service.table.OperationLogService;
import com.example.back2.service.table.StaffService;
import com.example.back2.utils.SHA_256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("login")
public class login {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AdminService adminService;
//    @Resource
//    private StaffService staffService;
    @Resource
    private OperationLogService operationLogService;

    private String initPassword="brccq123456";
    private HashMap<Integer,Boolean> userLogin = new HashMap<>();
    private HashMap<String,Boolean> adminLogin = new HashMap<>();


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
    public Integer adminLogin(String work_num, String password)throws GlobalException{
        if(!work_num.equals("") && !password.equals("") ) {
            if(adminLogin.containsKey(work_num) && adminLogin.get(work_num))
                return -1;
            if (adminService.queryById(work_num) != null)
            {
                password = SHA_256.getSHA256(password);
                if(password.equals(this.adminService.queryById(work_num).getPassword())){
                    adminLogin.put(work_num,true);
                    logger.info("账号为" + work_num + "的管理员登录");
                    return 1;
                }
            } else{
                return 0;
            }
        } else
            return 0;

        throw new GlobalException("管理员登录时发生错误","管理员登录时发生错误");
    }

    /**
     *  员工及领导登录接口
     *  @param  work_num 工号
     *  @param password 登录密码
     *  @return 0 登录失败 1 普通员工 2 一级领导 3二级领导
     */
    @GetMapping("user")
    public int userLogin(Integer work_num, String password) throws GlobalException{
        if(!password.equals("")) {
            if(userLogin.containsKey(work_num) && userLogin.get(work_num))
                return -1;
            Staff staff = this.staff_temp.queryById(work_num);
            if(staff == null){
                return 0;
//                throw new GlobalException("未查询到该员工账号     输入的员工编号为" , 0);
            }
            if (staff.getState() && staff.getInService())
            {
                password = SHA_256.getSHA256(password);
                if(password.equals(staff.getPassword())){
                    userLogin.put(work_num,true);
                    if(staff.getDepNum()==4||staff.getDepNum()==3){
                        logger.info("工号为" + work_num + "的普通员工登录");
                        return 1;
                    } else if(staff.getDepNum()==2){
                        logger.info("工号为" + work_num + "的一级领导登录");
                        return 2;
                    } else{
                        logger.info("工号为" + work_num + "的二级领导登录");
                        return 3;
                    }
                }
                else
                    return 0;
            }
            else
                return -2;
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

    @GetMapping("userLogout")
    public Boolean userLogout(Integer work_num){
        if(userLogin.containsKey(work_num)){
            userLogin.put(work_num,false);
            return true;
        }
        else
            return false;
    }

    @GetMapping("adminLogout")
    public Boolean adminLogout(String work_num){
        if(adminLogin.containsKey(work_num)){
            adminLogin.put(work_num,false);
            return true;
        }
        else
            return false;
    }
}
