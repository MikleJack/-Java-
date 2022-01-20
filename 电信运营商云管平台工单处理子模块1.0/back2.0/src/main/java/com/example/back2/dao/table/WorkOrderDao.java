package com.example.back2.dao.table;

import com.example.back2.entity.table.WorkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * (WorkOrder)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-11 20:33:15
 */
@Mapper
public interface WorkOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workOrderNum 主键
     * @return 实例对象
     */
    WorkOrder queryById(String workOrderNum);

    /**
     * 查询指定行数据
     *
     * @param workOrder 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<WorkOrder> queryAllByLimit(WorkOrder workOrder, @Param("pageable") Pageable pageable);

    /**
     * 无条件的分页查询
     *
     * @param workerNum 员工编号
     * @param pageable 分页请求
     * @return 用户的全部工单
     */
    List<WorkOrder> criteriaQueryAllByLimit(@Param("workerNum") Integer workerNum, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param workOrder 查询条件
     * @return 总行数
     */
    long count(WorkOrder workOrder);
    long criteriaCount(Integer workerNum);

    /**
     * 新增数据
     *
     * @param workOrder 实例对象
     * @return 影响行数
     */
    int insert(WorkOrder workOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WorkOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WorkOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WorkOrder> entities);

    /**
     * 修改数据
     *
     * @param workOrder 实例对象
     * @return 影响行数
     */
    int update(WorkOrder workOrder);

    /**
     * 通过主键删除数据
     *
     * @param workOrderNum 主键
     * @return 影响行数
     */
    int deleteById(String workOrderNum);

    /**
     * 通过员工编号分页查询
     *
     * @param workOrderNum 工单编号
     * @param offlineReason  下线原因
     * @return 用户的全部工单
     */
    Boolean offline(String workOrderNum, String offlineReason);


//----------------------------延期按钮-顶部----------------------------

    /**
     * 通过工单编号进行延期操作
     *
     * @param workOrder  延期工单
     * @return 是否发起延期请求成功
     */
    Boolean insertDelayWorkOrder(WorkOrder workOrder);

    /**
     * 通过工单编号查询该工单的价格
     *
     * @param workOrderNum 主键
     * @return 该工单总价
     */
    Double queryPriceById(String workOrderNum);

//----------------------------延期按钮-底部----------------------------


    //-------------------员工全部工单查询界面--查询按钮-顶部----------------------------
    /**
     * 统计总行数
     *
     * @param workerTitle 工单名
     * @param workerNum  员工编号
     * @param workOrderType 工单类型
     * @return 总行数
     */
    long parameterCount(String workOrderType, String workerTitle,Integer workerNum);

    /**
     * 查询指定行数据
     *
     * @param workerTitle 工单名
     * @param workerNum  员工编号
     * @param workOrderType 工单类型
     * @return 对象列表
     */
    List<WorkOrder> parameterQueryAllByLimit(String workOrderType, String workerTitle,Integer workerNum, @Param("pageable") Pageable pageable);

//------------------------员工全部工单查询界面----查询按钮-底部----------------------------


//------------------------员工变更工单界面
    List<WorkOrder> selectWorkOrderByworkNum(String workerNum);

//    统计待审批工单数量
    long getNumPendticket(Integer workerNum);

    long getNumFailedtickets(Integer workerNum);

}

