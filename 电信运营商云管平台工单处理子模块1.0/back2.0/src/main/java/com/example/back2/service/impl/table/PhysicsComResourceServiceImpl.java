package com.example.back2.service.impl.table;

import com.example.back2.entity.table.PhysicsComResource;
import com.example.back2.dao.table.PhysicsComResourceDao;
import com.example.back2.entity.table.WorkOrder;
import com.example.back2.service.table.PhysicsComResourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (PhysicsComResource)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 14:23:57
 */
@Service("physicsComResourceService")
public class PhysicsComResourceServiceImpl implements PhysicsComResourceService {
    @Resource
    private PhysicsComResourceDao physicsComResourceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    @Override
    public PhysicsComResource queryById(Integer comNum) {
        return this.physicsComResourceDao.queryById(comNum);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Async
    @Override
    public Future<Page<PhysicsComResource>> queryAllByLimit(PageRequest pageRequest) {
        long total = this.physicsComResourceDao.count();
        return new AsyncResult<>(new PageImpl<>(this.physicsComResourceDao.queryAllByLimit(pageRequest), pageRequest, total));
    }

    /**
     * 新增数据
     *
     * @param physicsComResource 实例对象
     * @return 实例对象
     */
    @Override
    public PhysicsComResource insert(PhysicsComResource physicsComResource) {
        this.physicsComResourceDao.insert(physicsComResource);
        return physicsComResource;
    }

    /**
     * 修改数据
     *
     * @param physicsComResource 实例对象
     * @return 实例对象
     */
    @Override
    public PhysicsComResource update(PhysicsComResource physicsComResource) {
        this.physicsComResourceDao.update(physicsComResource);
        return this.queryById(physicsComResource.getComNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param comNum .z主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer comNum) {
        return this.physicsComResourceDao.deleteById(comNum) > 0;
    }

    @Override
    public List<PhysicsComResource> selectAllpc(){
        return this.physicsComResourceDao.selectAllpc();
    }

    public boolean insertPhysics(int cpuCore, int ram, int storage, int price){
        this.physicsComResourceDao.insertPhysics(cpuCore,ram,storage,price);
        return true;
    }


    /**
     * 改变物理机分配状态
     *
     * @param comNums  即将下线的物理机编号列表
     * @param assign  选择分配状态
     * @return 是否修改成功
     */
    @Override
    public boolean setComAssign(List<Integer> comNums,boolean assign){
        boolean ifAssigned = true;
        for(int i =0 ;i < comNums.size(); i++){
            if(!this.physicsComResourceDao.setComAssign(comNums.get(i),assign)){
                ifAssigned = false;
            }
        }
        return ifAssigned;
    }


}
