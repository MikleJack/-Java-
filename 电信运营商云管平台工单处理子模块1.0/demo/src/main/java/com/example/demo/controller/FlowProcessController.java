package com.example.demo.controller;

import com.example.demo.entity.FlowProcess;
import com.example.demo.service.FlowProcessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FlowProcess)表控制层
 *
 * @author makejava
 * @since 2022-01-06 20:28:32
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
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FlowProcess selectOne(String id) {
        return this.flowProcessService.queryById(id);
    }


//    根据工号查询工单
    @GetMapping("selectOrderNumByWorkNum")
    public List<FlowProcess> selectOrderNumByWorkNum(Integer WorkNum,int page,int size){
        return this.flowProcessService.selectOrderNumByWorkNum(WorkNum,page,size);
    }


//根据工号查询操作过的工单，不含分页
    @GetMapping("selectByWorkNum")
    public List<FlowProcess> selectByWorkNum(Integer WorkNum){
        return this.flowProcessService.selectByWorkNum(WorkNum);
    }

    /**
     * 管理员工单查询界面，详情按钮
     * 通过工单编号查询该工单的所有流转过程
     *
     * @param workOrderNum 主键
     * @return 该工单的所有流转过程
     */
    @GetMapping("queryByWorkOrderNum")
    List<FlowProcess> queryByWorkOrderNum(String workOrderNum){
        return this.flowProcessService.queryByWorkOrderNum(workOrderNum);
    }

}
