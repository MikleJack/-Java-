package com.example.back2.service.view;

import com.example.back2.entity.view.InformSenderRecipentName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (InformSenderRecipentName)表服务接口
 *
 * @author makejava
 * @since 2022-01-19 09:50:54
 */
public interface InformSenderRecipentNameService {

    /**
     * 分页查询
     *
     * @param informSenderRecipentName 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<InformSenderRecipentName> queryByPage(InformSenderRecipentName informSenderRecipentName, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param informSenderRecipentName 实例对象
     * @return 实例对象
     */
    InformSenderRecipentName insert(InformSenderRecipentName informSenderRecipentName);


    /**
     * 通过员工编号查找和自己有关的
     *
     * @param workerNum 员工编号
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<InformSenderRecipentName> queryByWorkerNum(Integer workerNum, PageRequest pageRequest);
}
