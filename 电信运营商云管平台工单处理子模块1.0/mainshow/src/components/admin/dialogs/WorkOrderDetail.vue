<template>
<!--  管理员工单查询界面，详情按钮-->
  <el-dialog
    title="工单详情信息"
    :visible.sync="$store.state.workOrderDetailVisibleDetail"
    width="50%"
    :before-close="handleClose"
    append-to-body=“true”>

    <!--      审批工单时显示申请人信息的无框表格-->
    <el-descriptions class="margin-top" title="申请人信息" :column="3" size="medium" border></el-descriptions>
    <el-descriptions class="margin-top":column="3" :size="size">
      <el-descriptions-item label="申请人姓名">{{tableDetailTop.name}}</el-descriptions-item>
      <el-descriptions-item label="工号">{{tableDetailTop.workerNum}}</el-descriptions-item>
      <el-descriptions-item label="所在部门编号">{{tableDetailTop.depNum}}</el-descriptions-item>
      <el-descriptions-item label="所在部门名称">{{tableDetailTop.depName}}</el-descriptions-item>
      <el-descriptions-item label="电话号码">{{tableDetailTop.phone}}</el-descriptions-item>
    </el-descriptions>

    <!--      显示工单基础信息-->
    <el-descriptions class="margin-top" title="工单信息信息" :column="3" size="medium" border></el-descriptions>
    <el-descriptions class="margin-top" :column="3" :size="size">
      <el-descriptions-item label="工单编号">{{tableDetailTop.workOrderNum}}</el-descriptions-item>
      <el-descriptions-item label="工单标题">{{tableDetailTop.workOrderName}}</el-descriptions-item>
      <el-descriptions-item label="工单类型">{{tableDetailTop.workOrderType}}</el-descriptions-item>
      <el-descriptions-item label="工单申请时间">{{tableDetailTop.applyTime}}</el-descriptions-item>
    </el-descriptions>
<!--工单申请理由-->
    <el-descriptions class="margin-top" title="申请理由" :column="3" size="medium" border></el-descriptions>
    <div class="reason_contect">{{tableDetailTop.reason}}</div>


<!--工单分配的计算机资源-->
    <el-descriptions class="margin-top" title="资源" :column="3" :size="size" border></el-descriptions>
    <el-table
      :data="tableDetailResource"
      border
      style="width: 100%">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        label="计算机类型"
        width="auto">
        <template slot-scope="scope">{{ scope.row.comType }}</template>
      </el-table-column>
      <el-table-column
        label="CPU核数"
        width="auto">
        <template slot-scope="scope">{{ scope.row.cpuCore }}</template>
      </el-table-column>
      <el-table-column
        label="内存">
        <template slot-scope="scope">{{ scope.row.ram }}</template>
      </el-table-column>
      <el-table-column
        label="存储大小">
        <template slot-scope="scope">{{ scope.row.storage }}</template>
      </el-table-column>
      <el-table-column
        label="ip地址">
        <template slot-scope="scope">{{ scope.row.ip }}</template>
      </el-table-column>
      <el-table-column
        label="操作系统">
        <template slot-scope="scope">{{ scope.row.os }}</template>
      </el-table-column>
    </el-table>

    <!--工单分配的计算机资源-->
    <el-descriptions class="margin-top" title="流转过程" :column="3" size="medium" border></el-descriptions>
    <el-table
      :data="tableDetailProcess"
      border
      style="width: 100%">
      <el-table-column
        label="处理人工号"
        width="auto">
        <template slot-scope="scope">{{ scope.row.dealNum }}</template>
      </el-table-column>
      <el-table-column
        label="处理人姓名"
        width="auto">
        <template slot-scope="scope">{{ scope.row.dealName }}</template>
      </el-table-column>
      <el-table-column
        label="操作类型"
        width="auto">
        <template slot-scope="scope">{{ scope.row.operationType }}</template>
      </el-table-column>
      <el-table-column
        label="处理日期"
        width="auto">
        <template slot-scope="scope">{{ scope.row.dealDate }}</template>
      </el-table-column>
      <el-table-column
        label="批注"
        width="auto">
        <template slot-scope="scope">{{ scope.row.dealComment }}</template>
      </el-table-column>
    </el-table>


    <span slot="footer" class="dialog-footer">
    <el-button @click="$store.state.workOrderDetailVisibleDetail = false">关闭</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: "WorkOrderDetail",
  data(){
    return{
      tableDetailTop: [],
      tableDetailResource: [],
      tableDetailProcess: [],
      formLabelWidth: '120px'
    }
  },
  methods:{
    //获取所有详情
    autoGetAllDetail(workOrderNum){
      //上半部分个人信息、工单信息和部门信息
      this.$axios.get('http://localhost:8084/adminSearchOrder/queryWorkOrderDetailTop?workOrderNum=' + workOrderNum).then((res)=>{
        this.tableDetailTop = res.data;
      });
      //获取计算机使用情况
      this.$axios.get('http://localhost:8084/allocatedCom/queryByWorkOrderNum?workOrderNum=' + workOrderNum).then((res)=>{
        this.tableDetailResource = res.data;
      })
      //获取工单流转过程情况
      this.$axios.get('http://localhost:8084/flowProcess/queryByWorkOrderNum?workOrderNum='+workOrderNum).then((res)=>{
        this.tableDetailProcess = res.data;
      })
    }
  },
}
</script>

<style scoped>

</style>
