<template>
  <div class="page-main">
    <!--主要界面部分-->
    <div class="page-head">
      <!--界面头部分-->
      <div style="width:100%;text-align:center">
        <!--设置居中-->
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="输入姓名进行查询">
            <el-input v-model="formInline.name" placeholder="姓名"></el-input>
          </el-form-item>
          <!--输入查询框-->
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button @click="handleClick_clean">清空</el-button>
          </el-form-item>
          <!--查询按钮-->
        </el-form>
      </div>
      <div page class="page-body">
        <!--界面体部分-->
        <el-table
          :data="tableData.filter(data => !formInline.name || data.name.toLowerCase().includes(formInline.name.toLowerCase()))"
          border
          style="width: 100%">
          <el-table-column
            prop="workNum"
            label="工号"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="operateTime"
            label="操作时间">
          </el-table-column>
          <el-table-column
            prop="operate"
            label="操作">
          </el-table-column>
          <el-table-column
            prop="ip"
            label="ip地址">
          </el-table-column>
          <el-table-column
            prop="address"
            label="地址">
          </el-table-column>
        </el-table>

        <!--放置表格-->


      </div>
    </div>
    <div class="page-tail" style="width:70%;">
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
    name: "admin_operation_log",
    mounted() {
      this.$axios.get(this.$store.state.url+"/Log/query?page="+0+"&size="+this.pageSize).then((res)=>{
        this.tableData= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    },
    data() {
      return {
        //给后端一个姓名
        formInline: {
          name: '',
        },
        //分页相关
        currentPage:1,
        pageSize:9,
        totalSize:0,

        //  表格数据，需要后端传递工号、姓名、操作时间、操作、ip地址、地址
        tableData: []

      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },

      handleClick_clean(){
        this.$axios.get(this.$store.state.url+"/Log/query?page="+0+"&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      },

      //进行查询，后端给前端姓名对应的操作日志,包括工号、姓名、操作时间、操作、ip地址、地址
      handleCurrentChange(val){
        this.currentPage=parseInt(val);
        let page = this.currentPage-1;
        this.$axios.get(this.$store.state.url+"/Log/query?page="+page+"&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }
    },


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
    /*background-color: rgb(81, 227, 150);*/
  }
  .page-head{
    height: 100px;
    position: relative;
    /*background-color: rgb(51, 45, 243);*/
  }


  .page-tail{
    width:100%;
    height: 60px;
    position: absolute;
    bottom: 0;
    /*background-color: rgb(243, 119, 158);*/
  }

</style>

