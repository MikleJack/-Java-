<template>
  <div class="employeePortal">
    <div class="left-top1">
      <el-progress class="phyPie" type="circle" :percentage="76.29" stroke-width="12" width="120" status="success"></el-progress>
    </div>
    <!--        表格-->
    <div class="left-top2">
      <el-table class="phyTable" :data="Phy_Data">
        <!--            全部物理机资源-->
        <el-table-column
          prop="All_phy_machine"
          label="全部物理机资源"
          width="150">
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
            <el-progress type="circle" :percentage="35.83" :stroke-width="12" :width="120" status="warning"></el-progress>
    </div>
    <div class="right-top2">
      <el-table class="virTable" :data="Vir_Data">
        <!--            全部物理机资源-->
        <el-table-column
          prop="All_vir_machine"
          label="全部虚拟机资源"
          width="150">
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
<!--    用户申请的每台物理机详情信息-->
    <div class="left-bottom">
      <el-table :data="all_PhyData" height="100%" border style="width: 100%; height:100%; font-size: x-small" >
        <el-table-column
          prop="Host_num"
          label="主机号"
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
<!--    用户申请的每台虚拟机详情信息-->
    <div class="left-middle-bottom">
      <el-table :data="all_VirData" height="100%" border style="width: 100%; height:100%; font-size: x-small">
        <el-table-column
          prop="Vir_num"
          label="虚拟机号"
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
    <div class="right-bottom">
      <div class="right-top-bottom"></div>
      <div class="right-bottom-middle">
        <p>快捷</p>
        <p>入口</p>
      </div>
      <div class="right-bottom-bottom"></div>
    </div>

  </div>


</template>

<script>
export default {
  name: "employeePortal",
  // components: {},
  methods: {
    //退出
    signout() {
      this.$confirm("退出登录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$router.push({ path: "/" });
      });
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
        configuration: '8+128GB',
        utilization: '80.00%'
      }, {
        Host_num: '00000002',
        configuration: '16+128GB',
        utilization: '76.50%'
      }, {
        Host_num: '00000003',
        configuration: '16+512GB',
        utilization: '71.50%'
      },{
        Host_num: '00000004',
        configuration: '32+1TB',
        utilization: '76.50%'
      },{
        Host_num: '00000005',
        configuration: '16+512GG',
        utilization: '76.50%'
      }, {
        Host_num: '00000006',
        configuration: '16+128G',
        utilization: '76.50%'
      }, {
        Host_num: '00000007',
        configuration: '16+128G',
        utilization: '76.50%'
      }],

      // 虚拟机详情信息
      all_VirData: [{
        Vir_num: '00000001',
        configuration: 'Windows,8+128GB',
        utilization: '80.00%'
      }, {
        Vir_num: '00000002',
        configuration: 'Linux,16+128GB',
        utilization: '35.50%'
      }, {
        Vir_num: '00000003',
        configuration: 'Linux,16+512GB',
        utilization: '21.50%'
      },{
        Vir_num: '00000004',
        configuration: 'Windows,32+1TB',
        utilization: '42.50%'
      },{
        Vir_num: '00000005',
        configuration: 'Windows,16+512GB',
        utilization: '11.50%'
      }, {
        Vir_num: '00000006',
        configuration: 'Windows,16+128G',
        utilization: '57.50%'
      }, {
        Vir_num: '00000007',
        configuration: 'Linux,16+128G',
        utilization: '41.57%'
      },{
        Vir_num: '00000008',
        configuration: 'Linux,16+128G',
        utilization: '47.82%'
      },{
        Vir_num: '00000009',
        configuration: 'Linux,16+1TB',
        utilization: '38.09%'
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
  position: absolute;
}
.left-top1{
  width:18%;
  height:21.25%;
  float:left;
  /*background-color: #73c8b3;*/
}
.left-top2{
  width:22%;
  height:21.25%;
  float:left;
  /*background-color: #38836e;*/
}
.right-top1{
  width:18%;
  height:21.25%;
  float:left;
  position: relative;
  /*background-color: #49937b;*/
}
.right-top2{
  width:22%;
  height:21.25%;
  float:left;
  /*background-color: #6cbaa4;*/
}
.phyTable{
  margin-top: 15px;
  width: 100%;
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
  width:40%;
  height:5%;
  float:left;
  /*background-color: #83e1c8;*/
}
.right-middle{
  width:40%;
  height:5%;
  float:left;
  /*background-color: #65dac3;*/
}
.left-bottom{
  width:35.5%;
  height:60%;
  float:left;
  /*background-color: #73d3ba;*/
}
.left-middle-bottom{
  width:35.5%;
  height:60%;
  float:left;
  /*background-color: #538e81;*/
}
.right-middle-bottom{
  width:4%;
  height:60%;
  float:left;
  /*background-color: #538e81;*/
}
.right-bottom{
  width: 5%;
  height: 60%;
  float:left;
}
.right-top-bottom{
  width:100%;
  height:40%;
  float:left;
  background-color: #63bba8;
}
.right-bottom-middle{
  width:100%;
  height:20%;
  float:left;
  background-color: #51a08f;
}
.right-bottom-bottom{
  width:100%;
  height:40%;
  float:left;
  background-color: #82eed7;
}
</style>
