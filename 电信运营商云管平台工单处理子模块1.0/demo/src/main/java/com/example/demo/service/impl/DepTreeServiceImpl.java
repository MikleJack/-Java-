package com.example.demo.service.impl;

import com.example.demo.entity.DepTree;
import com.example.demo.dao.DepTreeDao;
import com.example.demo.service.DepTreeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DepTree)表服务实现类
 *
 * @author makejava
 * @since 2022-01-05 15:20:51
 */
@Service("depTreeService")
public class DepTreeServiceImpl implements DepTreeService {
    @Resource
    private DepTreeDao depTreeDao;

    /**
     * 分页查询
     *
     * @param depTree 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<DepTree> queryByPage(DepTree depTree, PageRequest pageRequest) {
        long total = this.depTreeDao.count(depTree);
        return new PageImpl<>(this.depTreeDao.queryAllByLimit(depTree, pageRequest), pageRequest, total);
    }

    @Override
    public List<DepTree> cateList() {
        return this.depTreeDao.queryAll();
    }
}
