<template>
  <div class="page">
<!--    <div class="page_title">申请人信息</div>-->

<!--    <div class="page_line"></div>-->

<!--    <div class="page_block">-->

<!--      <el-form :inline="true" :model="tabledata1" class="demo-form-inline" :label-position="labelPosition">-->
<!--        <el-form-item label="工号">-->
<!--          <el-input v-model="tabledata1.num" placeholder="工号"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="姓名">-->
<!--          <el-input v-model="tabledata1.name" placeholder="姓名"></el-input>-->
<!--        </el-form-item>-->
<!--&lt;!&ndash;      </el-form>&ndash;&gt;-->

<!--&lt;!&ndash;      <el-form :inline="true" :model="tabledata1" class="demo-form-inline">&ndash;&gt;-->
<!--        <el-form-item label="申请时间">-->
<!--          <el-input v-model="tabledata1.time" placeholder="申请时间" disabled></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="联系方式">-->
<!--          <el-input v-model="tabledata1.phone" placeholder="联系方式"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="所在部门">-->
<!--          <el-input v-model="tabledata1.depart"></el-input>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </div>-->
    <div style="border: rgba(82,182,154,0.25) solid 3px">
    <div class="page_title">工单信息</div>
    <div class="page_line"></div>
    <div class="page_block">

      <!-- 工单信息填写表单 -->
      <el-form :inline="true" :model="tabledata1" class="demo-form-inline" style="width: 100%" :label-position="labelPosition">
        <el-form-item label="工单编号">
          <el-input v-model="tabledata1.order_num" placeholder="工单编号"></el-input>
        </el-form-item>
        <el-form-item label="工单标题">
          <el-input v-model="tabledata1.order_name" placeholder="工单标题"></el-input>
        </el-form-item>
        <span class="demonstration">资源到期时间</span>
        <el-date-picker
          v-model="value1"
          type="date"
          placeholder="选择日期">
        </el-date-picker>

      </el-form>
      <el-form :model="tabledata1">
        <el-form-item label="申请理由">
          <el-input type="textarea" v-model="tabledata1.reason" style="width:100%;float:left"></el-input>
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
      <el-button class="el-icon-plus" type="success" @click="dialogTableVisible = true" style=" float: left;">  新增</el-button>

<!--      弹窗-->
      <el-dialog title="现有物理机资源" :visible.sync="dialogTableVisible">
        <el-table
          :header-cell-style="tableHeaderColor"

          ref="multipleTable"
          :data="gridData"
          :row-class-name="tableRowClassName"
          @selection-change="handleSelectionChange"
          @select = "onRowClick">
          <el-table-column  class="el-tableColumn" type="selection" width="55" :reserve-selection="true"></el-table-column>
          <el-table-column property="cpu" label="CPU(核)" width="150"></el-table-column>
          <el-table-column property="memory" label="内存(G)" width="200"></el-table-column>
          <el-table-column property="storage" label="存储(G)"></el-table-column>
          <el-table-column property="ip" label="IP地址" width="150"></el-table-column>
        </el-table>
        <p></p>
          <el-button class="add_type" type="primary" @click="getSelected()">添加选中结果</el-button>

      </el-dialog>


      <el-table :data="tabledata2" border @row-click="getDetails">
        <el-table-column property="cpu" label="CPU(核)" ></el-table-column>
        <el-table-column property="memory" label="内存(G)" ></el-table-column>
        <el-table-column property="storage" label="存储(G)" ></el-table-column>
        <el-table-column property="ip" label="IP地址"></el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="deleteRow(scope.$index, tabledata2)"
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
      <el-form :inline="true" :model="tabledata3" class="demo-form-inline" :label-position="labelPosition">
        <el-form-item label="CPU(核)">
          <el-input v-model="tabledata3.cpu" placeholder="CPU(核)"></el-input>
        </el-form-item>
        <el-form-item label="内存(G)">
          <el-input v-model="tabledata3.memory" placeholder="内存(G)"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="tabledata3" class="demo-form-inline">
        <el-form-item label="存储(G)">
          <el-input v-model="tabledata3.storage" placeholder="存储(G)"></el-input>
        </el-form-item>
        <el-form-item label="操作系统">
          <el-select v-model="tabledata3.win" placeholder="操作系统">
            <el-option label="windows" value="windows"></el-option>
            <el-option label="linux" value="linux"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="IP地址">
          <el-input v-model="tabledata3.IP" style="width:60%;float:left"></el-input>
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
      labelPosition: 'left',
      multipleSelection: [],
      currentRowIndex: [],
      list:[],
      length:'',
      value1:'',
      // selectRow: [],
      // 多选选择数量循环计数器
      multipleChoice_count:'0',
      // 多选选择行号循环计数器
      lineNumber_count:'0',
      //弹窗
      dialogTableVisible: false,
      // 个人信息以及工单信息表单数据
      tabledata1:[{
        num: ' ',
        name:' ',
        time:'',
        phone:'',
        depart:'',
        order_num:'',
        order_name:'',
        reason:''
      }],
      // 已添加的物理机资源信息表数据
      tabledata2:[
      ],
      // 虚拟机资源填写表数据
      tabledata3:[{
        cpu:'',
        memory:'',
        storage:'',
        win:'',
        IP:''
      }],
      // 新增物理机弹窗内表格数据
      gridData:[{
        cpu:'2',
        memory:'3',
        storage:'3',
        ip:'7'
      },
        {
          cpu:'1',
          memory:'3',
          storage:'3',
          ip:'5'
        },
        {
          cpu:'10',
          memory:'3',
          storage:'3',
          ip:'5'
        },
        {
          cpu:'10',
          memory:'3',
          storage:'8',
          ip:'5'
        },
        {
          cpu:'7',
          memory:'3',
          storage:'8',
          ip:'5'
        }],
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
    //获取选中数据的行号
   tableRowClassName({row, rowIndex}) {
     row.row_index = rowIndex;
   },
   onRowClick(row, event, column) {
     for (var k = 0; k < row.length; k++) {
       this.currentRowIndex.push(row[k].row_index)
     }
   },


   // getDetails(row){
   //   const data = row//此时就能拿到整行的信息
   // },


   // 点击selection多选框
   handleSelectionChange(data) {
     this.multipleSelection = data;
     this.length=this.$refs.multipleTable.selection;//获取当前选中数据的行数
   },
   // 获取点击行的数据
   getSelected() {
     this.dialogTableVisible=false//关闭弹窗
     console.log(this.currentRowIndex)
     while(this.multipleChoice_count<this.length.length){
       this.tabledata2.push(this.multipleSelection[this.multipleChoice_count]);
       this.gridData.splice(this.currentRowIndex[this.lineNumber_count],1)
       this.multipleChoice_count++;
       this.lineNumber_count++;

     }
     this.$refs.multipleTable.clearSelection()
     this.multipleChoice_count=0;
     this.lineNumber_count=0;
     this.currentRowIndex.splice(0,this.currentRowIndex.length)
   },
   // 获取当前时间并赋值给this.tabledata1.time
   getDateFunc(){
     let year = new Date().getFullYear();//年
     let month = new Date().getMonth() +1;//注意！月份是从0月开始获取的，所以要+1;
     let day = new Date().getDate();//日
     //拼接日期 YYYY-MM-DD HH:mm:ss
     this.tabledata1.time=
       year +
       '-' +
       (month >=10 ? month:'0'+ month) +
       '-' +
       (day >=10 ? day:'0' + day);
   },

   // 删除选中的物理机资源
   deleteRow(index, rows) {
     const data = this.tabledata2.slice(index,index+1)
     console.info(data)

     this.tabledata2.splice(index,1);
     this.gridData.splice(-1,0,data[0])
   },

//设置表头行的样式
   tableHeaderColor({row,column,rowIndex,columnIndex}){
     return 'background-color:rgba(82, 182, 154, 0.76);color:#fff;font-wight:500'

   }
   //改变多选时选中行的样式
   // rowClass({ row, rowIndex }) {
   //   if (this.selectRow.includes(rowIndex)) {
   //     return { background: "#a7a742" };
   //   }
   // },
   // handleRowClick(row, column, event) {
   //   // list -- 已选的数据
   //   let index = this.multipleSelection.findIndex((item) => {
   //     // 判断已选数组中是否已存在该条数据
   //     return item.id === row.id;
   //   });
   //   if (index === -1) {
   //     // 如果未存在，设置已选状态，并在list中添加这条数据
   //     this.$refs.multipleTable.toggleRowSelection(row, true); //设置复选框为选中状态
   //   } else {
   //     // 如果已存在，设置未选状态，并在list中删除这条数据
   //     this.$refs.multipleTable.toggleRowSelection(row, false); //设置复选框为未选状态
   //   }
   // },
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
