package com.example.back2.service.impl.view;

import com.example.back2.entity.view.FlowStaff;
import com.example.back2.dao.view.FlowStaffDao;
import com.example.back2.service.view.FlowStaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FlowStaff)表服务实现类
 *
 * @author makejava
 * @since 2022-01-13 19:24:10
 */
@Service("flowStaffService")
public class FlowStaffServiceImpl implements FlowStaffService {
    @Resource
    private FlowStaffDao flowStaffDao;
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<FlowStaff> queryAllByLimit(int offset, int limit) {
        return this.flowStaffDao.queryAllByLimit(offset, limit);
    }


//    通过工单号查找相应的流转过程
    @Override
    public List<FlowStaff> selectByWorkOrderNum(String workOrderNum){
        return this.flowStaffDao.selectByWorkOrderNum(workOrderNum);
    }
}
