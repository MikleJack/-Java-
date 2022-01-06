package com.example.demo.service;

import com.example.demo.entity.DepTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (DepTree)表服务接口
 *
 * @author makejava
 * @since 2022-01-05 15:20:51
 */
public interface DepTreeService {


    /**
     * 分页查询
     *
     * @param depTree 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<DepTree> queryByPage(DepTree depTree, PageRequest pageRequest);


    List<DepTree> cateList();
}
