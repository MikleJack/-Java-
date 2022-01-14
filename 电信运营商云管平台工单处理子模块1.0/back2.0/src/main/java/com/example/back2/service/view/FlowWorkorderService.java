package com.example.back2.service.view;

import com.example.back2.entity.view.FlowWorkorder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (FlowWorkorder)表服务接口
 *
 * @author makejava
 * @since 2022-01-14 09:33:37
 */
public interface FlowWorkorderService {

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<FlowWorkorder> queryByPage(String applyName,Integer dealNum, PageRequest pageRequest);


}
