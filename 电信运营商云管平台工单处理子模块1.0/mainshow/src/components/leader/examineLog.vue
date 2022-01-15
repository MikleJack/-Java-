<template>
  <div class="page-main">
    <!--主要界面部分-->
    <div class="page-head">
      <!--界面头部分-->
      <div style="width:100%;text-align:center">
        <!--设置居中-->
        <el-form :inline="true" :model="applyName" class="demo-form-inline">

          <el-form-item label="输入申请人姓名">
            <el-input v-model="applyName" placeholder="申请人姓名"></el-input>
          </el-form-item>

          <!--输入查询框-->
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
          <!--查询按钮-->
        </el-form>
      </div>
      <div class="page-body">
        <!--界面体部分-->
        <el-table
          :data="tableData"
          border
         >
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
            prop="expirationTime"
            label="资源到期时间"
            sortable
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
            prop="dealDate"
            label="处理时间">
          </el-table-column>
          <el-table-column
            prop="dealComment"
            label="处理批注">
          </el-table-column>
        </el-table>

        <!--放置表格-->


    </div>

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
    export default {
        name: "examine",
      mounted() {
        this.init();
      },
      data() {
        return {
          //分页相关
          currentPage:1,
          pageSize:9,
          totalSize:0,
          //给后端一个申请人姓名
          applyName:'',


        //  表格数据，需要后端传递工单号、工单名、审批人号、审批人名、处理时间、处理批注这些信息
          tableData: []

        }
      },
      methods: {
        //初始化数据
        init(){
          this.$axios.get("http://localhost:8084/flowWorkOrder/getLog?page=0&size="+this.pageSize+
            "&dealNum="+sessionStorage.getItem("work_num")+"&applyName="+this.applyName).then((res)=>{
            this.tableData= res.data.content;
            this.totalSize = res.data.totalPages*this.pageSize;
          })
        },
        onSubmit() {
          this.init();
        },
        //进行查询，后端给前端工单号对应的工单审批日志,包括工单号、工单名、审批人号、审批人名、处理时间、处理批注这些信息
        handleCurrentChange(val){
          this.currentPage=parseInt(val);
          let page = this.currentPage-1;
          this.$axios.get("http://localhost:8084/flowWorkOrder/getLog?page="+page+"&size="+this.pageSize
            +"&dealNum="+sessionStorage.getItem("work_num")+"&applyName="+this.applyName).then((res)=>{
            this.tableData= res.data.content;
            this.totalSize = res.data.totalPages*this.pageSize;
          })
        }

      }

    }
</script>


<!--页面分区显示的设置-->
<style scoped>
.page-main{
  position: relative;
  width:100%;
  height: 100%;
}

.page-body{
  position: relative;
}
.page-head{
  height: 100px;
  position: relative;
}


.page-tail{
  width:100%;
  position: absolute;
  bottom: 0;
}

</style>
