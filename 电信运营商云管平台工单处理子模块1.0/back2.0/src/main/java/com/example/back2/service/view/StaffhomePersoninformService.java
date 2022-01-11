package com.example.back2.service.view;

import com.example.back2.entity.view.AdminsearchorderTable;
import com.example.back2.entity.view.StaffhomePersoninform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * (StaffhomePersoninform)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 15:31:55
 */
public interface StaffhomePersoninformService {


    /**
     * 分页查询
     *
     * @param staffhomePersoninform 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<StaffhomePersoninform> queryByPage(StaffhomePersoninform staffhomePersoninform, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param staffhomePersoninform 实例对象
     * @return 实例对象
     */
    StaffhomePersoninform insert(StaffhomePersoninform staffhomePersoninform);

    /**
     * 通过员工编号查询员工信息
     *
     * @param workerNum 员工编号
     * @return 查询结果
     */
    StaffhomePersoninform queryPersonInformById(Integer workerNum);


}
