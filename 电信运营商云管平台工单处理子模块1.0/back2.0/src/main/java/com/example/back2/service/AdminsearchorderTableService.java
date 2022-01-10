package com.example.back2.service;

import com.example.back2.entity.AdminsearchorderTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AdminsearchorderTable)表服务接口
 *
 * @author makejava
 * @since 2022-01-10 19:56:49
 */
public interface AdminsearchorderTableService {


    /**
     * 分页查询
     *
     * @param adminsearchorderTable 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminsearchorderTable> queryByPage(AdminsearchorderTable adminsearchorderTable, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param adminsearchorderTable 实例对象
     * @return 实例对象
     */
    AdminsearchorderTable insert(AdminsearchorderTable adminsearchorderTable);


}
