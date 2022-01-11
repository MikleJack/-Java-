package com.example.back2.controller.admin;


import com.example.back2.entity.AdminsearchorderTable;
import com.example.back2.service.AdminsearchorderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("adminSearchOrder")
public class searchOrder {

//----------------首页表单显示-顶部--------------------------------------------------------

    @Resource
    private AdminsearchorderTableService adminsearchorderTableService;

        /**
     * 带条件的分页查询
     *
     * @param adminsearchorderTable
     * @return 查询结果
     */
    @PostMapping("criteriaQueryByPage")
    public ResponseEntity<Page<AdminsearchorderTable>> criteriaQueryByPage(@RequestBody AdminsearchorderTable adminsearchorderTable) {
        PageRequest pageRequest = PageRequest.of(0,8);
        return ResponseEntity.ok(this.adminsearchorderTableService.queryByPage(adminsearchorderTable, pageRequest));
    }

    /**
     * 无条件的分页查询
     *
     * @return 查询结果
     */
    @GetMapping("normalQueryByPage")
    public ResponseEntity<Page<AdminsearchorderTable>> normalQueryByPage() {
        PageRequest pageRequest = PageRequest.of(0,8);
        return ResponseEntity.ok(this.adminsearchorderTableService.normalQueryByPage(pageRequest));
    }


//---------------------------首页表单显示-底部-----------------------------------------------------------



//-----------------------------详情按钮-顶部---------------------------



//-----------------------------详情按钮-顶部---------------------------
}
