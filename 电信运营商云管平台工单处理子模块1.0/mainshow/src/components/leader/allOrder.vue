<template>

  <div class="page-main">
    <!--主要界面-->
    <div class="page-head">
      <!--界面头部-->

        <!--设置居中-->
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="工单类型">
          <el-select v-model="formInline.work_order_type" placeholder="工单类型">
            <!--下拉的可选表单框-->
            <el-option label="待审批工单" value="work_order_to_approve"></el-option>
            <el-option label="已审批工单" value="work_order_have_approved"></el-option>
            <el-option label="已挂起工单" value="work_order_have_suspended"></el-option>
            <!--为后端提供选择的工单类型，为上述三种之一-->
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
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
        <el-dialog title="工单详情" :visible.sync="dialogTableVisible"width="80%">
          <order_detail ref="order_detail" :show="false"></order_detail>
        </el-dialog>
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
// import Ticket_details from "./ticket_details";
import order_detail from "./order_detail";

    export default {
        name: "all_work_order",
      components: {order_detail},
      mounted() {
        this.$axios.get("http://localhost:8084/alltickets/selectTicketsByNum?second_leader_num=20220013&page=0&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      },
      data() {
        return {
          formInline: {
            work_order_type: ''
          },
          //工单详情弹窗
          dialogTableVisible: false,
          tableData: [],
          //分页相关
          currentPage:1,
          pageSize:8,
          totalSize:0,
        }
      },
      methods: {
        onSubmit() {
          console.log('submit!');
        },
        //进行查询，后端给前端姓名对应的操作日志,包括工号、姓名、操作时间、操作、ip地址、地址
        handleCurrentChange(val){
          this.currentPage=parseInt(val);
          let page = this.currentPage-1;
          this.$axios.get("http://localhost:8084/alltickets/selectTicketsByNum?second_leader_num=20220013&page="+page+"&size="
            +this.pageSize).then((res)=>{
            this.tableData= res.data.content;
            this.totalSize = res.data.totalPages*this.pageSize;
          })
        },
        handleClick_detail( workOrderNum ) {
          this.dialogTableVisible = true;
          this.$refs.order_detail.autoGetAllDetail(workOrderNum);
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

  /*.page-body{*/
  /*  position: relative;*/
  /*  width: 90%;*/
  /*  height: 100%;*/
  /*  left: 5%;*/
  /*}*/



  .page-tail{
    width:100%;
    position: relative;
    bottom: 0;
  }

</style>
