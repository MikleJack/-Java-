package com.example.demo.service;

import com.example.demo.entity.AllocatedCom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (AllocatedCom)表服务接口
 *
 * @author makejava
 * @since 2022-01-07 16:58:45
 */
public interface AllocatedComService {

    /**
     * 通过ID查询单条数据
     *
     * @param comNum 主键
     * @return 实例对象
     */
    AllocatedCom queryById(String comNum);

    /**
     * 分页查询
     *
     * @param allocatedCom 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AllocatedCom> queryByPage(AllocatedCom allocatedCom, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param allocatedCom 实例对象
     * @return 实例对象
     */
    AllocatedCom insert(AllocatedCom allocatedCom);

    /**
     * 修改数据
     *
     * @param allocatedCom 实例对象
     * @return 实例对象
     */
    AllocatedCom update(AllocatedCom allocatedCom);

    /**
     * 通过主键删除数据
     *
     * @param comNum 主键
     * @return 是否成功
     */
    boolean deleteById(String comNum);

    /**
     * 管理员工单查询界面，详细按钮
     * 通过工单编号查询该工单利用所有的资源
     *
     * @param workOrderNum 主键
     * @return 该工单利用的所有资源
     */
    List<AllocatedCom> queryByWorkOrderNum(String workOrderNum);

}
