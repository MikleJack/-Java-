package com.example.back2.service.impl.view;

import com.example.back2.entity.view.HistoryUsage;
import com.example.back2.dao.view.HistoryUsageDao;
import com.example.back2.service.view.HistoryUsageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HistoryUsage)表服务实现类
 *
 * @author makejava
 * @since 2022-01-20 16:46:37
 */
@Service("historyUsageService")
public class HistoryUsageServiceImpl implements HistoryUsageService {
    @Resource
    private HistoryUsageDao historyUsageDao;

    /**
     * 分页查询
     *
     * @param historyUsage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<HistoryUsage> queryByPage(HistoryUsage historyUsage, PageRequest pageRequest) {
        long total = this.historyUsageDao.count(historyUsage);
        return new PageImpl<>(this.historyUsageDao.queryAllByLimit(historyUsage, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param historyUsage 实例对象
     * @return 实例对象
     */
    @Override
    public HistoryUsage insert(HistoryUsage historyUsage) {
        this.historyUsageDao.insert(historyUsage);
        return historyUsage;
    }

    /**
     * 根据员工编号返回其所有工单
     *
     * @param workerNum 员工编号
     * @return 该员工的所有工单
     */
    @Override
    public Double getStaffUsage(Integer workerNum) {
        List<HistoryUsage> staffUsages =  this.historyUsageDao.getStaffUsage(workerNum);
        Double totalUsedPrice = 0.0,totalPrice = 0.0;

        for(int i = 0; i < staffUsages.size(); i++){
            HistoryUsage historyUsage = staffUsages.get(i);
            totalPrice += historyUsage.getPrice();
            totalUsedPrice += historyUsage.getPrice() * historyUsage.getResUtilization();
        }
        Double staffUsage = totalUsedPrice / totalPrice;
        return staffUsage;
    }

}
