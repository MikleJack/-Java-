<template>
  <div class="staffPortalchange">
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
          <el-tag class="power" type="success">普通员工</el-tag>
        </div>
        <!--姓名及所属部门-->
        <div class="nameInf">
          <p>{{workerInform.name}}</p>
        </div>
        <div class="depInf">
          <p>{{workerInform.depName}}</p>
        </div>
        <div class="workerInf">
          <p>工号 ：{{workerInform.workNum}}</p>
          <p>电话号码：{{workerInform.phone}}</p>
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
      <!--预算-->
      <div class="bud">
        <!--饼图：预算使用情况饼图-->
        <div class="budPie">
          <el-progress :percentage="depBudget" :color="budCustomcolors"
                       style="margin-left: 26%; margin-top: 20%"
                       type="dashboard" stroke-width="15" width="120"></el-progress>
        </div>
        <div class="budMessage">
          <p style="text-align: center">部门预算使用情况</p>
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
      <!--快捷入口具体按钮-->
      <div class="enter" >
        <el-row>
          <el-button type="success" icon="el-icon-search" size="medium " style="display:block;margin: 40% auto 0;" circle ></el-button>
          <el-button type="primary" icon="el-icon-edit" size="medium " style="display:block;margin:0 auto" circle></el-button>
          <el-button type="warning" icon="el-icon-star-off" size="medium " style="display:block;margin:0 auto" circle></el-button>
        </el-row>
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
      <div class="round-i1">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729已被审批通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729审批未通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729即将到期 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 您的账号已被解除锁定 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 请尽快完善个人信息 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729已被审批通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729审批未通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>

      <div class="pagination">
        <el-pagination
          layout="prev, pager, next"
          :total="1000"
          style="color: #0c805f">
        </el-pagination>
      </div>
    </div>
    <!--右下方-->
    <div class="right-bottom">
      <div class="chart">
        <!--部门预算折线图-->
        <div class="budChart" id="budmain"></div>
        <!--提交工单统计折线图-->
        <div class="orderChart" id="order"></div>
      </div>
      <!--展示栏-->
      <div class="promptBar">
        <!--即将到期栏-->
        <div class="duePart">
          <div class="due">
            <el-tag type="success" effect="dark" color="#349165"
                    style="width: 90%;height: 85%;font-size: large;
                    text-align: center;line-height: 40px;
                    margin-top: 10%;border-radius: 50%">期</el-tag>
          </div>
          <div class="Message">
            <p>即将到期</p>
            <p>{{due_order}}</p>
          </div>
        </div>
        <div class="onlinePart">
          <div class="online">
            <el-tag type="success" effect="dark" color="#349165"
                    style="width: 90%;height: 85%;font-size: large;
                    text-align: center;line-height: 40px;
                    margin-top: 10%;border-radius: 50%">在</el-tag>
          </div>
          <div class="Message">
            <p>在线工单</p>
            <p>{{online_order}}</p>
          </div>
        </div>
        <div class="untreatedPart">
          <div class="untreated">
            <el-tag type="success" effect="dark" color="#349165"
                    style="width: 90%;height: 85%;font-size: large;
                    text-align: center;line-height: 40px;
                    margin-top: 10%;border-radius: 50%">待</el-tag>
          </div>
          <div class="Message">
            <p>待审工单</p>
            <p>{{untreated_order}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "staffPortal",
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

  },

// 表格数据
  data() {
    return {
      // 全部物理机资源利用率
      total_Phyutilization:'76.29',
      // 全部虚拟机资源利用率
      total_Virutilization:'35.83',
      // 部门预算使用率
      total_budget:0,
      used_budget:0,
      depBudget:0,
      fits: ['cover'],
      url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F40%2Fc9%2F53%2F40c9533e47b9ce0945a2030f9320b80e.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644379289&t=487f84fb7f4d9252f4fa8ef334c39618',

      workerInform:{},
      due_order:'5',
      online_order:'12',
      untreated_order:'3',


    }

  },
  mounted(){
    //获取左上角用户信息
    this.$axios.get("http://localhost:8084/staffHome/queryPersonInformById?workerNum=" + sessionStorage.getItem("work_num")).then((res)=>{
      this.workerInform = res.data;
      let depNum = res.data.depNum;
      //获取部门总预算
      this.$axios.get("http://localhost:8084/depart/getDepBudget?depNum=" + depNum).then((res)=>{
        this.total_budget = res.data;
        //获取部门已使用预算
        this.$axios.get("http://localhost:8084/usedBudget/getUsedBudget?id=" + depNum).then((res)=>{
          this.used_budget = res.data.depUsedBudget;
          this.depBudget=parseFloat(100*this.used_budget/this.total_budget).toFixed(2);
        });
      });


    });


    var chartDom = document.getElementById('budmain');
    var budChart = echarts.init(chartDom);
    var budOption;
    budOption = {
      // width:'300px',
      // height:'500px',
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
.staffPortalchange{
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
  /*width: 36%;*/
  /*height: 61%;*/
  /*float: left;*/
  /*background-color: #33ab8a;*/
  border:2px solid #53c59e;
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
  height: 62%;
}
.chart{
  width: 85%;
  height: 100%;
  float: left;
  /*background-color: #34957a*/
}
.budChart{
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
.promptBar{
  width: 13%;
  height: 100%;
  float: left;
  /*background-color: #33ab8a*/
}
.duePart{
  width: 95%;
  height: 13%;
  float: left;
  margin-top: 30%;
  margin-left: 8%;
  border-radius: 20px;
  border:3px solid #53c59e
}
.due{
  width: 50%;
  height: 100%;
  float: left;
  border-radius: 20px;
}
.onlinePart{
  width: 95%;
  height: 13%;
  float: left;
  margin-top: 25%;
  margin-left: 8%;
  border-radius: 20px;
  border:3px solid #53c59e
}
.online{
  width: 50%;
  height: 100%;
  float: left;
  border-radius: 20px;
}
.untreatedPart{
  width: 95%;
  height: 13%;
  float: left;
  margin-top: 25%;
  margin-left: 8%;
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
  font-size: 5px;
  font-weight: bold;
  text-align: center;
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
  /*width: 10px;*/
  margin-top: 10%;
  margin-left: 3%;
}
.round-i{
  height: 10px;
  /*width: 10px;*/
  margin-top: 30px;
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
  height: fit-content;
  margin-bottom: 1px;
  margin-top: 5%;
}
</style>
