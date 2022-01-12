<template>
  <div>
    <StaffAllOrderOffline ref="StaffAllOrderOffline"></StaffAllOrderOffline>
    <StaffAllOrderDelay ref="StaffAllOrderDelay"></StaffAllOrderDelay>
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

    <!--    用户所有表单查询-->
    <el-table
      border
      style="width: 100%"
      :data="tableData"
      element-loading-background="rgba(245, 247, 250, 1)">
      <el-table-column
        prop="workOrderNum"
        label="工单编号"
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
          <el-button @click="handleClick_detail(scope.row)" type="text" size="small">详情</el-button>
          <el-button @click="handleClick_delay(scope.row.workOrderNum)"
                     type="text"
                     size="small"
                     :disabled="scope.row.workOrderState != '二级审批通过'">延期</el-button>
          <el-button @click="handleClick_offline(scope.row.workOrderNum,scope.row.workOrderState)"
                     type="text"
                     size="small"
                     :disabled="scope.row.workOrderState != '二级审批通过'">下线</el-button>
        </templte>
      </el-table-column>
    </el-table>
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
    <!--    点击详情后的dialog界面-->
    <el-dialog
      title="详情"
      :visible.sync="dialogVisible_detail"
      width="80%"
      :before-close="handleClose"
      center>
      <div class="page">
        <!-- -->
        <!--        <div class="page_top">工单详情</div>-->
        <div class="page_body">
          <!--      审批工单时显示申请人信息的无框表格-->
          <el-descriptions class="margin-top" title="申请人信息" :column="3" :size="size" border></el-descriptions>
          <el-descriptions class="margin-top":column="3" :size="size">
            <el-descriptions-item label="申请人姓名">{{name}}</el-descriptions-item>
            <el-descriptions-item label="工号">{{workNum}}</el-descriptions-item>
            <el-descriptions-item label="所在部门编号">{{depNum}}</el-descriptions-item>
            <el-descriptions-item label="所在部门名称">{{depName}}</el-descriptions-item>
            <el-descriptions-item label="电话号码">{{phone}}</el-descriptions-item>
          </el-descriptions>

          <!--      显示工单基础信息-->
          <el-descriptions class="margin-top" title="工单信息信息" :column="3" :size="size" border></el-descriptions>
          <el-descriptions class="margin-top" :column="3" :size="size">
            <el-descriptions-item label="工单编号">{{workOrderNum}}</el-descriptions-item>
            <el-descriptions-item label="工单标题">{{workOrderName}}</el-descriptions-item>
            <el-descriptions-item label="工单类型">{{workType}}</el-descriptions-item>
            <el-descriptions-item label="工单申请时间">{{applyTime}}</el-descriptions-item>
          </el-descriptions>
          <el-descriptions class="margin-top" :title="reason" :column="3" :size="size" border></el-descriptions>
          <div class="reason_contect">{{reasonContect}}</div>

          <el-descriptions class="margin-top" title="资源" :column="3" :size="size" border></el-descriptions>
          <el-table
            :data="allocatedCom"
            border
            style="width: 100%">
            <el-table-column
              type="index"
              width="50">
            </el-table-column>

            <el-table-column
              prop="comType"
              label="计算机类型"
              width="auto">
            </el-table-column>
            <el-table-column
              prop="cpuCore"
              label="CPU核数"
              width="auto">
            </el-table-column>
            <el-table-column
              prop="ram"
              label="内存">
            </el-table-column>
            <el-table-column
              prop="strange"
              label="存储大小">
            </el-table-column>
            <el-table-column
              prop="ip"
              label="ip地址">
            </el-table-column>
            <el-table-column
              prop="os"
              label="操作系统">
            </el-table-column>
          </el-table>

          <!--      <el-descriptions class="margin-top" title="虚拟机资源" :column="3" :size="size" border></el-descriptions>-->

          <!--显示流转过程-->
          <el-descriptions class="margin-top" title="流转过程" :column="3" :size="size" border></el-descriptions>

          <div class="resoure_usage">
            <el-table
              :data="informData"
              border
              style="width: 100%">
              <el-table-column
                prop="dealNum"
                label="处理人工号"
                width="auto">
              </el-table-column>
              <el-table-column
                prop="dealName"
                label="处理人姓名"
                width="auto">
              </el-table-column>
              <el-table-column
                prop="operationType"
                label="操作类型"
                width="auto">
              </el-table-column>
              <el-table-column
                prop="dealDate"
                label="处理日期"
                width="auto">
              </el-table-column>
              <el-table-column
                prop="dealComment"
                label="批注"
                width="auto">
              </el-table-column>
            </el-table>
          </div>
          <el-descriptions class="margin-top" title="批注" :column="3" :size="size" border></el-descriptions>
          <div class="resoure_usage">
            <el-input
              type="textarea"
              :rows="3"
              :disabled="true"
              placeholder="请输入内容"
              v-model="textarea">
            </el-input>

            <span slot="footer" class="dialog-footer">
              <div>
                <el-button @click="dialogVisible_detail = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_detail = false">确 定</el-button>
              </div>
          </span>
          </div>

        </div>

      </div>
    </el-dialog>
  </div>
</template>

<script>
import StaffAllOrderOffline from "./dialogs/StaffAllOrderOffline";
import StaffAllOrderDelay from "./dialogs/StaffAllOrderDelay";
export default {
  name: "StaffAllOrder",
  components:{StaffAllOrderOffline,StaffAllOrderDelay},
  data() {
    return {
      dialogVisible_detail: false,
      dialogVisible_delay: false,
      ticketData: [],

      //中间值，用来保存当前工单编号等信息以用于执行下线等操作
      IntermediateValue:'',

      //分页相关
      currentPage:1,
      pageSize:9,
      totalSize:0,
      ifPagination:false,

      search:'',

      workOrderTypeSelector:'',
      tableData: [],
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      value1: '',
      delayTextarea: '',
      offLineTextarea: ''
    }
  },
  mounted() {
    //获取全部工单信息
    this.$axios.get('http://localhost:8084/staffAllTickets/criteriaQueryByPage?workerNum=' + sessionStorage.getItem("work_num")
                                                  + '&page=0'+ '&size=' + this.pageSize).then((res)=>{
      this.tableData = res.data.content;
      this.totalSize = res.data.totalPages*this.pageSize;
    })

  },
  methods: {
    // //条件并分页查询
    // handleClick_search(){
    //   this.resetPageSituation();
    //   this.$axios.get('http://localhost:8084/staffAllTickets/criteriaQueryByPage?' + this.workOrderTypeSelector
    //     + '&workerName=' + this.searchOrderWorkerName + '&page='+ 0 +'&size=' + this.pageSize).then((res)=>{
    //     this.tableData = res.data.content;
    //     this.totalSize = res.data.totalPages*this.pageSize;})
    // },
    //
    // //分页按钮操作
    // handleCurrentChange(val){
    //   if(!this.ifPagination){
    //     this.currentPage=parseInt(val);
    //     let page = this.currentPage-1;
    //     this.$axios.get("http://localhost:8084/adminSearchOrder/normalQueryByPage?page="+page+"&size="+this.pageSize).then((res)=>{
    //       this.tableData= res.data.content;
    //       this.totalSize = res.data.totalPages*this.pageSize;
    //     })
    //   }else{
    //     this.currentPage=parseInt(val);
    //     let page = this.currentPage-1;
    //     this.$axios.get('http://localhost:8084/adminSearchOrder/parameterQueryByPage?workOrderType=' + this.workOrderTypeSelector
    //       + '&workerName=' + this.searchOrderWorkerName +'&page=' +page+"&size="+this.pageSize).then((res)=>{
    //       this.tableData= res.data.content;
    //       this.totalSize = res.data.totalPages*this.pageSize;
    //     })
    //   }
    // },
    //操作的详情dialog函数
    handleClick_detail() {
      this.dialogVisible_detail = true;
    },
    //延期按钮对话框显示
    handleClick_delay(workOrderNum) {
      this.$store.state.staffAllOrder_DelayDialogVisible = true;
      this.$refs.StaffAllOrderDelay.setWorkOrderNum(workOrderNum);
    },
    //下线按钮对话框显示
    handleClick_offline(workOrderNum,workOrderState) {
      this.$store.state.staffAllOrder_OfflineDialogVisible = true;
      this.$refs.StaffAllOrderOffline.setWorkOrderNumAndState(workOrderNum,workOrderState);
    },
    offlineAccess(){
      this.$axios.get("http://localhost:8084/staffAllTickets/offline?workOrderNum=" + sessionStorage.getItem("work_num") +
                        '&workOrderState='+  + '&offlineReason=' +this.offLineTextarea )
    },
  }
}

</script>

<style scoped>
.dialog-footer{
  width: 100%;
  height: 100px;
  bottom: 0;
  /*background: #888888;*/
  text-align: center;
  line-height: 100px;
}
</style>
