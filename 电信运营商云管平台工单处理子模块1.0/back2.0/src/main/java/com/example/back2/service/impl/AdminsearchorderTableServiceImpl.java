package com.example.back2.service.impl;

import com.example.back2.entity.AdminsearchorderTable;
import com.example.back2.dao.AdminsearchorderTableDao;
import com.example.back2.service.AdminsearchorderTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * (AdminsearchorderTable)表服务实现类
 *
 * @author makejava
 * @since 2022-01-10 19:56:49
 */
@Service("adminsearchorderTableService")
public class AdminsearchorderTableServiceImpl implements AdminsearchorderTableService {
    @Resource
    private AdminsearchorderTableDao adminsearchorderTableDao;

    /**
     * 分页查询
     *
     * @param adminsearchorderTable 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AdminsearchorderTable> queryByPage(AdminsearchorderTable adminsearchorderTable, PageRequest pageRequest) {
        long total = this.adminsearchorderTableDao.count(adminsearchorderTable);
        return new PageImpl<>(this.adminsearchorderTableDao.queryAllByLimit(adminsearchorderTable, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param adminsearchorderTable 实例对象
     * @return 实例对象
     */
    @Override
    public AdminsearchorderTable insert(AdminsearchorderTable adminsearchorderTable) {
        this.adminsearchorderTableDao.insert(adminsearchorderTable);
        return adminsearchorderTable;
    }


    /**
     * 无条件的分页查询
     *
     * @return 查询结果
     */
    public Page<AdminsearchorderTable> normalQueryByPage(PageRequest pageRequest) {
        long total = this.adminsearchorderTableDao.normalCount();
        return new PageImpl<>(this.adminsearchorderTableDao.normalQueryAllByLimit(pageRequest), pageRequest, total);
    }
}
