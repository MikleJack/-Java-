package com.example.back2.dao.view;

import com.example.back2.entity.view.InformSenderRecipentName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (InformSenderRecipentName)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-19 09:50:54
 */
@Mapper
public interface InformSenderRecipentNameDao {


    /**
     * 查询指定行数据
     *
     * @param informSenderRecipentName 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<InformSenderRecipentName> queryAllByLimit(InformSenderRecipentName informSenderRecipentName, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param informSenderRecipentName 查询条件
     * @return 总行数
     */
    long count(InformSenderRecipentName informSenderRecipentName);
    long countByWorkerNum(Integer workerNum);

    /**
     * 新增数据
     *
     * @param informSenderRecipentName 实例对象
     * @return 影响行数
     */
    int insert(InformSenderRecipentName informSenderRecipentName);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<InformSenderRecipentName> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<InformSenderRecipentName> entities);

    /**
     * 通过员工编号查找和自己有关的
     *
     * @param workerNum 员工编号
     * @param pageable      分页对象
     * @return 查询结果
     */
    List<InformSenderRecipentName> queryAllByLimitWorkerNum(Integer workerNum, @Param("pageable") Pageable pageable);


}

