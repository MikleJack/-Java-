package com.example.demo.dao.leader;

import com.example.demo.entity.leader.LeaderOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (LeaderOrderDetail)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-08 11:08:04
 */
@Mapper
public interface LeaderOrderDetailDao {



    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<LeaderOrderDetail> queryAllByLimit( String leader_num,@Param("pageable") Pageable pageable,String orderState);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count(String leader_num);


}

