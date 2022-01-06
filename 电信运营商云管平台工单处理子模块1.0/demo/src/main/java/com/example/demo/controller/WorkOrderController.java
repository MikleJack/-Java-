package com.example.demo.controller;

import com.example.demo.entity.WorkOrder;
import com.example.demo.service.WorkOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WorkOrder)表控制层
 *
 * @author makejava
 * @since 2022-01-04 21:25:48
 */
@RestController
@RequestMapping("workOrder")
public class WorkOrderController {
    /**
     * 服务对象
     */
    @Resource
    private WorkOrderService workOrderService;

    /**
     * 分页查询
     *
     *@param  page 初始页面号
     * @param size 分页大小
     * @return 查询结果
     */
    @GetMapping("queryByPage")
    public ResponseEntity<Page<WorkOrder>> queryByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.workOrderService.queryByPage(pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<WorkOrder> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.workOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param workOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<WorkOrder> add(WorkOrder workOrder) {
        return ResponseEntity.ok(this.workOrderService.insert(workOrder));
    }

    /**
     * 编辑数据
     *
     * @param workOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<WorkOrder> edit(WorkOrder workOrder) {
        return ResponseEntity.ok(this.workOrderService.update(workOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("deleteById")
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.workOrderService.deleteById(id));
    }

    /**
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    @GetMapping("queryAll")
    public List<WorkOrder> queryAll(){
        return this.workOrderService.queryAll();
    }

    @GetMapping("selectByWorkNum")
    public List<WorkOrder> selectByWorkNum(String WorkNum){
        return this.workOrderService.selectByWorkNum(WorkNum);
    }

    @GetMapping("selectByWorkOrderType")
    public List<WorkOrder> selectByWorkOrderType(String WorkOrderType){
        return this.workOrderService.selectByWorkOrderType(WorkOrderType);
    }

    @GetMapping("selectByWorkOrderReason")
    public List<WorkOrder> selectByWorkOrderReason(String WorkOrderReason){
        return this.workOrderService.selectByWorkOrderReason(WorkOrderReason);
    }
}

