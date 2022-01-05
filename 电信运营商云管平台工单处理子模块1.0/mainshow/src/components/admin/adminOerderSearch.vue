<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="工单类型">
        <!--    根据工单类型筛选工单-->
        <el-select v-model="workOrderTypeSelector" filterable placeholder="请选择工单类型">
          <el-option
            v-for="item in tableData"
            :key="item.workOrderType"
            :label="item.workOrderType"
            :value="item.workOrderType">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="工单标题">
        <!--          通过项目名称搜索项目-->
        <el-input
          placeholder="输入工单标题搜索"
          v-model="search"
          clearable>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>

    <!--    管理员表单查询-->


    <el-table
      border
      style="width: 100%"
      :data = "tableData">

<!--      接口调用数据-->
<!--      :data="tableData.filter(data => !search || data.workOrderType.equals(workOrderTypeSelector) ||-->
<!--      data.workOrderName.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"-->
<!--      element-loading-background="rgba(245, 247, 250, 1)">-->
      <el-table-column
        prop="worker_num"
        label="工号"
        width="150">
<!--        <template slot="header" slot-scope="scope">-->

<!--        </template>-->
        <template slot-scope="scope">
          <!--          文字链接，当点击项目名称后将跳x  转到该项目的考核页面-->
          <el-link :underline="false" type="primary" @click="go(scope.row.workOrderNum)">{{ scope.row.workOrderNum }}</el-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="workOrderType"
        label="工单类型"
        width="150">
      </el-table-column>
      <el-table-column
        width="180"
        prop="workOrderName"
        label="工单标题">
      </el-table-column>
      <el-table-column
        prop="applicant"
        label="申请人"
        width="150">
      </el-table-column>
      <el-table-column
        prop="applyTime"
        label="时间"
        width="250">
      </el-table-column>
      <el-table-column
        prop="workOrderState"
        label="工单状态"
        width="180">
      </el-table-column>
      <el-table-column
        prop="workOrderState"
        label="项目状态"
        width="180">
        <template slot-scope="scope" >
          <div v-if="scope.row.projectStatus===1">
            已结项
          </div>
          <div v-else-if="scope.row.projectStatus===0">
            未结项
          </div>
        </template>
      </el-table-column>

      <el-table-column
        fixed="right"
        label="操作"
        width="250">
        <templte slot-scope="scope">
          <el-button @click="handleClick_detail(scope.row)" type="text" size="small">详情</el-button>
        </templte>

      </el-table-column>
    </el-table>

<!--    点击详情后的dialog界面-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible_detail"
      width="30%"
      :before-close="handleClose">
      <span>是否确定重置该用户账号？</span>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_detail = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_detail = false">确 定</el-button>
              </span>
    </el-dialog>

    <div>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper ,sizes"
        :total="this.tableData.length">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "ticketSearch",
  data() {
    return {
      dialogVisible_detail: false,
      ticketData: [],
      currentPage:1,
      pageSize:8,
      search:'',
      workOrderTypeSelector:'',
      tableData: [
        {
        worker_num: '00000001',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }, {
        worker_num: '00000002',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }
      ]
    }
  },
  mounted() {
    //获取全部工单信息
    this.$axios.get('http://localhost:8084/workOrder/queryAll').then((res)=>{
      this.tableData = res.data;
    })
  },
  methods: {
    //操作的详情dialog函数
    handleClick_detail() {
      this.dialogVisible_detail = true;
    },
    //dialog弹窗关闭提示函数
    handleClose(done) {
      // this.$confirm('确认关闭？')
      // .then(_ => {
      done();
      // })
      // .catch(_ => {});
    }
  }
}


</script>

<style scoped>

</style>
