<template>
  <div class="page-main">
    <!--主要界面部分-->
    <div class="page-head">
      <!--界面头部分-->
      <div style="width:100%;text-align:center">
        <!--设置居中-->
        <el-form :inline="true" :model="formInline.work_order_num" class="demo-form-inline">

          <el-form-item label="输入工单号查询">
            <el-input v-model="formInline.work_order_num" placeholder="工单号"></el-input>
          </el-form-item>

          <!--输入查询框-->
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
          <!--查询按钮-->
        </el-form>
      </div>
      <div class="page-body">
        <!--界面体部分-->
        <el-table
          :data="tableData.filter(data => !formInline.work_order_num || data.work_order_num.toLowerCase().includes(formInline.work_order_num.toLowerCase()))"
          border
         >
          <el-table-column
            prop="work_order_num"
            label="工单号"
            width="180">
          </el-table-column>
          <el-table-column
            prop="work_order_name"
            label="工单名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="work_num"
            label="审批人号">
          </el-table-column>
          <el-table-column
            prop="name"
            label="审批人姓名">
          </el-table-column>
          <el-table-column
            prop="deal_date"
            label="处理时间">
          </el-table-column>
          <el-table-column
            prop="deal_comment"
            label="处理批注">
          </el-table-column>
        </el-table>

        <!--放置表格-->


    </div>

  </div>
    <div class="page-tail">
      <!--放置分页部分-->
      <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
      </el-pagination>
    </div>
  </div>

</template>

<script>
    export default {
        name: "examine",
      data() {
        return {

          formInline:
            {
            work_order_num: '',
          },
          //给后端一个工单号


          tableData: [{
            work_order_num: '000000000000000001',
            work_order_name: '工单一',
            work_num: '00000001',
            name:'张大炮',
            deal_date:'2022-01-04 00:00:00',
            deal_comment:'无'
          }, {
            work_order_num: '000000000000000002',
            work_order_name: '工单二',
            work_num: '00000001',
            name:'张大炮',
            deal_date:'2022-01-05 00:00:00',
            deal_comment:'无'
          }]
        //  表格数据，需要后端传递工单号、工单名、审批人号、审批人名、处理时间、处理批注这些信息
        }
      },
      methods: {
        onSubmit() {
          console.log('submit!');
        },
        //进行查询，后端给前端工单号对应的工单审批日志,包括工单号、工单名、审批人号、审批人名、处理时间、处理批注这些信息


      }

    }
</script>


<!--页面分区显示的设置-->
<style scoped>
.page-main{
  position: relative;
  width:100%;
  height: 100%;
}

.page-body{
  position: relative;
}
.page-head{
  height: 100px;
  position: relative;
}


.page-tail{
  width:100%;
  height: 60px;
  position: absolute;
  bottom: 0;
}

</style>
