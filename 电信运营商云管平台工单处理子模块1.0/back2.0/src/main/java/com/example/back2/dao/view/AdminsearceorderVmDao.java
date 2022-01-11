package com.example.back2.dao.view;

import com.example.back2.entity.view.AdminsearceorderVm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AdminsearceorderVm)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 18:53:15
 */
@Mapper
public interface AdminsearceorderVmDao {

    /**
     * 查询指定行数据
     *
     * @param adminsearceorderVm 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminsearceorderVm> queryAllByLimit(AdminsearceorderVm adminsearceorderVm, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param adminsearceorderVm 查询条件
     * @return 总行数
     */
    long count(AdminsearceorderVm adminsearceorderVm);


    List<AdminsearceorderVm> getOrderVm(String workOrderNum);

}

