package com.example.back2.service.table;

import com.example.back2.entity.table.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Staff)表服务接口
 *
 * @author makejava
 * @since 2022-01-10 19:34:37
 */
public interface StaffService {

    /**
     * 通过ID查询单条数据
     *
     * @param workNum 主键
     * @return 实例对象
     */
    Staff queryById(Integer workNum);

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
    boolean deleteById(Integer workNum);

}
