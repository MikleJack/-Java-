<template>

  <div class="page-main">
    <!--主要界面-->
    <div class="page-head">
      <!--界面头部-->
      <div style="width:100%;text-align:center">
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



      </div>
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
          }
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
    /*padding: 0 50px 15px;*/
    /*margin-left: 15%;*/
    position: absolute;

    width:100%;
    bottom:0;
    top:48px;
    /*background-color: rgb(255, 227, 89);*/
  }


  .page-head{
    height: 100px;
    padding: 30px ;
    position: relative;
    /*background-color: rgb(51, 45, 243);*/
  }


  .page-body{
    position: fixed;
    bottom:0;
    top:208px;
    left:200px;
    right:200px;
    /*background-color: rgb(81, 227, 150);*/
  }

</style>
