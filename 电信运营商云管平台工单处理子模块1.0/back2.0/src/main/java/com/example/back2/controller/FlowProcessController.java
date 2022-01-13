package com.example.back2.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.back2.entity.table.FlowProcess;
import com.example.back2.entity.view.FlowStaff;
import com.example.back2.service.table.FlowProcessService;
import com.example.back2.service.view.FlowStaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @GetMapping("id")
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

    /**
     * 插入申请延期的流转过程
     *
     * @param workOrderNum 工单编号
     * @param workerNum 员工编号
     * @return 是否插入流转过程
     */
    public ResponseEntity<Boolean> DelayInsert(String workOrderNum, Integer workerNum, Date DealDate) {
        return ResponseEntity.ok(this.flowProcessService.DelayInsert(workOrderNum, workerNum, DealDate));
    }

    @Resource
    private FlowStaffService flowStaffService;
//  查询对应工单号的所有流转过程
    @GetMapping("selectByWorkOrderNum")
    public List<FlowStaff> selectByWorkOrderNum(String workOrederNum){
        return this.flowStaffService.selectByWorkOrderNum(workOrederNum);
    }

//  通过工单号查找申请时间
    @GetMapping("selectApplyTime")
    public List<FlowProcess> selectApplyTime(String workOrderNum){
        System.out.println(workOrderNum);
        List<FlowProcess> ans = this.flowProcessService.selectApplyTime(workOrderNum);
        for (FlowProcess i:ans){
            System.out.println(i.getDealDate());
        }
//        System.out.println(ans);
        return ans;
    }
}

