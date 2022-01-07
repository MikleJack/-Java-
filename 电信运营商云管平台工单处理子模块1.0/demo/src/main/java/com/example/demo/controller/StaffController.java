package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Staff;
import com.example.demo.service.AdminService;
import com.example.demo.service.StaffService;
import com.example.demo.service.impl.AdminServiceImpl;
import com.example.demo.service.impl.StaffServiceImpl;
import com.example.demo.utils.SHA_256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Staff)表控制层
 *
 * @author makejava
 * @since 2022-01-04 14:10:51
 */
@RestController
@RequestMapping("staff")
public class StaffController {
    /**
     * 服务对象
     */
    @Resource
    private StaffService staffService;
//    private AdminService a;
    private String initPassword="brccq123456";


    //必须通过Autowired注解来生成其他服务类
    @Autowired
    AdminService temp =new AdminServiceImpl();
    @Autowired
    StaffService staff_temp = new StaffServiceImpl();

    /**
     * 分页查询
     *
     * @param staff 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("allStaff")
    public ResponseEntity<Page<Staff>> queryByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.staffService.queryByPage( pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Staff> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.staffService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param staff 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Staff> add(Staff staff) {
        return ResponseEntity.ok(this.staffService.insert(staff));
    }

    /**
     * 编辑数据
     *
     * @param staff 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Staff> edit(Staff staff) {
        return ResponseEntity.ok(this.staffService.update(staff));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.staffService.deleteById(id));
    }
    /**
     * 登录
     *
     *0 登录失败
     * 1 普通员工
     * 2 领导
     */
    @GetMapping("login")
    public int login(Integer work_num, String password){
        if(!work_num.equals("") && !password.equals("") ) {
            Staff staff = this.staff_temp.queryById(work_num);
            if (staff.getState() && staff.getInService())
            {
                password = SHA_256.getSHA256(password);
                if(password.equals(staff.getPassword())){
                    if(staff.getDepNum().equals("0001"))
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

    /**
     * 重置密码
     *
     */
    @GetMapping("reset")
    public ResponseEntity<Boolean> ResetStaffPassword(Integer work_num,String root_num, String password){
        if (!work_num.equals("")&&!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                staffService.updatePassword(work_num,SHA_256.getSHA256(initPassword));
//                temp_staff = staffService.queryById(work_num);
//                temp_staff.setPassword(SHA_256.getSHA256(initPassword));

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
        if (!work_num.equals("")&&!password.equals("")&&!root_num.equals("")){
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

    /**
     * 根据工号查询账户
     *
     * @param work_num 用户编号
     */
    @GetMapping("searchAccount")
    public ResponseEntity<Staff> searchAccount(Integer work_num ){
        return ResponseEntity.ok(this.staffService.searchAccount(work_num));
    }
}

