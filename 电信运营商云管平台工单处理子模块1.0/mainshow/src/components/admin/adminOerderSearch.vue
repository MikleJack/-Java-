<template>
  <div>
    <!--    工单详情对话框-->
    <WorkOrderDetail ref="WorkOrderDetail"></WorkOrderDetail>

    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="工单类型">
        <!--    根据工单类型筛选工单-->
        <el-select v-model="criteriaQueryByPage.workOrderTypeSelector" filterable placeholder="请选择工单类型">
          <el-option
            v-for="item in criteriaQueryByPage.searchOrderType"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="员工姓名">
        <!--          通过项目名称搜索项目-->
        <el-input
          placeholder="输入员工姓名搜索"
          v-model="criteriaQueryByPage.searchOrderWorkerName">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleClick_search" class="search_button">查询</el-button>
        <el-button @click="handleClick_clean">清空</el-button>
      </el-form-item>
    </el-form>


<!--    data => !searchForm.searchOrderTitle || data.workOrderName.includes(searchForm.searchOrderTitle) 勿删，原版备份-->
<!--    .filter(data => data.workOrderName.includes(searchForm.searchOrderTitle))-->
    <!--    管理员表单查询-->
    <el-table
      border
      style="width: 100%"
      :data="tableData"
      element-loading-background="rgba(245, 247, 250, 1)">
      <el-table-column
        prop="workOrderNum"
        label="工单单号"
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
        prop="name"
        label="申请人"
        width="auto">
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
        </templte>
      </el-table-column>
    </el-table>

<!--    分页-->
    <div class="paging">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="prev, pager, next, jumper"
        :total="totalSize">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import WorkOrderDetail from "./dialogs/WorkOrderDetail";
export default {
  name: "ticketSearch",
  components:{WorkOrderDetail},
  data() {
    return {
      dialogVisibleDetail: false,
      dialogVisible_detail: false,
      ticketData: [],

      //分页相关
      currentPage:1,
      pageSize:9,
      totalSize:0,
      ifPagination:false,

      //搜索栏数据
      criteriaQueryByPage:{
        searchOrderType: ['申请工单','变更工单','延期工单'],
        workOrderTypeSelector:'',
        searchOrderWorkerName:'',
      },



      //当前页面
      thisPage:0,
      //存放所有工单
      tableData: [],
      //工单类型
      orderType:['申请工单'],
    }
  },
  mounted() {
    //获取全部工单信息
    this.$axios.get(this.$store.state.url+'/adminSearchOrder/normalQueryByPage?page='+ 0 + '&size=' +this.pageSize).then((res)=>{
      this.tableData = res.data.content;
      this.totalSize = res.data.totalPages*this.pageSize;
    })
    //条件查询工单信息


  },
  methods: {
    //操作的详情dialog函数
    handleClick_detail( workOrderNum ) {
      this.$store.state.workOrderDetailVisibleDetail = true;
      this.$refs.WorkOrderDetail.autoGetAllDetail(workOrderNum);
    },

    //条件并分页查询
    handleClick_search(){
      this.ifPagination = true;
      this.resetPageSituation();
      this.$axios.get(this.$store.state.url+'/adminSearchOrder/parameterQueryByPage?workOrderType=' + this.criteriaQueryByPage.workOrderTypeSelector
                      + '&workerName=' + this.criteriaQueryByPage.searchOrderWorkerName + '&page='+ 0 +'&size=' + this.pageSize).then((res)=>{
        this.tableData = res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;})
    },

    //重置查询
    handleClick_clean(){
      this.ifPagination = false;
      this.resetPageSituation();
      this.criteriaQueryByPage.workOrderTypeSelector = '';
      this.criteriaQueryByPage.searchOrderWorkerName = '';
      this.$axios.get(this.$store.state.url+'/adminSearchOrder/normalQueryByPage?page='+ 0 + '&size=' +this.pageSize).then((res)=>{
        this.tableData = res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    },

    //分页按钮操作
    handleCurrentChange(val){
      if(!this.ifPagination){
        this.currentPage=parseInt(val);
        let page = this.currentPage-1;
        this.$axios.get(this.$store.state.url+"/adminSearchOrder/normalQueryByPage?page="+page+"&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }else{
        this.currentPage=parseInt(val);
        let page = this.currentPage-1;
        this.$axios.get(this.$store.state.url+'/adminSearchOrder/parameterQueryByPage?workOrderType=' + this.criteriaQueryByPage.workOrderTypeSelector
                + '&workerName=' + this.criteriaQueryByPage.searchOrderWorkerName +'&page=' +page+"&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }
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
.paging{
  width:100%;
  height: 60px;
  position: relative;
  bottom: 0;
}
.search_button{
  color: #fff;
  background-color: rgba(82, 182, 154, 0.76);
  border-color: #52b69a;
}
</style>
