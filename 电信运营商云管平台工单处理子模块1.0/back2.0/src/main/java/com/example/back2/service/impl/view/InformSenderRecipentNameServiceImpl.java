package com.example.back2.service.impl.view;

import com.example.back2.entity.view.InformSenderRecipentName;
import com.example.back2.dao.view.InformSenderRecipentNameDao;
import com.example.back2.service.view.InformSenderRecipentNameService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (InformSenderRecipentName)表服务实现类
 *
 * @author makejava
 * @since 2022-01-19 09:50:54
 */
@Service("informSenderRecipentNameService")
public class InformSenderRecipentNameServiceImpl implements InformSenderRecipentNameService {
    @Resource
    private InformSenderRecipentNameDao informSenderRecipentNameDao;


    /**
     * 分页查询
     *
     * @param informSenderRecipentName 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<InformSenderRecipentName> queryByPage(InformSenderRecipentName informSenderRecipentName, PageRequest pageRequest) {
        long total = this.informSenderRecipentNameDao.count(informSenderRecipentName);
        return new PageImpl<>(this.informSenderRecipentNameDao.queryAllByLimit(informSenderRecipentName, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param informSenderRecipentName 实例对象
     * @return 实例对象
     */
    @Override
    public InformSenderRecipentName insert(InformSenderRecipentName informSenderRecipentName) {
        this.informSenderRecipentNameDao.insert(informSenderRecipentName);
        return informSenderRecipentName;
    }


    /**
     * 通过员工编号查找和自己有关的
     *
     * @param workerNum 员工编号
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<InformSenderRecipentName> queryByWorkerNum(Integer workerNum, PageRequest pageRequest){
        long total = this.informSenderRecipentNameDao.countByWorkerNum(workerNum);
        return new PageImpl<>(this.informSenderRecipentNameDao.queryAllByLimitWorkerNum(workerNum, pageRequest), pageRequest, total);
    }
}
