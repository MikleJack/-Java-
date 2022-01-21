<template>
  <div class="adminPortal">
    <!--页面顶部：用户信息-->
    <div class="left-top">
      <!--头像-->
      <div class="headPortrait" :key="fit">
        <img class="head" src="../../assets/admin.png" :fit="fit">
<!--        <el-image class="head" :src="url" :fit="fit">-->
<!--        </el-image>-->
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
    <div class="left-bottom">
      <div class="size-icon">
        <i class="el-icon-message-solid" ></i>
      </div>
      <div class="message_title">通知中心</div>
      <div class="round-i1" v-for="(item,i) in informationTable">
        <i class="round"></i>
        <div class="message">
          <el-button style="line-height: 0px;"
                     @click="setInfromState(item.informNum)"
                     :disabled = "item.readState"
                     type="text">{{ '员工' + item.senderNum + '账号被' + item.details}} </el-button>
        </div>
        <div class="message_date" style="color: #0c805f" >{{item.sendTime}}</div>
      </div>
      <div class="pagination">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pageSize"
          layout=" prev, pager, next, jumper"
          :total="totalSize"
          style="color: #0c805f">
        </el-pagination>
      </div>
    </div>
    <!--右下方-->
    <div class="right-bottom">
      <div class="chart">
        <!--公司预算折线图-->
        <div class="budChart" id="budmain"></div>
      </div>
      <!--管理员按钮-->
      <div class="adminBottom">
        <el-row class="adminButton1" type="flex" justify="center" align="middle">
          <el-tooltip effect="light" content="部门预算设置" placement="left">
          <el-button style="margin-top: 80%; color: white;background-color: #52b69a;font-size: 30px"
                     @click="seebudget" size="mini"
                     icon="el-icon-odometer" circle
                     ></el-button>
          </el-tooltip>
        </el-row>
        <el-row class="adminButton2" type="flex" justify="center" align="middle">
          <el-tooltip effect="light" content="物理机资源及价格配置" placement="left">
          <el-button style="margin-top: 10%;color: white;background-color: #52b69a;font-size: 30px"
                     @click="seephy" size="mini"
                     icon="el-icon-monitor" circle></el-button>
          </el-tooltip>
        </el-row>

        <el-row class="adminButton2" type="flex" justify="center" align="middle">
          <el-tooltip effect="light" content="虚拟机资源及价格配置" placement="left">
            <el-button style="margin-top: 10%;color: white;background-color: #52b69a;font-size: 30px"
                       @click="seeVM" size="mini"
                       icon="el-icon-connection" circle></el-button>
          </el-tooltip>
        </el-row>
      </div>
    </div>


    <!--    点击部门预算设置后的dialog界面-->
    <Depbudget ref="depbudget"></Depbudget>

    <!--点击虚拟机资源配置后的dialog界面-->
    <VMconfiguration ref="vm_configuration"></VMconfiguration>
<!--    点击物理机资源配置后的dialog界面-->
    <Phyconfiguration ref="phyconfiguration"></Phyconfiguration>


    <ConfirmPW>
    </ConfirmPW>
  </div>



</template>

<script>
import * as echarts from 'echarts';
import ConfirmPW from "./ConfirmPW";
import Depbudget from "./dialogs/Depbudget";
import VMconfiguration from "./dialogs/VMconfiguration";
import Phyconfiguration from "./dialogs/Phyconfiguration";

export default {
  name: "adminPortal",
  components: {Phyconfiguration, VMconfiguration, Depbudget, ConfirmPW},


// 表格数据
  data() {
    return {
      //分页相关
      currentPage:1,
      pageSize:7,
      totalSize:0,

      informationTable: [],

      confirm_password:'',

      //dialog中的密码验证参数
      password_confirm: false,
      //对哪个dialog页面进行密码验证，1：预算页面，2：物理机页面，3：虚拟机存储价格页面，4：虚拟机总资源页面，5：虚拟机页面
      which_page_confirm:0,

      activeNames: [],//折叠面板
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

      tableData_bud: [],

      tableData_phy:[
      ],
      num: '',

      tableData_vir:[
      ],



      ruleForm_virtual: {
        comNum:1,
        description:'',
        processorModel: '',
        cpuCore: 1,
        ram:1,
        processorFrequency: 0,
        price: 0,
      },
    }

  },
  mounted(){
    const chartDom = document.getElementById('budmain');
    const budChart = echarts.init(chartDom);
    let budOption;
    budOption = {
      width: '600px',
      height: '250px',
      color:'#349165',
      title:{
        tooltip: {
          trigger: 'item',
          triggerOn: 'click',
          formatter: '{b}:{c}'
        },
        subtext:'近四年部门预算使用情况(万元)',
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
          data: [9.6, 8.7, 7.4, 0.9],
          type: 'line',
          label: {
            show: true,	// 是否可见
            // color:'green',
            rotate: 0 	// 旋转角度
          },
          lineStyle:{
            color: 'green',
            // type: 'dashed'//可选值还有 dotted  solid
          },
          //显示最大值与最小值
          markPoint: {
            data: [
              {
                type: 'max', name: '最大值'
              },
              {
                type: 'min', name: '最小值'
              }
            ]
          },
          //显示平均值
          markLine: {
            data: [
              {
                type: 'average', name: '平均值'
              }
            ]
          }
        }
      ]
    };
    budOption && budChart.setOption(budOption);

    //获取通知中心
    this.$axios.get(this.$store.state.url + "/inform/adminQueryInform?page=0&size=" + this.pageSize ).then((res)=>{
      this.informationTable = res.data.content;
      this.totalSize = res.data.totalPages*this.pageSize;
    });


  },
  methods: {
    //分页按钮操作
    handleCurrentChange(val){
      this.currentPage=parseInt(val);
      let page = this.currentPage-1;
      this.$axios.get(this.$store.state.url + "/inform/adminQueryInform?page=" + page + '&size=' + this.pageSize ).then((res)=>{
        this.informationTable= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    },

    //通知中心设置状态已读
    setInfromState(informNum){
      this.$axios.get(this.$store.state.url + "/inform/changeInformState?informNum=" + informNum);
      setTimeout(()=>{
        this.$axios.get(this.$store.state.url + "/inform/adminQueryInform?page=" + (this.currentPage-1) + '&size=' + this.pageSize ).then((res)=>{
          this.informationTable= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }, 500)
    },

    //显示虚拟机资源配置
    seeVM(){
      this.$store.state.dialogVisible_vir = true;
      this.$refs.vm_configuration.setVm();
    },
    //查看物理机配置
    seephy(){
      this.$store.state.dialogVisible_phy = true;
      this.$refs.phyconfiguration.setphy();
    },
    //查看部门的详情页
    seebudget(){
      this.$store.state.dialogVisible_budget = true;
      this.$refs.depbudget.setbudget();
    },
    //折叠面板
    handleChange_collapse(val) {
    },

    resCustomColor(total_Phyutilization) {
      if (total_Phyutilization < 50 ) {
        return 'rgba(239,125,10,0.7)';
      } else if (total_Phyutilization > 90) {
        return 'rgba(239,125,10,0.7)';
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

  },
}
</script>

<style scoped>
.adminPortal{
  width: 100%;
  height: 725px;
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
  /*background-color: #34957a*/
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
.size-icon{
  font-size: 50px;
  color: #0c805f;
  width:60px;
  float: left;
  height:50px;
}
.message_title{
  font-size: 25px;
  color: #3e8f76;
  font-weight: bolder;
  margin-left: 40%;
  margin-top: 2%;
}
.round-i1{
  height: 10px;
  margin-top: 6%;
  margin-left: 3%;
}
.round{
  display: block;
  float: left;
  height: 10px;
  width: 10px;
  border-radius: 5px;
  margin-top: 6px;

  background: rgb(62, 143, 118) !important;

  margin-left: 0;
}
.message{
  display: block;
  float: left;
  width: fit-content;
  color: rgba(12, 12, 12, 0.51);
  margin-left: 5px;
}
.message_date{
  display: block;
  float: right;
  color: rgba(12, 12, 12, 0.51);

  margin-right: 5px;
}
.pagination {
  position: absolute;
  bottom: 10px;
  height: fit-content;
  margin-bottom: 1px;
  margin-top: 5%;
}
.title {
  font-size: 16px;
  font-weight: bold;
  color: #303331;
  padding-bottom: 17px;
  padding-top: 17px;
}

</style>
