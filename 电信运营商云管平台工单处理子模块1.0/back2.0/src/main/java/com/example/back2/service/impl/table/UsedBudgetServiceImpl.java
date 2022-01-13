package com.example.back2.service.impl.table;

import com.example.back2.entity.table.UsedBudget;
import com.example.back2.dao.table.UsedBudgetDao;
import com.example.back2.service.table.UsedBudgetService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (UsedBudget)表服务实现类
 *
 * @author makejava
 * @since 2022-01-13 14:37:28
 */
@Service("usedBudgetService")
public class UsedBudgetServiceImpl implements UsedBudgetService {
    @Resource
    private UsedBudgetDao usedBudgetDao;

    /**
     * 通过ID查询单条数据
     *
     * @param depNum 主键
     * @return 实例对象
     */
    @Override
    public UsedBudget queryById(Integer depNum) {
        return this.usedBudgetDao.queryById(depNum);
    }

    /**
     * 分页查询
     *
     * @param usedBudget 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<UsedBudget> queryByPage(UsedBudget usedBudget, PageRequest pageRequest) {
        long total = this.usedBudgetDao.count(usedBudget);
        return new PageImpl<>(this.usedBudgetDao.queryAllByLimit(usedBudget, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param usedBudget 实例对象
     * @return 实例对象
     */
    @Override
    public UsedBudget insert(UsedBudget usedBudget) {
        this.usedBudgetDao.insert(usedBudget);
        return usedBudget;
    }

    /**
     * 修改数据
     *
     * @param usedBudget 实例对象
     * @return 实例对象
     */
    @Override
    public UsedBudget update(UsedBudget usedBudget) {
        this.usedBudgetDao.update(usedBudget);
        return this.queryById(usedBudget.getDepNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param depNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depNum) {
        return this.usedBudgetDao.deleteById(depNum) > 0;
    }
}
