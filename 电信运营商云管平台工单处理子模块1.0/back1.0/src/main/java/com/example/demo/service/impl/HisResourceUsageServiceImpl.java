package com.example.demo.service.impl;

import com.example.demo.entity.HisResourceUsage;
import com.example.demo.dao.HisResourceUsageDao;
import com.example.demo.service.HisResourceUsageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HisResourceUsage)表服务实现类
 *
 * @author makejava
 * @since 2022-01-05 21:19:23
 */
@Service("hisResourceUsageService")
public class HisResourceUsageServiceImpl implements HisResourceUsageService {
    @Resource
    private HisResourceUsageDao hisResourceUsageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    @Override
    public HisResourceUsage queryById(String workOrderNum) {
        return this.hisResourceUsageDao.queryById(workOrderNum);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HisResourceUsage> queryAllByLimit(int offset, int limit) {
        return this.hisResourceUsageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param hisResourceUsage 实例对象
     * @return 实例对象
     */
    @Override
    public HisResourceUsage insert(HisResourceUsage hisResourceUsage) {
        this.hisResourceUsageDao.insert(hisResourceUsage);
        return hisResourceUsage;
    }

    /**
     * 修改数据
     *
     * @param hisResourceUsage 实例对象
     * @return 实例对象
     */
    @Override
    public HisResourceUsage update(HisResourceUsage hisResourceUsage) {
        this.hisResourceUsageDao.update(hisResourceUsage);
        return this.queryById(hisResourceUsage.getWorkOrderNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String workOrderNum) {
        return this.hisResourceUsageDao.deleteById(workOrderNum) > 0;
    }
}
