<template>
  <div>
    <StaffAllOrderOffline ref="StaffAllOrderOffline"></StaffAllOrderOffline>
    <StaffAllOrderDelay ref="StaffAllOrderDelay"></StaffAllOrderDelay>
    <StaffAllOrderDetail ref="StaffAllOrderDetail"></StaffAllOrderDetail>
    <el-form :inline="true" :model="criteriaQueryByPage" class="demo-form-inline">
      <el-form-item label="工单类型">
        <!--    根据工单类型筛选工单-->
        <el-select v-model="criteriaQueryByPage.workOrderTypeSelector" placeholder="请选择工单类型">
          <el-option
            v-for="item in criteriaQueryByPage.orderType"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="工单标题">
        <!--          通过项目名称搜索项目-->
        <el-input
          placeholder="输入工单标题搜索"
          v-model="criteriaQueryByPage.searchOrderWorkerName"
          clearable>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleClick_search">查询</el-button>
        <el-button @click="handleClick_clean">清空</el-button>
      </el-form-item>
    </el-form>

    <!--    用户所有表单查询-->
    <el-table
      border
      style="width: 100%"
      :data="tableData"
      element-loading-background="rgba(245, 247, 250, 1)">
      <el-table-column
        prop="workOrderNum"
        label="工单编号"
        width="auto">
      </el-table-column>
      <el-table-column
        prop="workOrderType"
        label="工单类型"
        width="auto">
      </el-table-column>
      <el-table-column
        width="auto"
        prop="workOrderName"
        label="工单标题">
      </el-table-column>
      <el-table-column
        prop="expirationTime"
        label="到期时间"
        width="auto">
      </el-table-column>
      <el-table-column
        prop="workOrderState"
        label="工单状态"
        width="auto">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="250">
        <templte slot-scope="scope">
          <el-button @click="handleClick_detail(scope.row.workOrderNum)" type="text" size="small">详情</el-button>
          <el-button @click="handleClick_delay(scope.row.workOrderNum)"
                     type="text"
                     size="small"
                     :disabled="scope.row.workOrderState != '二级审批通过'">延期</el-button>
          <el-button @click="handleClick_offline(scope.row.workOrderNum,scope.row.workOrderState)"
                     type="text"
                     size="small"
                     :disabled="scope.row.workOrderState != '二级审批通过'">下线</el-button>
        </templte>
      </el-table-column>
    </el-table>
    <div class="page-tail" >
      <!--放置分页部分-->
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout=" prev, pager, next, jumper"
        :total="totalSize">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import StaffAllOrderOffline from "./dialogs/StaffAllOrderOffline";
import StaffAllOrderDelay from "./dialogs/StaffAllOrderDelay";
import StaffAllOrderDetail from "./dialogs/StaffAllOrderDetail";
export default {
  name: "StaffAllOrder",
  components:{StaffAllOrderOffline,StaffAllOrderDelay,StaffAllOrderDetail},
  data() {
    return {
      dialogVisible_detail: false,
      dialogVisible_delay: false,
      ticketData: [],

      //中间值，用来保存当前工单编号等信息以用于执行下线等操作
      IntermediateValue:'',

      //条件查询
      criteriaQueryByPage:{
        orderType: ['申请工单','延期工单','变更工单'],
        workOrderTypeSelector: '',
        searchOrderWorkerName: '',
      },

      //分页相关
      currentPage:1,
      pageSize:8,
      totalSize:0,
      ifPagination:false,

      search:'',

      tableData: [],
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      value1: '',
      delayTextarea: '',
      offLineTextarea: ''
    }
  },
  mounted() {
    //获取全部工单信息
    this.$axios.get(this.$store.state.url+'/staffAllTickets/criteriaQueryByPage?workerNum=' + sessionStorage.getItem("work_num")
                                                  + '&page=0'+ '&size=' + this.pageSize).then((res)=>{
      this.tableData = res.data.content;
      this.totalSize = res.data.totalPages*this.pageSize;
    })

  },
  methods: {
    //条件并分页查询
    handleClick_search(){
      this.ifPagination = true;
      this.resetPageSituation();
      this.$axios.get(this.$store.state.url+'/staffAllTickets/parameterQueryByPage?workOrderType=' + this.criteriaQueryByPage.workOrderTypeSelector+ '&workOrderTile='
        + this.criteriaQueryByPage.searchOrderWorkerName +'&workerNum=' +  sessionStorage.getItem('work_num') + '&page='+ 0 +'&size=' + this.pageSize).then((res)=>{
        this.tableData = res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;})
    },

    //重置查询
    handleClick_clean(){
      this.ifPagination = false;
      this.resetPageSituation();
      this.criteriaQueryByPage.workOrderTypeSelector = '';
      this.criteriaQueryByPage.searchOrderWorkerName = '';
      this.$axios.get(this.$store.state.url+'/staffAllTickets/criteriaQueryByPage?workerNum=' + sessionStorage.getItem("work_num")
        + '&page=0'+ '&size=' + this.pageSize).then((res)=>{
        this.tableData = res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    },

    //分页按钮操作
    handleCurrentChange(val){
      if(!this.ifPagination){
        this.currentPage=parseInt(val);
        let page = this.currentPage-1;
        this.$axios.get(this.$store.state.url+"/staffAllTickets/criteriaQueryByPage?workerNum=" + sessionStorage.getItem("work_num")
                          + '&page=' +page+"&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }else{
        this.currentPage=parseInt(val);
        let page = this.currentPage-1;
        this.$axios.get(this.$store.state.url+'/staffAllTickets/parameterQueryByPage?workOrderType=' + this.criteriaQueryByPage.workOrderTypeSelector+ '&workOrderTile='
          + this.criteriaQueryByPage.searchOrderWorkerName +'&workerNum=' +  sessionStorage.getItem('work_num') + '&page='+ page +'&size=' + this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }
    },
    //操作的详情dialog函数
    handleClick_detail(workOrderNum) {
      this.$refs.StaffAllOrderDetail.autoGetAllDetail(workOrderNum);
      this.$store.state.staffAllOrder_DetailDialogVisible = true;
    },
    //延期按钮对话框显示
    handleClick_delay(workOrderNum) {
      this.$store.state.staffAllOrder_DelayDialogVisible = true;
      this.$refs.StaffAllOrderDelay.setWorkOrderNum(workOrderNum);
    },
    //下线按钮对话框显示
    handleClick_offline(workOrderNum,workOrderState) {
      this.$store.state.staffAllOrder_OfflineDialogVisible = true;
      this.$refs.StaffAllOrderOffline.setWorkOrderNumAndState(workOrderNum,workOrderState);
    },

    //在进行查询时重置当前页状态，防止上一次查询的结果影响到当前的分页结果
    resetPageSituation(){
      this.currentPage = 1;
      this.pageSize = 8;
      this.totalSize = 0;
    }
  }
}

</script>

<style scoped>

.page-tail{
  bottom: 0;
  position: absolute;
}
</style>
