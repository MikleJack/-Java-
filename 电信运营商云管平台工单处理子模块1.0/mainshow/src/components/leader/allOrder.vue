<template>

  <div class="page-main">
    <!--主要界面-->
    <div class="page-head">
      <!--界面头部-->

        <!--设置居中-->
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="工单类型">
          <el-select v-model="formInline.work_order_type" placeholder="工单类型">
            <!--下拉的可选表单框-->
            <el-option label="待审批工单" value="work_order_to_approve"></el-option>
            <el-option label="已审批工单" value="work_order_have_approved"></el-option>
            <el-option label="已挂起工单" value="work_order_have_suspended"></el-option>
            <!--为后端提供选择的工单类型，为上述三种之一-->
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
          <!--从后端查询选择的工单类型的详细信息-->
        </el-form-item>
      </el-form>
      </div>


      <div class="page-body">
        <!--界面体-->
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
            label="时间"
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
    <div class="page-tail">
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
        name: "all_work_order",
      data() {
        return {
          formInline: {
            work_order_type: ''
          },
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
      },
      methods: {
        onSubmit() {
          console.log('submit!');
        }
      }
    }
</script>


<!--对界面各个部分进行划分范围-->
<style scoped>
  .page-main{
    position: relative;
    width:100%;
    height: 100%;
  }


  .page-head{
    position: relative;
    width:100%;
    text-align:center
  }


  .page-body{
    position: relative;
  }

  .page-tail{
    width:100%;
    height: 60px;
    position: absolute;
    bottom: 0;
  }

</style>
