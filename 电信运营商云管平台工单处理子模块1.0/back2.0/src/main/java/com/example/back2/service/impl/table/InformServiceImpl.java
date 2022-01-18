package com.example.back2.service.impl.table;

import com.example.back2.entity.table.Inform;
import com.example.back2.dao.table.InformDao;
import com.example.back2.entity.table.Leadership;
import com.example.back2.service.table.InformService;
import com.example.back2.service.table.LeadershipService;
import com.example.back2.service.table.WorkOrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Inform)表服务实现类
 *
 * @author makejava
 * @since 2022-01-18 14:35:14
 */
@Service("informService")
public class InformServiceImpl implements InformService {
    @Resource
    private InformDao informDao;

    @Resource
    private LeadershipService leadershipService;

    @Resource
    private WorkOrderService workOrderService;


    /**
     * 通过ID查询单条数据
     *
     * @param informNum 主键
     * @return 实例对象
     */
    @Override
    public Inform queryById(Integer informNum) {
        return this.informDao.queryById(informNum);
    }

    /**
     * 分页查询
     *
     * @param inform 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Inform> queryByPage(Inform inform, PageRequest pageRequest) {
        long total = this.informDao.count(inform);
        return new PageImpl<>(this.informDao.queryAllByLimit(inform, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param inform 实例对象
     * @return 实例对象
     */
    @Override
    public Inform insert(Inform inform) {
        this.informDao.insert(inform);
        return inform;
    }

    /**
     * 修改数据
     *
     * @param inform 实例对象
     * @return 实例对象
     */
    @Override
    public Inform update(Inform inform) {
        this.informDao.update(inform);
        return this.queryById(inform.getInformNum());
    }

    /**
     * 通过主键删除数据
     *
     * @param informNum 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer informNum) {
        return this.informDao.deleteById(informNum) > 0;
    }

    /**
     * 普通员工发起工单申请，插入消息通知
     *
     * @param workOrderNum 工单编号
     * @param workNum 工人编号
     * @param detail 消息详情
     * @return 实例对象
     */
    @Override
    public void staffApplyInsertInform(String workOrderNum, Integer workNum, String detail){
        Inform inform = new Inform();
        inform.setReadState(false);
        inform.setSendTime(new Date());
        inform.setSenderNum(workNum);
        inform.setDetails(detail);
        inform.setWorkOrderNum(workOrderNum);

        //消息接受者只为自己上一级领导
        List<Leadership> upLeadershipList = this.leadershipService.getLeaderNum(workNum);
        for(int i = 0; i < upLeadershipList.size() ; i++){
            inform.setWorkOrderNum(workOrderNum);
            inform.setRecipient(upLeadershipList.get(i).getLederNum());
            this.informDao.insert(inform);
        }
        return ;
    }

    /**
     * 一级领导审批工单，发起消息通知
     *
     * @param workOrderNum 工单编号
     * @param workNum 工人编号
     * @param detail 消息详情
     * @return 实例对象
     */
    @Override
    public void firstLeaderInsertInform(String workOrderNum, Integer workNum, String detail){
        Inform inform = new Inform();
        inform.setReadState(false);
        inform.setSendTime(new Date());
        inform.setSenderNum(workNum);
        inform.setDetails(detail);
        inform.setWorkOrderNum(workOrderNum);
        inform.setRecipient(this.workOrderService.queryById(workOrderNum).getWorkerNum());

        this.informDao.insert(inform);
        //消息接受者只为工单申请者和领导的上级领导
        List<Leadership> upLeadershipList = this.leadershipService.getLeaderNum(workNum);
        for(int i = 0; i < upLeadershipList.size() ; i++){
            inform.setWorkOrderNum(workOrderNum);
            inform.setRecipient(upLeadershipList.get(i).getLederNum());
            this.informDao.insert(inform);
        }

        return ;
    }

    /**
     * 二级领导审批或挂起工单，发起消息通知
     *
     * @param workOrderNum 工单编号
     * @param workNum 工人编号
     * @param detail 消息详情
     * @return 实例对象
     */
    @Override
    public void secondLeaderInsertInform(String workOrderNum, Integer workNum, String detail){
        Inform inform = new Inform();
        inform.setReadState(false);
        inform.setSendTime(new Date());
        inform.setSenderNum(workNum);
        inform.setDetails(detail);
        inform.setWorkOrderNum(workOrderNum);

        Integer applicant = this.workOrderService.queryById(workOrderNum).getWorkerNum();
        inform.setRecipient(applicant);
        //消息的通知者只为该工单的发起人和该发起人的一级领导
        this.informDao.insert(inform);
        List<Leadership> upLeadershipList = this.leadershipService.getLeaderNum(applicant);
        for(int i = 0; i < upLeadershipList.size() ; i++){
            inform.setWorkOrderNum(workOrderNum);
            inform.setRecipient(upLeadershipList.get(i).getLederNum());
            this.informDao.insert(inform);
        }

        return ;
    }

    /**
     * 通过消息接受人的账号查询此人发送或接收的所有通知
     *
     * @param workNum 消息接受人的账号
     * @return 此人发送或接收的所有通知
     */
    public List<Inform> queryBySenderNumOrRecipientNum(Integer workNum){
        return this.informDao.queryBySenderNumOrRecipientNum(workNum);
    }
}
