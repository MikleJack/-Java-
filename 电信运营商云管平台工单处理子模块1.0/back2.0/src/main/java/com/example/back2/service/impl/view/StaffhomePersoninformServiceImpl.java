package com.example.back2.service.impl.view;

import com.example.back2.entity.view.StaffhomePersoninform;
import com.example.back2.dao.view.StaffhomePersoninformDao;
import com.example.back2.service.view.StaffhomePersoninformService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * (StaffhomePersoninform)表服务实现类
 *
 * @author makejava
 * @since 2022-01-11 15:31:55
 */
@Service("staffhomePersoninformService")
public class StaffhomePersoninformServiceImpl implements StaffhomePersoninformService {
    @Resource
    private StaffhomePersoninformDao staffhomePersoninformDao;


    /**
     * 分页查询
     *
     * @param staffhomePersoninform 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<StaffhomePersoninform> queryByPage(StaffhomePersoninform staffhomePersoninform, PageRequest pageRequest) {
        long total = this.staffhomePersoninformDao.count(staffhomePersoninform);
        return new PageImpl<>(this.staffhomePersoninformDao.queryAllByLimit(staffhomePersoninform, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param staffhomePersoninform 实例对象
     * @return 实例对象
     */
    @Override
    public StaffhomePersoninform insert(StaffhomePersoninform staffhomePersoninform) {
        this.staffhomePersoninformDao.insert(staffhomePersoninform);
        return staffhomePersoninform;
    }

    /**
     * 通过员工编号查询员工信息
     *
     * @param workerNum 员工编号
     * @return 查询结果
     */
    @Override
    public StaffhomePersoninform queryPersonInformById(Integer workerNum) {
        return this.staffhomePersoninformDao.queryPersonInformById(workerNum);
    }
}
