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
      :data="tableData.filter(data => !search ||
      data.workOrderName.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"
      element-loading-background="rgba(245, 247, 250, 1)">
      <el-table-column
        prop="worker_num"
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
<!--      <el-table-column-->
<!--        prop="workOrderState"-->
<!--        label="项目状态"-->
<!--        width="180">-->
<!--        <template slot-scope="scope" >-->
<!--          <div v-if="scope.row.projectStatus===1">-->
<!--            已结项-->
<!--          </div>-->
<!--          <div v-else-if="scope.row.projectStatus===0">-->
<!--            未结项-->
<!--          </div>-->
<!--        </template>-->
<!--      </el-table-column>-->

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
      title="工单详情"
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
          </div>
        </div>

        <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_detail = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible_detail = false">确 定</el-button>
        </span>
      </div>


    </el-dialog>

<!--    分页-->
    <div class="paging">
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
      ],
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
  background: #42b983;
}
.dialog-footer{
  width: 100%;
  height: 100px;
  bottom: 0;
  float: right;
  /*background: #888888;*/
  text-align: center;
  line-height: 100px;
}
.paging{
  bottom: 0;
}
</style>
