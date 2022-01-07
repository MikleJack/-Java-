package com.example.demo.service.impl;

import com.example.demo.dao.AllocatedComDao;
import com.example.demo.entity.AllocatedCom;
import com.example.demo.service.AllocatedComService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AllocatedCom)表服务实现类
 *
 * @author makejava
 * @since 2022-01-07 16:58:45
 */
@Service("allocatedComService")
public class AllocatedComServiceImpl implements AllocatedComService {
    @Resource
    private AllocatedComDao allocatedComDao;

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    @Override
    public AllocatedCom queryById(String comNum) {
        return this.allocatedComDao.queryById(comNum);
    }

    /**
     * 分页查询
     *
     * @param allocatedCom 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AllocatedCom> queryByPage(AllocatedCom allocatedCom, PageRequest pageRequest) {
        long total = this.allocatedComDao.count(allocatedCom);
        return new PageImpl<>(this.allocatedComDao.queryAllByLimit(allocatedCom, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param allocatedCom 实例对象
     * @return 实例对象
     */
    @Override
    public AllocatedCom insert(AllocatedCom allocatedCom) {
        this.allocatedComDao.insert(allocatedCom);
        return allocatedCom;
    }

    /**
     * 修改数据
     *
     * @param allocatedCom 实例对象
     * @return 实例对象
     */
    @Override
    public AllocatedCom update(AllocatedCom allocatedCom) {
        this.allocatedComDao.update(allocatedCom);
        return this.queryById(allocatedCom.getComNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String comNum) {
        return this.allocatedComDao.deleteById(comNum) > 0;
    }

    /**
     * 管理员工单查询界面，详细按钮
     * 通过工单编号查询该工单利用所有的资源
     *
     * @param workOrderNum 主键
     * @return 该工单利用的所有资源
     */
    @Override
    public List<AllocatedCom> queryByWorkOrderNum(String workOrderNum) {
        return this.allocatedComDao.queryByWorkOrderNum(workOrderNum);
    }
}


