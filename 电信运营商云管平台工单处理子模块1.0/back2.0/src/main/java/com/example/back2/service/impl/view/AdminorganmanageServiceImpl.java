package com.example.back2.service.impl.view;

import com.example.back2.entity.view.Adminorganmanage;
import com.example.back2.dao.view.AdminorganmanageDao;
import com.example.back2.service.view.AdminorganmanageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Adminorganmanage)表服务实现类
 *
 * @author makejava
 * @since 2022-01-17 20:23:06
 */
@Service("adminorganmanageService")
public class AdminorganmanageServiceImpl implements AdminorganmanageService {
    @Resource
    private AdminorganmanageDao adminorganmanageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param  depNum 主键
     * @return 实例对象
     */
    @Override
    public List<Adminorganmanage> queryById(Integer depNum) {
        return this.adminorganmanageDao.queryById(depNum);
    }

    /**
     * 分页查询
     *
     * @param adminorganmanage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Adminorganmanage> queryByPage(Adminorganmanage adminorganmanage, PageRequest pageRequest) {
        long total = this.adminorganmanageDao.count(adminorganmanage);
        return new PageImpl<>(this.adminorganmanageDao.queryAllByLimit(adminorganmanage, pageRequest), pageRequest, total);
    }

    @Override
    public List<Integer> getLowDep(Integer depNum){
        List<Integer> ans=new ArrayList<Integer>();
        List<Adminorganmanage> temp = this.adminorganmanageDao.queryById(depNum);
        for(Adminorganmanage i:temp){
            ans.add(i.getLowNum());
        }
        return ans;
    }

}
