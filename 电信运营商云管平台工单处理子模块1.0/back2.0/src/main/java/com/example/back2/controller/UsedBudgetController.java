package com.example.back2.controller;

import com.example.back2.entity.table.UsedBudget;
import com.example.back2.entity.view.HistoryUsage;
import com.example.back2.service.table.UsedBudgetService;
import com.example.back2.service.view.HistoryUsageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UsedBudget)表控制层
 *
 * @author makejava
 * @since 2022-01-13 14:37:28
 */
@RestController
@RequestMapping("usedBudget")
public class UsedBudgetController {
    /**
     * 服务对象
     */
    @Resource
    private UsedBudgetService usedBudgetService;

    @Resource
    private HistoryUsageService historyUsageService;

    /**
     * 分页查询
     *
     * @param usedBudget 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<UsedBudget>> queryByPage(UsedBudget usedBudget, PageRequest pageRequest) {
        return ResponseEntity.ok(this.usedBudgetService.queryByPage(usedBudget, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getUsedBudget")
    public ResponseEntity<UsedBudget> queryById( Integer id) {
        return ResponseEntity.ok(this.usedBudgetService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param usedBudget 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<UsedBudget> add(UsedBudget usedBudget) {
        return ResponseEntity.ok(this.usedBudgetService.insert(usedBudget));
    }

    /**
     * 编辑数据
     *
     * @param usedBudget 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<UsedBudget> edit(UsedBudget usedBudget) {
        return ResponseEntity.ok(this.usedBudgetService.update(usedBudget));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.usedBudgetService.deleteById(id));
    }

    /**
     * 根据员工编号返回其所有工单
     *
     * @param workerNum 员工编号
     * @return 该员工的所有工单
     */
    @GetMapping("getStaffUsage")
    public Double getStaffUsage(Integer workerNum) {
        return this.historyUsageService.getStaffUsage(workerNum);
    }
}

