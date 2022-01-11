<template>
  <div class="adminPortal">
    <!--页面顶部：用户信息-->
    <div class="left-top">
      <!--头像-->
      <div class="headPortrait" :key="fit">
        <el-image class="head" :src="url" :fit="fit">
        </el-image>
      </div>
      <!--信息-->
      <div class="worker_information">
        <!--权限-->
        <div class="powerInf">
          <el-tag class="power" type="success">{{worker_power}}</el-tag>
        </div>
        <!--姓名及所属部门-->
        <div class="nameInf">
          <p>{{worker_name}}</p>
        </div>
        <div class="depInf">
          <p>{{worker_dep}}</p>
        </div>
        <div class="workerInf">
          <p>工号 ：{{worker_num}}</p>
        </div>
      </div>
    </div>
    <!--页面饼图部分-->
    <div class="right-top">
      <!--物理机-->
      <div class="phy">
        <!--饼图：物理机利用率饼图-->
        <div class="phyPie">
          <el-progress :percentage="total_Phyutilization" :color="resCustomColor"
                       style="margin-left: 27%; margin-top: 20%"
                       type="circle" stroke-width="15" width="120"></el-progress>
        </div>
        <div class="phyMessage">
          <p style="text-align: center" >系统物理机利用率</p>
        </div>
      </div>
      <!--预算-->
      <div class="bud">
        <!--饼图：预算使用情况饼图-->
        <div class="budPie">
          <el-progress :percentage="depBudget" :color="budCustomcolors"
                       style="margin-left: 26%; margin-top: 20%"
                       type="dashboard" stroke-width="15" width="120"></el-progress>
        </div>
        <div class="budMessage">
          <p style="text-align: center">公司预算使用情况</p>
        </div>
      </div>
      <!--虚拟机-->
      <div class="vir">
        <!--饼图：虚拟机利用率饼图-->
        <div class="virPie">
          <el-progress :percentage="total_Virutilization" :color="resCustomColor"
                       style="margin-left:27%; margin-top: 20%"
                       type="circle" :stroke-width="15" :width="120"></el-progress>
        </div>
        <div class="virMessage">
          <p style="text-align: center">系统虚拟机利用率</p>
        </div>
      </div>
    </div>
    <!--通知栏-->
    <div class="left-bottom"></div>
    <!--右下方-->
    <div class="right-bottom">
      <div class="chart">
        <!--公司预算折线图-->
        <div class="budChart" id="budmain"></div>
      </div>
      <!--管理员按钮-->
      <div class="adminBottom">
        <el-row class="adminButton1" type="flex" justify="center" align="middle">
          <el-button @click="dialogVisible_budget=true" size="small" type="primary" round>部门预算设置</el-button>
        </el-row>
        <el-row class="adminButton2" type="flex" justify="center" align="middle">
          <el-button size="small" type="primary" round>系统资源量调整</el-button>
        </el-row>
      </div>
    </div>
    <!--    点击部门预算设置后的dialog界面-->
    <el-dialog
      title="部门预算设置"
      :visible.sync="dialogVisible_budget"
      width="500px"
      height="700px"
      :before-close="handleClose">
      <div class="block" style="text-align: center">
        <el-table class="budTable" frame=above
          :data="tableData_bud"
          border
          style="width: 100%"
        :cell-style="{textAlign:'center'}">
          <el-table-column prop="department_name" label="部门名称" width="180" align="center">
          </el-table-column>
          <el-table-column prop="budget" label="预算" width="278.5" align="center">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.budget" controls-position="right" @change="handleChange"
                               :precision="2" :step="0.1" :min="0" :max="9999"
                               style="margin-left: 8%" size="mini"></el-input-number>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_budget = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_budget = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>



</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "adminPortal",
  components: {},
  methods: {
    resCustomColor(total_Phyutilization) {
      if (total_Phyutilization < 50 ) {
        return 'rgb(255,186,39)';
      } else if (total_Phyutilization > 80) {
        return 'rgb(226,131,22)';
      } else return '#41c6a2'
    },

    budCustomcolors(depBudget){
      if (depBudget <= 90){
        return '#41c6a2';
      } else return '#f56c6c';
    },

    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },

    //部门预算设置的dialog函数
    handleClick_budget() {
      this.dialogVisible_budget = true;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
      .then(_ => {
      done();
      })
      .catch(_ => {});
    },

    handleChange(value) {
      console.log(value);
    }
  },

// 表格数据
  data() {
    return {
      // 全部物理机资源利用率
      total_Phyutilization:'76.29',
      // 全部虚拟机资源利用率
      total_Virutilization:'35.83',
      // 部门预算使用率
      depBudget:'78.90',
      fits: ['cover'],
      //头像地址
      url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F40%2Fc9%2F53%2F40c9533e47b9ce0945a2030f9320b80e.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644379289&t=487f84fb7f4d9252f4fa8ef334c39618',
      //左上角信息展示数据
      //权限
      worker_power:'管理员',
      //姓名
      worker_name:'Admin',
      //部门
      worker_dep:'软件学院',
      //工号
      worker_num:'root',

      //部门预算
      //部门名称
      department_name:'小组1',
      //具体部门预算
      department_nameBud:'10',
      dialogVisible_budget: false,

      tableData_bud: [{
        department_name: '小组1',
        budget: '10',
      },{
        department_name: '小组2',
        budget: '10',
      }
      ]

    }

  },
  mounted(){
    var chartDom = document.getElementById('budmain');
    var budChart = echarts.init(chartDom);
    var budOption;
    budOption = {
      width: '700px',
      height: '250px',
      color:'#349165',
      title:{
        tooltip: {
          trigger: 'item',
          triggerOn: 'click',
          formatter: '{b}:{c}'
        },
        subtext:'近四年部门预算使用情况',
        x:'center',
        subtextStyle: {//副标题文本样式{"color": "#aaa"}
          // fontFamily: 'Arial, Verdana, sans...',
          fontSize: 15,
          fontStyle: 'normal',
          fontWeight: 'normal',
          "color": "#48b981"
        },
      },
      xAxis: {
        type: 'category',
        data: ['2019', '2020', '2021', '2022'],
        scale: true
      },
      yAxis: {
        type: 'value',
        scale: true
      },
      series: [
        {
          data: [8.7, 9.6, 7.4, 0.9],
          type: 'line',
          label: {
            show: true,	// 是否可见
            // color:'green',
            rotate: 0 	// 旋转角度
          },
          lineStyle:{
            color: 'green',
            // type: 'dashed'//可选值还有 dotted  solid
          }
        }
      ]
    };
    budOption && budChart.setOption(budOption);

    var chartDome = document.getElementById('order');
    var orderCharts = echarts.init(chartDome);
    var orderOption;
    orderOption = {
      height: '120px',
      color:'rgba(63,152,111,0.9)',
      title:{
        tooltip: {
          trigger: 'item',
          triggerOn: 'click',
          formatter: '{b}:{c}'
        },
        subtext:'最近工单申请情况',
        x:'center',
        subtextStyle: {//副标题文本样式{"color": "#aaa"}
          // fontFamily: 'Arial, Verdana, sans...',
          fontSize: 15,
          fontStyle: 'normal',
          fontWeight: 'normal',
          "color": "#48b981"
        },
      },
      xAxis: {
        type: 'category',
        data: ['2021.9','2021.9','2021.10', '2021.11', '2021.12', '2022.1'],
        scale: true
      },
      yAxis: {
        type: 'value',
        scale: true
      },
      series: [
        {
          data: [9.8, 8.7, 8.7, 9.6, 7.4, 0.9],
          type: 'bar',
          label: {
            show: true,	// 是否可见
            // color:'green',
          },
          barStyle:{
            color: 'green',
            // type: 'dashed'//可选值还有 dotted  solid
          },
          markPoint: {
            data: [
              {
                type: 'max', name: '最大值'
              },
              {
                type: 'min', name: '最小值'
              }
            ]
          }
        }
      ]
    };
    orderOption && orderCharts.setOption(orderOption);
  },
}
</script>

<style scoped>
.adminPortal{
  width: 100%;
  height: 100%;
  position: relative;
  overflow-y: hidden;
}
.left-top{
  width: 37%;
  height: 38%;
  float: left;
  /*background-color: #73c8b3;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.headPortrait{
  width: 45%;
  height: 100%;
  float: left;
  /*background-color: #55cfb0;*/
  /*布局线框颜色*/
  /*border:3px solid #000*/
}
.head{
  width: 90%;
  height: 90%;
  margin-top: 5%;
  margin-left: 5%;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  border:2px solid #53c59e;

}
.worker_information{
  width: 55%;
  height: 100%;
  float: left;
  /*background-color: #1caf8a;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.powerInf{
  width: 100%;
  height: 15%;
  float: left;
  /*background-color: #147960;*/
}
.power{
  margin-left: 70%;
  margin-top: 5%;
}
.nameInf{
  width: 100%;
  height: 20%;
  font-size: 35px;
  margin-left: 10%;
  /*background-color: #55cfb0;*/
}
.depInf{
  width: 100%;
  height: 15%;
  font-size: 25px;
  margin-left: 10%;
  /*background-color: #52b69a;*/
}
.workerInf{
  width: 100%;
  height: 50%;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.55);
  margin-top: 5%;
  margin-left: 10%;
  /*background-color: #19755e;*/
}
.right-top{
  width: 63%;
  height: 38%;
  float: left;
  /*background-color: #235c4e;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.phy{
  width: 30%;
  height: 100%;
  float: left;
  /*background-color: #73c8b3;*/
}
.phyPie{
  width: 100%;
  height: 75%;
  float: left;

  /*background-color: #73c8b3;*/
}
.phyMessage{
  width: 100%;
  height: 25%;
  float: left;
  font-size: 15px;
  /*margin-left: 14%;*/
  /*background-color: #73c8b3;*/
}
.bud{
  width: 30%;
  height: 100%;
  float: left;
  /*background-color: #3eb698;*/
}
.budPie{
  width: 100%;
  height: 75%;
  float: left;
  /*background-color: #3eb698;*/
}
.budMessage{
  width: 100%;
  height: 25%;
  float: left;
  font-size: 15px;
  /*background-color: #36ba99;*/
}
.vir{
  width: 30%;
  height: 100%;
  float: left;
  /*background-color: #1e4d42;*/
}
.virPie{
  width: 100%;
  height: 75%;
  float: left;
  /*background-color: #1e4d42;*/
}
.virMessage{
  width: 100%;
  height: 25%;
  float: left;
  font-size: 15px;
  /*background-color: #4cbda3;*/
}
.enter{
  width: 10%;
  height: 100%;
  float: left;
  /*background-color: #36ba99;*/
}
.left-bottom{
  width: 36%;
  height: 61%;
  float: left;
  /*background-color: #33ab8a;*/
  border:2px solid #53c59e
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.right-bottom{
  width: 63%;
  height: 62%;
  float: left;
  /*background-color: #50b299;*/
  /*布局线框颜色*/
  /*border:2px solid #53c59e*/
  /*border-bottom:2px dashed #55c97e*/
}
.chart{
  width: 85%;
  height: 100%;
  float: left;
  /*background-color: #34957a*/
}
.budChart{
  width: 100%;
  height: 100%;
  float: left;
  margin-top: 5%;
  background-color: #34957a
}
.adminButton1 {
  height: 20%;
}
.adminButton2 {
  height: 20%;
}
.budTable{
  /*border:2px solid #53c59e;*/
  /*border-color: #52b69a;*/
  border:solid #add9c0;
  border-width:1px 1px 1px 1px;
}

</style>
