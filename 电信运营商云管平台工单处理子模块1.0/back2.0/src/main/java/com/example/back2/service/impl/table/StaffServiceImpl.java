package com.example.back2.service.impl.table;

import com.example.back2.entity.table.Staff;
import com.example.back2.dao.table.StaffDao;
import com.example.back2.service.table.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Staff)表服务实现类
 *
 * @author makejava
 * @since 2022-01-10 19:34:37
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffDao staffDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workNum 主键
     * @return 实例对象
     */
    @Override
    public Staff queryById(Integer workNum) {
        return this.staffDao.queryById(workNum);
    }

    /**
     * 分页查询
     *
     * @param staff 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Staff> queryByPage(Staff staff, PageRequest pageRequest) {
        long total = this.staffDao.count(staff);
        return new PageImpl<>(this.staffDao.queryAllByLimit(staff, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    @Override
    public Staff insert(Staff staff) {
        this.staffDao.insert(staff);
        return staff;
    }

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 实例对象
     */
    @Override
    public Staff update(Staff staff) {
        this.staffDao.update(staff);
        return this.queryById(staff.getWorkNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param workNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workNum) {
        return this.staffDao.deleteById(workNum) > 0;
    }
}
