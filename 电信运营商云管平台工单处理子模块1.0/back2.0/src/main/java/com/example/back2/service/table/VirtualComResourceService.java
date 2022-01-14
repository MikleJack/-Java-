package com.example.back2.service.table;

import com.example.back2.entity.VirtualComResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (VirtualComResource)表服务接口
 *
 * @author makejava
 * @since 2022-01-14 11:33:07
 */
public interface VirtualComResourceService {

    /**
     * 分页查询
     *
     * @param virtualComResource 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VirtualComResource> queryByPage(VirtualComResource virtualComResource, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param virtualComResource 实例对象
     * @return 实例对象
     */
    VirtualComResource insert(VirtualComResource virtualComResource);

    /**
     * 更新虚拟机资源
     *
     * @param cpuCore cpu核心
     * @param ram 内存
     * @param storage 存储
     * @param upOrDown 新增还是减少  up / down
     * @return 实例对象
     */
    boolean updateVmResource(Integer cpuCore ,Integer ram,Integer storage, String upOrDown);
}
