<template>
  <div class="employeePortal">
    <!--    利用率饼图-->
    <div class="left-top1">
      <el-progress class="phyPie" type="circle" :percentage="total_Phyutilization" stroke-width="12" width="120" :color="customColor"></el-progress>
    </div>
    <!--        表格-->
    <div class="left-top2">
      <el-table class="phyTable" :data="Phy_Data">
        <!--            全部物理机资源-->
        <el-table-column
          prop="All_phy_machine"
          label="全部物理机资源"
          width="130">
        </el-table-column>
        <!--            总利用率-->
        <el-table-column
          prop="Total_utilization"
          label="总利用率"
          width="150">
        </el-table-column>
      </el-table>
    </div>
    <div class="right-top1">
      <el-progress type="circle" :percentage="total_Virutilization" :stroke-width="12" :width="120" :color="customColor"></el-progress>
    </div>
    <div class="right-top2">
      <el-table class="virTable" :data="Vir_Data">
        <!--            全部物理机资源-->
        <el-table-column
          prop="All_vir_machine"
          label="全部虚拟机资源"
          width="130">
        </el-table-column>
        <!--            总利用率-->
        <el-table-column
          prop="Total_utilization"
          label="总利用率"
          width="150">
        </el-table-column>
      </el-table>
    </div>
    <div class="left-middle">
      <p align="center" style="margin-top: 5px">物理机资源利用详情</p>
    </div>
    <div class="right-middle">
      <p align="center" style="margin-top: 5px">虚拟机资源利用详情</p>
    </div>
    <div class="left-bottom0"></div>
    <!--    下属申请的每台物理机详情信息-->
    <div class="left-bottom">
      <el-table :data="all_PhyData" height="100%" border style="width: 100%; height:100%; font-size: x-small" >
        <el-table-column
          prop="Host_num"
          label="主机号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_num"
          label="下属工号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_name"
          label="下属姓名"
          width="100"
          align="center">
        </el-table-column>
        <el-table-column
          prop="configuration"
          label="配置"
          width="180"
          align="center">
        </el-table-column>
        <el-table-column
          prop="utilization"
          label="利用率"
          align="center">
        </el-table-column>
      </el-table>
    </div>
    <div class="right-middle-bottom"></div>
    <!--    下属申请的每台虚拟机详情信息-->
    <div class="left-middle-bottom">
      <el-table :data="all_VirData" height="100%" border style="width: 100%; height:100%; font-size: x-small">
        <el-table-column
          prop="Vir_num"
          label="虚拟机号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_num"
          label="下属工号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_name"
          label="下属姓名"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="configuration"
          label="配置"
          width="180"
          align="center">
        </el-table-column>
        <el-table-column
          prop="utilization"
          label="利用率"
          align="center">
        </el-table-column>
      </el-table>
    </div>
    <!--    快捷入口栏-->
    <div class="right-bottom">
      <!--      快捷入口上方预留空位-->
      <div class="right-top-bottom"></div>
      <!--      快捷入口图标-->
      <div class="right-bottom-middle">
        <!--        <p align="center">快捷</p>-->
        <!--        <p align="center">入口</p>-->
      </div>
      <!--      快捷入口具体按钮-->
      <div class="right-bottom-bottom" >
        <el-row>
          <el-button type="success" icon="el-icon-search" size="medium " style="display:block;margin:0 auto" circle ></el-button>
          <el-button type="primary" icon="el-icon-edit" size="medium " style="display:block;margin:0 auto" circle></el-button>
          <el-button type="warning" icon="el-icon-star-off" size="medium " style="display:block;margin:0 auto" circle></el-button>
        </el-row>
      </div>
    </div>

  </div>


</template>

<script>
export default {
  name: "leaderPortal",
  components: {},
  methods: {
    customColor(total_Phyutilization) {
      if (total_Phyutilization < 50 ) {
        return '#f56c6c';
      } else if (total_Phyutilization > 80) {
        return '#f56c6c';
      } else return '#41c6a2'
    },

    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    }
  },

// 表格数据
  data() {
    return {
      total_Phyutilization:'76.29',
      total_Virutilization:'35.83',
      // 物理机总利用率
      Phy_Data: [{
        All_phy_machine: '7台',
        Total_utilization: '76.29%',
      },],

      // 虚拟机总利用率
      Vir_Data: [{
        All_vir_machine: '3712GB',
        Total_utilization: '35.83%',
      },],

      // 物理机详情信息
      all_PhyData: [{
        Host_num: '00000001',
        worker_num: '20220014',
        worker_name: '王二',
        configuration: '8+128GB',
        utilization: '80.00%'
      }, {
        Host_num: '00000002',
        worker_num: '20220015',
        worker_name: '王平',
        configuration: '16+128GB',
        utilization: '76.50%'
      }, {
        Host_num: '00000003',
        worker_num: '20220013',
        worker_name: '王伟',
        configuration: '16+512GB',
        utilization: '71.50%'
      },{
        Host_num: '00000004',
        worker_num: '20220001',
        worker_name: '张大炮',
        configuration: '32+1TB',
        utilization: '76.50%'
      },{
        Host_num: '00000005',
        worker_num: '20220013',
        worker_name: '王伟',
        configuration: '16+512GG',
        utilization: '76.50%'
      }, {
        Host_num: '00000006',
        worker_num: '20220015',
        worker_name: '王平',
        configuration: '16+128G',
        utilization: '76.50%'
      }, {
        Host_num: '00000007',
        worker_num: '20220001',
        worker_name: '张大炮',
        configuration: '16+128G',
        utilization: '76.50%'
      }],

      // 虚拟机详情信息
      all_VirData: [{
        Vir_num: '00000001',
        worker_num: '20220013',
        worker_name: '王伟',
        configuration: 'Windows,8+128GB',
        utilization: '80.00%'
      }, {
        Vir_num: '00000002',
        worker_num: '20220001',
        worker_name: '张大炮',
        configuration: 'Linux,16+128GB',
        utilization: '35.50%'
      }, {
        Vir_num: '00000003',
        worker_num: '20220001',
        worker_name: '张大炮',
        configuration: 'Linux,16+512GB',
        utilization: '21.50%'
      },{
        Vir_num: '00000004',
        worker_num: '20220013',
        worker_name: '王伟',
        configuration: 'Windows,32+1TB',
        utilization: '42.50%'
      },{
        Vir_num: '00000005',
        worker_num: '20220001',
        worker_name: '张大炮',
        configuration: 'Windows,16+512GB',
        utilization: '11.50%'
      }, {
        Vir_num: '00000006',
        worker_num: '20220013',
        worker_name: '王伟',
        configuration: 'Windows,16+128G',
        utilization: '57.50%'
      }, {
        Vir_num: '00000007',
        worker_num: '20220015',
        worker_name: '王平',
        configuration: 'Linux,16+128G',
        utilization: '41.57%'
      },{
        Vir_num: '00000008',
        worker_num: '20220001',
        worker_name: '张大炮',
        configuration: 'Linux,16+128G',
        utilization: '47.82%'
      },{
        Vir_num: '00000009',
        worker_num: '20220013',
        worker_name: '王伟',
        configuration: 'Linux,16+1TB',
        utilization: '38.09%'
      },{
        Vir_num: '00000009',
        worker_num: '20220001',
        worker_name: '张大炮',
        configuration: 'Linux,16+1TB',
        utilization: '35.83%'
      },{
        Vir_num: '00000009',
        worker_num: '20220014',
        worker_name: '王二',
        configuration: 'Linux,16+1TB',
        utilization: '35.83%'
      },{
        Vir_num: '00000009',
        worker_num: '20220015',
        worker_name: '王平',
        configuration: 'Linux,16+1TB',
        utilization: '35.83%'
      },
      ]
    }
  },
}
</script>

<style scoped>

.employeePortal{
  width: 100%;
  height: 100%;
  position: relative;
}
.left-top1{
  width:20%;
  height:25%;
  float:left;
  /*background-color: #73c8b3;*/
}
.left-top2{
  width:30%;
  height:25%;
  float:left;
  /*background-color: #38836e;*/
}
.right-top1{
  width:20%;
  height:25%;
  float:left;
  position: relative;
  /*background-color: #49937b;*/
}
.right-top2{
  width:30%;
  height:25%;
  float:left;
  /*background-color: #6cbaa4;*/
}
.phyTable{
  margin-top: 15px;
  width: 1000%;
  height: 77%;
  /*background-color: #76fbb6;*/
}
.virTable{
  margin-top: 15px;
  width: 100%;
  height: 77%;
  /*background-color: #5be6b7;*/
}
.left-middle{
  width:50%;
  height:5%;
  float:left;
  /*background-color: #83e1c8;*/
}
.right-middle{
  width:50%;
  height:5%;
  float:left;
  /*background-color: #65dac3;*/
}
/*占位，美观*/
.left-bottom0{
  width:5%;
  height:68%;
  float:left;
  /*background-color: #73d3ba;*/
}
.left-bottom{
  width:40%;
  height:68%;
  float:left;
  /*background-color: #73d3ba;*/
}
.left-middle-bottom{
  width:40%;
  height:68%;
  float:left;
  /*background-color: #538e81;*/
}
.right-middle-bottom{
  width:10%;
  height:68%;
  float:left;
  /*background-color: #538e81;*/
}
.right-bottom{
  width: 5%;
  height: 68%;
  float:left;
}
.right-top-bottom{
  width:100%;
  height:35%;
  float:left;
  /*background-color: #63bba8;*/
}
.right-bottom-middle{
  width:100%;
  height:25%;
  float:left;
  /*background-color: #51a08f;*/
}
.right-bottom-bottom{
  width:100%;
  height:40%;
  float:left;
  /*background-color: #82eed7;*/
}


</style>
