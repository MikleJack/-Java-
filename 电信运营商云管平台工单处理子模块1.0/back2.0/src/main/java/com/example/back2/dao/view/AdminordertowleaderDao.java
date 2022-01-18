package com.example.back2.dao.view;

import com.example.back2.entity.view.Adminordertowleader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Adminordertowleader)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-18 16:54:29
 */
@Mapper
public interface AdminordertowleaderDao {


    /**
     * 查询指定行数据

     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Adminordertowleader> queryAllByLimit(@Param("pageable") Pageable pageable);

    /**
     * 统计总行数

     * @return 总行数
     */
    long count();


}

