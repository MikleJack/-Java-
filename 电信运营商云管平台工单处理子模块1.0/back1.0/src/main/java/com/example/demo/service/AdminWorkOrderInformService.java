package com.example.demo.service;

import com.example.demo.entity.AdminWorkOrderInform;
import com.example.demo.entity.adminSearchWorkOrderDetail.AdminWorkorderSingleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AdminWorkOrderInform)表服务接口
 *
 * @author makejava
 * @since 2022-01-07 10:47:48
 */
public interface AdminWorkOrderInformService {


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminWorkOrderInform> queryByPage(PageRequest pageRequest);

    /**
     * 带条件的分页查询
     *
     * @param adminWorkOrderInform
     *@param  pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminWorkOrderInform> criteriaQueryByPage(AdminWorkOrderInform adminWorkOrderInform, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param adminWorkOrderInform 实例对象
     * @return 实例对象
     */
    AdminWorkOrderInform insert(AdminWorkOrderInform adminWorkOrderInform);


    /**
     * 管理员工单界面的详情按钮
     *
     * @param workOrderNum 工单编号
     * @return 每个工单详情页面上的员工信息、部门信息、工单信息
     */
    AdminWorkorderSingleDetail queryWorkOrderDetail(String workOrderNum);

}
