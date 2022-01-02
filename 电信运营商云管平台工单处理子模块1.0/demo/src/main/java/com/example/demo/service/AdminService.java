package com.example.demo.service;

import com.example.demo.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2022-01-02 16:31:38
 */
public interface AdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param adminNum 主键
     * @return 实例对象
     */
    Admin queryById(String adminNum);

    /**
     * 分页查询
     *
     * @param admin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Admin> queryByPage(Admin admin, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param adminNum 主键
     * @return 是否成功
     */
    boolean deleteById(String adminNum);

}
