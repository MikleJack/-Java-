package com.example.back2.service.view;

import com.example.back2.entity.view.AdminsearceorderVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.concurrent.Future;

/**
 * (AdminsearceorderVm)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 18:53:15
 */
public interface AdminsearceorderVmService {


    /**
     * 分页查询
     *
     * @param adminsearceorderVm 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AdminsearceorderVm> queryByPage(AdminsearceorderVm adminsearceorderVm, PageRequest pageRequest);


    Future<List<AdminsearceorderVm>> getOrderVm(String workOrderNum);
}
