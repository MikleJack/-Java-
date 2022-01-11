package com.example.back2.dao.view;

import com.example.back2.entity.view.Adminoperationlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Adminoperationlog)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 09:42:32
 */
@Mapper
public interface AdminoperationlogDao {


    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Adminoperationlog> queryAllByLimit( @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count();


}

