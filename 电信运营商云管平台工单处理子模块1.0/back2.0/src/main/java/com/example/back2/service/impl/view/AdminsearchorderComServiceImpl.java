package com.example.back2.service.impl.view;

import com.example.back2.entity.view.AdminsearchorderCom;
import com.example.back2.dao.view.AdminsearchorderComDao;
import com.example.back2.service.view.AdminsearchorderComService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (AdminsearchorderCom)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 18:13:47
 */
@Service("adminsearchorderComService")
public class AdminsearchorderComServiceImpl implements AdminsearchorderComService {
    @Resource
    private AdminsearchorderComDao adminsearchorderComDao;

    /**
     * 分页查询
     *
     * @param adminsearchorderCom 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AdminsearchorderCom> queryByPage(AdminsearchorderCom adminsearchorderCom, PageRequest pageRequest) {
        long total = this.adminsearchorderComDao.count(adminsearchorderCom);
        return new PageImpl<>(this.adminsearchorderComDao.queryAllByLimit(adminsearchorderCom, pageRequest), pageRequest, total);
    }

    @Async
    @Override
    public Future<List<AdminsearchorderCom>> getOrderCom(String workOrderNum){
        return new AsyncResult<>(this.adminsearchorderComDao.getOrderCom(workOrderNum));
    }

}
