package com.example.back2.service.impl.table;

import com.example.back2.entity.table.DepRelate;
import com.example.back2.dao.table.DepRelateDao;
import com.example.back2.service.table.DepRelateService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (DepRelate)表服务实现类
 *
 * @author makejava
 * @since 2022-01-18 10:31:34
 */
@Service("depRelateService")
public class DepRelateServiceImpl implements DepRelateService {
    @Resource
    private DepRelateDao depRelateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param depNumSuperior 主键
     * @return 实例对象
     */
    @Override
    public DepRelate queryById(Integer depNumSuperior) {
        return this.depRelateDao.queryById(depNumSuperior);
    }

    /**
     * 分页查询
     *
     * @param depRelate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<DepRelate> queryByPage(DepRelate depRelate, PageRequest pageRequest) {
        long total = this.depRelateDao.count(depRelate);
        return new PageImpl<>(this.depRelateDao.queryAllByLimit(depRelate, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param depRelate 实例对象
     * @return 实例对象
     */
    @Override
    public DepRelate insert(DepRelate depRelate) {
        this.depRelateDao.insert(depRelate);
        return depRelate;
    }

    /**
     * 修改数据
     *
     * @param depRelate 实例对象
     * @return 实例对象
     */
    @Override
    public DepRelate update(DepRelate depRelate) {
        this.depRelateDao.update(depRelate);
        return this.queryById(depRelate.getDepNumSuperior());
    }

    /**
     * 通过主键删除数据
     *
     * @param depNumSuperior 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depNumSuperior) {
        return this.depRelateDao.deleteById(depNumSuperior) > 0;
    }
}
