<template>
  <div>
    <!--    员工所有工单查询界面, 点击延期后的dialog界面-->
    <el-dialog
      title="延期申请"
      :visible.sync="$store.state.staffAllOrder_DelayDialogVisible"
      width="50%"
      :before-close="handleClose">
      <!--        延期日期选择-->
      <div class="block">
        <el-date-picker
          v-model="delayTime"
          type="datetime"
          placeholder="选择延期时间">
        </el-date-picker>
      </div>

      <!--        空格占位，美观-->
      <div style="margin: 20px 0;"></div>

      <!--        延期原因-->
      <el-input
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 4}"
        placeholder="请输入延期原因"
        v-model="delayReason">
      </el-input>

      <span slot="footer" class="dialog-footer">
                <el-button @click="cancelDelay">取 消</el-button>
                <el-button type="primary" @click="requireToDelay" style="color: white;background-color: #52b69a;border-color: #52b69a ">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "StaffAllOrderDelay",
  data(){
    return{
      workOrderNum:'',
      delayTime:'',
      delayReason:''
    }
  },
  methods:{
    setWorkOrderNum(workOrderNum){
      this.workOrderNum = workOrderNum;
    },

    //确认延期
    requireToDelay(){
      this.$axios.post(this.$store.state.url+"/staffAllTickets/delay",{
        workOrderNum: this.workOrderNum,
        delayTime:this.delayTime,
        delayReason: this.delayReason
      }).then((res)=>{
        if(res.data.message == null){
          this.$store.state.staffAllOrder_DelayDialogVisible = false;
          this.refresh();
        }else{
        }
      })
    },

    handleClose(){
      this.refresh();
      this.$store.state.staffAllOrder_DelayDialogVisible = false;
    },

    //取消延期
    cancelDelay(){
      this.refresh();
      this.$store.state.staffAllOrder_DelayDialogVisible = false;
    },

    //退出刷新数据
    refresh(){
      this.workOrderNum = '';
      this.delayTime = '';
      this.delayReason = '';
    }
  }
}
</script>

<style scoped>

</style>
