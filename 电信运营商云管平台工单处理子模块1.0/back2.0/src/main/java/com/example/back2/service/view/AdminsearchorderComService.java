package com.example.back2.service.view;

import com.example.back2.entity.view.AdminsearchorderCom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.Future;

/**
 * (AdminsearchorderCom)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 18:13:47
 */
public interface AdminsearchorderComService {

    /**
     * 分页查询
     *
     * @param adminsearchorderCom 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminsearchorderCom> queryByPage(AdminsearchorderCom adminsearchorderCom, PageRequest pageRequest);

    Future<List<AdminsearchorderCom>> getOrderCom(String workOrderNum);
}
