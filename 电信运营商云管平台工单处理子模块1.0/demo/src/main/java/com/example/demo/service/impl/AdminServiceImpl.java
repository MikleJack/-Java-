package com.example.demo.service.impl;

import com.example.demo.entity.Admin;
import com.example.demo.dao.AdminDao;
import com.example.demo.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2022-01-02 16:31:38
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param adminNum 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(String adminNum) {
        return this.adminDao.queryById(adminNum);
    }

    /**
     * 分页查询
     *
     * @param admin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Admin> queryByPage(Admin admin, PageRequest pageRequest) {
        long total = this.adminDao.count(admin);
        return new PageImpl<>(this.adminDao.queryAllByLimit(admin, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getAdminNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param adminNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String adminNum) {
        return this.adminDao.deleteById(adminNum) > 0;
    }
}
