<template>
  <div class="page">

    <div style="border: rgba(82,182,154,0.25) solid 3px">
      <div class="page_title">工单信息</div>
      <div class="page_line"></div>
      <div class="page_block">

        <!-- 工单信息填写表单 -->
        <el-form :inline="true" :model="workorder" class="demo-form-inline" style="width: 100%" >
<!--          :label-position="labelPosition">-->
          <el-form-item label="工单标题" required>
            <el-input v-model="workorder.workOrderName" placeholder="工单标题"></el-input>
          </el-form-item>
          <el-form-item label="资源到期时间" required>
<!--            <span class="demonstration" style="color: black" >资源到期时间</span>-->
            <el-date-picker
              v-model="workorder.expirationTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="原工单编号" required>
<!--            <span  style="color: black" ></span>-->
            <el-select v-model="value"  placeholder="请选择" @change="checkOldOrderNum">
              <el-option
                v-for="item in options"
                :key="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>

        </el-form>
        <el-form :model="workorder">
          <el-form-item label="变更理由">
            <el-input type="textarea" v-model="workorder.reason" style="width:100%;float:left"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <p></p>
    <div style="border: rgba(82,182,154,0.25) solid 3px">
      <div class="page_title">物理机资源</div>
      <div class="page_line"></div>

      <div class="page_block">
        <el-button class="el-icon-plus" type="success" @click="dialogTableVisible_physics = true" style=" float: left;">  新增</el-button>

        <!--      弹窗-->
        <el-dialog title="现有物理机资源" :visible.sync="dialogTableVisible_physics" width="45%">
          <el-table
            :header-cell-style="tableHeaderColor"

            ref="multipleTable_physics"
            :data="gridData_physics"
            :row-class-name="tableRowClassName_physics"
            @selection-change="handleSelectionChange_physics"
            @select = "onRowClick_physics">
            <el-table-column  class="el-tableColumn" type="selection" width="55" :reserve-selection="true"></el-table-column>
            <el-table-column property="cpuCore" label="CPU(核)" width="150"></el-table-column>
            <el-table-column property="ram" label="内存(G)" width="150"></el-table-column>
            <el-table-column property="storage" label="硬盘(G)" width="150"></el-table-column>
            <el-table-column property="price" label="单价(/月)" ></el-table-column>
            <!--          <el-table-column property="ip" label="IP地址" width="150"></el-table-column>-->
          </el-table>
<!--          <p></p>-->
          <el-button class="add_type" type="primary" @click="getSelected(1)">添加选中结果</el-button>

        </el-dialog>


        <el-table :data="tabledata_physics"
                  border
        >
          <el-table-column property="cpuCore" label="CPU(核)" ></el-table-column>
          <el-table-column property="ram" label="内存(G)" ></el-table-column>
          <el-table-column property="storage" label="硬盘(G)" ></el-table-column>
          <el-table-column property="price" label="单价(/月)" ></el-table-column>
          <!--        <el-table-column property="ip" label="IP地址"></el-table-column>-->
          <el-table-column
            fixed="right"
            label="操作"
            width="120">
            <template slot-scope="scope">
<!--              , tabledata_physics-->
              <el-button
                @click.native.prevent="deleteRow_physics(scope.$index)"
                type="text"
                size="small"
                style="color: #52b69a"
              >
                移除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <p></p>
    </div>

    <p></p>

    <div style="border: rgba(82,182,154,0.25) solid 3px">

      <div class="page_title">虚拟机资源</div>
      <div class="page_line"></div>

      <div class="page_block">
        <el-button class="el-icon-plus" type="success" @click="dialogTableVisible_virtual = true" style=" float: left;">  新增</el-button>
        <!--      现有虚拟机资源弹窗-->
        <el-dialog title="现有虚拟机资源" :visible.sync="dialogTableVisible_virtual" width="70%">
          <el-table
            :header-cell-style="tableHeaderColor"
            ref="multipleTable_virtual"
            :data="gridData_virtual"
            highlight-current-row
            :row-class-name="tableRowClassName_virtual"
            @row-click = "onRowClick_virtual"
            @selection-change="handleSelectionChange_virtual"
            @select = "onRowClick_virtual">
            >
            <el-table-column  class="el-tableColumn" type="selection" width="55" :reserve-selection="true"></el-table-column>
            <el-table-column property="description" label="规格族" width="150"></el-table-column>
            <el-table-column property="cpuCore" label="VCPu" width="150"></el-table-column>
            <el-table-column property="ram" label="内存(G)" width="150"></el-table-column>
            <el-table-column property="processorFrequency" label="处理器主频/睿频" width="200"></el-table-column>
            <el-table-column property="processorModel" label="处理器型号" width="150"></el-table-column>
            <el-table-column property="price" label="单价(/月)"></el-table-column>

            <!--          <el-table-column property="ip" label="IP地址" width="150"></el-table-column>-->
          </el-table>
          <p></p>
          <el-button class="add_type" type="primary" @click="getSelected(2)">添加选中结果</el-button>

        </el-dialog>

        <el-table :data="tabledata_virtual" border >
          <el-table-column property="description" label="规格族" width="130"></el-table-column>
          <el-table-column property="cpuCore" label="VCPu" width="100"></el-table-column>
          <el-table-column property="ram" label="内存(G)" width="100"></el-table-column>
          <el-table-column property="processorFrequency" label="处理器主频/睿频" width="150"></el-table-column>
          <el-table-column property="processorModel" label="处理器型号" width="150"></el-table-column>
          <el-table-column property="price" label="单价(/月)" width="100"></el-table-column>
          <el-table-column property="account_virtual" label="数量" >

            <template slot-scope="scope" >
              <el-input-number v-model="scope.row.quantity"
                               controls-position="right"
                               :min="1"
                               @change="handleChange_virtual(value, scope)"
              >
              </el-input-number>
            </template>

          </el-table-column>

          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
<!--              , tabledata_virtual-->
              <el-button
                @click.native.prevent="deleteRow_virtual(scope.$index)"
                type="text"
                size="small"
                style="color: #52b69a"
              >
                移除
              </el-button>
            </template>
          </el-table-column>
        </el-table>



        <P></P>
        <el-form :inline="true"  class="demo-form-inline">
          <el-form-item label="存储(G)">
            <el-input v-model="storage" placeholder="存储(G)"></el-input>
          </el-form-item>
          <el-form-item label="操作系统">
            <el-select v-model="os" placeholder="操作系统">
              <el-option label="windows" value="windows"></el-option>
              <el-option label="linux" value="linux"></el-option>
            </el-select>
          </el-form-item>
        </el-form>


      </div>
    </div>

    <p></p>

<!--    附件-->
    <div style="border: rgba(82,182,154,0.25) solid 3px">
      <div class="page_title">附件</div>
      <div class="page_line"></div>
      <div class="page_block">
        <upload-file></upload-file>
      </div>
    </div>

<!--    <p></p>-->
    <div style="text-align: center">
      <!-- 保存、提交按钮 -->
      <el-button>保存</el-button>
      <el-button class="add_type" @click="submit()">提交</el-button>
    </div>
  </div>
</template>

<script>
import UploadFile from "./uploadfile/uploadFile";
export default {
  components: {UploadFile},
  name: "applytable2",
  data() {
    return {
      //原工单编号下拉框内部数据
      options: [],
      value: '',//原工单编号初始化数据
      storage:'',
      os:'',
      expire_time: '',//资源到期时间
      // labelPosition: 'left',
      multipleSelection_physics: [],//存储选中物理机的数据
      multipleSelection_virtual: [],//存储选中虚拟机的数据
      currentRowIndex_physics: [],//存储选中物理机的行号
      currentRowIndex_virtual: '',//存储选中虚拟机的行号
      list: [],
      length_physics: '',//选中物理机的个数
      length_virtual:'',//选中虚拟机的个数
      // 多选选择数量循环计数器
      multipleChoice_count: '0',
      // 多选选择行号循环计数器
      lineNumber_count: '0',
      //物理机虚拟机弹窗
      dialogTableVisible_physics: false,
      dialogTableVisible_virtual: false,
      // 个人信息以及工单信息表单数据
      workorder: {
        workOrderNum:'',
        expirationTime: '',
        workOrderName: '',
        reason: '',
        workerNum:'',
        file:'',
        price:'',
        workOrderType:"变更工单",
        WorkOrderState:"待审批"
      },
      // 已添加的物理机资源信息表数据
      tabledata_physics: [],
      // 已添加的虚拟机资源信息表数据
      tabledata_virtual: [],
      // 新增物理机弹窗内表格数据
      gridData_physics: [],
      // 新增虚拟机弹窗内表格数据
      gridData_virtual: [],
      // 流转过程需要的数据
      flowProcess:{
        workOrderNum:'',
        dealNum:'',
        operationType:'',
        dealDate:'',
        dealComment:''
      }
    }
  },
  // 获取当前时间的定时器
  mounted() {
    this.getreasources();
  },
  methods: {
    getDateFunc() {
      var date = new Date();
      var seperator1 = "-";
      var seperator2 = ":";
      //以下代码依次是获取当前时间的年月日时分秒
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      var minute = date.getMinutes();
      var hour = date.getHours();
      var second = date.getSeconds();
      //固定时间格式
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
      }
      if (minute >= 0 && minute <= 9) {
        minute = "0" + minute;
      }
      if (second >= 0 && second <= 9) {
        second = "0" + second;
      }
      this.flowProcess.dealDate =  year + seperator1 + month + seperator1 + strDate
        + " " + hour + seperator2 + minute + seperator2 + second;
    },
    //物理机获取选中数据的行号
    tableRowClassName_physics({row, rowIndex}) {
      row.row_index = rowIndex;
    },
  // , event, column
    onRowClick_physics(row) {
      for (let k = 0; k < row.length; k++) {
        this.currentRowIndex_physics.push(row[k].row_index)
      }
    },
    //虚拟机获取选中数据的行号
    tableRowClassName_virtual({row, rowIndex}) {
      row.row_index = rowIndex
    },
  // , event, column
    onRowClick_virtual(row) {
      for (let k = 0; k < row.length; k++) {
        this.currentRowIndex_virtual.push(row[k].row_index)
      }
    },


    // 点击selection多选框
    handleSelectionChange_physics(data) {
      this.multipleSelection_physics = data;
      this.length_physics = this.$refs.multipleTable_physics.selection;//获取当前选中数据的行数
    },
    handleSelectionChange_virtual(data) {
      this.multipleSelection_virtual = data;
      this.length_virtual = this.$refs.multipleTable_virtual.selection;//获取当前选中数据的行数
    },

    // 删除选中的物理机资源
    // , rows
    deleteRow_physics(index) {
      const data = this.tabledata_physics.slice(index, index + 1)

      this.tabledata_physics.splice(index, 1);
      this.gridData_physics.splice(-1, 0, data[0])
    },
    //删除选中的虚拟机资源
    //, rows
    deleteRow_virtual(index) {
      const data = this.tabledata_virtual.slice(index, index + 1)

      this.tabledata_virtual.splice(index, 1);
      this.gridData_virtual.splice(-1, 0, data[0])

      this.virtual_price_temp = 0
      for (let i = 0; i < this.tabledata_virtual.length; ++i) {
        this.virtual_price_temp += this.tabledata_virtual[i].price * this.tabledata_virtual[i].account_virtual
      }
    },

    // 虚拟机获取点击行的数据
    getSelected(type) {
      if (type === 1) {
        this.dialogTableVisible_physics = false//关闭弹窗
        while (this.multipleChoice_count < this.length_physics.length) {
          this.tabledata_physics.push(this.multipleSelection_physics[this.multipleChoice_count]);
          this.gridData_physics.splice(this.currentRowIndex_physics[this.lineNumber_count], 1)
          this.multipleChoice_count++;
          this.lineNumber_count++;

        }
        this.$refs.multipleTable_physics.clearSelection()
        this.multipleChoice_count = 0;
        this.lineNumber_count = 0;
        this.currentRowIndex_physics.splice(0, this.currentRowIndex_physics.length)
      } else {
        this.dialogTableVisible_virtual = false//关闭弹窗
        while (this.multipleChoice_count < this.length_virtual.length) {
          this.tabledata_virtual.push(this.multipleSelection_virtual[this.multipleChoice_count]);
          this.gridData_virtual.splice(this.currentRowIndex_virtual[this.lineNumber_count], 1)
          this.multipleChoice_count++;
          this.lineNumber_count++;

        }
        this.$refs.multipleTable_virtual.clearSelection()
        this.multipleChoice_count = 0;
        this.lineNumber_count = 0;
        this.currentRowIndex_virtual.splice(0, this.currentRowIndex_virtual.length)
      }
    },
//设置表头行的样式
    tableHeaderColor({row, column, rowIndex, columnIndex}) {
      return 'background-color:rgba(82, 182, 154, 0.76);color:#fff;font-wight:500'

    },

//虚拟机资源数量改变
    handleChange_virtual(value, scope) {
      this.tabledata_virtual[scope.$index].account_virtual = scope.row.quantity
      this.virtual_price_temp = 0

      for (let i = 0; i < this.tabledata_virtual.length; ++i) {
        this.virtual_price_temp += this.tabledata_virtual[i].price * this.tabledata_virtual[i].account_virtual
      }
      this.virtual_price_temp = this.diff_time * this.virtual_price_temp


    },
    //计算工单资源总价格
    handleAdd() {
      this.$refs.fileUploadDialog.show()
    },
    //提交所有工单数据
    submit() {
      this.workorder.workerNum = sessionStorage.getItem("work_num");
      this.workorder.file=this.$store.state.FileName;
      //插入到表单中
      this.$axios.post(this.$store.state.url+"/changeTickets/insertChangedTickets",
        this.workorder).then((res) => {

        if (res.data) {
          //将原工单状态变为已变更
          this.$axios.put(this.$store.state.url+"/changeTickets/OrderStateChange?workOrderNum="+this.value+"&state=已变更");
          //插入申请的物理机资源
          this.$axios.post(this.$store.state.url+"/applyTickets/insertAllocatedCom", {
            qs: JSON.stringify(this.tabledata_physics),
            workOrderNum: res.request.response
          });
          //插入申请的虚拟机资源
          this.$axios.post(this.$store.state.url+"/applyTickets/insertAllocatedVm",{
            qs:JSON.stringify(this.tabledata_virtual),
            workOrderNum:res.request.response,
            storage:this.storage,
            os:this.os
          });
          //插入流转过程
          this.flowProcess.workOrderNum=res.request.response;
          this.flowProcess.dealComment=this.workorder.reason;
          this.getDateFunc();
          this.flowProcess.dealNum=sessionStorage.getItem("work_num");
          this.flowProcess.operationType="变更工单";
          this.$axios.post(this.$store.state.url+"/flowProcess/insert",{
            flowProcess:JSON.stringify(this.flowProcess)}).then((res)=>{
          });
          this.$axios.post(this.$store.state.url+"/changeTickets/insertWorkOrderChange?workOrderNum=" +
            this.value + "&changedOldOrder=" + res.request.response
          ).then((res)=>{
            this.$message({
              showClose: true,
              message: '申请成功',
              type: 'success'
            });
            this.reset();
          });
        }
      });

    },
    //改变原工单号填入数据
    checkOldOrderNum(){
      //通过原工单号填入数据
      this.$axios.get(this.$store.state.url+"/changeTickets/selectByWorkOrderNum?workOrderNum="
        +this.value).then((res)=>{
        this.workorder.workOrderName = res.data.workOrderName;
        this.workorder.expirationTime = res.data.expirationTime;

        this.workorder.workerNum = sessionStorage.getItem("work_num");
        this.workorder.reason = res.data.reason;
        this.workorder.price = res.data.price;
      });
      this.$axios.get(this.$store.state.url+"/changeTickets/selectComByWorkOrderNum?workOrderNum=" +
        this.value).then((res)=>{
        this.tabledata_physics = res.data;
      });

      this.$axios.get(this.$store.state.url+"/changeTickets/selectVmByWorkOrderNum?workOrderNum=" +
        this.value).then((res)=>{
        this.tabledata_virtual = res.data;
        this.storage = res.data[0].storage;
        this.os = res.data[0].os;
      });
    },
    getreasources(){
      this.$axios.get(this.$store.state.url+"/applyTickets/selectAllPc").then((res) => {
        this.gridData_physics = res.data;
      });
      this.$axios.get(this.$store.state.url+"/applyTickets/selectAllVm").then((res) => {
        this.gridData_virtual = res.data;
      });
      //获得当前工号人申请二级通过的工单号
      this.$axios.get(this.$store.state.url+"/changeTickets/selectWorkOrderByworkNum?workerNum="
        +sessionStorage.getItem("work_num")).then((res)=>{
        this.options = res.data;
      });
    },
    reset(){
      this.storage='',
        this.os='',
        this.expire_time ='',//资源到期时间
        this.multipleSelection_physics= [],//存储选中物理机的数据
        this.multipleSelection_virtual=[],//存储选中虚拟机的数据
        this.currentRowIndex_physics= [],//存储选中物理机的行号
        this.currentRowIndex_virtual='',//存储选中虚拟机的行号
        this.list= [],
        this.length_physics= '',//选中物理机的个数
        this.length_virtual='',//选中虚拟机的个数
        // 多选选择数量循环计数器
        this.multipleChoice_count= '0',
        // 多选选择行号循环计数器
        this.lineNumber_count= '0',
        // 个人信息以及工单信息表单数据
        this.workorder={
        workOrderNum:'',
          expirationTime: '',
          workOrderName: '',
          reason: '',
          workerNum:'',
          file:'',
          price:'',
          workOrderType:"变更工单",
          WorkOrderState:"待审批"
      },
      // 已添加的物理机资源信息表数据
        this.tabledata_physics= [],
        // 已添加的虚拟机资源信息表数据
        this.tabledata_virtual= [],
        // 新增物理机弹窗内表格数据
        this.gridData_physics= [],
        // 新增虚拟机弹窗内表格数据
        this.gridData_virtual= [],
        // 流转过程需要的数据
        this.flowProcess={
        workOrderNum:'',
          dealNum:'',
          operationType:'',
          dealDate:'',
          dealComment:''
      }
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
}
.page_title{
  width:80px;
  margin-left:50%;
  margin-bottom:10px;
  font-weight:bolder;
  color: #0c805f;
}


.page_line{
  margin-left:10%;
  margin-bottom:10px;
  padding:0;
  width:80%;
  height:0.5px;
  background-color:#52b69a;
  overflow:hidden;
  text-align: center;
}

.page_block{
  width: 80%;
  margin-left: 10%;
}


.el-icon-plus {
  color: #fff;
  background-color: rgba(82, 182, 154, 0.76);
  border-color: #52b69a;
}
.add_type {
  color: #fff;
  background-color: rgba(82, 182, 154, 0.8);
  border-color: #52b69a;
}
.el-tableColumn {
  color: #fff;
  background-color: #52b69a;
  border-color: #52b69a;
}

</style>
