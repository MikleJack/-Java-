<template>
  <div>
    <!--    工单详情对话框-->
    <WorkOrderDetail ref="WorkOrderDetail"></WorkOrderDetail>

    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="工单类型">
        <!--    根据工单类型筛选工单-->
        <el-select v-model="workOrderTypeSelector" filterable placeholder="请选择工单类型">
          <el-option
            v-for="item in searchForm.searchOrderType"
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
          v-model="searchForm.searchOrderTitle">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleClick_search">查询</el-button>
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
        label="工号"
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

      // adminWorkOrderInform:{
      //   rollbackOldOrder: '',
      //   changedOldOrder: '',
      //   workOrderNum: '',
      //   workOrderName: '',
      //   workOrderType: '申请工单',
      //   applyTime: null,
      //   workerNum: '',
      //   file: '',
      //   workOrderState: '',
      //   reason: '',
      //   name: '',
      //   depNum: '',
      //   phone: '',
      //   state: null,
      //   inService: null
      // },
      // adminWorkOrderInform:{
      //   "rollbackOldOrder": '',
      //   "changedOldOrder": '',
      //   "workOrderNum": '',
      //   "workOrderName": '',
      //   "workOrderType": '申请工单',
      //   "applyTime": null,
      //   "workerNum": '',
      //   "file": '',
      //   "workOrderState": '',
      //   "reason": '',
      //   "name": '',
      //   "depNum": '',
      //   "phone": '',
      //   "state": null,
      //   "inService": null
      // },

      adminWorkOrderInform:{
        workOrderNum: null,
        workOrderType: null,
        workOrderName: "",
        workerNum: "",
        workOrderState: "",
        expirationTime: "",
        name: ""
      },

      currentPage:1,
      totalpage:0,
      pageSize:5,

      //搜索栏数据
      searchForm:{
        searchOrderTitle: '',
        searchOrderType: ['申请工单','回退工单']
      },
      workOrderTypeSelector:'',


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
    this.$axios.get('http://localhost:8084/adminSearchOrder/normalQueryByPage').then((res)=>{
      this.tableData = res.data.content;
      this.totalpage = res.data.numberOfElements;
      this.filtrateOrder();
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
      // let data = this.adminWorkOrderInform;
      // this.$axios.post('http://localhost:8084/adminWorkOrderInform/criteriaQueryByPage',{data},{
      //   headers:{"content-type":"application/json"}
      // }).then((res)=>{
      //   this.tableData = res.data.content;
      //   this.totalpage = res.data.numberOfElements;
      //   this.filtrateOrder();
      // })

      let data = JSON.stringify(this.adminWorkOrderInform);
      this.$axios({
        method: 'post',
        url: 'http://localhost:8084/adminSearchOrder/criteriaQueryByPage',
        data: {data},
        headers:{"Content-Type":"application/json"},
        transformRequest: [
          (data) => {
            return this.$qs.stringify(data); //使用Qs将请求参数序列化
          }
        ]
      }).then((res) => {
        //逻辑代码
        console.log(res);
      });
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
.paging{
  width:100%;
  height: 60px;
  position: relative;
  bottom: 0;
}
</style>
