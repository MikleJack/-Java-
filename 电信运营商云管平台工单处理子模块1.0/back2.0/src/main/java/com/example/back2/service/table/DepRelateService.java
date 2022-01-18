package com.example.back2.service.table;

import com.example.back2.entity.table.DepRelate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (DepRelate)表服务接口
 *
 * @author makejava
 * @since 2022-01-18 10:31:34
 */
public interface DepRelateService {

    /**
     * 通过ID查询单条数据
     *
     * @param depNumSuperior 主键
     * @return 实例对象
     */
    DepRelate queryById(Integer depNumSuperior);

    /**
     * 分页查询
     *
     * @param depRelate 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<DepRelate> queryByPage(DepRelate depRelate, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param depRelate 实例对象
     * @return 实例对象
     */
    DepRelate insert(DepRelate depRelate);

    /**
     * 修改数据
     *
     * @param depRelate 实例对象
     * @return 实例对象
     */
    DepRelate update(DepRelate depRelate);

    /**
     * 通过主键删除数据
     *
     * @param depNumSuperior 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depNumSuperior);

}
