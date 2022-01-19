<template>
  <div class="leaderPortal">
    <!--页面顶部：用户信息-->
    <div class="left-top">
      <!--头像-->
      <div class="headPortrait" :key="fit">
        <img class="head" src="../../assets/leader.jpg" :fit="fit">
      </div>
      <!--信息-->
      <div class="worker_information">
        <!--权限-->
        <div class="powerInf">
          <el-tag class="power" type="success" v-if="workInfo.depLevel===2">一级领导</el-tag>
          <el-tag class="power" type="success" v-if="workInfo.depLevel===3">二级领导</el-tag>
        </div>
        <!--姓名及所属部门-->
        <div class="nameInf">
          <p>{{workInfo.name}}</p>
        </div>
        <div class="depInf">
          <p>{{workInfo.depName}}</p>
        </div>
        <div class="workerInf">
          <p>工号 ：{{workInfo.workNum}}</p>
          <p>电话号码：{{workInfo.phone}}</p>
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
          <p style="text-align: center" >物理机利用率</p>
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
          <p style="text-align: center">虚拟机利用率</p>
        </div>
      </div>
      <!--展示栏-->
      <div class="promptBar">
        <!--即将到期栏-->
        <div class="hangPart">
          <div class="hang">
            <el-tag type="success" effect="dark" color="#349165"
                    style="width: 90%;height: 86%;font-size: large;
                    text-align: center;line-height: 50px;
                    margin-top: 10%;border-radius: 50%">挂</el-tag>
          </div>
          <div class="Message">
            <p>挂起工单</p>
            <p>{{hangingTickets}}</p>
          </div>
        </div>
        <div class="untreatedPart">
          <div class="untreated">
            <el-tag type="success" effect="dark" color="#349165"
                    style="width: 90%;height: 86%;font-size: large;
                    text-align: center;line-height: 50px;
                    margin-top: 10%;border-radius: 50%">待</el-tag>
          </div>
          <div class="Message">
            <p>待审工单</p>
            <p>{{pendingTickets}}</p>
          </div>
        </div>
      </div>
    </div>
    <!--通知栏-->
    <div class="left-bottom">
      <div class="size-icon">
        <i class="el-icon-message-solid" ></i>
      </div>
      <div class="message_title">
        通知中心
      </div>
      <div class="round-i1" v-for="(item,i) in informationTable">
        <i class="round"></i>
        <div class="message">
          <el-button style="line-height: 0px;"
                     @click="setInfromState(item.informNum)"
                     :disabled = "item.readState"
                     type="text">{{ item.senderName + item.details +'(' + item.workOrderName +')' }} </el-button>
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
        <!--审批情况折线图-->
        <div class="approveChart" id="approvemain"></div>
        <!--最近工单申请柱状图图-->
        <div class="orderChart" id="order"></div>
      </div>
      <!--快捷入口具体按钮-->
      <div class="enter" >
        <el-row>
          <el-tooltip effect="light" content="修改个人信息" placement="left">
            <el-button type="primary" icon="el-icon-edit" size="medium " style="display:block;margin:20% auto 0" circle @click="goRouter(1)"></el-button>
          </el-tooltip>
          <el-tooltip effect="light" content="全部工单查询" placement="left">
            <el-button type="success" icon="el-icon-search" size="medium " style="display:block;margin: 0 auto 0;" circle  @click="goRouter(2)"></el-button>
          </el-tooltip>
          <el-tooltip effect="light" content="工单审批日志" placement="left">
            <el-button type="warning" icon="el-icon-star-off" size="medium " style="display:block;margin:0 auto 0" circle @click="goRouter(3)"></el-button>
          </el-tooltip>
        </el-row>
      </div>
    </div>
  </div>

</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "leaderPortal",
  components: {},
  methods: {
    //分页按钮操作
    handleCurrentChange(val){
      this.currentPage=parseInt(val);
      let page = this.currentPage-1;
      this.$axios.get(this.$store.state.url + "/inform/queryByRecipientNum?workNum="
        + sessionStorage.getItem("work_num") + '&page=' + page + '&size=' + this.pageSize ).then((res)=>{
        this.informationTable= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    },

    //通知中心设置状态已读
    setInfromState(informNum){
      this.$axios.get(this.$store.state.url + "/inform/changeInformState?informNum=" + informNum);
      setTimeout(()=>{
        this.$axios.get(this.$store.state.url + "/inform/queryByRecipientNum?workNum="
          + sessionStorage.getItem("work_num") + '&page=' + (this.currentPage-1) + '&size=' + this.pageSize ).then((res)=>{
          this.informationTable= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }, 500)
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
    init(){
      this.$axios.get(this.$store.state.url+"/staffHome/queryPersonInformById?workerNum="+
        sessionStorage.getItem("work_num")).then((res)=>{
          this.workInfo=res.data;
      })
    },
    goRouter(index){
      if (index===1)
        this.$router.push({path:"/leader/changePass"});
      else if (index===2)
        this.$router.push({path:"/leader/allOrder"});
      else if (index===3)
        this.$router.push({path:"/leader/examineLog"});
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
      workInfo:{

      },
      second:'',

      //分页相关
      currentPage:1,
      pageSize:7,
      totalSize:0,

      informationTable: [],

      pendingTickets: '',
      hangingTickets: '',
    }
  },
  mounted(){
    this.init();

    //获取待审批的工单数量
    if(sessionStorage.getItem("level")==="3"){
      this.worderOrderState="一级审批通过";
      this.second=sessionStorage.getItem("work_num");
      this.$axios.get(this.$store.state.url+"/pendtickets/selectTow?page=0&size=1").then((res)=>{
        this.pendingTickets = res.data.totalElements;
      })
    }
    else {
      this.worderOrderState="待审批";
      this.first=sessionStorage.getItem("work_num");
      this.$axios.get(this.$store.state.url+"/pendtickets/selectTicketsByState?second_leader_num="+this.second+"&first_leader_num="+this.first+"&page=0&size=1"
        +"&orderState="+this.worderOrderState).then((res)=>{
        this.pendingTickets = res.data.totalElements;
      });
      this.$axios.get(this.$store.state.url+"/pendtickets/selectTicketsByState?second_leader_num="+this.second+"&first_leader_num="+this.first+"&page=0&size=1"
        +"&orderState=挂起").then((res)=>{
        this.hangingTickets = res.data.totalElements;
      });
    }

    //获取通知中心
    this.$axios.get(this.$store.state.url + "/inform/queryByRecipientNum?workNum="
      + sessionStorage.getItem("work_num") + '&page=0&size=' + this.pageSize ).then((res)=>{
      this.informationTable = res.data.content;
      this.totalSize = res.data.totalPages*this.pageSize;
    })

    var chartDom = document.getElementById('approvemain');
    var approveChart = echarts.init(chartDom);
    var approveOption;
    approveOption = {
      width: '650px',
      height: '120px',
      color:'#349165',
      title:{
        tooltip: {
          trigger: 'item',
          triggerOn: 'click',
          formatter: '{b}:{c}'
        },
        subtext:'工单审批情况(个)',
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
        data: ['2021.8','2021.9','2021.10', '2021.11', '2021.12', '2022.1'],
        scale: true
      },
      yAxis: {
        type: 'value',
        scale: true
      },
      series: [
        {
          data: [12, 12, 14, 11, 13, 4],
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
          markLine: {
            data: [
              {
                type: 'average',
                name: '平均值'
              }
            ]
          }
        }
      ]
    };
    approveOption && approveChart.setOption(approveOption);

    var chartDome = document.getElementById('order');
    var orderCharts = echarts.init(chartDome);
    var orderOption;
    orderOption = {
      width: '700px',
      height: '120px',
      color:'rgba(63,152,111,0.9)',
      title:{
        tooltip: {
          trigger: 'item',
          triggerOn: 'click',
          formatter: '{b}:{c}'
        },
        subtext:'最近工单申请情况(个)',
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
        data: ['付守宇','付泓菁','朱鹏飞', '陈海涛'],
        scale: true
      },
      yAxis: {
        type: 'value',
        scale: true
      },
      series: [
        {
          data: [6, 4, 5, 3],
          type: 'bar',
          barwidth: '10%',
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
.leaderPortal{
  width: 100%;
  height: 100%;
  position: relative;
  overflow-y: hidden;
}
.left-top{
  /*width: 37%;*/
  /*height: 38%;*/
  /*float: left;*/
  /*background-color: #73c8b3;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
  position:absolute;
  top:0;
  left: 0;
  width: 37%;
  height: 38%;
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
  /*width: 63%;*/
  /*height: 38%;*/
  /*float: left;*/
  /*background-color: #235c4e;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
  position: absolute;
  top: 0;
  right: 0;
  width: 63%;
  height: 38%;
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
  /*width: 36%;*/
  /*height: 61%;*/
  /*float: left;*/
  /*background-color: #33ab8a;*/
  border:2px solid #53c59e;
  box-sizing: border-box;
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
  position: absolute;
  bottom: 0;
  left: 0;
  width: 36%;
  height: 61%;
}
.right-bottom{
  /*width: 63%;*/
  /*height: 62%;*/
  /*float: left;*/
  /*background-color: #50b299;*/
  /*布局线框颜色*/
  /*border:2px solid #53c59e*/
  /*border-bottom:2px dashed #55c97e*/
  position: absolute;
  bottom: 0;
  right: 0;
  width: 63%;
  height: 61%;
}
.promptBar{
  width: 30%;
  height: 100%;
  float: left;
  /*background-color: #33ab8a*/
}
.hangPart{
  width: 50%;
  height: 25%;
  float: left;
  margin-top: 15%;
  margin-left: 30%;
  border-radius: 20px;
  border:3px solid #53c59e
}
.hang{
  width: 50%;
  height: 100%;
  float: left;
  border-radius: 20px;
}
.untreatedPart{
  width: 50%;
  height: 25%;
  float: left;
  margin-top: 10%;
  margin-left: 30%;
  border-radius: 20px;
  border:3px solid #53c59e
}
.untreated{
  width: 50%;
  height: 100%;
  float: left;
  border-radius: 20px;
}
.Message{
  font-size: 10px;
  font-weight: bold;
  text-align: center;
  margin-top: 10%;
}
.chart{
  width: 90%;
  height: 100%;
  float: left;
  /*background-color: #34957a*/
}
.approveChart{
  width: 100%;
  height: 50%;
  float: left;
  /*background-color: #34957a*/
}
.orderChart{
  width: 100%;
  height: 50%;
  float: left;
  /*background-color: #37af8e*/
}
.enter{
  margin-top: 30%;
  width: 10%;
  height: 100%;
  float: left;
  /*background-color: #36ba99;*/
}
.icon_div{
  width: 20%;
  height: 10%;

  /*font-size: 50px;*/
/*  color: #0c805f;*/
/*  width:60px;*/
/*  float: left;*/
/*  height:50px;*/
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
</style>
