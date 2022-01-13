package com.example.back2.dao.view;

import com.example.back2.entity.view.AllocatedVmSpecifications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AllocatedVmSpecifications)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-13 19:52:29
 */
@Mapper
public interface AllocatedVmSpecificationsDao {

    /**
     * 查询指定行数据
     *
     * @param allocatedVmSpecifications 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AllocatedVmSpecifications> queryAllByLimit(AllocatedVmSpecifications allocatedVmSpecifications, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param allocatedVmSpecifications 查询条件
     * @return 总行数
     */
    long count(AllocatedVmSpecifications allocatedVmSpecifications);

    /**
     * 新增数据
     *
     * @param allocatedVmSpecifications 实例对象
     * @return 影响行数
     */
    int insert(AllocatedVmSpecifications allocatedVmSpecifications);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AllocatedVmSpecifications> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AllocatedVmSpecifications> entities);

    /**
     * 通过工单编号查询该工单所有虚拟机机资源
     *
     * @param workOrderNum 工单编号
     * @return 该工单所有虚拟机资源
     */
    List<AllocatedVmSpecifications> queryVmByWorkOrderNum(String workOrderNum);

}

