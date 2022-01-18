package com.example.back2.dao.view;

import com.example.back2.entity.view.Adminaccountmanage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Adminaccountmanage)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 10:09:09
 */
@Mapper
public interface AdminaccountmanageDao {

    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Adminaccountmanage> queryAllByLimit( @Param("pageable") Pageable pageable);

    /**
     * 根据员工姓名分页查询
     *
     * @param name          员工姓名
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Adminaccountmanage> criteriaQueryAllByLimit(String name, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count();
    long criteriaQueryCount(String name);

}

