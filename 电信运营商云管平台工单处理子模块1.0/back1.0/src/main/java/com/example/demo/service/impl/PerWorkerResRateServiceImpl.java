package com.example.demo.service.impl;

import com.example.demo.entity.PerWorkerResRate;
import com.example.demo.dao.PerWorkerResRateDao;
import com.example.demo.entity.WorkOrder;
import com.example.demo.service.PerWorkerResRateService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Perworkerresrate)表服务实现类
 *
 * @author makejava
 * @since 2022-01-05 10:59:07
 */
@Service("perworkerresrateService")
public class PerWorkerResRateServiceImpl implements PerWorkerResRateService {
    @Resource
    private PerWorkerResRateDao perworkerresrateDao;

    /**
     * 分页查询
     *
     * @param perworkerresrate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<PerWorkerResRate> queryByPage(PerWorkerResRate perworkerresrate, PageRequest pageRequest) {
        long total = this.perworkerresrateDao.count(perworkerresrate);
        return new PageImpl<>(this.perworkerresrateDao.queryAllByLimit(perworkerresrate, pageRequest), pageRequest, total);
    }

    /**
     * 查询返回所有数据
     *
     * @return 实例对象
     */
    public List<WorkOrder> queryAll(){
        return perworkerresrateDao.queryAll();
    }


}
