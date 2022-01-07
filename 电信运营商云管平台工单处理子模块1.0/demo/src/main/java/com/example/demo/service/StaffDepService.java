package com.example.demo.service;

import com.example.demo.entity.StaffDep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (StaffDep)表服务接口
 *
 * @author makejava
 * @since 2022-01-07 18:22:49
 */
public interface StaffDepService {



    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<StaffDep> queryByPage( PageRequest pageRequest);


}
