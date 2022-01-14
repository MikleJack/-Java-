package com.example.back2.service.impl.view;

import com.example.back2.entity.view.FlowWorkorder;
import com.example.back2.dao.view.FlowWorkorderDao;
import com.example.back2.service.view.FlowWorkorderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (FlowWorkorder)表服务实现类
 *
 * @author makejava
 * @since 2022-01-14 09:33:37
 */
@Service("flowWorkorderService")
public class FlowWorkorderServiceImpl implements FlowWorkorderService {
    @Resource
    private FlowWorkorderDao flowWorkorderDao;

    /**
     * 分页查询
     *
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<FlowWorkorder> queryByPage(String applyName,Integer dealNum, PageRequest pageRequest) {
        long total = this.flowWorkorderDao.count(applyName,dealNum);
        return new PageImpl<>(this.flowWorkorderDao.queryAllByLimit(applyName,dealNum, pageRequest), pageRequest, total);
    }
}
