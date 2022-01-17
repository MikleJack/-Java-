<template>
  <div class="page">

    <div style="border: rgba(82,182,154,0.25) solid 3px">
    <div class="page_title">工单信息</div>
    <div class="page_line"></div>
    <div class="page_block">

      <!-- 工单信息填写表单 -->
      <el-form :inline="true" :model="workorder" class="demo-form-inline" style="width: 100%" :label-position="labelPosition">

        <el-form-item label="工单标题">
          <el-input v-model="workorder.workOrderName" placeholder="工单标题"></el-input>
        </el-form-item>
        <span class="demonstration">资源到期时间</span>
        <el-date-picker
          v-model="workorder.expirationTime"
          type="date"
          placeholder="选择日期">
        </el-date-picker>

      </el-form>
      <el-form :model="workorder">
        <el-form-item label="申请理由">
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
<!--      <div class="page_title_min">-->
<!--        物理机资源-->
<!--      </div>-->
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
        <p></p>
          <el-button class="add_type" type="primary" @click="getSelected(1)">添加选中结果</el-button>

      </el-dialog>


      <el-table :data="tabledata_physics"
                border
                :summary-method="getSum"
                show-summary
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
            <el-button
              @click.native.prevent="deleteRow_physics(scope.$index, tabledata_physics)"
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
          <!--          <el-table-column  class="el-tableColumn" type="selection" width="55" :reserve-selection="true"></el-table-column>-->
          <!--          <el-table-column label="选择" width="100">-->
          <!--            <template slot-scope="scope">-->
          <!--              <el-radio v-model="radio" :label="scope.$index"-->
          <!--                        @change.native="radioChange(scope.row, scope.$index)"-->
          <!--                        >-->
          <!--              </el-radio>-->
          <!--            </template>-->
          <!--          </el-table-column>-->
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
                               @change="(value) => handleChange_virtual(value, scope)"
              ></el-input-number>
            </template>

          </el-table-column>

        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="deleteRow_virtual(scope.$index, tabledata_virtual)"
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
    <!--    //部门预算使用情况 进度条-->
    <div style="border: rgba(82,182,154,0.25) solid 3px;height: 250px;margin-bottom: 1%">
      <div class="budget_title" >部门预算利用情况</div>
      <!--        部门已用预算/部门总预算进度条-->
      <div class="total_progress">
        <br>
        <el-progress type="circle" class="left_progress"
                     :stroke-width="15"
                     :percentage="total_percentage()"
                     :color="customColorMethod"></el-progress>
      </div>
      <!--        文字描述-->
      <div class="total_description">
        <br><br>部门总预算：&nbsp;{{total_budget}}元<br><br>
        已使用预算：&nbsp;{{used_budget}}元
      </div>
      <!--        工单预算/部门剩余预算进度条-->
      <div class="progress">
        <br>
        <el-progress type="circle"
                     class="right_progress"
                     :stroke-width="15"
                     :percentage="percentage()"
                     :color="customColorMethod">

        </el-progress>
      </div>
      <!--        文字描述-->
      <div class="description">
        <br><br>部门剩余预算：&nbsp;{{surplus_budget}}元<br><br>
        工单使用预算：&nbsp;{{order_budget}}元
      </div>
    </div>





  <p></p>
    <div style="border: rgba(82,182,154,0.25) solid 3px">
      <div class="page_title">附件</div>
      <div class="page_line"></div>
      <div class="page_block">
        <upload-file-byfhj></upload-file-byfhj>
      </div>
    </div>

    <p></p>
    <div style="text-align: center">
      <!-- 保存、提交按钮 -->
      <el-button>保存</el-button>
      <el-button class="add_type" @click="submit">提交</el-button>
    </div>
  </div>
</template>

<script>
import upload_file from "./uploadfile/upload_file";
import Upload_file_new from "./uploadfile/upload_file_new";
import qs from "qs";
import UploadFileByfhj from "./uploadfile/uploadFileByfhj";
export default {
  components: {UploadFileByfhj, Upload_file_new, upload_file},
  name: "applytable2",
  data() {
    return {
      virtual_price_temp: 0,//暂存虚拟机总价
      storage: 40,
      os:"",
      storage_price: 0.5,
      current_time: '',//当前时间
      expire_time: '',//资源到期时间
      diff_time: 0,//资源到期时间-当前时间

      physics_price: 0,//物理机总价
      virtual_price: 0,//虚拟机总价
      total_price: 0,//资源总价
      radio: '',//单选
      radioSelect: '',//选中的数据赋值给它

      labelPosition: 'left',

      multipleSelection_physics: [],//存储选中物理机的数据
      multipleSelection_virtual: [],//存储选中虚拟机的数据

      currentRowIndex_physics: [],//存储选中物理机的行号
      currentRowIndex_virtual: '',//存储选中虚拟机的行号

      list: [],
      length_physics: '',//选中物理机的个数
      length_virtual:'',//选中虚拟机的个数

      // selectRow: [],
      // 多选选择数量循环计数器
      multipleChoice_count: '0',
      // 多选选择行号循环计数器
      lineNumber_count: '0',
      //物理机虚拟机弹窗
      dialogTableVisible_physics: false,
      dialogTableVisible_virtual: false,
      //部门总预算利用情况
      used_budget:'',
      //部门总预算
      total_budget:0,
      //部门剩余预算
      surplus_budget:0,
      //工单预算
      order_budget:0,
      // 个人信息以及工单信息表单数据
      workorder: {
        workOrderNum:'',
        expirationTime: '',
        workOrderName: '',
        reason: '',
        workerNum:'',
        file:'',
        price:'',
        workOrderType:"申请工单",
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
      //流转过程需要的数据
      flowProcess:{
        workOrderNum:'',
        dealNum:'',
        operationType:'申请工单',
        dealDate:'',
        dealComment:''
      },
      disable:false
    }
  },
  // 获取当前时间的定时器
  mounted() {
    this.$axios.get(this.$store.state.url+"/applyTickets/selectAllPc").then((res) => {
      this.gridData_physics = res.data;
    });
    this.$axios.get(this.$store.state.url+"/applyTickets/selectAllVm").then((res) => {
      this.gridData_virtual = res.data;
    });
    this.$axios.get(this.$store.state.url+"/staffHome/queryPersonInformById?workerNum=" + sessionStorage.getItem("work_num")).then((res)=>{
      let depNum = res.data.depNum;
      //获取部门总预算
      this.$axios.get(this.$store.state.url+"/depart/getDepBudget?depNum=" + depNum).then((res)=>{
        this.total_budget = res.data;
      });
      //获取部门已使用预算
      this.$axios.get(this.$store.state.url+"/usedBudget/getUsedBudget?id=" + depNum).then((res)=>{
        this.used_budget = res.data.depUsedBudget;
      });
    });

    let that = this;
    //定时器
    setInterval(() => {
      that.getDateFunc();
      that.calculate_sum();
      this.disable = this.isdisable;
    }, 1000)
    this.scope.row.quantity = 50;
  },
  methods: {
    //物理机价钱求和
    getSum(param) {
//此处打印param可以看到有两项，一项是columns，一项是data，最后一列可以通过columns.length获取到。
      const {columns, data} = param
      const len = columns.length
      const sums = []
      columns.forEach((column, index) => {
        //如果是第一列，则最后一行展示为“总计”两个字
        if (index === 0) {
          sums[index] = '总计/元'
          //如果是最后一列，索引为列数-1，则显示计算总和
        } else if (index === 3) {
          const values = data.map(item => Number(item[column.property]))
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr * this.diff_time;
              } else {
                return prev
              }
            }, 0)
          } else {
            sums[index] = 'N/A'
          }
          //如果是除了第一列和最后一列的其他列，则显示为空
        } else {
          sums[index] = ''
        }
      })
      this.physics_price = sums[3]
      return sums

    },
  //物理机获取选中数据的行号
    tableRowClassName_physics({row, rowIndex}) {
      row.row_index = rowIndex;
    },
    onRowClick_physics(row, event, column) {
      for (let k = 0; k < row.length; k++) {
        this.currentRowIndex_physics.push(row[k].row_index)
      }
    },
    //虚拟机获取选中数据的行号
    tableRowClassName_virtual({row, rowIndex}) {
      row.row_index = rowIndex
    },
    onRowClick_virtual(row, event, column) {
      for (let k = 0; k < row.length; k++) {
        this.currentRowIndex_virtual.push(row[k].row_index)
      }
    },
    // 点击selection多选框
    handleSelectionChange_physics(data) {
      this.multipleSelection_physics = data;
      console.log(data)
      this.length_physics = this.$refs.multipleTable_physics.selection;//获取当前选中数据的行数
    },
    handleSelectionChange_virtual(data) {
      this.multipleSelection_virtual = data;
      this.length_virtual = this.$refs.multipleTable_virtual.selection;//获取当前选中数据的行数
    },
    deleteRow_physics(index, rows) {
      const data = this.tabledata_physics.slice(index, index + 1)

      this.tabledata_physics.splice(index, 1);
      this.gridData_physics.splice(-1, 0, data[0])
    },
    //删除选中的虚拟机资源
    deleteRow_virtual(index, rows) {
      const data = this.tabledata_virtual.slice(index, index + 1)

      this.tabledata_virtual.splice(index, 1);
      this.gridData_virtual.splice(-1, 0, data[0])

      this.virtual_price_temp = 0
      for (let i = 0; i < this.tabledata_virtual.length; ++i) {
        // this.virtual_price += this.tabledata_virtual[i].unit_price*this.tabledata_virtual[i].account_virtual
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
    getDateFunc() {
      let year = new Date().getFullYear();//年
      let month = new Date().getMonth() + 1;//注意！月份是从0月开始获取的，所以要+1;
      let day = new Date().getDate();//日

      let year1 = this.workorder.expirationTime.getFullYear();//年
      let month1 = this.workorder.expirationTime.getMonth() + 1;//注意！月份是从0月开始获取的，所以要+1;
      let day1 = this.workorder.expirationTime.getDate();//日
      //拼接日期 YYYY-MM-DD HH:mm:ss
      this.current_time =
        year +
        '-' +
        (month >= 10 ? month : '0' + month) +
        '-' +
        (day >= 10 ? day : '0' + day);

      this.expire_time =
        year1 +
        '-' +
        (month1 >= 10 ? month1 : '0' + month1) +
        '-' +
        (day1 >= 10 ? day1 : '0' + day1);

      let date1 = this.current_time.split('-');
      let date2 = this.expire_time.split('-');
      date1 = parseInt(date1[0] * 12 * 30 + parseInt(date1[1] * 30) + parseInt(date1[2]));
      date2 = parseInt(date2[0] * 12 * 30 + parseInt(date2[1] * 30) + parseInt(date2[2]));
      this.diff_time = parseInt(date2 - date1)
      if (this.diff_time % 30 !== 0) {
        this.diff_time = parseInt(this.diff_time / 30) + 1
      } else {
        this.diff_time = parseInt(this.diff_time / 30)
      }
    },

    isdisable(){
      return this.order_budget <= this.surplus_budget;
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
        // this.virtual_price += this.tabledata_virtual[i].unit_price*this.tabledata_virtual[i].account_virtual
        this.virtual_price_temp += this.tabledata_virtual[i].price * this.tabledata_virtual[i].account_virtual
      }
      this.virtual_price_temp = this.diff_time * this.virtual_price_temp


    },
    calculate_sum() {
      let storage_sum = this.storage * this.storage_price * this.diff_time
      if (isNaN(this.virtual_price_temp)) {
        this.virtual_price_temp = 0
      }

      if (isNaN(this.physics_price)) {
        this.physics_price = 0
      }
      if (isNaN(storage_sum)) {
        storage_sum = 0
      }

      this.total_price = this.virtual_price_temp + this.physics_price + storage_sum

      if (isNaN(this.total_price)) {
        this.total_price = 0
      }
    },

    handleAdd() {
      this.$refs.fileUploadDialog.show()
    },

    //提交所有工单数据
    submit() {
      this.workorder.workerNum = sessionStorage.getItem("work_num");
      this.workorder.price= this.total_price;
      this.workorder.file=this.$store.state.FileName;
      //插入到表单中
      this.$axios.post(this.$store.state.url+"/applyTickets/intsertApplyTicket",
        this.workorder).then((res) => {
        if (res.data) {
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
          this.flowProcess.dealDate=this.current_time;
          this.flowProcess.dealNum=sessionStorage.getItem("work_num");
          this.flowProcess.operationType="申请工单";
          this.$axios.post(this.$store.state.url+"/flowProcess/insert",{
            flowProcess:JSON.stringify(this.flowProcess)}).then((res)=>{
            if(res.data===true){
              this.$message({
                message: '申请成功',
                type: 'success',
                center: true
              });
              this.reset();
            }
          });

        }
      });

    },
    //部门已用预算/部门总预算进度条
    total_percentage(){
      return (100*this.used_budget/this.total_budget).toFixed(2);
    },
    //工单预算/部门剩余预算进度条
    percentage(){
      this.surplus_budget=this.total_budget-this.used_budget;
      this.order_budget=this.total_price;
      let temp_per=parseFloat(this.order_budget/this.surplus_budget).toFixed(4)
      return (100*temp_per).toFixed(2);
    },
    customColorMethod(percentage) {
      if (percentage < 90) {
        return '#52b69a';

      } else {
        return 'rgba(239,125,10,0.7)';
      }
    },
    reset() {
      this.storage = '',
        this.os = '',
        this.expire_time = '',//资源到期时间
        this.multipleSelection_physics = [],//存储选中物理机的数据
        this.multipleSelection_virtual = [],//存储选中虚拟机的数据
        this.currentRowIndex_physics = [],//存储选中物理机的行号
        this.currentRowIndex_virtual = '',//存储选中虚拟机的行号
        this.list = [],
        this.length_physics = '',//选中物理机的个数
        this.length_virtual = '',//选中虚拟机的个数
        // 多选选择数量循环计数器
        this.multipleChoice_count = '0',
        // 多选选择行号循环计数器
        this.lineNumber_count = '0',
        // 个人信息以及工单信息表单数据
        this.workorder = {
          workOrderNum: '',
          expirationTime: '',
          workOrderName: '',
          reason: '',
          workerNum: '',
          file: '',
          price: '',
          workOrderType: "变更工单",
          WorkOrderState: "待审批"
        },
        // 已添加的物理机资源信息表数据
        this.tabledata_physics = [],
        // 已添加的虚拟机资源信息表数据
        this.tabledata_virtual = [],
        // 新增物理机弹窗内表格数据
        this.gridData_physics = [],
        // 新增虚拟机弹窗内表格数据
        this.gridData_virtual = [],
        // 流转过程需要的数据
        this.flowProcess = {
          workOrderNum: '',
          dealNum: '',
          operationType: '',
          dealDate: '',
          dealComment: ''
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
.total_progress{
  width:25%;
  float: left;
  height: 200px;
  text-align: center;
}
.total_description{
  width: 25%;
  float: left;
  height: 200px;
  font-size: larger;
  font-weight: bolder;
}
.progress{
  width: 25%;
  float: left;
  height: 200px;
}
.description{
  width: 25%;
  float: left;
  height: 200px;
  font-size: larger;
  font-weight: bolder;
}
.budget_title{
  font-size: large;
  margin-left: 47%;
  margin-bottom:20px;
  font-weight:bolder;
  color: #0c805f;
}

</style>
