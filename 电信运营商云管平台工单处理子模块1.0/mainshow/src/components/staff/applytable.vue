<template>
  <div class="page">

    <div style="border: rgba(82,182,154,0.25) solid 3px">
    <div class="page_title">工单信息</div>
    <div class="page_line"></div>
    <div class="page_block">

      <!-- 工单信息填写表单 -->
      <el-form :inline="true" :model="tabledata_message" class="demo-form-inline" style="width: 100%" :label-position="labelPosition">

        <el-form-item label="工单标题">
          <el-input v-model="tabledata_message.order_name" placeholder="工单标题"></el-input>
        </el-form-item>
        <span class="demonstration">资源到期时间</span>
        <el-date-picker
          v-model="tabledata_message.apply_time"
          type="date"
          placeholder="选择日期">
        </el-date-picker>

      </el-form>
      <el-form :model="tabledata_message">
        <el-form-item label="申请理由">
          <el-input type="textarea" v-model="tabledata_message.reason" style="width:100%;float:left"></el-input>
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
          <el-table-column property="cpu" label="CPU(核)" width="150"></el-table-column>
          <el-table-column property="memory" label="内存(G)" width="150"></el-table-column>
          <el-table-column property="storage" label="硬盘(G)" width="150"></el-table-column>
          <el-table-column property="unit_price" label="单价(/月)" ></el-table-column>
<!--          <el-table-column property="ip" label="IP地址" width="150"></el-table-column>-->
        </el-table>
        <p></p>
          <el-button class="add_type" type="primary" @click="getSelected(1)">添加选中结果</el-button>

      </el-dialog>


      <el-table :data="tabledata_physics" border >
        <el-table-column property="cpu" label="CPU(核)" ></el-table-column>
        <el-table-column property="memory" label="内存(G)" ></el-table-column>
        <el-table-column property="storage" label="硬盘(G)" ></el-table-column>
        <el-table-column property="unit_price" label="单价(/月)" ></el-table-column>
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
    <!--    <div class="page_title_min">虚拟机资源</div>-->
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
          >
<!--          <el-table-column  class="el-tableColumn" type="selection" width="55" :reserve-selection="true"></el-table-column>-->
          <el-table-column label="选择" width="100">
            <template slot-scope="scope">
              <el-radio v-model="radio" :label="scope.$index"
                        @change.native="radioChange(scope.row, scope.$index)"
                        >
              </el-radio>
            </template>
          </el-table-column>
          <el-table-column property="spe_family" label="规格族" width="150"></el-table-column>
          <el-table-column property="cpu" label="VCPu" width="150"></el-table-column>
          <el-table-column property="memory" label="内存(G)" width="150"></el-table-column>
          <el-table-column property="cpu_frequency" label="处理器主频/睿频" width="200"></el-table-column>
          <el-table-column property="cpu_model" label="处理器型号" width="150"></el-table-column>
          <el-table-column property="unit_price" label="单价(/月)"></el-table-column>

          <!--          <el-table-column property="ip" label="IP地址" width="150"></el-table-column>-->
        </el-table>
        <p></p>
        <el-button class="add_type" type="primary" @click="getSelected(2)">添加选中结果</el-button>

      </el-dialog>

      <el-table :data="tabledata_virtual" border >
        <el-table-column property="spe_family" label="规格族" width="130"></el-table-column>
        <el-table-column property="cpu" label="VCPu" width="100"></el-table-column>
        <el-table-column property="memory" label="内存(G)" width="100"></el-table-column>
        <el-table-column property="cpu_frequency" label="处理器主频/睿频" width="150"></el-table-column>
        <el-table-column property="cpu_model" label="处理器型号" width="150"></el-table-column>
        <el-table-column property="unit_price" label="单价(/月)" width="100"></el-table-column>
          <el-table-column property="account_virtual" label="数量" >
            <template scope="scope">
              <el-input-number v-model="scope.row.num"
                               controls-position="right"
                               :min="1"
                               @change="(value) => handleChange_virtual(value, scope)
                               "
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




      <el-form :inline="true" :model="tabledata_virtual" class="demo-form-inline">
        <el-form-item label="存储(G)">
          <el-input v-model="tabledata_virtual.storage" placeholder="存储(G)"></el-input>
        </el-form-item>
        <el-form-item label="操作系统">
          <el-select v-model="tabledata_virtual.win" placeholder="操作系统">
            <el-option label="windows" value="windows"></el-option>
            <el-option label="linux" value="linux"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </div>
    </div>

    <p></p>
    <div style="text-align: center">
      <!-- 保存、提交按钮 -->
      <el-button>保存</el-button>
      <el-button class="add_type">提交</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "applytable2",
  data(){
    return{

      radio: '',//单选
      radioSelect: '',//选中的数据赋值给它

      labelPosition: 'left',

      multipleSelection_physics: [],//存储选中物理机的数据
      multipleSelection_virtual: [],//存储选中虚拟机的数据

      currentRowIndex_physics: [],//存储选中物理机的行号
      currentRowIndex_virtual: '',//存储选中虚拟机的行号

      list:[],
      length:'',//选中物理机的个数
      // selectRow: [],
      // 多选选择数量循环计数器
      multipleChoice_count:'0',
      // 多选选择行号循环计数器
      lineNumber_count:'0',
      //物理机虚拟机弹窗
      dialogTableVisible_physics: false,
      dialogTableVisible_virtual: false,
      // 个人信息以及工单信息表单数据
      tabledata_message:[{

        //资源到期时间
        apply_time: '',
        order_name:'',
        reason:''
      }],
      // 已添加的物理机资源信息表数据
      tabledata_physics:[
      ],
      // 已添加的虚拟机资源信息表数据
      tabledata_virtual:[
      ],
      // 新增物理机弹窗内表格数据
      gridData_physics:[{
        cpu:'2',
        memory:'3',
        storage:'3',
        unit_price:'1000'

      },
        {
          cpu:'1',
          memory:'3',
          storage:'3',
          unit_price:'500'
        },
        {
          cpu:'10',
          memory:'3',
          storage:'3',
          unit_price:'10000'
        },
        {
          cpu:'10',
          memory:'3',
          storage:'8',
          unit_price:'11000'
        },
        {
          cpu:'7',
          memory:'3',
          storage:'8',
          unit_price:'8000'
        }],
      // 新增虚拟机弹窗内表格数据
      gridData_virtual :[{
        spe_family:'共享标准型s6',
        cpu:'1vCPU',
        memory:'1',
        cpu_frequency:'2.5/3.2GHz',
        cpu_model:'intel1',
        unit_price:'30',

      },
        {
          spe_family:'共享标准型s6',
          cpu:'1vCPU',
          memory:'2',
          cpu_frequency:'2.5/3.2GHz',
          cpu_model:'intel1',
          unit_price:'60',
        },{
          spe_family:'共享标准型s6',
          cpu:'1vCPU',
          memory:'3',
          cpu_frequency:'2.5/3.2GHz',
          cpu_model:'intel1',
          unit_price:'90',
        },{
          spe_family:'共享标准型s6',
          cpu:'1vCPU',
          memory:'4',
          cpu_frequency:'2.5/3.2GHz',
          cpu_model:'intel1',
          unit_price:'120',
        },]
    }
  },
  // 获取当前时间的定时器
  mounted(){
    let that = this;
    //定时器
    setInterval(()=>{
      that.getDateFunc();
    },1000)
  },
 methods: {
    //获取单选行的数据
   radioChange(row, index) {
     this.radioSelect = row;
   },

    //获取选中数据的行号
   tableRowClassName_physics({row, rowIndex}) {
     row.row_index = rowIndex;
   },
   onRowClick_physics(row, event, column) {
     for (var k = 0; k < row.length; k++) {
       this.currentRowIndex_physics.push(row[k].row_index)
     }
   },

   tableRowClassName_virtual({row,rowIndex}){
     row.row_index = rowIndex
   },
   onRowClick_virtual(row, event, column){
     this.currentRowIndex_virtual = row.row_index;
   },


   // 点击selection多选框
   handleSelectionChange_physics(data) {
     this.multipleSelection_physics = data;
     this.length=this.$refs.multipleTable_physics.selection;//获取当前选中数据的行数
   },

   // 获取点击行的数据

   getSelected(type) {
     if(type===1){
       this.dialogTableVisible_physics=false//关闭弹窗
       while(this.multipleChoice_count<this.length.length){
         this.tabledata_physics.push(this.multipleSelection_physics[this.multipleChoice_count]);
         this.gridData_physics.splice(this.currentRowIndex_physics[this.lineNumber_count],1)
         this.multipleChoice_count++;
         this.lineNumber_count++;

       }
       this.$refs.multipleTable_physics.clearSelection()
       this.multipleChoice_count=0;
       this.lineNumber_count=0;
       this.currentRowIndex_physics.splice(0,this.currentRowIndex_physics.length)
     }else{
       this.dialogTableVisible_virtual=false//关闭弹窗

         this.tabledata_virtual.push(this.radioSelect);
         this.gridData_virtual.splice(this.currentRowIndex_virtual,1)
       this.$refs.multipleTable_virtual.clearSelection()
       this.radioSelect.splice(0,this.radioSelect.length)
       this.currentRowIndex_virtual.splice(0,this.currentRowIndex_virtual.length)
     }
   },
   // 获取当前时间并赋值给this.tabledata_message.time
   getDateFunc(){
     let year = new Date().getFullYear();//年
     let month = new Date().getMonth() +1;//注意！月份是从0月开始获取的，所以要+1;
     let day = new Date().getDate();//日
     //拼接日期 YYYY-MM-DD HH:mm:ss
     this.tabledata_message.time=
       year +
       '-' +
       (month >=10 ? month:'0'+ month) +
       '-' +
       (day >=10 ? day:'0' + day);
   },

   // 删除选中的物理机资源
   deleteRow_physics(index, rows) {
     const data = this.tabledata_physics.slice(index,index+1)
     console.info(data)

     this.tabledata_physics.splice(index,1);
     this.gridData_physics.splice(-1,0,data[0])
   },
    //删除选中的虚拟机资源
   deleteRow_virtual(index,rows) {
     const data = this.tabledata_virtual.slice(index,index+1)

     this.tabledata_virtual.splice(index,1);
     this.gridData_virtual.splice(-1,0,data[0])
   },
//设置表头行的样式
   tableHeaderColor({row,column,rowIndex,columnIndex}){
     return 'background-color:rgba(82, 182, 154, 0.76);color:#fff;font-wight:500'

   },

//虚拟机资源数量改变
   handleChange_virtual(value,scope){
     this.$nextTick(()=>{

     })
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
  /*background-color: #409EFF;*/
}
.page_title{
  width:80px;
  margin-left:50%;
  /*text-align: center;*/
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

.page_title_min{
  width:80px;
  margin-left:0%;
  /*text-align: center;*/
  margin-bottom:10px;
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
