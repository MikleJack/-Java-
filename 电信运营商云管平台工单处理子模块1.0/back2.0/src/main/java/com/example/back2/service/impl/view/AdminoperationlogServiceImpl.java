package com.example.back2.service.impl.view;

import com.example.back2.entity.view.Adminoperationlog;
import com.example.back2.dao.view.AdminoperationlogDao;
import com.example.back2.service.view.AdminoperationlogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Adminoperationlog)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 09:42:32
 */
@Service("adminoperationlogService")
public class AdminoperationlogServiceImpl implements AdminoperationlogService {
    @Resource
    private AdminoperationlogDao adminoperationlogDao;
    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Adminoperationlog> queryByPage( String name,PageRequest pageRequest) {
        long total = this.adminoperationlogDao.count(name);
        return new PageImpl<>(this.adminoperationlogDao.queryAllByLimit( name,pageRequest), pageRequest, total);
    }

}
