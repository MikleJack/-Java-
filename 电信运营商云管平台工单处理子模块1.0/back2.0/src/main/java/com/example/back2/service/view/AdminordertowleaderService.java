package com.example.back2.service.view;

import com.example.back2.entity.view.Adminordertowleader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.concurrent.Future;

/**
 * (Adminordertowleader)表服务接口
 *
 * @author makejava
 * @since 2022-01-18 16:54:29
 */
public interface AdminordertowleaderService {


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Future<Page<Adminordertowleader>> queryByPage(String orderState,PageRequest pageRequest);


}
