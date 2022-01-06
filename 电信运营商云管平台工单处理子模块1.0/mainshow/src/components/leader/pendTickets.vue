<template>
  <div class="page">
<!--    <div class="page_top">-->
<!--      待审批工单-->
<!--    </div>-->
    <div class="page_central">
      <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
          fixed
          prop="ticket_num"
          label="工单编号"
          width="auto">
        </el-table-column>
        <el-table-column
          prop="ticket_name"
          label="工单标题"
          width="auto">
        </el-table-column>
        <el-table-column
          prop="apply_time"
          label="申请到的时间"
          sortable
          width="auto">
        </el-table-column>
        <el-table-column
          prop="work_num"
          label="申请人工号"
          width="auto">
        </el-table-column>
        <el-table-column
          prop="name"
          label="申请人姓名"
          width="auto">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="scope">

            <el-button type="text" @click="dialogTableVisible = true">查看详情</el-button>

          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="工单详情" :visible.sync="dialogTableVisible">
        <ticket_details></ticket_details>
      </el-dialog>
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
import Ticket_details from "./ticket_details";
export default {
  name: "pending_ticket",
  components: {Ticket_details},
  methods: {

  },

  data() {
    return {
      //需要数据： 申请工单 的 申请工单号，工单标题，申请时间（apply_time），申请人工号，申请人姓名
      tableData: [{
        ticket_num:'000000000000000002',
        ticket_name: '二班资源申请工单',
        apply_time: '2023-02-03 00:00:00',
        work_num:'00000002',
        name:'陈大炮'
      },{
        ticket_num:'000000000000000002',
        ticket_name: '二班资源申请工单',
        apply_time: '2023-02-03 00:00:00',
        work_num:'00000002',
        name:'陈大炮'
      },{
        ticket_num:'000000000000000002',
        ticket_name: '二班资源申请工单',
        apply_time: '2023-02-03 00:00:00',
        work_num:'00000002',
        name:'陈大炮'
      },
      ],
      dialogTableVisible:false
    }
  }
}
</script>

<style scoped>
/*页面样式*/
.page {
  position: relative;
  width: 100%;
  height: 100%;
}

/*页面中部*/
.page_central{
  position: relative;
  width: 80%;
}

.page-tail{
  width:100%;
  height: 60px;
  position: absolute;
  bottom: 0;
}
</style>
