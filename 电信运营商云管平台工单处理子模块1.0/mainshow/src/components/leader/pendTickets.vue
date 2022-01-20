<template>
  <div class="page">
    <div class="page_central">
      <el-table
        :data="tableData"
        border
        style="width: 100%;">
        <el-table-column
          fixed
          prop="workOrderNum"
          label="工单编号"
          align="center">
        </el-table-column>
        <el-table-column
          prop="workOrderName"
          label="工单标题"
          align="center">
        </el-table-column>
        <el-table-column
          prop="workOrderType"
          label="工单类型"
          align="center">
        </el-table-column>
        <el-table-column
          prop="workOrderState"
          label="工单状态"
          align="center">
        </el-table-column>
        <el-table-column
          prop="expirationTime"
          label="资源到期时间"
          sortable
          align="center">
        </el-table-column>
        <el-table-column
          prop="workerNum"
          label="申请人工号"
          align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="申请人姓名"
          align="center">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          align="center">
          <template slot-scope="scope">
            <el-button type="text" @click=handleClick_detail(scope.row.workOrderNum,scope.row.workerNum)>查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <ticket_details ref="ticket_details" :show="true"></ticket_details>

    </div>
    <div class="page-tail">
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
import Ticket_details from "./ticket_details";
export default {
  name: "pending_ticket",
  components: {Ticket_details},
  mounted() {
    if(sessionStorage.getItem("level")==="3"){
      this.worderOrderState="一级审批通过";
      this.second=sessionStorage.getItem("work_num");
      this.$axios.get(this.$store.state.url+"/pendtickets/selectTow?page=0&size="+this.pageSize).then((res)=>{
        this.tableData= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    }
    else {
      this.worderOrderState="待审批";
      this.first=sessionStorage.getItem("work_num");
      this.$axios.get(this.$store.state.url+"/pendtickets/selectTicketsByState?second_leader_num="+this.second+"&first_leader_num="+this.first+"&page="+0+"&size="
        +this.pageSize+"&orderState="+this.worderOrderState).then((res)=>{
        this.tableData= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      });
    }

  },
  methods: {
    //进行查询，后端给前端姓名对应的操作日志,包括工号、姓名、操作时间、操作、ip地址、地址
    handleCurrentChange(val){
      this.currentPage=parseInt(val);
      let page = this.currentPage-1;
      if(sessionStorage.getItem("level")==="3"){
        this.$axios.get("https://localhost:8084/pendtickets/selectTow?page="+page+"&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }
      else{
        this.$axios.get(this.$store.state.url+"/pendtickets/selectTicketsByState?second_leader_num="+this.second+"&first_leader_num="+this.first+"&page="+page+"&size="
          +this.pageSize+"&orderState="+this.worderOrderState).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }

    },
    handleClick_detail(workOrderNum,workerNum){
      // this.dialogTableVisible = true;
      this.$store.state.pendtickets_dialogTableVisible = true;
      this.$refs.ticket_details.autoGetAllDetail(workOrderNum, workerNum);
    }
  },

  data() {
    return {
      //查询的工单状态
      worderOrderState:"待审批",
      //领导人编号
      first:"",
      second:"",
      //需要数据： 申请工单 的 申请工单号，工单标题，申请时间（apply_time），申请人工号，申请人姓名
      tableData: [],
      //分页相关
      currentPage:1,
      pageSize:7,
      totalSize:0
      // dialogTableVisible:false
    }
  }
}
</script>

<style scoped>
/*页面样式*/
.page {
  position: relative;
  width: 100%;
  left: 0;
  height: 100%;
}

/*页面中部*/
.page_central{
  position: relative;
  width: 100%;

}

.page-tail{
  width:100%;
  position: absolute;
  bottom: 0;
}
</style>
