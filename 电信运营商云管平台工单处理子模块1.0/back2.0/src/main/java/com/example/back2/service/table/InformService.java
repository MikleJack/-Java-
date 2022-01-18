package com.example.back2.service.table;

import com.example.back2.entity.table.Inform;
import com.example.back2.entity.table.Leadership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

/**
 * (Inform)表服务接口
 *
 * @author makejava
 * @since 2022-01-18 14:35:14
 */
public interface InformService {

    /**
     * 通过ID查询单条数据
     *
     * @param informNum 主键
     * @return 实例对象
     */
    Inform queryById(Integer informNum);

    /**
     * 分页查询
     *
     * @param inform 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Inform> queryByPage(Inform inform, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param inform 实例对象
     * @return 实例对象
     */
    Inform insert(Inform inform);

    /**
     * 修改数据
     *
     * @param inform 实例对象
     * @return 实例对象
     */
    Inform update(Inform inform);

    /**
     * 通过主键删除数据
     *
     * @param informNum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer informNum);

    /**
     * 通过工单编号和申请人插入消息通知
     *
     * @param workOrderNum 工单编号
     * @param workNum 工人编号
     * @param detail 消息详情
     * @return 实例对象
     */
    void staffApplyInsertInform(String workOrderNum, Integer workNum, String detail);

    /**
     * 一级领导审批或挂起工单，发起消息通知
     *
     * @param workOrderNum 工单编号
     * @param workNum 工人编号
     * @param detail 消息详情
     * @return 实例对象
     */
    void firstLeaderInsertInform(String workOrderNum, Integer workNum, String detail);

}
