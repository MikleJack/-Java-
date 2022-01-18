package com.example.back2.controller;

import com.example.back2.entity.table.DepRelate;
import com.example.back2.service.table.DepRelateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DepRelate)表控制层
 *
 * @author makejava
 * @since 2022-01-18 10:31:34
 */
@RestController
@RequestMapping("depRelate")
public class DepRelateController {
    /**
     * 服务对象
     */
    @Resource
    private DepRelateService depRelateService;

    /**
     * 分页查询
     *
     * @param depRelate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<DepRelate>> queryByPage(DepRelate depRelate, PageRequest pageRequest) {
        return ResponseEntity.ok(this.depRelateService.queryByPage(depRelate, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<DepRelate> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.depRelateService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param depRelate 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<DepRelate> add(DepRelate depRelate) {
        return ResponseEntity.ok(this.depRelateService.insert(depRelate));
    }

    /**
     * 编辑数据
     *
     * @param depRelate 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<DepRelate> edit(DepRelate depRelate) {
        return ResponseEntity.ok(this.depRelateService.update(depRelate));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.depRelateService.deleteById(id));
    }

}

