<template>

  <div class="page-main">
    <!--主要界面-->
    <div class="page-head">
      <!--界面头部-->

        <!--设置居中-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="工单类型">
          <el-select v-model="orderState" placeholder="工单类型">
            <!--下拉的可选表单框-->
            <el-option label="全部工单" value=""></el-option>
            <el-option label="待审批工单" value="待审批"></el-option>
            <el-option label="审批不通过" value="审批不通过"></el-option>
            <el-option label="已变更" value="已变更"></el-option>
            <el-option label="挂起" value="挂起"></el-option>
            <el-option label="一级审批通过" value="一级审批通过"></el-option>
            <el-option label="二级审批通过" value="二级审批通过"></el-option>
            <!--为后端提供选择的工单类型，为上述三种之一-->

          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" class="add_type">查询</el-button>
          <!--从后端查询选择的工单类型的详细信息-->
        </el-form-item>
      </el-form>
      </div>


      <div class="page-body">
        <!--界面体-->
        <el-table
          :data="tableData"
          border
          style="width: 100%">
          <el-table-column
            fixed
            prop="workOrderNum"
            label="工单编号"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="workOrderName"
            label="工单标题"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="workOrderType"
            label="工单类型"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="workOrderState"
            label="工单状态"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="expirationTime"
            label="到期时间"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="workerNum"
            label="申请人工号"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="name"
            label="申请人姓名"
            width="auto">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button @click=handleClick_detail(scope.row.workOrderNum) type="text" size="small">查看</el-button>

            </template>
          </el-table-column>
        </el-table>
        <order_detail ref="order_detail"></order_detail>

      </div>



    <div class="page-tail">
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
import order_detail from "./order_detail";

    export default {
      name: "all_work_order",
      components: {order_detail},
      mounted() {
        if(sessionStorage.getItem("level")==="3"){
          this.second=sessionStorage.getItem("work_num");
          this.$axios.get(this.$store.state.url+"/pendtickets/selectTow?page=0&size="+this.pageSize+"&orderState="+this.orderState).then((res)=>{
            this.tableData= res.data.content;
            this.totalSize = res.data.totalPages*this.pageSize;
          })
        }
        else {
          this.first=sessionStorage.getItem("work_num");
          this.$axios.get(this.$store.state.url+"/leader/selectTicketsByNum?second_leader_num="+this.second+
            "&first_leader_num="+this.first+"&size="+this.pageSize+"&page="+0+"&orderState="+this.orderState).then((res)=>{
            this.tableData= res.data.content;
            this.totalSize = res.data.totalPages*this.pageSize;
          })
        }

      },
      data() {
        return {
          //领导人编号
          first:"",
          second:"",
          orderState:'',
          //工单详情弹窗
          // dialogTableVisible: false,
          tableData: [],
          //分页相关
          currentPage:1,
          pageSize:8,
          totalSize:0,
        }
      },
      methods: {
        onSubmit() {
          if(sessionStorage.getItem("level")==="3"){
            this.$axios.get(this.$store.state.url+"/pendtickets/selectTow?page=0&size="+this.pageSize+"&orderState="+this.orderState).then((res)=>{
              this.tableData= res.data.content;
              this.totalSize = res.data.totalPages*this.pageSize;
            })
          }
          else {
            this.$axios.get(this.$store.state.url+"/leader/selectTicketsByNum?second_leader_num="+this.second+
              "&first_leader_num="+this.first+"&size="+this.pageSize+"&page="+0+"&orderState="+this.orderState).then((res)=>{
              this.tableData= res.data.content;
              this.totalSize = res.data.totalPages*this.pageSize;
            })
          }

        },
        //进行查询，后端给前端姓名对应的操作日志,包括工号、姓名、操作时间、操作、ip地址、地址
        handleCurrentChange(val){
          this.currentPage=parseInt(val);
          let page = this.currentPage-1;
          if(sessionStorage.getItem("level")==="3"){
            this.$axios.get(this.$store.state.url+"/pendtickets/selectTow?page="+page+"&size="+this.pageSize+"&orderState="+this.orderState).then((res)=>{
              this.tableData= res.data.content;
              this.totalSize = res.data.totalPages*this.pageSize;
            })
          }
          else {
            this.$axios.get(this.$store.state.url+"/leader/selectTicketsByNum?second_leader_num="+this.second+
              "&first_leader_num="+this.first+"&size="+this.pageSize+"&page="+page+"&orderState="+this.orderState).then((res)=>{
              this.tableData= res.data.content;
              this.totalSize = res.data.totalPages*this.pageSize;
            })
          }
        },
        handleClick_detail( workOrderNum ) {
          this.$refs.order_detail.autoGetAllDetail(workOrderNum);
          this.$store.state.order_detail_dialogTableVisible = true;


        }

      }
    }
</script>


<!--对界面各个部分进行划分范围-->
<style scoped>
  .page-main{
    position: relative;
    width:100%;
    height: 100%;
  }

  .page-tail{
    width:100%;
    position: absolute;
    bottom: 0;
  }
  .add_type {
    color: #fff;
    background-color: rgba(82, 182, 154, 0.8);
    border-color: #52b69a;
  }
</style>
