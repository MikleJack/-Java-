<template>
  <div>
    <!--   员工所有工单查询界面 点击下线后的dialog界面-->
    <el-dialog
      title="下线申请"
      :visible.sync="$store.state.staffAllOrder_OfflineDialogVisible"
      width="50%">
      <!--      下线申请原因-->
      <el-input
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 4}"
        placeholder="请输入下线原因"
        v-model="offlineReason">
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="offlineAccess">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "staffAllOrderOffline",
  data(){
    return{
      offlineReason:'',
      workOrderState:'',
      workOrderNum:'',
    }
  },
  methods:{
    cancel(){
      this.fresh();
      this.$store.state.staffAllOrder_OfflineDialogVisible = false;
    },
    //确认下线
    offlineAccess(){
      this.$axios.get(this.$store.state.url+"/staffAllTickets/offline?workOrderNum=" + this.workOrderNum + "&workOrderState="+
                                                                  this.workOrderState + '&offlineReason=' + this.offlineReason).then((res)=>{
          if(res.data){
            this.$store.state.staffAllOrder_OfflineDialogVisible = false;
            this.$message({
              showClose: true,
              message: '下线成功',
              type: 'success'
            });
          }else{
            this.$message({
              showClose: true,
              message: '下线失败',
              type: 'error'
            });
          }
      })
      this.fresh();
    },
    //将工单的信息传入
    setWorkOrderNumAndState(workOrderNum,workOrderState){
      this.workOrderState = workOrderState;
      this.workOrderNum = workOrderNum;
    },
    fresh(){
      this.offlineReason = '';
      this.workOrderState = '';
      this.workOrderNum = '';
    }
  }
}
</script>

<style scoped>

</style>
