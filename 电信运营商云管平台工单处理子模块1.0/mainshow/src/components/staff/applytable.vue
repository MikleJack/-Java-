<template>
  <div class="page">
<!--    <el-main class="main" style="overflow-x:hidden">-->
<!--      <div>-->
    <div class="page_title">申请人信息</div>
    <!--水平线-->
    <div style="margin-left:10%;margin-bottom:10px;padding:0; width:1000px;height:0.5px;background-color:black;overflow:hidden;"></div>

    <!-- 申请人信息表单 -->
    <div class="page_block">
      <el-form :inline="true" :model="tabledata1" class="demo-form-inline">
        <el-form-item label="工号">
          <el-input v-model="tabledata1.num" placeholder="工号"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="tabledata1.name" placeholder="姓名"></el-input>
        </el-form-item>
      </el-form>
      <el-form :inline="true" :model="tabledata1" class="demo-form-inline">
        <el-form-item label="申请时间">
          <el-input v-model="tabledata1.time" placeholder="申请时间" disabled></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="tabledata1.phone" placeholder="联系方式"></el-input>
        </el-form-item>
        <!--      </el-form>-->
        <!--      <el-form ref="form" :model="tabledata1" label-position=right label-width=430px>-->
        <el-form-item label="所在部门">
          <el-input v-model="tabledata1.depart" style="width:60%;float:left"></el-input>
        </el-form-item>
      </el-form>
    </div>



    <div class="page_title">工单信息</div>
    <div style="margin-left:10%;margin-bottom:10px;padding:0; width:1000px;height:0.5px;background-color:black;overflow:hidden;">

    </div>
    <div class="page_block">

      <!-- 工单信息填写表单 -->
      <el-form :inline="true" :model="tabledata1" class="demo-form-inline">
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

      <!--          <div class="block" style="margin-left:25%;margin-bottom:20px;color:black">-->

      <!--          </div>-->

      <el-form :model="tabledata1">
        <!--                   label-width=430px-->
        <!--          >-->
        <el-form-item label="申请理由">
          <el-input type="textarea" v-model="tabledata1.reason" style="width:100%;float:left"></el-input>
        </el-form-item>
      </el-form>

    </div>





        <!-- 资源信息填写表单 -->


<!--        <div style="width:80px; margin-left:46%;margin-bottom:10px;font-weight:bolder">-->
    <div class="page_title"> 资源信息</div>

    <div style="margin-left:10%;margin-bottom:10px;padding:0; width:1000px;height:0.5px;background-color:black;overflow:hidden;"></div>

    <div class="page_title">物理机资源</div>

    <!-- 新增物理机资源弹窗 -->

    <el-dialog title="现有物理机资源" :visible.sync="dialogTableVisible">
      <el-table ref="multipleTable" :data="gridData" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" :reserve-selection="true"></el-table-column>
        <el-table-column property="cpu" label="CPU(核)" width="150"></el-table-column>
        <el-table-column property="memory" label="内存(G)" width="200"></el-table-column>
        <el-table-column property="storage" label="存储(G)"></el-table-column>
        <el-table-column property="ip" label="IP地址" width="150"></el-table-column>
      </el-table>
      <el-button type="primary" @click="getSelected()">添加选中结果</el-button>
    </el-dialog>


    <div class="line">
      <div style="margin-left: 10%">
        <el-button type="success" plain @click="dialogTableVisible = true">新增</el-button>
      </div>
    </div>



        <!-- 已添加物理机资源表 -->
        <el-table :data="tabledata2" border style="width: 1000px;margin-left: 10%">
          <el-table-column property="cpu" label="CPU(核)" ></el-table-column>
          <el-table-column property="memory" label="内存(G)" ></el-table-column>
          <el-table-column property="storage" label="存储(G)" ></el-table-column>
          <el-table-column property="ip" label="IP地址"></el-table-column>
        </el-table>

        <div class="page_title">虚拟机资源</div>
        <!-- 虚拟机资源填写表单 -->

        <div class="page_block">
          <el-form :inline="true" :model="tabledata3" class="demo-form-inline" style="margin-left:-20px">
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
      <div style="text-align: center">
        <!-- 保存、提交按钮 -->
        <el-button>保存</el-button>
        <el-button type="success">提交</el-button>
      </div>
<!--    </el-main>-->
  </div>

</template>

<script>
export default {
  name: "applytable",
  data() {
    return {
      multipleSelection: [],
      list:[],
      length:'',
      i:'0',
      //弹窗
      dialogTableVisible: false,

      value1:'',
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
      }]
    };
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

    // 点击selection多选框
    handleSelectionChange(data) {
      this.multipleSelection = data;
      this.length=this.$refs.multipleTable.selection;//获取当前选中数据的行数
    },
    // 获取点击行的数据
    getSelected() {
      this.dialogTableVisible=false//关闭弹窗
      while(this.i<this.length.length){
        this.tabledata2.push(this.multipleSelection[this.i]);
        this.gridData.slice
        this.i++;
      }
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
  }

}
</script>

<style>

.page{
  position: absolute;
  width: 100%;
  height: auto;
  /*background-color: #409EFF;*/
}

.page_title{
  width:80px;
  margin-left:40%;
  /*text-align: center;*/
  margin-bottom:10px;
  font-weight:bolder
}

.page_block{
  width: 1000px;
  margin-left: 10%;

}
.line{
  width: 80%;
  height: auto;
  background-color: #409EFF;
  left: 10%;
}
</style>
