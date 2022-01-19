package com.example.back2.controller.admin;

import com.example.back2.entity.table.Admin;
import com.example.back2.entity.table.Department;
import com.example.back2.entity.table.Staff;
import com.example.back2.entity.view.Adminaccountmanage;
import com.example.back2.exception.GlobalException;
import com.example.back2.service.impl.table.AdminServiceImpl;
import com.example.back2.service.impl.table.StaffServiceImpl;
import com.example.back2.service.table.AdminService;
import com.example.back2.service.table.DepartmentService;
import com.example.back2.service.table.InformService;
import com.example.back2.service.table.StaffService;
import com.example.back2.service.view.AdminaccountmanageService;
import com.example.back2.utils.SHA_256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("account")
public class manageAccount {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private StaffService staffService;
    @Resource
    private AdminaccountmanageService adminaccountmanageService;
    @Resource
    private DepartmentService departmentService;

    @Resource
    private InformService informService;

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

    /**
     * 带条件的分页查询
     *
     * @param name 员工姓名
     * @param page 页数
     * @param size 每页大小
     * @return 查询结果
     */
    @GetMapping("criteriaQueryByPage")
    public ResponseEntity<Page<Adminaccountmanage>> criteriaQueryByPage(String name,int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.adminaccountmanageService.criteriaQueryByPage(name, pageRequest));
    }

    @GetMapping("reset")
    public ResponseEntity<Boolean> ResetStaffPassword(Integer work_num,String root_num, String password) throws GlobalException{
        logger.info("账号为" + root_num + "的管理员对员工编号为" + work_num + "的员工进行了重置操作");

        System.out.println(work_num+" "+root_num+" "+password);
        if (!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                staffService.updatePassword(work_num,SHA_256.getSHA256(initPassword));
                return ResponseEntity.ok(true);
            }
            else {
                throw new GlobalException("解密后的管理员密码不匹配     输入的密码为",password);
            }
        }else{
            throw new GlobalException("输入的管理员账号或密码为空值     输入的密码为",password);
        }
    }

    /**
     * 锁定当前账户，将账户状态从正常改为锁定
     *
     */
    @GetMapping("lockAccount")
    public ResponseEntity<Boolean> lockAccount(Integer work_num)throws GlobalException{
        logger.info("有管理员对员工编号为" + work_num + "的员工进行了锁定操作");

        if(staffService.lockAccount(work_num)) {
            this.informService.adminInsertInform(work_num,"锁定");
            return ResponseEntity.ok(true);
        } else {
            throw new GlobalException("在锁定员工账户时发生错误     员工的编号为",work_num);
        }
    }

    /**
     * 解锁当前账户，将账户状态从false改为true
     *
     */
    @GetMapping("unlockAccount")
    public ResponseEntity<Boolean> unlockAccount(Integer work_num,String root_num, String password)throws GlobalException{
        logger.info("账号为" + root_num + "的管理员对员工编号为" + work_num + "的员工进行了解锁操作");

        if (!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                staffService.unlockAccount(work_num);
                this.informService.adminInsertInform(work_num,"解锁");
                return ResponseEntity.ok(true);
            }
            else
                throw new GlobalException("解密后的管理员密码不匹配     输入的密码为",password);
        }else
            throw new GlobalException("输入的管理员账号或密码为空值     输入的密码为",password);
    }

    /**
     * 删除制定账户,将用户是否在公司的状态改为false
     *
     */
    @GetMapping("deleteAccount")
    public ResponseEntity<Boolean> deleteAccount(Integer work_num, String root_num, String password) throws GlobalException{
        logger.info("账号为" + root_num + "的管理员对员工编号为" + work_num + "的员工进行了删除操作");

        if (!work_num.equals("")&&!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                if(staffService.deleteAccount(work_num) || staffService.lockAccount(work_num)) {
                    return ResponseEntity.ok(true);
                }else{
                    throw new GlobalException("对员工进行删除，在改变员工在职状态或锁定员工时发生错误    输入的员工编号为",work_num);
                }
            }
            else
                throw new GlobalException("解密后的管理员密码不匹配     输入的密码为",password);
        }else
            throw new GlobalException("输入的管理员账号或密码为空值     输入的密码为",password);
    }

    /**
     * 新增账户
     *
     */
    @GetMapping("addAccount")
    public ResponseEntity<Boolean> addAccount(String root_num, String admin_password,String name,String depNum,
                                              String phone,String work_password) throws GlobalException{
        logger.info("账号为" + root_num + "的管理员对给员工" + name + "进行了新增账户操作" );

        if (!admin_password.equals("")&&!root_num.equals("")){
            admin_password = SHA_256.getSHA256(admin_password);

            Admin admin = this.temp.queryById("root");

            if (admin_password.equals(admin.getPassword())){
                staffService.addAccount(name,depNum,phone,SHA_256.getSHA256(work_password));
                return ResponseEntity.ok(true);
            }
            else
                throw new GlobalException("解密后的管理员密码不匹配     输入的密码为",admin_password);
        }else
            throw new GlobalException("输入的管理员账号或密码为空值     输入的密码为",admin_password);
    }

    /**
     * 查询所有部门
     *
     * @return 查询结果
     */
    @GetMapping("getDep")
    public List<Department> getAllDep() {
        return this.departmentService.AllDep();
    }
}
