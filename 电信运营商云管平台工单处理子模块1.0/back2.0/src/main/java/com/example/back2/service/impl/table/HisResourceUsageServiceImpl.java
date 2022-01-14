package com.example.back2.service.impl.table;

import com.example.back2.entity.table.HisResourceUsage;
import com.example.back2.dao.table.HisResourceUsageDao;
import com.example.back2.service.table.HisResourceUsageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HisResourceUsage)表服务实现类
 *
 * @author makejava
 * @since 2022-01-14 15:35:14
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
     * 分页查询
     *
     * @param hisResourceUsage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HisResourceUsage> queryByPage(HisResourceUsage hisResourceUsage, PageRequest pageRequest) {
        long total = this.hisResourceUsageDao.count(hisResourceUsage);
        return new PageImpl<>(this.hisResourceUsageDao.queryAllByLimit(hisResourceUsage, pageRequest), pageRequest, total);
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
