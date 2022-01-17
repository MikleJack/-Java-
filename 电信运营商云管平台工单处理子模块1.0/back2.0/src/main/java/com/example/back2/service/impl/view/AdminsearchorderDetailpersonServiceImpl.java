package com.example.back2.service.impl.view;

import com.example.back2.entity.view.AdminsearchorderDetailperson;
import com.example.back2.dao.view.AdminsearchorderDetailpersonDao;
import com.example.back2.service.view.AdminsearchorderDetailpersonService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * (AdminsearchorderDetailperson)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 09:50:13
 */
@Service("adminsearchorderDetailpersonService")
public class AdminsearchorderDetailpersonServiceImpl implements AdminsearchorderDetailpersonService {
    @Resource
    private AdminsearchorderDetailpersonDao adminsearchorderDetailpersonDao;

    /**
     * 分页查询
     *
     * @param adminsearchorderDetailperson 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AdminsearchorderDetailperson> queryByPage(AdminsearchorderDetailperson adminsearchorderDetailperson, PageRequest pageRequest) {
        long total = this.adminsearchorderDetailpersonDao.count(adminsearchorderDetailperson);
        return new PageImpl<>(this.adminsearchorderDetailpersonDao.queryAllByLimit(adminsearchorderDetailperson, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param adminsearchorderDetailperson 实例对象
     * @return 实例对象
     */
    @Override
    public AdminsearchorderDetailperson insert(AdminsearchorderDetailperson adminsearchorderDetailperson) {
        this.adminsearchorderDetailpersonDao.insert(adminsearchorderDetailperson);
        return adminsearchorderDetailperson;
    }


    /**
     * 管理员工单界面的详情按钮
     *此处只返回上半部分信息，下半部分信息调用其他接口
     *
     * @param workOrderNum 工单编号
     * @return 每个工单详情页面上的员工信息、部门信息、工单信息
     */
    @Async
    @Override
    public Future<AdminsearchorderDetailperson> queryWorkOrderDetailTop(String workOrderNum){
        return new AsyncResult<>(this.adminsearchorderDetailpersonDao.queryWorkOrderDetailTop(workOrderNum));
    }

}
