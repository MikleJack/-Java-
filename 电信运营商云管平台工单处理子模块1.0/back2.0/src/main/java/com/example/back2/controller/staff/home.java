package com.example.back2.controller.staff;

import com.example.back2.entity.view.AdminsearchorderTable;
import com.example.back2.entity.view.StaffhomePersoninform;
import com.example.back2.service.view.StaffhomePersoninformService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("staffHome")
public class home {

//----------------左上角员工信息界面-顶部-------------------------------
    @Resource
    private StaffhomePersoninformService staffhomePersoninformService;

    /**
     * 通过员工编号查询员工信息
     *
     * @param workerNum 员工编号
     * @return 查询结果
     */
    @GetMapping("queryPersonInformById")
    public ResponseEntity<StaffhomePersoninform> queryPersonInformById(Integer workerNum) {
        return ResponseEntity.ok(this.staffhomePersoninformService.queryPersonInformById(workerNum));
    }

//----------------左上角员工信息界面-底部部-------------------------------

}
