package com.example.back2.service.view;

import com.example.back2.entity.view.Adminorganmanage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Adminorganmanage)表服务接口
 *
 * @author makejava
 * @since 2022-01-17 20:23:06
 */
public interface AdminorganmanageService {

    /**
     * 通过ID查询单条数据
     *
     * @param  depNum 主键
     * @return 实例对象
     */
    List<Adminorganmanage> queryById(Integer depNum);

    /**
     * 分页查询
     *
     * @param adminorganmanage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Adminorganmanage> queryByPage(Adminorganmanage adminorganmanage, PageRequest pageRequest);

    List<Integer> getLowDep(Integer depNum);

}
