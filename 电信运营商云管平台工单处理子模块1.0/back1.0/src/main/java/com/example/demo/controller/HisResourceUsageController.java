package com.example.demo.controller;

import com.example.demo.entity.HisResourceUsage;
import com.example.demo.service.HisResourceUsageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HisResourceUsage)表控制层
 *
 * @author makejava
 * @since 2022-01-05 21:19:24
 */
@RestController
@RequestMapping("hisResourceUsage")
public class HisResourceUsageController {
    /**
     * 服务对象
     */
    @Resource
    private HisResourceUsageService hisResourceUsageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public HisResourceUsage selectOne(String id) {
        return this.hisResourceUsageService.queryById(id);
    }

    @GetMapping("selectResource")
    public double selectResource(){
        double ans = 0.0;
        do{
            ans = Math.random();
        }while(ans <= 0.2);
//        DecimalFormat df = new DecimalFormat("######0.00");
//        df.format(ans);
        ans *= 100;
        int temp = (int) ans;
        ans = (double) temp;
        return ans/100;
    }
}
