package com.example.back2.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back2.entity.table.FlowProcess;
import com.example.back2.service.table.FlowProcessService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (FlowProcess)表控制层
 *
 * @author makejava
 * @since 2022-01-13 09:41:13
 */
@RestController
@RequestMapping("flowProcess")
public class FlowProcessController {
    /**
     * 服务对象
     */
    @Resource
    private FlowProcessService flowProcessService;

    /**
     * 分页查询
     *
     * @param flowProcess 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<FlowProcess>> queryByPage(FlowProcess flowProcess, PageRequest pageRequest) {
        return ResponseEntity.ok(this.flowProcessService.queryByPage(flowProcess, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<FlowProcess> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.flowProcessService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param flowProcess 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public Boolean add(String flowProcess) {
        FlowProcess m = JSONObject.parseObject(flowProcess,FlowProcess.class);
        this.flowProcessService.insert(m);
        return true;
    }

    /**
     * 编辑数据
     *
     * @param flowProcess 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<FlowProcess> edit(FlowProcess flowProcess) {
        return ResponseEntity.ok(this.flowProcessService.update(flowProcess));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.flowProcessService.deleteById(id));
    }

}

