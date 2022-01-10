package com.example.back2.controller;

import com.example.back2.entity.table.Staff;
import com.example.back2.service.table.StaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Staff)表控制层
 *
 * @author makejava
 * @since 2022-01-10 19:34:37
 */
@RestController
@RequestMapping("staff")
public class StaffController {
    /**
     * 服务对象
     */
    @Resource
    private StaffService staffService;

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
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.staffService.deleteById(id));
    }

}

