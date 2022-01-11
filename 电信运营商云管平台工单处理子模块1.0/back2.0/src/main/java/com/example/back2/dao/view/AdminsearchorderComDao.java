package com.example.back2.dao.view;

import com.example.back2.entity.view.AdminsearchorderCom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AdminsearchorderCom)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 18:13:47
 */
@Mapper
public interface AdminsearchorderComDao {


    /**
     * 查询指定行数据
     *
     * @param adminsearchorderCom 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminsearchorderCom> queryAllByLimit(AdminsearchorderCom adminsearchorderCom, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param adminsearchorderCom 查询条件
     * @return 总行数
     */
    long count(AdminsearchorderCom adminsearchorderCom);

    List<AdminsearchorderCom> getOrderCom(String workOrderNum);


}

