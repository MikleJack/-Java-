<template>
  <el-dialog
    title="延期申请"
    :visible.sync="$store.state.staffAllOrder_DetailDialogVisible"
    width="70%"
    :before-close="handleClose">
    <div class="page" >
      <div class="page_body">
        <!--      申请人信息展示-->
        <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px">
          <div class="page_title">申请人信息</div>
          <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>
          <el-descriptions class="margin-top":column="3" :size="size">
            <el-descriptions-item label="申请人姓名">{{singleInformForm.name}}</el-descriptions-item>
            <el-descriptions-item label="员工编号">{{singleInformForm.workerNum}}</el-descriptions-item>
            <el-descriptions-item label="所在部门编号">{{singleInformForm.depNum}}</el-descriptions-item>
            <el-descriptions-item label="所在部门名称">{{singleInformForm.depName}}</el-descriptions-item>
            <el-descriptions-item label="部门级别">{{singleInformForm.depLevel}}</el-descriptions-item>
            <el-descriptions-item label="电话号码">{{singleInformForm.phone}}</el-descriptions-item>
          </el-descriptions>
        </div>
        <!--      显示工单基础信息-->
        <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
          <div class="page_title">工单信息</div>

          <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>
          <el-descriptions class="margin-top" :column="3" :size="size">
            <el-descriptions-item label="工单编号">{{singleInformForm.workOrderNum}}</el-descriptions-item>
            <el-descriptions-item label="工单标题">{{singleInformForm.workOrderName}}</el-descriptions-item>
            <el-descriptions-item label="工单类型">{{singleInformForm.workOrderType}}</el-descriptions-item>
            <el-descriptions-item label="工单申请时间">{{beginTime}}</el-descriptions-item>
            <el-descriptions-item label="工单到期时间">{{singleInformForm.expirationTime}}</el-descriptions-item>
          </el-descriptions>
          <!--      申请理由-->
          <el-descriptions class="margin-top" :column="3" :size="size">
            <el-descriptions-item label="申请理由"></el-descriptions-item>
          </el-descriptions>
          <div class="reason_contect" style="border: rgba(0,0,0,0.25) solid 1px" >{{singleInformForm.reason}}</div>
        </div>


        <!--      物理机资源信息展示-->
        <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
          <div class="page_title">物理机资源信息</div>

          <el-table
            :data="allocatedCom"
            border>
            <!--          :summary-method="getSum"-->
            <!--          show-summary-->
            <el-table-column
              type="index"
              label="序号"
              width="auto">
            </el-table-column>
            <el-table-column
              prop="comNum"
              label="物理机编号"
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
            :data="allocatedVm"
            border
          ><el-table-column
            type="index"
            label="序号"
            width="auto">
          </el-table-column>
            <el-table-column
            prop="workOrderNum"
            label="虚拟机编号"
            width="auto">
          </el-table-column>
            <el-table-column
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
              prop="storage"
              label="硬盘大小/G">
            </el-table-column>
            <el-table-column
              prop="processorFrequency"
              label="处理机主频/GHz"
              width="auto">
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
              prop="price"
              label="单价 元/月">
            </el-table-column>
          </el-table>
        </div>

        <!--      部门预算利用情况展示-->
        <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px;height: 250px;text-align: center">
          <div class="page_title">预算使用情况</div>
          <el-row :gutter="50">

<!--              部门已使用预算/部门总预算情况展示-->
            <el-col :span="7">
              <div class="grid-content bg-purple">
                <div class="processColumn">
                  <el-progress type="circle" class="left_progress"
                               :stroke-width="15"
                               :percentage="depUsedDivisionDepTotal()"
                               :color="customColorMethod">
                  </el-progress>
                  <el-descriptions :column="1">
                    <el-descriptions-item label="部门总预算">{{ depTotalBudget }}</el-descriptions-item>
                    <el-descriptions-item label="部门已用预算">{{ depUsedBudget }}</el-descriptions-item>
                  </el-descriptions>
                </div>
              </div>
            </el-col>

<!--        工单使用预算/部门剩余预算-->
            <el-col :span="7">
              <div class="grid-content bg-purple">
                <div class="processColumn">
                  <el-progress type="circle" class="left_progress"
                               :stroke-width="15"
                               :percentage="OrderBudgetDivisionDepSurplus()"
                               :color="customColorMethod">
                  </el-progress>
                  <el-descriptions :column="1">
                    <el-descriptions-item label="工单预算">{{ order_budget }}</el-descriptions-item>
                    <el-descriptions-item label="部门剩余预算">{{ surplus_budget }}</el-descriptions-item>
                  </el-descriptions>
                </div>
              </div>
            </el-col>

<!--        工单使用预算 和 工单资源利用率-->
            <el-col :span="7">
              <div class="grid-content bg-purple">
                <div class="processColumn">
                  <el-progress type="circle" class="left_progress"
                               :stroke-width="15"
                               :percentage="resourceUsage"
                               :color="customColorMethod">
                  </el-progress>
                  <el-descriptions :column="1">
                    <el-descriptions-item label="工单预算">{{ order_budget }}</el-descriptions-item>
                    <el-descriptions-item label="工单资源利用率">{{ resourceUsage }}</el-descriptions-item>
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
              :data="flowProcess"
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
      </div>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: "StaffAllOrderDetail",
  data() {
    return {
      //上部分个人信息和部门信息
      singleInformForm: [],
      //分配的物理机资源
      allocatedCom: [],
      //分配的虚拟机资源
      allocatedVm: [],
      //流转过程
      flowProcess: [],

      //部门预算使用情况
      depTotalBudget: '',
      depUsedBudget: '',
      order_budget: '',

      //工单资源利用率
      resourceUsage: '',

      //工单开始时间
      beginTime: '',

      labelPosition: 'left',
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
      let temp_per=parseFloat(this.order_budget/this.surplus_budget).toFixed(2);
      return 100*temp_per;
    },
    customColorMethod(percentage) {
      if (percentage < 90) {
        return '#52b69a';

      } else {
        return 'rgba(239,125,10,0.7)';
      }
    },
    autoGetAllDetail(workOrderNum) {
      //上半部分个人信息和工单获取
      this.$axios.get('http://localhost:8084/adminSearchOrder/queryWorkOrderDetailTop?workOrderNum=' + workOrderNum).then((res)=>{
        this.singleInformForm = res.data;
        this.order_budget = res.data.price;
        //部门预算使用情况获取
        this.$axios.get('http://localhost:8084/depart/getDepBudget?depNum=' + res.data.depNum).then((res)=>{
          this.depTotalBudget = res.data;
        });
        this.$axios.get('http://localhost:8084/usedBudget/getUsedBudget?id=' + res.data.depNum).then((res)=>{
          this.depUsedBudget = res.data.depUsedBudget;
        });
      });

      //物理机使用情况获取
      this.$axios.get('http://localhost:8084/staffAllTickets/allocatedCom?workOrderNum=' + workOrderNum).then((res)=>{
        this.allocatedCom = res.data;
      });

      //虚拟机使用情况获取
      this.$axios.get('http://localhost:8084/staffAllTickets/allocatedVir?workOrderNum=' + workOrderNum).then((res)=>{
        this.allocatedVm = res.data;
      });

      //流转过程情况获取
      this.$axios.get('http://localhost:8084/flowProcess/selectByWorkOrderNum?workOrderNum=' + workOrderNum).then((res)=>{
        this.flowProcess = res.data;
      });

      //通过工单编号得到开始时间
      this.$axios.get('http://localhost:8084/staffAllTickets/queryBeginAndEndTime?workOrderNum=' + workOrderNum).then((res)=>{
        this.beginTime = res.data.dealDate;
      });

      //通过工单编号得到资源利用情况
      this.$axios.get('http://localhost:8084/staffAllTickets/queryResourceUsage?workOrderNum=' + workOrderNum).then((res)=>{
        this.resourceUsage = res.data.resUtilization;
      });
    },

    handleClose(){
      this.$store.state.staffAllOrder_DetailDialogVisible = false;
      this.refresh();
    },
    //刷新页面数据
    refresh(){
      this.singleInformForm = [];
      this.allocatedCom = [];
      this.allocatedVm = [];
      this.flowProcess = [];
      this.depUsedBudget = '';
      this.order_budget = '';
      this.depTotalBudget = '';
      this.resourceUsage = '';
      this.beginTime = '';
    }

  },

}
</script>

<style scoped>
.page{
  position: relative;
  width: 100%;
  height: auto;
  left: 0;
  top: 0;
}
.page_body{
  position: relative;
  width: 90%;
  height: 100%;
  left: 10%;
}
.reason_contect{
  width: 80%;
  height: 80px;
  margin-left: 10%;
  margin-bottom: 1%;

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

.frame{
  margin-bottom: 3%;
  margin-right: 10%;
  margin-left:-10%;
  align:center,
}

/*资源使用情况，圆形进度条*/
.processColumn{
  position: relative;
  margin-left: 40%;
  float: left;
}

</style>
