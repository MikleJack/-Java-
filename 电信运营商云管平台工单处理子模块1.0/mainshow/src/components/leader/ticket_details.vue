<template>
  <el-dialog title="工单详情" :visible.sync="this.$store.state.pendtickets_dialogTableVisible" width="80%" :before-close="handleClose">

  <div class="page">
    <div class="page_body">
      <!--      申请人信息展示-->
      <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px">
        <div class="page_title">申请人信息</div>
        <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>
        <el-descriptions class="margin-top":column="3" :size="size">
          <el-descriptions-item label="申请人姓名">{{name}}</el-descriptions-item>
          <el-descriptions-item label="工号">{{workNum}}</el-descriptions-item>
          <el-descriptions-item label="所在部门编号">{{depNum}}</el-descriptions-item>
          <el-descriptions-item label="所在部门名称">{{depName}}</el-descriptions-item>
          <el-descriptions-item label="电话号码">{{phone}}</el-descriptions-item>
        </el-descriptions>
      </div>
      <!--      显示工单基础信息-->
      <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
        <div class="page_title">工单信息</div>

        <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>
        <el-descriptions class="margin-top" :column="3" :size="size">
          <el-descriptions-item label="工单编号">{{workOrderNum}}</el-descriptions-item>
          <el-descriptions-item label="工单标题">{{workOrderName}}</el-descriptions-item>
          <el-descriptions-item label="工单类型">{{workType}}</el-descriptions-item>
          <el-descriptions-item label="工单申请时间">{{applyTime}}</el-descriptions-item>
          <el-descriptions-item label="工单到期时间">{{expireTime}}</el-descriptions-item>
        </el-descriptions>
        <!--      申请理由-->
        <el-descriptions class="margin-top" :column="3" :size="size">
          <el-descriptions-item label="申请理由"></el-descriptions-item>
        </el-descriptions>
        <div class="reason_contect" style="border: rgba(0,0,0,0.25) solid 1px" >{{reasonContect}}</div>
      </div>
      <!--      物理机资源信息展示-->
      <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
        <div class="page_title">物理机资源信息</div>

        <el-table
          :data="phyCom"
          border>
<!--          :summary-method="getSum"-->
<!--          show-summary-->

          <el-table-column
            type="index"
            label="序号"
            width="100">
          </el-table-column>
          <el-table-column
            prop="cpuCore"
            label="CPU核数/个"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="ram"
            label="内存/G">
          </el-table-column>
          <el-table-column
            prop="storage"
            label="存储大小/G">
          </el-table-column>
          <el-table-column
            prop="price"
            label="单价 元/月">
          </el-table-column>
        </el-table>
      </div>

      <!--      虚拟机资源信息展示-->
      <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
        <div class="page_title">虚拟机资源信息</div>
        <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>
        <el-table
          :data="virtualCom"
          border
        ><el-table-column
          prop="description"
          label="规格族"
          width="auto">
        </el-table-column>
          <el-table-column
            prop="cpuCore"
            label="CPU核数/个"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="ram"
            label="内存/G">
          </el-table-column>
          <el-table-column
            prop="processorFrequency"
            label="处理机主频/GHz"
          width="150px">
          </el-table-column>
          <el-table-column
            prop="processorModel"
            label="处理器型号">
          </el-table-column>
          <el-table-column
          prop="os"
          label="操作系统">
        </el-table-column>
          <el-table-column
            prop="perprice"
            label="单价 元/月">
          </el-table-column>
          <el-table-column
            prop="storage"
            label="硬盘大小/G">
          </el-table-column>
          <el-table-column
            prop="quantity"
            label="数量">
          </el-table-column>

        </el-table>
      </div>
      <!--      部门预算利用情况展示-->
      <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px;height: 250px;text-align: center">
        <div class="page_title">预算使用情况</div>
        <el-row :gutter="50">

          <!--              部门已使用预算/部门总预算情况展示-->
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <div class="processColumn">
                <el-progress type="circle" class="left_progress"
                             :stroke-width="15"
                             :percentage="depUsedDivisionDepTotal()"
                             :color="customColorMethod">
                </el-progress>
                <el-descriptions label-style style="margin-left: 40px" :column="1">
                  <el-descriptions-item label="部门总预算">{{ depTotalBudget }}</el-descriptions-item>
                  <el-descriptions-item label="部门已用预算">{{ depUsedBudget }}</el-descriptions-item>
                </el-descriptions>
              </div>
            </div>
          </el-col>

          <!--        工单使用预算/部门剩余预算-->
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <div class="processColumn">
                <el-progress type="circle" class="left_progress"
                             :stroke-width="15"
                             :percentage="OrderBudgetDivisionDepSurplus()"
                             :color="customColorMethod">
                </el-progress>
                <el-descriptions label-style style="margin-left: 40px" :column="1">
                  <el-descriptions-item label="工单预算">{{ order_budget }}</el-descriptions-item>
                  <el-descriptions-item label="部门剩余预算">{{ surplus_budget }}</el-descriptions-item>
                </el-descriptions>
              </div>
            </div>
          </el-col>

          <!--        工单使用预算 和 工单资源利用率-->
          <el-col :span="8">
            <div class="grid-content bg-purple">
              <div class="processColumn">
                <el-progress type="circle" class="left_progress"
                             :stroke-width="15"
                             :percentage="staffUsage"
                             :color="customColorMethod">
                </el-progress>
                <el-descriptions label-style style="margin-left: 40px" :column="1">
                  <el-descriptions-item label="员工历史资源利用率">{{ staffUsage }}</el-descriptions-item>
                  <el-descriptions-item label="工单预算">{{ order_budget }}</el-descriptions-item>
                </el-descriptions>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>

      <!--显示流转过程-->
      <div class="frame" style=" border: rgba(82,182,154,0.25) solid 3px ">
        <div class="page_title">流转过程</div>
        <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>

        <div class="resoure_usage">
          <el-table
            :data="informData"
            :default-sort = "{prop: 'dealDate', order: 'ascending'}"
            border>
            <el-table-column
              type="index"
              label="序号"
              width="50">
            </el-table-column>
            <el-table-column
              prop="dealNum"
              label="处理人工号"
              width="auto">
            </el-table-column>
            <el-table-column
              prop="name"
              label="处理人姓名"
              width="auto">
            </el-table-column>
            <el-table-column
              prop="operationType"
              label="操作类型"
              width="auto">
            </el-table-column>
            <el-table-column
              prop="dealDate"
              label="处理日期"
              width="auto">
            </el-table-column>
            <el-table-column
              prop="dealComment"
              label="批注"
              width="auto">
            </el-table-column>
          </el-table>
        </div>
      </div>

      <div class="note_title" style="margin-top: 4%" v-if="show">批注</div>
      <div class="note" v-if="show">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入批注"
          v-model="note">
        </el-input>
      </div>
      <div class="page_bottom" v-if="show">
        <el-button class="button" @click="handleDownLoad" v-if="showFile">附件下载</el-button>
        <el-button class="button"  @click="pass">审批通过</el-button>
        <el-button v-if="hasHangup" @click="hangup">挂起</el-button>
        <el-button @click="nopass">审批不通过</el-button>
      </div>
    </div>
  </div>
  </el-dialog>
</template>

<script>
export default {
  name: "ticket_details",
  data() {
    return {
      //附件名称
      file:'',
      //附件下载按钮可见
      showFile:true,
      //是否显示挂起按钮
      hasHangup:false,
      labelPosition: 'left',
      //字体大小
      size: '',
      //workNum工号
      workNum: '',
      //name人员姓名
      name: '',
      //所在部门编号
      depNum: '',
      //所在部门名称
      depName: '',
      //电话号码
      phone: '',
      //工单编号
      workOrderNum: '',
      //工单标题
      workOrderName: '',
      //工单类型
      workType: '',
      //申请时间
      applyTime: '',
      //到期时间
      expireTime:'',
      //reason可变，为申请工单时，为申请理由，为回退工单时，为回退理由，
      reasonContect: '',
      //  输入的批注内容
      note: '',

      //部门预算使用情况
      depTotalBudget: '',
      depUsedBudget: '',
      order_budget: '',

      //工单资源利用率
      resourceUsage: '',
      //员工历史资源利用率
      staffUsage: '',

      //物理机资源数据
      phyCom: [],
      virtualCom: [],
      //流传过程数据
      informData: [],

      flowProcess:{
        workOrderNum:'',
        dealNum:'',
        operationType:'申请工单',
        dealDate:'',
        dealComment:''
      },
      passable:''

    };
  },
  mounted() {
    if(sessionStorage.getItem("level")==="3"){
      this.hasHangup=true;
    };
  },
  props:["show"],
  methods: {
    //部门已用预算/部门总预算进度条
    depUsedDivisionDepTotal(){
      return parseFloat(100*this.depUsedBudget/this.depTotalBudget).toFixed(2);
    },
    //工单预算/部门剩余预算进度条
    OrderBudgetDivisionDepSurplus(){
      this.surplus_budget = this.depTotalBudget-this.depUsedBudget;
      return parseFloat(100*this.order_budget/this.surplus_budget).toFixed(2);
    },
    customColorMethod(percentage) {
      if (percentage < 90) {
        return '#52b69a';
      } else {
        return 'rgba(239,125,10,0.7)';
      }
    },

    handleDownLoad() {
     if(this.file===''){
       this.$message({
         message:"当前工单无附件！",
         type:'warning',
         center:true
       })
     }
     else {
       window.location.href = `https://101.200.200.1:8083/file/download?fileName=` + this.file;
     }
    },
    setFlow(){
      this.flowProcess.workOrderNum=this.workOrderNum;
      this.flowProcess.dealComment=this.note;
      this.getDateFunc();
      this.flowProcess.dealNum=sessionStorage.getItem("work_num");
    },
    pass(){
      this.setFlow();
      this.flowProcess.operationType="审批通过";
      this.$axios.post(this.$store.state.url+"/flowProcess/insert",{
        flowProcess:JSON.stringify(this.flowProcess)}).then((res)=>{
          if(res.data===true){
            if(sessionStorage.getItem("level")!=="3"){
              this.$axios.post(this.$store.state.url+"/pendtickets/oneExamine",{
                workOrderNum:this.workOrderNum,
                firstWorkerNum: sessionStorage.getItem("work_num"),
                state:"审批通过"
              }).then((res)=>{
                this.$store.state.pendtickets_dialogTableVisible = false;
                //   审批通过提示框
                this.$message({
                  message: '审批通过',
                  type: 'success',
                  center: true
                });
              })
            }
            else {
              this.$axios.post(this.$store.state.url+"/pendtickets/towExamine",{
                workOrderNum:this.workOrderNum,
                secondNum: sessionStorage.getItem("work_num"),
                state:"审批通过",
              }).then((res)=>{
                this.$store.state.pendtickets_dialogTableVisible = false;
                //   审批通过提示框
                this.$message({
                  message: '审批通过',
                  type: 'success',
                  center: true
                });
              })
            }
          }
      })


    },
    nopass(){
      this.setFlow();
      this.flowProcess.operationType="审批不通过";
      this.$axios.post(this.$store.state.url+"/flowProcess/insert",{
        flowProcess:JSON.stringify(this.flowProcess)}).then((res)=>{
        if(res.data===true){
          if(sessionStorage.getItem("level")!=="3"){
            this.$axios.post(this.$store.state.url+"/pendtickets/oneExamine",{
              workOrderNum:this.workOrderNum,
              firstWorkerNum: sessionStorage.getItem("work_num"),
              state:"审批不通过"
            }).then((res)=>{
              this.$store.state.pendtickets_dialogTableVisible = false;
              //   审批通过提示框
              this.$message({
                message: '审批不通过',
                type: 'ture',
                center: true
              });
            })
          }
          else {
            this.$axios.post(this.$store.state.url+"/pendtickets/towExamine",{
              workOrderNum:this.workOrderNum,
              secondNum: sessionStorage.getItem("work_num"),
              state:"审批不通过",
            }).then((res)=>{
              this.$store.state.pendtickets_dialogTableVisible = false;
              //   审批通过提示框
              this.$message({
                message: '审批不通过',
                type: 'ture',
                center: true
              });
            })
          }
        }
      })
    },
    hangup(){
      this.setFlow();
      this.flowProcess.operationType="挂起";
      this.$axios.post(this.$store.state.url+"/flowProcess/insert",{
        flowProcess:JSON.stringify(this.flowProcess)}).then((res)=>{
        this.$axios.post(this.$store.state.url+"/pendtickets/towExamine",{
          workOrderNum:this.workOrderNum,
          secondNum: sessionStorage.getItem("work_num"),
          state:"挂起"
        });
        this.$store.state.pendtickets_dialogTableVisible = false;
        this.$message({
          message: '挂起成功！',
          type: 'success',
          center: true
        });
      })
    },
    //刷新数据
    refresh(){
      //workNum工号
      this.workNum =  '',
        //name人员姓名
        this.name= '',
        //所在部门编号
        this.depNum= '',
        //所在部门名称
        this.depName='',
        //电话号码
        this.phone= '',
        //工单编号
        this.workOrderNum= '',
        //工单标题
        this.workOrderName='',
        //工单类型
        this.workType= '',
        //申请时间
        this.applyTime= '',
        //到期时间
        this.expireTime='',
        //reason可变，为申请工单时，为申请理由，为回退工单时，为回退理由，
        this.reasonContect= '',
        //  输入的批注内容
        this.note='',


        //部门总预算利用情况
        this.depUsedBudget='',
        //部门总预算
        this.depTotalBudget='',
        //部门剩余预算
        this.surplus_budget='',
        //工单预算
        this.order_budget='',
        this.staffUsage='',

        //物理机资源数据
        this.phyCom=[],
        this.virtualCom= [],
        //流传过程数据
        this.informData=[],

        this.flowProcess={
        workOrderNum:'',
          dealNum:'',
          operationType:'',
          dealDate:'',
          dealComment:''
      },
      this.passable=''
    },

    //关闭详情页
    handleClose(){
      this.$store.state.pendtickets_dialogTableVisible = false;
      this.refresh();
    },

    getDateFunc() {
      var date = new Date();
      var seperator1 = "-";
      var seperator2 = ":";
      //以下代码依次是获取当前时间的年月日时分秒
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      var minute = date.getMinutes();
      var hour = date.getHours();
      var second = date.getSeconds();
      //固定时间格式
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
      }
      if (minute >= 0 && minute <= 9) {
        minute = "0" + minute;
      }
      if (second >= 0 && second <= 9) {
        second = "0" + second;
      }
      this.flowProcess.dealDate =  year + seperator1 + month + seperator1 + strDate
        + " " + hour + seperator2 + minute + seperator2 + second;
    },

    autoGetAllDetail(workOrderNum, workerNum) {
      setTimeout(()=>{
        this.$axios.get(this.$store.state.url+"/usedBudget/getStaffUsage?workerNum=" + workerNum ).then((res)=>{
          this.staffUsage = parseFloat(res.data).toFixed(2);
        })
      },500)
      this.$axios.get(this.$store.state.url+"/pendtickets/queryWorkOrderDetailTop?workOrderNum="
        +workOrderNum).then((res)=>{
          //个人信息
          this.workNum = res.data.workerNum;
          this.name = res.data.name;
          this.depNum = res.data.depNum;
          this.depName = res.data.depName;
          this.phone = res.data.phone;
          //工单信息
          this.workOrderNum = workOrderNum;
          this.workOrderName  = res.data.workOrderName;
          this.workType = res.data.workOrderType;
          this.expireTime = res.data.expirationTime;
          this.reasonContect = res.data.reason;
          this.file=res.data.file;
          if(this.file==="")
            this.showFile=false;
          //获取工单使用预算
          this.order_budget = res.data.price;
          //获取部门总预算
          this.$axios.get(this.$store.state.url+"/depart/getDepBudget?depNum=" + this.depNum).then((res)=>{
            this.depTotalBudget = res.data;
          });
          //获取部门已使用预算
          this.$axios.get(this.$store.state.url+"/usedBudget/getUsedBudget?id=" + this.depNum).then((res)=>{
            this.depUsedBudget = res.data.depUsedBudget;
          });
      });
      //查找工单申请的物理机资源
      this.$axios.get(this.$store.state.url+"/pendtickets/getOrderCom?workOrderNum="
      +workOrderNum).then((res)=>{
        this.phyCom = res.data;
      });
      //查找工单申请的虚拟机资源
      this.$axios.get(this.$store.state.url+"/pendtickets/getOrderVm?workOrderNum="
        +workOrderNum).then((res)=>{
          this.virtualCom = res.data;
      });
    //通过工单编号查找流转过程
      this.$axios.get(this.$store.state.url+"/flowProcess/selectByWorkOrderNum?workOrderNum="
      +workOrderNum).then((res)=>{
        this.informData = res.data;
      });
    //  通过工单号查找申请时间
      this.$axios.get(this.$store.state.url +"/pendtickets/getordertime?workOrderNum="+
      workOrderNum).then((res)=>{
        this.applyTime = res.data;
      })
    }

},

}
</script>

<style>
.page{
  position: relative;
  width: 90%;
  height: auto;
  left: 10%;
  top: 0;
}
.page_body{
  position: relative;
  width: 90%;
  height: 100%;
  left: 5%;
}
.reason_contect{
  width: 80%;
  height: 80px;
  /*background: #409EFF;*/
  margin-left: 10%;
  margin-bottom: 1%;

}
.note{
  width: 100%;
  height: auto;
  margin-left: -10%;
  margin-right: -20%;
}
.page_bottom{
  width: 100%;
  height: 100px;
  bottom: 0;
  /*background: #888888;*/
  text-align: center;
  line-height: 100px;
  margin-left: -10%;
}
.margin-top{
  margin-left: 10%;
}
.page_title{
  font-size: large;
  text-align: center;
  margin-bottom:20px;
  font-weight:bolder;
  color: #0c805f;
}
.note_title{
  margin-left: -20%;
  text-align: center;
  margin-bottom:20px;
  font-weight:bolder;
  color: #0c805f;
}

.frame{
  margin-bottom: 3%;
  margin-right: 10%;
  margin-left:-10%;
  align:center,
}
.button{
  color:white;
  background-color: #52b69a
}
</style>
