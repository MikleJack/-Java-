<template>
  <div>
    <!--    工单详情对话框-->
    <WorkOrderDetail ref="WorkOrderDetail"></WorkOrderDetail>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="工单类型">
        <!--    根据工单类型筛选工单-->
        <el-select v-model="workOrderTypeSelector" filterable placeholder="请选择工单类型">
          <el-option
            v-for="item in orderType"
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
      :data="tableData.filter(data => !search ||
      data.workOrderName.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"
      element-loading-background="rgba(245, 247, 250, 1)">
      <el-table-column
        prop="workerNum"
        label="工号"
        width="150">
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
        layout="total, prev, pager, next, jumper"
        :total="totalpage">
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
      currentPage:1,
      totalpage:0,
      pageSize:8,
      search:'',
      workOrderTypeSelector:'',
      //当前页面
      thisPage:0,
      WorkOrderDetailTopSector:[],
      //存放所有工单
      tableData: [],
      //工单类型
      orderType:[],
      //字体大小
      size: '',
      //workNum工号
      workNum:'00000001',
      //name人员姓名
      name:'张大炮',
      //所在部门编号
      depNum:'0003',
      //所在部门名称
      depName:'小组1',
      //电话号码
      phone:'13000000000',
      //工单编号
      workOrderNum:'000000000000000001',
      //工单标题
      workOrderName:'资源申请工单',
      //工单类型
      workType:'申请工单',
      //申请时间
      applyTime:'2023-5-3 00:00:00',
      //reason可变，为申请工单时，为申请理由，为回退工单时，为回退理由，
      reason:'申请理由',
      reasonContect:'申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由',
      //流传过程数据
      informData: [{
        //处理人工号
        dealNum:'00000002',
        //处理人姓名
        dealName:'陈大炮',
        //操作类型
        operationType:'审批通过',
        //操作日期
        dealDate:'2021-12-28 15:30:00',
        //批注
        dealComment:'申请合理，建议通过',
      }],
      allocatedCom:[{
        //  计算机类型
        comType:'物理机',
        //cpu核数
        cpuCore:'8',
        //  内存
        ram:'16',
        //  存储
        strange:'256',
        //  ip
        ip:'194.15.1.1',
        //  操作系统
        os:'Window'
      }],
      //  输入的批注内容
      textarea: ''
    }
  },
  mounted() {
    //获取全部工单信息
    this.$axios.get('http://localhost:8084/workOrder/queryByPage?page='+(this.currentPage-1)+"&size="+this.pageSize).then((res)=>{
      this.tableData = res.data.content;
      this.totalpage = res.data.numberOfElements;
      this.filtrateOrder();
    })
  },
  methods: {
    //操作的详情dialog函数
    handleClick_detail( workOrderNum ) {
      this.$store.state.workOrderDetailVisibleDetail = true;
      this.$refs.WorkOrderDetail.autoGetAllDetail(workOrderNum);
    },

    //dialog弹窗关闭提示函数
    handleClose(done) {
      // this.$confirm('确认关闭？')
      // .then(_ => {
      done();
      // })
      // .catch(_ => {});
    },filtrateOrder(){
      //   for( let i=0 ;i<this.tableData.length;i=i+1){
      //     if(this.orderType.find(this.tableData[i].workOrderType)){
      //       alert('1')
      //     }
      //     else
      //       this.orderType.push(this.tableData[i].workOrderType)
      //   }
      }
    //过滤工单类型
    //
  }
}


</script>

<style scoped>
.page{
  position: relative;
  width: 100%;
  height: auto;
  left: 0;
  top: 0;
  background: #fffefe;
}
.page_top{
  width: 100%;
  height: 60px;
  text-align: center;
  line-height: 60px;
}
.page_body{
  position: relative;
  width: 90%;
  /*height: 20px;*/
  height: 100%;
  /*top: 0;*/
  left: 5%;
  /*background: #0c805f;*/
}
.reason_contect{
  width: 100%;
  height: 150px;
  /*background: #409EFF;*/
}
.resoure_usage{
  width: 100%;
  height: auto;
  background: #ffffff;
}
.dialog-footer{
  width: 100%;
  height: 100px;
  bottom: 0;
  /*background: #888888;*/
  text-align: center;
  line-height: 100px;
}
.paging{
  width:100%;
  height: 60px;
  position: absolute;
  bottom: 0;
}
</style>
