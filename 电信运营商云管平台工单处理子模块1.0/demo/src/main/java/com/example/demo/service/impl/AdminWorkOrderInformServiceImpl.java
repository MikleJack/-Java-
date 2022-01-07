package com.example.demo.service.impl;

import com.example.demo.entity.AdminWorkOrderInform;
import com.example.demo.dao.AdminWorkOrderInformDao;
import com.example.demo.service.AdminWorkOrderInformService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (AdminWorkOrderInform)表服务实现类
 *
 * @author makejava
 * @since 2022-01-07 10:47:48
 */
@Service("adminWorkOrderInformService")
public class AdminWorkOrderInformServiceImpl implements AdminWorkOrderInformService {
    @Resource
    private AdminWorkOrderInformDao adminWorkOrderInformDao;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AdminWorkOrderInform> queryByPage(PageRequest pageRequest) {
        long total = this.adminWorkOrderInformDao.count();
        return new PageImpl<>(this.adminWorkOrderInformDao.queryAllByLimit(pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param adminWorkOrderInform 实例对象
     * @return 实例对象
     */
    @Override
    public AdminWorkOrderInform insert(AdminWorkOrderInform adminWorkOrderInform) {
        this.adminWorkOrderInformDao.insert(adminWorkOrderInform);
        return adminWorkOrderInform;
    }


}
