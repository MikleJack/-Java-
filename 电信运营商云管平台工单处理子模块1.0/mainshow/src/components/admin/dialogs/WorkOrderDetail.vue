<template>
<!--  管理员工单查询界面，详情按钮-->
  <el-dialog
    title="工单详情信息"
    :visible.sync="$store.state.workOrderDetailVisibleDetail"
    width="70%"
    append-to-body=“true”>

    <!--      申请人信息展示-->
    <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px">
      <div class="page_title">申请人信息</div>
      <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>
      <el-descriptions class="margin-top":column="3" :size="size">
        <el-descriptions-item label="申请人姓名">{{tableDetailTop.name}}</el-descriptions-item>
        <el-descriptions-item label="工号">{{tableDetailTop.workerNum}}</el-descriptions-item>
        <el-descriptions-item label="所在部门编号">{{tableDetailTop.depNum}}</el-descriptions-item>
        <el-descriptions-item label="所在部门名称">{{tableDetailTop.depName}}</el-descriptions-item>
        <el-descriptions-item label="电话号码">{{tableDetailTop.phone}}</el-descriptions-item>
      </el-descriptions>
    </div>

    <!--      显示工单基础信息-->
    <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
      <div class="page_title">工单信息</div>

      <el-descriptions class="margin-top" title="" :column="3" :size="size" border></el-descriptions>
      <el-descriptions class="margin-top" :column="2" :size="size">
        <el-descriptions-item label="工单编号">{{tableDetailTop.workOrderNum}}</el-descriptions-item>
        <el-descriptions-item label="工单标题">{{tableDetailTop.workOrderName}}</el-descriptions-item>
        <el-descriptions-item label="工单类型">{{tableDetailTop.workOrderType}}</el-descriptions-item>
        <el-descriptions-item label="工单到期时间">{{tableDetailTop.expirationTime}}</el-descriptions-item>
      </el-descriptions>
      <!--      申请理由-->
      <el-descriptions class="margin-top" :column="3" :size="size">
        <el-descriptions-item label="申请理由"></el-descriptions-item>
      </el-descriptions>
      <div class="reason_contect" style="border: rgba(0,0,0,0.25) solid 1px" >{{tableDetailTop.reason}}</div>
    </div>

    <!--      物理机资源信息展示-->
    <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
      <div class="page_title">物理机资源信息</div>
      <el-table
        :data="OrderCom"
        border
        :summary-method="getSum"
        show-summary
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
        :data="OrderVm"
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
            prop="dealName"
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

    <!--      部门资源利用情况展示-->
    <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
      <div class="page_title" >部门预算利用情况</div>
      <!--        部门已用预算/部门总预算-->
      <el-progress :text-inside="true" :stroke-width="25" :format="format" :percentage="total_percentage()":color="customColorMethod"></el-progress>
      <!--        工单预算/部门剩余预算-->
      <el-progress :text-inside="true" :stroke-width="25" :format="format2" :percentage="percentage()"></el-progress>
    </div>



    <span slot="footer" class="dialog-footer">
    <el-button @click="$store.state.workOrderDetailVisibleDetail = false">关闭</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: "WorkOrderDetail",
  data(){
    return{
      tableDetailTop: [],
      OrderCom: [],
      OrderVm: [],
      formLabelWidth: '120px',
      //部门总预算利用情况
      used_budget:'1000',
      //部门总预算
      total_budget:0,
      //部门剩余预算
      surplus_budget:'',
      //工单预算
      order_budget:'300',
    }
  },
  methods:{
    //获取所有详情
    autoGetAllDetail(workOrderNum){
      //上半部分个人信息、工单信息和部门信息
      this.$axios.get('http://localhost:8084/adminSearchOrder/queryWorkOrderDetailTop?workOrderNum=' + workOrderNum).then((res)=>{
        this.tableDetailTop = res.data;
        this.$axios.get("http://localhost:8084/adminSearchOrder/getDepBudget?depNum="+res.data.depNum).then((budget)=>{
          this.total_budget = budget.data;
        })
      });
      this.$axios.get("http://localhost:8084/adminSearchOrder/getOrderCom?workOrderNum="+workOrderNum).then((res)=>{
        this.OrderCom = res.data;
      });
      this.$axios.get("http://localhost:8084/adminSearchOrder/getOrderVm?workOrderNum="+workOrderNum).then((res)=>{
        this.OrderVm = res.data;
      });
    },
    //物理机价钱求和
    getSum(param) {
//此处打印param可以看到有两项，一项是columns，一项是data，最后一列可以通过columns.length获取到。
      const {columns, data} = param
      const len = columns.length
      const sums = []
      columns.forEach((column, index) => {
        //如果是第一列，则最后一行展示为“总计”两个字
        if (index === 0) {
          sums[index] = '总计/元'
          //如果是最后一列，索引为列数-1，则显示计算总和
        } else if (index === 4) {
          const values = data.map(item => Number(item[column.property]))
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0)
          } else {
            sums[index] = 'N/A'
          }
          //如果是除了第一列和最后一列的其他列，则显示为空
        } else {
          sums[index] = ''
        }
      })
      return sums
    },
    //部门已用预算/部门总预算进度条
    total_percentage(){
      return 100*this.used_budget/this.total_budget;
    },
    format(){
      return '部门总预算：'+this.total_budget+'  '+'已使用预算'+this.used_budget;
    },
    //工单预算/部门剩余预算进度条
    percentage(){
      return 100*this.order_budget/this.surplus_budget;
    },
    format2(){
      this.surplus_budget=this.total_budget-this.used_budget;
      return '部门剩余预算：'+this.surplus_budget+'  '+'工单所需预算'+this.order_budget;
    },
    customColorMethod(percentage) {
      if (percentage < 90) {
        return '#52b69a';

      } else {
        return 'rgba(255,165,0,0.7)';
      }
    },
  },
}
</script>

<style scoped>
.reason_contect{
  width: 80%;
  height: 80px;
  /*background: #409EFF;*/
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
</style>
