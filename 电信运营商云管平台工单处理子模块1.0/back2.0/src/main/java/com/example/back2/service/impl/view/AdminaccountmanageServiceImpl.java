package com.example.back2.service.impl.view;

import com.example.back2.entity.view.Adminaccountmanage;
import com.example.back2.dao.view.AdminaccountmanageDao;
import com.example.back2.service.view.AdminaccountmanageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Adminaccountmanage)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 10:09:10
 */
@Service("adminaccountmanageService")
public class AdminaccountmanageServiceImpl implements AdminaccountmanageService {
    @Resource
    private AdminaccountmanageDao adminaccountmanageDao;


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Adminaccountmanage> queryByPage( PageRequest pageRequest) {
        long total = this.adminaccountmanageDao.count();
        return new PageImpl<>(this.adminaccountmanageDao.queryAllByLimit( pageRequest), pageRequest, total);
    }

}
