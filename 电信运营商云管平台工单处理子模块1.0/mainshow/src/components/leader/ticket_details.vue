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
          :summary-method="getSum"
          show-summary
        >
          <el-table-column
            type="index"
            label="序号"
            width="100">
          </el-table-column>
          <el-table-column
            prop="phy_cpuCore"
            label="CPU核数/个"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="phy_ram"
            label="内存/G">
          </el-table-column>
          <el-table-column
            prop="phy_mem"
            label="存储大小/G">
          </el-table-column>
          <el-table-column
            prop="phy_price"
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
          prop="vir_require"
          label="规格族"
          width="auto">
        </el-table-column>
          <el-table-column
            prop="vir_cpuCore"
            label="CPU核数/个"
            width="auto">
          </el-table-column>
          <el-table-column
            prop="vir_ram"
            label="内存/G">
          </el-table-column>
          <el-table-column
            prop="vir_frequency"
            label="处理机主频/GHz"
          width="150px">
          </el-table-column>
          <el-table-column
            prop="vir_model"
            label="处理器型号">
          </el-table-column>
          <el-table-column
          prop="vir_os"
          label="操作系统">
        </el-table-column>
          <el-table-column
            prop="vir_price"
            label="单价 元/月">
          </el-table-column>
          <el-table-column
            prop="hardDisk"
            label="硬盘大小/G">
          </el-table-column>
          <el-table-column
            prop="vir_totalPrice"
            label="总价/元">
          </el-table-column>
        </el-table>
      </div>
<!--      部门资源利用情况展示-->
      <div class="frame" style="border: rgba(82,182,154,0.25) solid 3px ">
        <div class="page_title" >部门预算利用情况</div>
<!--        部门已用预算/部门总预算-->
        <el-progress :text-inside="true" :stroke-width="25":format="format" :percentage="total_percentage()":color="customColorMethod"></el-progress>
<!--        工单预算/部门剩余预算-->
        <el-progress :text-inside="true" :stroke-width="25":format="format2" :percentage="percentage()"></el-progress>
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
      <div class="page_title" style="margin-top: 4%" >批注</div>
      <div class="resoure_usage">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入批注"
          v-model="note">
        </el-input>
      </div>
      <div class="page_bottom">
        <el-button style="color:white;background-color: #52b69a " >审批通过</el-button>
        <el-button>挂起</el-button>
        <el-button>审批不通过</el-button>
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
      workNum: '00000001',
      //name人员姓名
      name: '张大炮',
      //所在部门编号
      depNum: '0003',
      //所在部门名称
      depName: '小组1',
      //电话号码
      phone: '13000000000',
      //工单编号
      workOrderNum: '000000000000000001',
      //工单标题
      workOrderName: '资源申请工单',
      //工单类型
      workType: '申请工单',
      //申请时间
      applyTime: '2023-5-3 00:00:00',
      //到期时间
      expireTime:'2023-6-30 00:00:00',
      //reason可变，为申请工单时，为申请理由，为回退工单时，为回退理由，
      reasonContect: '申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由',
      //  输入的批注内容
      note: '',
      //部门总预算利用情况
      used_budget:'1000',
      //部门总预算
      total_budget:'2000',
      //部门剩余预算
      surplus_budget:'',
      //工单预算
      order_budget:'300',
      //物理机资源数据
      phyCom: [{
        //物理机cpu核数
        phy_cpuCore: '8',
        //物理机内存
        phy_ram: '16',
        //物理机存储
        phy_mem: '256',
        //物理机单价
        phy_price: '2000'
      }],
      virtualCom: [{
        //虚拟机规格族
        vir_require:'共享标准型',
        //虚拟机cpu核数
        vir_cpuCore: '2',
        //虚拟机内存
        vir_ram: '32',
        //虚拟机处理器主频
        vir_frequency: '2.5',
        //虚拟机处理器型号
        vir_model: '型号',
        //虚拟机操作系统
        vir_os: 'windows',
        //虚拟机单价
        vir_price:'100',
        //硬盘
        hardDisk:'256',
        //总价
        vir_totalPrice: '200'
      }],
      //流传过程数据
      informData: [{
        //处理人工号
        dealNum: '00000002',
        //处理人姓名
        dealName: '陈大炮',
        //操作类型
        operationType: '审批通过',
        //操作日期
        dealDate: '2021-12-28 15:30:00',
        //批注
        dealComment: '申请合理，建议通过',
      }],

    };
  },
  methods: {
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
  }
  }
</script>

<style>
.page{
  position: relative;
  width: 100%;
  height: auto;
  left: 0;
  top: 0;
  /*background: #ffffff;*/
}
.page_top{
  width: 100%;
  height: 30px;
  text-align: center;
  /*line-height: 60px;*/
  font-size:x-large;
  font-weight: bolder;
  margin-top: 0;
  color: #0c805f;
}
.page_body{
  position: relative;
  width: 90%;
  /*height: 20px;*/
  height: 100%;
  /*top: 0;*/
  left: 5%;
  /*background: #0c805f;*/
}
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
.page_bottom{
  width: 100%;
  height: 100px;
  bottom: 0;
  /*background: #888888;*/
  text-align: center;
  line-height: 100px;
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
.el-table{
  margin-left: 2%;
  margin-right: 1%;
  width: 95%;
  margin-bottom: 1%;
}
.frame{
  margin-bottom: 3%;

}
.el-progress{
  margin-left: 10%;
  width: 80%;
  white-space: pre;
  margin-bottom: 1%;
}

.el-progress-bar__outer{
  background-color: white;
  border-color: #52b69a;
  border-style: solid;
}
.el-progress-bar__inner{
  background-color:#52b69a;
}
.el-progress-bar__innerText{
  white-space:pre-wrap;
}
</style>
