package com.example.demo.controller;

import com.example.demo.entity.OperationLog;
import com.example.demo.service.OperationLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (OperationLog)表控制层
 *
 * @author makejava
 * @since 2022-01-03 10:27:45
 */
@RestController
@RequestMapping("operationLog")
public class OperationLogController {
    /**
     * 服务对象
     */
    @Resource
    private OperationLogService operationLogService;

    /**
     * 分页查询
     *
     * @param operationLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping("queryByPage")
    public ResponseEntity<Page<OperationLog>> queryByPage(OperationLog operationLog, PageRequest pageRequest) {
        return ResponseEntity.ok(this.operationLogService.queryByPage(operationLog, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<OperationLog> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.operationLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param operationLog 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<OperationLog> add(OperationLog operationLog) {
        return ResponseEntity.ok(this.operationLogService.insert(operationLog));
    }
    /**
     * 编辑数据
     *
     * @param operationLog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<OperationLog> edit(OperationLog operationLog) {
        return ResponseEntity.ok(this.operationLogService.update(operationLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.operationLogService.deleteById(id));
    }

}

