package com.example.back2.service.view;

import com.example.back2.entity.view.Adminoperationlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Adminoperationlog)表服务接口
 *
 * @author makejava
 * @since 2022-01-11 09:42:32
 */
public interface AdminoperationlogService {

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Adminoperationlog> queryByPage( PageRequest pageRequest);

}
