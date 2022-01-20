package com.example.back2.service.impl.table;

import com.example.back2.entity.table.VmSpecifications;
import com.example.back2.dao.table.VmSpecificationsDao;
import com.example.back2.service.table.VmSpecificationsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (VmSpecifications)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 14:35:55
 */
@Service("vmSpecificationsService")
public class VmSpecificationsServiceImpl implements VmSpecificationsService {
    @Resource
    private VmSpecificationsDao vmSpecificationsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    @Override
    public VmSpecifications queryById(Integer comNum) {
        return this.vmSpecificationsDao.queryById(comNum);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public Future<Page<VmSpecifications>> queryAllByLimit(PageRequest pageRequest) {
        long total = this.vmSpecificationsDao.count();
        return new AsyncResult<>(new PageImpl<>(this.vmSpecificationsDao.queryAllByLimit(pageRequest),pageRequest, total));
    }

    /**
     * 新增数据
     *
     * @param vmSpecifications 实例对象
     * @return 实例对象
     */
    @Override
    public VmSpecifications insert(VmSpecifications vmSpecifications) {
        this.vmSpecificationsDao.insert(vmSpecifications);
        return vmSpecifications;
    }

    /**
     * 修改数据
     *
     * @param vmSpecifications 实例对象
     * @return 实例对象
     */
    @Override
    public VmSpecifications update(VmSpecifications vmSpecifications) {
        this.vmSpecificationsDao.update(vmSpecifications);
        return this.queryById(vmSpecifications.getComNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer comNum) {
        return this.vmSpecificationsDao.deleteById(comNum) > 0;
    }

    @Override
    public List<VmSpecifications> selectAllvm(){
        return this.vmSpecificationsDao.selectAllvm();
    }

    @Override
    public boolean insertVirtual(int cpuCore, int ram, int price,String processorModel,String processorFrequency,String description){
        this.vmSpecificationsDao.insertVirtual(cpuCore,ram,price,processorModel,processorFrequency,description);
        return true;
    }

    @Override
    public Boolean updateDiskPrice(Double diskPrice){
        this.vmSpecificationsDao.updateDiskPrice(diskPrice);
        return true;
    }

    @Override
    public int setVmPrice(Integer comNum,double price){
        return this.vmSpecificationsDao.setVmPrice(comNum,price);
    }
}
