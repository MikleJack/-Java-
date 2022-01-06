<template>
  <div class="page">
    <div class="page_title">申请人信息</div>
    <div class="page_line"></div>
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
          <el-input v-model="tabledata1.time" placeholder="申请时间"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="tabledata1.phone" placeholder="联系方式"></el-input>
        </el-form-item>
      </el-form>
      <el-form ref="form" :model="tabledata1" label-position=right>
        <el-form-item label="所在部门">
          <el-input v-model="tabledata1.depart" style="width:60%;float:left"></el-input>
        </el-form-item>
      </el-form>
    </div>

    <div class="page_title">工单信息</div>
    <div class="page_line"></div>
    <!-- 工单信息填写表单 -->

    <div class="page_block">
      <el-form :inline="true" :model="tabledata1" class="demo-form-inline">
        <el-form-item label="工单编号">
          <el-input v-model="tabledata1.order_num" placeholder="工单编号"></el-input>
        </el-form-item>
        <el-form-item label="原工单编号">
          <el-input v-model="tabledata1.order_num0" placeholder="原工单编号"></el-input>
        </el-form-item>
      </el-form>
      <el-form ref="form" :model="tabledata1" >
        <el-form-item label="工单标题">
          <el-input v-model="tabledata1.order_name" style="width:40%;float:left"></el-input>
          <span>资源到期时间</span>
          <el-date-picker
            v-model="value1"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="变更理由">
          <el-input type="textarea" v-model="tabledata1.reason" style="width:60%;float:left"></el-input>
        </el-form-item>
      </el-form>
    </div>

    <div class="page_title">资源信息</div>
    <div class="page_line"></div>
    <div class="page_block">
      <div class="page_title_min">物理机资源</div>

      <el-table :data="tabledata2" border>
        <el-table-column property="cpu" label="CPU(核)" ></el-table-column>
        <el-table-column property="memory" label="内存(G)" ></el-table-column>
        <el-table-column property="storage" label="存储(G)" ></el-table-column>
        <el-table-column property="ip" label="IP地址"></el-table-column>
      </el-table>
      <div class="page_title_min">虚拟机资源</div>
      <el-form :inline="true" :model="tabledata3" class="demo-form-inline">
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
          <el-input v-model="tabledata3.IP" ></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div style="text-align: center">
      <!-- 保存、提交按钮 -->
      <el-button>保存</el-button>
      <el-button type="success">提交</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "changetable2",
  data(){
    return{
      multipleSelection: [],
      list:[],
      length:'',
      i:'0',
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
        order_num0:'',
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
        //     {
        //   cpu:'',
        //   memory:'',
        //   storage:'',
        //   ip:''
        // }
      ],
      tabledata3:[{
        cpu:'',
        memory:'',
        storage:'',
        win:'',
        IP:''
      }]
    }
  }
};
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
  font-weight:bolder
}

.page_line{
  margin-left:10%;
  margin-bottom:10px;
  padding:0;
  width:80%;
  height:0.5px;
  background-color:black;
  overflow:hidden;
  text-align: center;
}

.page_block{
  width: 80%;
  margin-left: 10%;
}

.page_title_min{
  width:80px;
  margin-left:50%;
  margin-bottom:10px;
}

</style>
