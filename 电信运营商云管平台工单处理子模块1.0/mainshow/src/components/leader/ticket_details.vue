<template>
  <div class="page">
<!-- -->
<!--    <div class="page_top">工单详情</div>-->
    <div class="page_body">
        <div class="page_title">申请人信息</div>

        <div class="page_line"></div>

        <div class="page_block">

          <el-form :inline="true" :model="tabledata1" class="demo-form-inline" :label-position="labelPosition">
            <el-form-item label="工号">
              <el-input v-model="tabledata1.num" placeholder="工号"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="tabledata1.name" placeholder="姓名"></el-input>
            </el-form-item>
    <!--      </el-form>-->

    <!--      <el-form :inline="true" :model="tabledata1" class="demo-form-inline">-->
            <el-form-item label="申请时间">
              <el-input v-model="tabledata1.time" placeholder="申请时间" disabled></el-input>
            </el-form-item>
            <el-form-item label="联系方式">
              <el-input v-model="tabledata1.phone" placeholder="联系方式"></el-input>
            </el-form-item>
            <el-form-item label="所在部门">
              <el-input v-model="tabledata1.depart"></el-input>
            </el-form-item>
          </el-form>
        </div>

<!--      审批工单时显示申请人信息的无框表格-->
<!--      <el-descriptions class="margin-top" title="申请人信息" :column="3" :size="size" border></el-descriptions>-->
<!--        <el-descriptions class="margin-top":column="3" :size="size">-->
<!--          <el-descriptions-item label="申请人姓名">{{name}}</el-descriptions-item>-->
<!--          <el-descriptions-item label="工号">{{workNum}}</el-descriptions-item>-->
<!--          <el-descriptions-item label="所在部门编号">{{depNum}}</el-descriptions-item>-->
<!--          <el-descriptions-item label="所在部门名称">{{depName}}</el-descriptions-item>-->
<!--          <el-descriptions-item label="电话号码">{{phone}}</el-descriptions-item>-->
<!--        </el-descriptions>-->

<!--      显示工单基础信息-->
      <el-descriptions class="margin-top" title="工单信息信息" :column="3" :size="size" border></el-descriptions>
      <el-descriptions class="margin-top" :column="3" :size="size">
        <el-descriptions-item label="工单编号">{{workOrderNum}}</el-descriptions-item>
        <el-descriptions-item label="工单标题">{{workOrderName}}</el-descriptions-item>
        <el-descriptions-item label="工单类型">{{workType}}</el-descriptions-item>
        <el-descriptions-item label="工单申请时间">{{applyTime}}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions class="margin-top" :title="reason" :column="3" :size="size" border></el-descriptions>
      <div class="reason_contect">{{reasonContect}}</div>

      <el-descriptions class="margin-top" title="资源" :column="3" :size="size" border></el-descriptions>
      <el-table
        :data="allocatedCom"
        border
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>

        <el-table-column
          prop="comType"
          label="计算机类型"
          width="auto">
        </el-table-column>
        <el-table-column
          prop="cpuCore"
          label="CPU核数"
          width="auto">
        </el-table-column>
        <el-table-column
          prop="ram"
          label="内存">
        </el-table-column>
        <el-table-column
          prop="strange"
          label="存储大小">
        </el-table-column>
        <el-table-column
          prop="ip"
          label="ip地址">
        </el-table-column>
        <el-table-column
          prop="os"
          label="操作系统">
        </el-table-column>
      </el-table>

<!--      <el-descriptions class="margin-top" title="虚拟机资源" :column="3" :size="size" border></el-descriptions>-->

<!--显示流转过程-->
      <el-descriptions class="margin-top" title="流转过程" :column="3" :size="size" border></el-descriptions>

      <div class="resoure_usage">
        <el-table
          :data="informData"
          border
          style="width: 100%">
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
      <el-descriptions class="margin-top" title="批注" :column="3" :size="size" border></el-descriptions>
      <div class="resoure_usage">
        <el-input
          type="textarea"
          :rows="3"
          placeholder="请输入内容"
          v-model="textarea">
        </el-input>
      </div>
      <div class="page_bottom">
        <el-button>审批通过</el-button>
        <el-button>挂起</el-button>
        <el-button>审批不通过</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ticket_details",
  data () {
    return {
      labelPosition: 'left',
      //字体大小
      size: '',
      //workNum工号
      workNum:'00000001',
      //name人员姓名
      name:'张大炮',
      //所在部门编号
      depNum:'0003',
      //所在部门名称
      depName:'小组1',
      //电话号码
      phone:'13000000000',
      //工单编号
      workOrderNum:'000000000000000001',
      //工单标题
      workOrderName:'资源申请工单',
      //工单类型
      workType:'申请工单',
      //申请时间
      applyTime:'2023-5-3 00:00:00',
      //reason可变，为申请工单时，为申请理由，为回退工单时，为回退理由，
      reason:'申请理由',
      reasonContect:'申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由申请理由',
      //流传过程数据
      informData: [{
        //处理人工号
        dealNum:'00000002',
        //处理人姓名
        dealName:'陈大炮',
        //操作类型
        operationType:'审批通过',
        //操作日期
        dealDate:'2021-12-28 15:30:00',
        //批注
        dealComment:'申请合理，建议通过',
      }],

      tabledata1:[{
        num: ' ',
        name:' ',
        time:'',
        phone:'',
        depart:'',
        order_num:'',
        order_name:'',
        reason:''
      }],


      allocatedCom:[{
      //  计算机类型
        comType:'物理机',
        //cpu核数
        cpuCore:'8',
      //  内存
        ram:'16',
      //  存储
        strange:'256',
      //  ip
        ip:'194.15.1.1',
      //  操作系统
        os:'Window'
      }],
    //  输入的批注内容
      textarea: ''
    };
  }
}
</script>

<style scoped>
.page{
  position: relative;
  width: 100%;
  height: auto;
  left: 0;
  top: 0;
  background: #ffffff;
}
.page_top{
  width: 100%;
  height: 60px;
  text-align: center;
  line-height: 60px;
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
  width: 100%;
  height: 150px;
  /*background: #409EFF;*/
}
.resoure_usage{
  width: 100%;
  height: auto;
  background: #42b983;
}
.page_bottom{
  width: 100%;
  height: 100px;
  bottom: 0;
  /*background: #888888;*/
  text-align: center;
  line-height: 100px;
}
</style>
