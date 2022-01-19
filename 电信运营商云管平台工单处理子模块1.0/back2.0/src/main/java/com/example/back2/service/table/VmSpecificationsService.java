package com.example.back2.service.table;

import com.example.back2.entity.table.VmSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.Future;

/**
 * (VmSpecifications)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 14:35:55
 */
public interface VmSpecificationsService {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    VmSpecifications queryById(Integer comNum);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    Future<Page<VmSpecifications>> queryAllByLimit(PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param vmSpecifications 实例对象
     * @return 实例对象
     */
    VmSpecifications insert(VmSpecifications vmSpecifications);

    /**
     * 修改数据
     *
     * @param vmSpecifications 实例对象
     * @return 实例对象
     */
    VmSpecifications update(VmSpecifications vmSpecifications);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer comNum);

    List<VmSpecifications> selectAllvm();

    boolean insertVirtual(int cpuCore, int ram, int price, String processorModel, String processorFrequency, String description);

    Boolean updateDiskPrice(Double diskPrice);
}
