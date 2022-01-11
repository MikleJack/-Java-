package com.example.back2.dao.table;

import com.example.back2.entity.table.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Staff)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-10 19:34:37
 */
@Mapper
public interface StaffDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workNum 主键
     * @return 实例对象
     */
    Staff queryById(Integer workNum);

    /**
     * 查询指定行数据
     *
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Staff> queryAllByLimit(@Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count();

    /**
     * 新增数据
     *
     * @param staff 实例对象
     * @return 影响行数
     */
    int insert(Staff staff);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Staff> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Staff> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Staff> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Staff> entities);

    /**
     * 修改数据
     *
     * @param staff 实例对象
     * @return 影响行数
     */
    int update(Staff staff);

    /**
     * 通过主键删除数据
     *
     * @param workNum 主键
     * @return 影响行数
     */
    int deleteById(Integer workNum);

    /**
     * 重置密码
     *
     * @param work_num 用户编号
     * @param password   密码
     * @return 影响行数
     */
    boolean updatePassword(Integer work_num,String password);

    /**
     * 锁定当前账户，将账户状态从正常改为锁定
     *
     * @param workNum 用户编号
     */
    boolean lockAccount(Integer workNum);

    /**
     * 解锁当前账户，将账户状态从false改为true
     * @param workNum 用户编号
     */
    boolean unlockAccount(Integer workNum);

    /**
     * 删除制定账户,将用户是否在公司的状态改为false
     * @param workNum 用户编号
     */
    boolean deleteAccount(Integer workNum);

    /**
     * 新增账户
     * @param name 账户名称
     * @param depNum 账户部门编号
     * @param phone 账户电话
     * @param work_password 账户密码
     */
    boolean addAccount(String name,String depNum,
                       String phone,String work_password);


}

