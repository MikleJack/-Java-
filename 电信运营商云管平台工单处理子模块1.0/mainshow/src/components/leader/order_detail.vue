<template>
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
          border
        >
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
            prop="allprice"
            label="总价/元">
          </el-table-column>
        </el-table>
      </div>
      <!--      部门资源利用情况展示-->
<!--      <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px;height: 250px">-->
<!--        <div class="page_title" >部门预算利用情况</div>-->
<!--        <div class="total_progress">-->
<!--          <br>-->
<!--          <el-progress type="circle" class="left_progress"-->
<!--                       :stroke-width="15"-->
<!--                       :percentage="total_percentage()"-->
<!--                       :color="customColorMethod">-->
<!--          </el-progress>-->
<!--        </div>-->
<!--        <div class="total_description">-->
<!--          <br><br>部门总预算：&nbsp;{{total_budget}}元<br><br>-->
<!--          已使用预算：&nbsp;{{used_budget}}元-->
<!--        </div>-->
<!--        <div class="progress">-->
<!--          <br>-->
<!--          <el-progress type="circle"-->
<!--                       class="right_progress"-->
<!--                       :stroke-width="15"-->
<!--                       :percentage="percentage()"-->
<!--                       :color="customColorMethod">-->
<!--          </el-progress>-->
<!--        </div>-->
<!--        <div class="description">-->
<!--          <br><br>部门剩余预算：&nbsp;{{surplus_budget}}元<br><br>-->
<!--          工单使用预算：&nbsp;{{order_budget}}元-->
<!--        </div>-->

<!--      </div>-->

      <!--显示流转过程-->
      <div class="frame" style=" border: rgba(82,182,154,0.25) solid 3px ">
        <div class="page_title">流转过程</div>
        <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>

        <div class="resoure_usage">
          <el-table
            :data="informData"
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
</template>

<script>
export default {
  name: "ticket_details",
  data() {
    return {
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
      workOrderName: '第一次的默认标题/',
      //工单类型
      workType: '',
      //申请时间
      applyTime: '2023-5-3 00:00:00',
      //到期时间
      expireTime:'',
      //reason可变，为申请工单时，为申请理由，为回退工单时，为回退理由，
      reasonContect: '',
      //部门总预算利用情况
      used_budget:'1000',
      //部门总预算
      total_budget:'2000',
      //部门剩余预算
      surplus_budget:'',
      //工单预算
      order_budget:'300',
      //物理机资源数据
      phyCom: [],
      virtualCom: [],
      //流传过程数据
      informData: [],

    };
  },
  methods: {
    //部门已用预算/部门总预算进度条
    total_percentage(){
      return 100*this.used_budget/this.total_budget;
    },
    //工单预算/部门剩余预算进度条
    percentage(){
      this.surplus_budget=this.total_budget-this.used_budget;
      return 100*this.order_budget/this.surplus_budget;
    },
    customColorMethod(percentage) {
      if (percentage < 90) {
        return '#52b69a';

      } else {
        return 'rgba(250,116,14,0.55)';
      }
    },
    autoGetAllDetail(workOrderNum) {
      this.$axios.get("http://localhost:8084/leader/queryWorkOrderDetailTop?workOrderNum="
        + workOrderNum).then((res)=>{
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
      });
      this.$axios.get("http://localhost:8084/leader/getOrderCom?workOrderNum="
      +workOrderNum).then((res)=>{
        this.phyCom = res.data;
      });
      this.$axios.get("http://localhost:8084/leader/getOrderVm?workOrderNum="
      +workOrderNum).then((res)=>{
        this.virtualCom = res.data;
      });
      //通过工单编号查找流转过程
      this.$axios.get("http://localhost:8084/flowProcess/selectByWorkOrderNum?workOrederNum="
        +workOrderNum).then((res)=>{
        this.informData = res.data;
      });
    }
  }
}
</script>

<style>
.page{
  position: relative;
  width: 100%;
  height: auto;
  left: 10%;
  top: 0;
  /*background-color: #409EFF;*/
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

  margin-left: 10%;
  margin-bottom: 1%;

}
.resoure_usage{
  width: 100%;
  height: auto;

}
.margin-top{
  margin-left: 10%;
}
.page_title{
  text-align: center;
  margin-bottom:20px;
  font-weight:bolder;
  color: #0c805f;
}
.frame{
  margin-bottom: 3%;
}
.total_progress{
  width:25%;
  float: left;
  height: 200px;
  text-align: center;
}
.total_description{
  width: 25%;
  float: left;
  height: 200px;
  font-size: larger;
  font-weight: bolder;
}
.progress{
  width: 25%;
  float: left;
  height: 200px;
}
.description{
  width: 25%;
  float: left;
  height: 200px;
  font-size: larger;
  font-weight: bolder;
}
</style>

