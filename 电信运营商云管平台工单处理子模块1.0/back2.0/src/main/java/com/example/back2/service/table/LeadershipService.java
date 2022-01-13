package com.example.back2.service.table;

import com.example.back2.entity.table.Leadership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Leadership)表服务接口
 *
 * @author makejava
 * @since 2022-01-13 16:05:43
 */
public interface LeadershipService {

    /**
     * 通过ID查询单条数据
     *
     * @param lederNum 主键
     * @return 实例对象
     */
    Leadership queryById(Integer lederNum);

    /**
     * 分页查询
     *
     * @param leadership 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Leadership> queryByPage(Leadership leadership, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param leadership 实例对象
     * @return 实例对象
     */
    Leadership insert(Leadership leadership);

    /**
     * 修改数据
     *
     * @param leadership 实例对象
     * @return 实例对象
     */
    Leadership update(Leadership leadership);

    /**
     * 通过主键删除数据
     *
     * @param lederNum 主键
     * @return 是否成功
     */
    boolean deleteById(Integer lederNum);


    List<Leadership> getLeaderNum(Integer underNum);
}
