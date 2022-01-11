package com.example.back2.service;

import com.example.back2.entity.AdminsearchorderDetailperson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * (AdminsearchorderDetailperson)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 09:50:13
 */
public interface AdminsearchorderDetailpersonService {

    /**
     * 分页查询
     *
     * @param adminsearchorderDetailperson 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminsearchorderDetailperson> queryByPage(AdminsearchorderDetailperson adminsearchorderDetailperson, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param adminsearchorderDetailperson 实例对象
     * @return 实例对象
     */
    AdminsearchorderDetailperson insert(AdminsearchorderDetailperson adminsearchorderDetailperson);

    /**
     * 管理员工单界面的详情按钮
     *此处只返回上半部分信息，下半部分信息调用其他接口
     *
     * @param workOrderNum 工单编号
     * @return 每个工单详情页面上的员工信息、部门信息、工单信息
     */
    AdminsearchorderDetailperson queryWorkOrderDetailTop(String workOrderNum);


}
