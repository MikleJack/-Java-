package com.example.demo.service;

import com.example.demo.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Staff)表服务接口
 *
 * @author makejava
 * @since 2022-01-04 14:10:52
 */
public interface StaffService {

    /**
     * 通过ID查询单条数据
     *
     * @param workNum 主键
     * @return 实例对象
     */
    Staff queryById(String workNum);

    /**
     * 分页查询
     *
     * @param staff 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Staff> queryByPage(Staff staff, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    Staff insert(Staff staff);

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    Staff update(Staff staff);

    /**
     * 通过主键删除数据
     *
     * @param workNum 主键
     * @return 是否成功
     */
    boolean deleteById(String workNum);

    /**
     * 重置密码
     *
     * @param work_num 用户编号
     * @param password    密码
     * @return 影响行数
     */
    boolean updatePassword(String work_num,String password);

    /**
     * 锁定当前账户，将账户状态从正常改为锁定
     *
     */
    boolean lockAccount(String workNum);
}
