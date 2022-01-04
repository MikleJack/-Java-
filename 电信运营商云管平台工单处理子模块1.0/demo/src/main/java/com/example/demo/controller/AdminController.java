package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2022-01-02 16:31:37
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 分页查询
     *
     * @param admin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Admin>> queryByPage(Admin admin, PageRequest pageRequest) {
        return ResponseEntity.ok(this.adminService.queryByPage(admin, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id")
    public ResponseEntity<Admin> queryById(String id) {
        return ResponseEntity.ok(this.adminService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param admin 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Admin> add(Admin admin) {
        return ResponseEntity.ok(this.adminService.insert(admin));
    }

    /**
     * 编辑数据
     *
     * @param admin 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Admin> edit(Admin admin) {
        return ResponseEntity.ok(this.adminService.update(admin));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.adminService.deleteById(id));
    }

    /**
     * 登录
     *
     *
     */
    @GetMapping("login")
    public ResponseEntity<Boolean> login(String work_num, String password){
        if(!work_num.equals("") && !password.equals("") ) {
            VertifyCodeController vertifyCodeController = new VertifyCodeController();
            if (adminService.queryById(work_num) != null)
                return ResponseEntity.ok(password.equals(this.adminService.queryById(work_num).getPassword()));
            else
                return ResponseEntity.ok(false);
        }
        else
            return ResponseEntity.ok(false);
    }
}

