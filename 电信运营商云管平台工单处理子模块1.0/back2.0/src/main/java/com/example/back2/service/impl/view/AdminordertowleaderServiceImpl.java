package com.example.back2.service.impl.view;

import com.example.back2.entity.view.Adminordertowleader;
import com.example.back2.dao.view.AdminordertowleaderDao;
import com.example.back2.service.view.AdminordertowleaderService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * (Adminordertowleader)表服务实现类
 *
 * @author makejava
 * @since 2022-01-18 16:54:29
 */
@Service("adminordertowleaderService")
public class AdminordertowleaderServiceImpl implements AdminordertowleaderService {
    @Resource
    private AdminordertowleaderDao adminordertowleaderDao;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Async
    @Override
    public Future<Page<Adminordertowleader>> queryByPage(PageRequest pageRequest) {
        long total = this.adminordertowleaderDao.count();
        return new AsyncResult<>( new PageImpl<>(this.adminordertowleaderDao.queryAllByLimit(pageRequest), pageRequest, total));
    }

}
