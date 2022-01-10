package com.example.demo.controller;

import com.example.demo.entity.AllocatedCom;
import com.example.demo.service.AllocatedComService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AllocatedCom)表控制层
 *
 * @author makejava
 * @since 2022-01-07 16:58:45
 */
@RestController
@RequestMapping("allocatedCom")
public class AllocatedComController {
    /**
     * 服务对象
     */
    @Resource
    private AllocatedComService allocatedComService;

    /**
     * 分页查询
     *
     * @param allocatedCom 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<AllocatedCom>> queryByPage(AllocatedCom allocatedCom, PageRequest pageRequest) {
        return ResponseEntity.ok(this.allocatedComService.queryByPage(allocatedCom, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<AllocatedCom> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.allocatedComService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param allocatedCom 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<AllocatedCom> add(AllocatedCom allocatedCom) {
        return ResponseEntity.ok(this.allocatedComService.insert(allocatedCom));
    }

    /**
     * 编辑数据
     *
     * @param allocatedCom 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<AllocatedCom> edit(AllocatedCom allocatedCom) {
        return ResponseEntity.ok(this.allocatedComService.update(allocatedCom));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.allocatedComService.deleteById(id));
    }

    /**
     * 管理员工单查询界面，详细按钮
     * 通过工单编号查询该工单利用所有的资源
     *
     * @param workOrderNum 主键
     * @return 该工单利用的所有资源
     */
    @GetMapping("queryByWorkOrderNum")
    public ResponseEntity<List<AllocatedCom>> queryByWorkOrderNum(String workOrderNum) {
        return ResponseEntity.ok(this.allocatedComService.queryByWorkOrderNum(workOrderNum));
    }

}

