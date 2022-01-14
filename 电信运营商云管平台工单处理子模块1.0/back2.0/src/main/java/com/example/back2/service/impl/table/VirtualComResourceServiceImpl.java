package com.example.back2.service.impl.table;

import com.example.back2.entity.VirtualComResource;
import com.example.back2.dao.table.VirtualComResourceDao;
import com.example.back2.service.table.VirtualComResourceService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (VirtualComResource)表服务实现类
 *
 * @author makejava
 * @since 2022-01-14 11:33:07
 */
@Service("virtualComResourceService")
public class VirtualComResourceServiceImpl implements VirtualComResourceService {
    @Resource
    private VirtualComResourceDao virtualComResourceDao;

    /**
     * 分页查询
     *
     * @param virtualComResource 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VirtualComResource> queryByPage(VirtualComResource virtualComResource, PageRequest pageRequest) {
        long total = this.virtualComResourceDao.count(virtualComResource);
        return new PageImpl<>(this.virtualComResourceDao.queryAllByLimit(virtualComResource, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param virtualComResource 实例对象
     * @return 实例对象
     */
    @Override
    public VirtualComResource insert(VirtualComResource virtualComResource) {
        this.virtualComResourceDao.insert(virtualComResource);
        return virtualComResource;
    }

    /**
     * 更新虚拟机资源
     *
     * @param cpuCore cpu核心
     * @param ram 内存
     * @param storage 存储
     * @param upOrDown 新增还是减少  up / down
     * @return 实例对象
     */
    @Override
    public boolean updateVmResource(Integer cpuCore ,Integer ram,Integer storage, String upOrDown){
        return this.virtualComResourceDao.updateVmResource(cpuCore, ram, storage, upOrDown);
    }

}
