package com.example.back2.dao.table;

import com.example.back2.entity.VirtualComResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (VirtualComResource)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-14 11:33:07
 */
@Mapper
public interface VirtualComResourceDao {

    /**
     * 查询指定行数据
     *
     * @param virtualComResource 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VirtualComResource> queryAllByLimit(VirtualComResource virtualComResource, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param virtualComResource 查询条件
     * @return 总行数
     */
    long count(VirtualComResource virtualComResource);

    /**
     * 新增数据
     *
     * @param virtualComResource 实例对象
     * @return 影响行数
     */
    int insert(VirtualComResource virtualComResource);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VirtualComResource> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VirtualComResource> entities);

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

    VirtualComResource selectOne();
}

