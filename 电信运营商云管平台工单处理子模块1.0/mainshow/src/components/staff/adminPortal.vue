<template>
  <div class="employeePortal">
    <!--饼图：利用率饼图-->
    <div class="left-top1">
      <el-progress class="phyPie" type="circle" :percentage="total_Phyutilization" stroke-width="15" width="120"
                   style="margin-left: 30%" :color="customColor"></el-progress>
    </div>
    <!--表格：物理机饼图表格-->
    <div class="left-top2">
      <el-table class="phyTable" :data="Phy_Data":cell-style="{borderColor:'#ffffff'}" :header-cell-style="{borderColor:'#ffffff'}">
        <!--            全部物理机资源-->
        <el-table-column
          prop="All_phy_machine"
          label="系统物理机资源"
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
    <!--饼图：虚拟机饼图-->
    <div class="right-top1">
      <el-progress class="virPie" type="circle" :percentage="total_Virutilization" :stroke-width="15" :width="120"
                   style="margin-left: 30%" :color="customColor"></el-progress>
    </div>
    <!--表格：虚拟机饼图表格-->
    <div class="right-top2">
      <el-table class="virTable" :data="Vir_Data" :cell-style="{borderColor:'#ffffff'}" :header-cell-style="{borderColor:'#ffffff'}">
        <!--            全部物理机资源-->
        <el-table-column
          prop="All_vir_machine"
          label="系统虚拟机资源"
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
    <!--管理员系统自定义审核以及系统资源量调整按钮-->
    <div class="right-top">
      <div class="right-top-top"></div>
      <el-row>
<!--        系统自定义审核-->
        <el-tooltip content="系统自定义审核" placement="bottom" effect="light">
        <el-button type="primary" @click="dialogVisible_set = true" icon="el-icon-set-up"  circle></el-button>
        </el-tooltip>
        <el-dialog
          title="系统自定义审核"
          :visible.sync="dialogVisible_set"
          width="50%"
          :before-close="handleClose">
          <p>请设置CPU核（个）范围</p>
          <div class="block" style="width: 80%;margin: auto">
            <el-slider
              v-model="CPU_value"
              max="1024"
              show-input>
            </el-slider>
          </div>
          <div style="margin-top: 30px">请设置内存（G）范围</div>
          <div class="block" style="width: 80%;margin: auto">
            <el-slider
              v-model="RAM_value"
              max="1024"
              show-input>
            </el-slider>
          </div>
          <p style="margin-top: 30px">请设置存储（G）范围</p>
          <div class="block" style="width: 80%;margin: auto">
            <el-slider
              v-model="ROM_value"
              max="1024"
              show-input>
            </el-slider>
          </div>
          <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible_set = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible_set = false">确 定</el-button>
  </span>
        </el-dialog>

<!--        系统资源调整-->
        <el-tooltip content="系统资源调整" placement="bottom" effect="light">
        <el-button type="success" icon="el-icon-odometer" circle></el-button>
        </el-tooltip>
      </el-row>

    </div>
    <!--文字：物理机资源利用详情-->
    <div class="left-middle">
      <p align="center" style="margin-top: 5px">物理机资源利用详情</p>
    </div>
    <!--文字：虚拟机资源利用详情-->
    <div class="right-middle">
      <p align="center" style="margin-top: 5px">虚拟机资源利用详情</p>
    </div>
    <!--左下方预留位，准备装饰-->
    <div class="left-bottom0"></div>
    <!--表格：系统每台物理机详情信息-->
    <div class="left-bottom">
      <el-table class="phyTableData" :data="all_PhyData" height="100%" border style="width: 100%; height:100%; font-size: x-small"
                :header-cell-style="{borderColor:'#55c5a7'}" :cell-style="{borderColor:'#55c5a7'}">
        <el-table-column
          prop="Host_num"
          label="主机号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_num"
          label="员工工号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_name"
          label="员工姓名"
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
    <!--预留位：两表之间-->
    <div class="right-middle-bottom"></div>
    <!--系统每台虚拟机详情信息-->
    <div class="left-middle-bottom">
      <el-table class="virTableData" :data="all_VirData" height="100%" border style="width: 100%; height:100%; font-size: x-small"
                :header-cell-style="{borderColor:'#55c5a7'}" :cell-style="{borderColor:'#55c5a7'}">
        <el-table-column
          prop="Vir_num"
          label="虚拟机号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_num"
          label="员工工号"
          width="80"
          align="center">
        </el-table-column>
        <el-table-column
          prop="worker_name"
          label="员工姓名"
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
    <!-- 预留：快捷入口栏-->
    <div class="right-bottom">
<!--      &lt;!&ndash;      快捷入口上方预留空位&ndash;&gt;-->
<!--      <div class="right-top-bottom"></div>-->
<!--      &lt;!&ndash;      快捷入口图标&ndash;&gt;-->
<!--      <div class="right-bottom-middle"></div>-->
<!--      &lt;!&ndash;      快捷入口具体按钮&ndash;&gt;-->
<!--      <div class="right-bottom-bottom" ></div>-->
    </div>

  </div>



</template>

<script>
export default {
  name: "adminPortal",
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
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    }

  },

// 表格数据
  data() {
    return {
      CPU_value: 0,
      RAM_value: 0,
      ROM_value: 0,
      total_Phyutilization:'76.29',
      total_Virutilization:'35.83',
      // 物理机总利用率
      Phy_Data: [{
        All_phy_machine: '8台',
        Total_utilization: '76.29%',
      },],
      dialogVisible_set: false,
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
        worker_num: '20220015',
        worker_name: '王平',
        configuration: '16+128GB',
        utilization: '76.50%'
      },{
        Host_num: '00000008',
        worker_num: '20220015',
        worker_name: '王平',
        configuration: '16+128GB',
        utilization: '76.50%'
      },
      ],

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
  width:17.5%;
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
  width:17.5%;
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
.right-top{
  width:5%;
  height:25%;
  float:left;
  /*background-color: #5bc2a5;*/
}
.right-top-top{
  width:100%;
  height:30%;
  float:left;
  /*background-color: #5bc2a5;*/
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
.phyTableData{
  border-radius: 15px;
  border: solid 2px rgba(82, 182, 154, 0.5);
}
.virTableData{
  border-radius: 15px;
  /*height: 20px;*/
  border: solid 2px rgba(82, 182, 154, 0.5);
  /*overflow-y: scroll;*/
}

/*预留快捷入口*/
/*.right-top-bottom{*/
/*  width:100%;*/
/*  height:35%;*/
/*  float:left;*/
/*  !*background-color: #63bba8;*!*/
/*}*/
/*.right-bottom-middle{*/
/*  width:100%;*/
/*  height:25%;*/
/*  float:left;*/
/*  !*background-color: #51a08f;*!*/
/*}*/
/*.right-bottom-bottom{*/
/*  width:100%;*/
/*  height:40%;*/
/*  float:left;*/
/*  !*background-color: #82eed7;*!*/
/*}*/
/*.adminButton1 {*/
/*  height: 20%;*/
/*}*/
/*.adminButton2 {*/
/*  height: 20%;*/
/*}*/


</style>
