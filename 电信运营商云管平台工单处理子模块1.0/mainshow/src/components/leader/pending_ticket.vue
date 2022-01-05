<template>
  <div class="page">
    <div class="page_top">
      待审批工单
    </div>
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
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>

    </div>
  </div>
</template>

<script>
export default {
  name: "pending_ticket",
  methods: {
    handleClick(row) {
      // console.log(row);
      const h = this.$createElement;
      this.$msgbox({
        title: '消息',
        message: h('p', null, [
          h('span', null, '内容可以是 '),
          h('i', { style: 'color: teal' }, 'VNode')
        ]),
        showCancelButton: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',

        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '执行中...';
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);
          } else {
            done();
          }
        }
      }).then(action => {
        this.$message({
          type: 'info',
          message: 'action: ' + action
        });
      });
    }
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
      ]
    }
  }
}
</script>

<style scoped>
/*页面样式*/
.page {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  /*background: #409EFF;*/
  /*text-align: center;*/
}
/*页面头部*/
.page_top{
  position:absolute;
  width: 100%;
  height: 60px;
  /*background: #0c805f;*/
  text-align: center;
  font-size: 20px;
  margin: auto;
  line-height: 60px;
}
/*页面中部*/
.page_central{
  position: relative;
  width: 80%;
  height: 90%;
  /*background: aqua;*/
  top: 60px;
  margin: auto;
}
</style>
