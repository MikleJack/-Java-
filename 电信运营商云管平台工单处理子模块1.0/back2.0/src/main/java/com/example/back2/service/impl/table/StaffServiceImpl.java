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
    public Page<Staff> queryByPage(PageRequest pageRequest) {
        long total = this.staffDao.count();
        return new PageImpl<>(this.staffDao.queryAllByLimit( pageRequest), pageRequest, total);
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

    /**
     * 重置密码
     *
     * @param work_num 用户编号
     * @param password    密码
     * @return 影响行数
     */
    @Override
    public boolean updatePassword(Integer work_num,String password){
        return this.staffDao.updatePassword(work_num,password);
    }

    /**
     * 锁定当前账户，将账户状态从正常改为锁定
     *
     */
    @Override
    public boolean lockAccount(Integer workNum){
        return this.staffDao.lockAccount(workNum);
    }

    /**
     * 解锁当前账户，将账户状态从false改为true
     *
     */
    @Override
    public boolean unlockAccount(Integer workNum){
        return this.staffDao.unlockAccount(workNum);
    }

    /**
     * 删除制定账户,将用户是否在公司的状态改为false
     * @param workNum 用户编号
     */
    @Override
    public boolean deleteAccount(Integer workNum){
        return this.staffDao.deleteAccount(workNum);
    }

    /**
     * 新增账户
     * @param name 账户名称
     * @param depNum 账户部门编号
     * @param phone 账户电话
     * @param work_password 账户密码
     */
    @Override
    public boolean addAccount(String name,String depNum,
                              String phone,String work_password){
        return this.staffDao.addAccount(name,depNum,phone,work_password);
    }

}
