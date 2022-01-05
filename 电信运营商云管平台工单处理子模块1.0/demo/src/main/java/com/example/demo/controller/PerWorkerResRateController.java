package com.example.demo.controller;

import com.example.demo.entity.PerWorkerResRate;
import com.example.demo.entity.WorkOrder;
import com.example.demo.service.PerWorkerResRateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Perworkerresrate)表控制层
 *
 * @author makejava
 * @since 2022-01-05 10:59:07
 */
@RestController
@RequestMapping("perWorkerResRate")
public class PerWorkerResRateController {
    /**
     * 服务对象
     */
    @Resource
    private PerWorkerResRateService perWorkerResRateService;

    /**
     * 分页查询
     *
     * @param perworkerresrate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<PerWorkerResRate>> queryByPage(PerWorkerResRate perworkerresrate, PageRequest pageRequest) {
        return ResponseEntity.ok(this.perWorkerResRateService.queryByPage(perworkerresrate, pageRequest));
    }

    /**
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    @GetMapping("queryAll")
    public List<WorkOrder> queryAll(){
        return this.perWorkerResRateService.queryAll();
    }

}

