package com.example.demo.service.impl;

import com.example.demo.entity.StaffDep;
import com.example.demo.dao.StaffDepDao;
import com.example.demo.service.StaffDepService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StaffDep)表服务实现类
 *
 * @author makejava
 * @since 2022-01-07 18:22:49
 */
@Service("staffDepService")
public class StaffDepServiceImpl implements StaffDepService {
    @Resource
    private StaffDepDao staffDepDao;



    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<StaffDep> queryByPage( PageRequest pageRequest) {
        long total = this.staffDepDao.count();
        List<StaffDep> staffDepList = this.staffDepDao.queryAllByLimit( pageRequest);
        for (StaffDep staffDep : staffDepList) {
            if (staffDep.getState())
                staffDep.setState_string("未锁定");
            else
                staffDep.setState_string("锁定");
        }
        return new PageImpl<>(staffDepList, pageRequest, total);
    }






}
