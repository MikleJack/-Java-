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
      :data="tableData.filter(data => !search || data.workOrderName.toLowerCase().includes(search.toLowerCase()))"

    >
<!--      :data = "tableData"-->

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
          <el-button @click="handleClick_delay(scope.row)" type="text" size="small">延期</el-button>
          <el-button @click="handleClick_offline(scope.row)" type="text" size="small">下线</el-button>
        </templte>

      </el-table-column>
    </el-table>

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

      <!--    点击延期后的dialog界面-->
      <el-dialog
        title="延期申请"
        :visible.sync="dialogVisible_delay"
        width="50%"
        :before-close="handleClose">
<!--        延期日期选择-->
        <div class="block">
          <el-date-picker
            v-model="value1"
            type="datetime"
            placeholder="选择延期时间">
          </el-date-picker>
        </div>

<!--        空格占位，美观-->
        <div style="margin: 20px 0;"></div>

<!--        延期原因-->
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 4}"
          placeholder="请输入延期原因"
          v-model="delayTextarea">
        </el-input>

      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_delay = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_delay = false">确 定</el-button>
      </span>
    </el-dialog>

    <!--    点击下线后的dialog界面-->
    <el-dialog
      title="下线申请"
      :visible.sync="dialogVisible_offline"
      width="50%"
      :before-close="handleClose">

<!--      下线申请原因-->
      <el-input
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 4}"
        placeholder="请输入下线原因"
        v-model="offLineTextarea">
      </el-input>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_offline = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_offline = false">确 定</el-button>
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
  name: "staffAllOrder",
  data() {
    return {
      dialogVisible_detail: false,
      dialogVisible_delay: false,
      dialogVisible_offline: false,
      ticketData: [],
      currentPage:1,
      pageSize:8,

      search:'',

      workOrderTypeSelector:'',
      tableData: [
        {
          // worker_num: '00000001',
          // name: '王小虎',
          // phone: '15155185464',
          // dep_name: '软件学院',
          // dep_level: '3',
          // state: '正常'
        }, {
          // worker_num: '00000002',
          // name: '王小虎',
          // phone: '15155185464',
          // dep_name: '软件学院',
          // dep_level: '3',
          // state: '正常'
        }
      ],
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
    this.$axios.get('http://localhost:8084/workOrder/queryAll').then((res)=>{
      this.tableData = res.data;
    })
  },
  methods: {
    //操作的详情dialog函数
    handleClick_detail() {
      this.dialogVisible_detail = true;
    },
    //操作的延期dialog函数
    handleClick_delay() {
      this.dialogVisible_delay = true;
    },
    //操作的下线dialog函数
    handleClick_offline() {
      this.dialogVisible_offline = true;
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
.dialog-footer{
  width: 100%;
  height: 100px;
  bottom: 0;
  /*background: #888888;*/
  text-align: center;
  line-height: 100px;
}
</style>
