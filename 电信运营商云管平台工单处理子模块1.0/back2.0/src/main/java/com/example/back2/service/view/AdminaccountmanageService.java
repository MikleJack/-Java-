package com.example.back2.service.view;

import com.example.back2.entity.view.Adminaccountmanage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * (Adminaccountmanage)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 10:09:09
 */
public interface AdminaccountmanageService {


    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Adminaccountmanage> queryByPage( PageRequest pageRequest);

    /**
     * 带条件的分页查询
     *
     * @param name 员工姓名
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Adminaccountmanage> criteriaQueryByPage(String name, PageRequest pageRequest);

}
