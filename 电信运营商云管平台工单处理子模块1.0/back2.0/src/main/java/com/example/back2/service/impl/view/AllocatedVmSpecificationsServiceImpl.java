package com.example.back2.service.impl.view;

import com.example.back2.entity.view.AllocatedVmSpecifications;
import com.example.back2.dao.view.AllocatedVmSpecificationsDao;
import com.example.back2.service.view.AllocatedVmSpecificationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AllocatedVmSpecifications)表服务实现类
 *
 * @author makejava
 * @since 2022-01-13 19:52:29
 */
@Service("allocatedVmSpecificationsService")
public class AllocatedVmSpecificationsServiceImpl implements AllocatedVmSpecificationsService {
    @Resource
    private AllocatedVmSpecificationsDao allocatedVmSpecificationsDao;

    /**
     * 分页查询
     *
     * @param allocatedVmSpecifications 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AllocatedVmSpecifications> queryByPage(AllocatedVmSpecifications allocatedVmSpecifications, PageRequest pageRequest) {
        long total = this.allocatedVmSpecificationsDao.count(allocatedVmSpecifications);
        return new PageImpl<>(this.allocatedVmSpecificationsDao.queryAllByLimit(allocatedVmSpecifications, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param allocatedVmSpecifications 实例对象
     * @return 实例对象
     */
    @Override
    public AllocatedVmSpecifications insert(AllocatedVmSpecifications allocatedVmSpecifications) {
        this.allocatedVmSpecificationsDao.insert(allocatedVmSpecifications);
        return allocatedVmSpecifications;
    }


    /**
     * 通过工单编号查询该工单所有虚拟机机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    @Override
    public List<AllocatedVmSpecifications> queryVmByWorkOrderNum(String workOrderNum){
        return this.allocatedVmSpecificationsDao.queryVmByWorkOrderNum(workOrderNum);
    }
}
