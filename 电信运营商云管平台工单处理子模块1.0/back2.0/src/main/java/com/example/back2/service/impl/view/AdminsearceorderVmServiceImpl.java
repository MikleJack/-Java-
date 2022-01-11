package com.example.back2.service.impl.view;

import com.example.back2.entity.view.AdminsearceorderVm;
import com.example.back2.dao.view.AdminsearceorderVmDao;
import com.example.back2.service.view.AdminsearceorderVmService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AdminsearceorderVm)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 18:53:15
 */
@Service("adminsearceorderVmService")
public class AdminsearceorderVmServiceImpl implements AdminsearceorderVmService {
    @Resource
    private AdminsearceorderVmDao adminsearceorderVmDao;


    /**
     * 分页查询
     *
     * @param adminsearceorderVm 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AdminsearceorderVm> queryByPage(AdminsearceorderVm adminsearceorderVm, PageRequest pageRequest) {
        long total = this.adminsearceorderVmDao.count(adminsearceorderVm);
        return new PageImpl<>(this.adminsearceorderVmDao.queryAllByLimit(adminsearceorderVm, pageRequest), pageRequest, total);
    }

    @Override
    public List<AdminsearceorderVm> getOrderVm(String workOrderNum){
        return this.adminsearceorderVmDao.getOrderVm(workOrderNum);
    }

}
