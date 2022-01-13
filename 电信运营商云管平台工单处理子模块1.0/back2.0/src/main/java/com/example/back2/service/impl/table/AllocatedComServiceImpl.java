package com.example.back2.service.impl.table;

import com.example.back2.entity.table.AllocatedCom;
import com.example.back2.dao.table.AllocatedComDao;
import com.example.back2.service.table.AllocatedComService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AllocatedCom)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 19:33:19
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
    public AllocatedCom queryById(Integer comNum) {
        return this.allocatedComDao.queryById(comNum);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AllocatedCom> queryAllByLimit(int offset, int limit) {
        return this.allocatedComDao.queryAllByLimit(offset, limit);
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
    public boolean deleteById(Integer comNum) {
        return this.allocatedComDao.deleteById(comNum) > 0;
    }

    /**
     * 通过工单编号查询该工单所有物理机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有物理机资源
     */
    @Override
    public List<AllocatedCom> queryByWorkOrderNum(String workOrderNum){
        return this.allocatedComDao.queryByWorkOrderNum(workOrderNum);
    }
}
