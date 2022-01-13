package com.example.back2.service.view;

import com.example.back2.entity.view.AllocatedVmSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * (AllocatedVmSpecifications)表服务接口
 *
 * @author makejava
 * @since 2022-01-13 19:52:29
 */
public interface AllocatedVmSpecificationsService {

    /**
     * 分页查询
     *
     * @param allocatedVmSpecifications 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AllocatedVmSpecifications> queryByPage(AllocatedVmSpecifications allocatedVmSpecifications, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param allocatedVmSpecifications 实例对象
     * @return 实例对象
     */
    AllocatedVmSpecifications insert(AllocatedVmSpecifications allocatedVmSpecifications);

    /**
     * 通过工单编号查询该工单所有虚拟机机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    List<AllocatedVmSpecifications> queryVmByWorkOrderNum(String workOrderNum);

}
