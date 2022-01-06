package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Staff;
import com.example.demo.service.AdminService;
import com.example.demo.service.StaffService;
import com.example.demo.service.impl.AdminServiceImpl;
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

    /**
     * 分页查询
     *
     * @param staff 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Staff>> queryByPage(Staff staff, PageRequest pageRequest) {
        return ResponseEntity.ok(this.staffService.queryByPage(staff, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Staff> queryById(@PathVariable("id") String id) {
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
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.staffService.deleteById(id));
    }
    /**
     * 登录
     *
     *
     */
    @GetMapping("login")
    public ResponseEntity<Boolean> login(String work_num, String password){
        if(!work_num.equals("") && !password.equals("") ) {
            if (staffService.queryById(work_num) != null)
            {
                password = SHA_256.getSHA256(password);
                return ResponseEntity.ok(password.equals(staffService.queryById(work_num).getPassword()));
            }
            else
                return ResponseEntity.ok(false);
        }
        else
            return ResponseEntity.ok(false);
    }

    /**
     * 重置密码
     *
     */
    @GetMapping("reset")
    public ResponseEntity<Boolean> ResetStaffPassword(String work_num,String root_num, String password){
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
    public ResponseEntity<Boolean> lockAccount(String work_num){
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
    public ResponseEntity<Boolean> unlockAccount(String work_num,String root_num, String password){
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
    public ResponseEntity<Boolean> deleteAccount(String work_num, String root_num, String password){
        if (!work_num.equals("")&&!password.equals("")&&!root_num.equals("")){
            password = SHA_256.getSHA256(password);

            Admin admin = this.temp.queryById("root");

            if (password.equals(admin.getPassword())){
                staffService.deleteAccount(work_num);
                return ResponseEntity.ok(true);
            }
            else
                return ResponseEntity.ok(false);
        }else
            return ResponseEntity.ok(false);
    }


}

