package com.example.demo.controller;

import com.example.demo.entity.StaffDep;
import com.example.demo.service.StaffDepService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.attribute.IntegerSyntax;

/**
 * (StaffDep)表控制层
 *
 * @author makejava
 * @since 2022-01-07 18:22:49
 */
@RestController
@RequestMapping("staffDep")
public class StaffDepController {
    /**
     * 服务对象
     */
    @Resource
    private StaffDepService staffDepService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("allStaff")
    public ResponseEntity<Page<StaffDep>> queryByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(this.staffDepService.queryByPage( pageRequest));
    }


}

