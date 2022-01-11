<template>
  <div class="page">
    <div class="page_central">
      <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
          fixed
          prop="workOrderNum"
          label="工单编号"
          style="margin: auto"
          align="center">
        </el-table-column>
        <el-table-column
          prop="workOrderName"
          label="工单标题"
          width="auto"
          align="center">
        </el-table-column>
        <el-table-column
          prop="workOrderType"
          label="工单类型"
          width="auto"
          align="center">
        </el-table-column>
        <el-table-column
          prop="workOrderState"
          label="工单状态"
          width="auto"
          align="center">
        </el-table-column>
        <el-table-column
          prop="expirationTime"
          label="资源到期时间"
          sortable
          width="auto"
          align="center">
        </el-table-column>
        <el-table-column
          prop="workerNum"
          label="申请人工号"
          width="auto"
          align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="申请人姓名"
          width="auto"
          align="center">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100"
          align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="dialogTableVisible = true">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="工单详情" :visible.sync="dialogTableVisible"width="80%">
        <ticket_details></ticket_details>
      </el-dialog>
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
    this.$axios.get("http://localhost:8084/leader/selectTicketsByState?first_leader_num=20220002&page="+0+"&size="
      +this.pageSize+"&orderState=待审批").then((res)=>{
      this.tableData= res.data.content;
      this.totalSize = res.data.totalPages*this.pageSize;
    })
  },
  methods: {
    //进行查询，后端给前端姓名对应的操作日志,包括工号、姓名、操作时间、操作、ip地址、地址
    handleCurrentChange(val){
      this.currentPage=parseInt(val);
      let page = this.currentPage-1;
      this.$axios.get("http://localhost:8084/leader/selectTicketsByState?first_leader_num=20220002&page="+page+"&size="
        +this.pageSize+"&orderState=待审批").then((res)=>{
        this.tableData= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    }
  },

  data() {
    return {
      //需要数据： 申请工单 的 申请工单号，工单标题，申请时间（apply_time），申请人工号，申请人姓名
      tableData: [],
      //分页相关
      currentPage:1,
      pageSize:9,
      totalSize:0,

      dialogTableVisible:false
    }
  }
}
</script>

<style scoped>
/*页面样式*/
.page {
  position: relative;
  left:130px;
  width: 100%;
  height: 100%;
}

/*页面中部*/
.page_central{
  position: relative;
  width: 80%;

}

.page-tail{
  width:100%;
  height: 60px;
  position: absolute;
  bottom: 0;
}
</style>
