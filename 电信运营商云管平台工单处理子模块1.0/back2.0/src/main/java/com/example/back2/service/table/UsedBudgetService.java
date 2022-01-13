package com.example.back2.service.table;

import com.example.back2.entity.table.UsedBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (UsedBudget)表服务接口
 *
 * @author makejava
 * @since 2022-01-13 14:37:28
 */
public interface UsedBudgetService {

    /**
     * 通过ID查询单条数据
     *
     * @param depNum 主键
     * @return 实例对象
     */
    UsedBudget queryById(Integer depNum);

    /**
     * 分页查询
     *
     * @param usedBudget 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<UsedBudget> queryByPage(UsedBudget usedBudget, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param usedBudget 实例对象
     * @return 实例对象
     */
    UsedBudget insert(UsedBudget usedBudget);

    /**
     * 修改数据
     *
     * @param usedBudget 实例对象
     * @return 实例对象
     */
    UsedBudget update(UsedBudget usedBudget);

    /**
     * 通过主键删除数据
     *
     * @param depNum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depNum);

}
