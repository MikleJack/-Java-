package com.example.demo.service;

import com.example.demo.entity.AdminWorkOrderInform;
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
     * @param adminWorkOrderInform 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminWorkOrderInform> queryByPage(PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param adminWorkOrderInform 实例对象
     * @return 实例对象
     */
    AdminWorkOrderInform insert(AdminWorkOrderInform adminWorkOrderInform);


}
