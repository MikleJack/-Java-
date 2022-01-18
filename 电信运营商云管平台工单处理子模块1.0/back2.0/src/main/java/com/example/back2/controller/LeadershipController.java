package com.example.back2.controller;

import com.example.back2.entity.table.Leadership;
import com.example.back2.service.table.LeadershipService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Leadership)表控制层
 *
 * @author makejava
 * @since 2022-01-13 16:05:43
 */
@RestController
@RequestMapping("leadership")
public class LeadershipController {
    /**
     * 服务对象
     */
    @Resource
    private LeadershipService leadershipService;

    /**
     * 分页查询
     *
     * @param leadership 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Leadership>> queryByPage(Leadership leadership, PageRequest pageRequest) {
        return ResponseEntity.ok(this.leadershipService.queryByPage(leadership, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<List<Leadership>> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.leadershipService.getUnderNum(id));
    }

    /**
     * 新增数据
     *
     * @param leadership 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Leadership> add(Leadership leadership) {
        return ResponseEntity.ok(this.leadershipService.insert(leadership));
    }

    /**
     * 编辑数据
     *
     * @param leadership 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<List<Leadership>> edit(Leadership leadership) {
        return ResponseEntity.ok(this.leadershipService.update(leadership));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.leadershipService.deleteById(id));
    }

}

