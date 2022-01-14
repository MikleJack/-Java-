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
                <el-button type="primary" @click="requireToDelay">确 定</el-button>
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

    //时间类型转换
    dateFormat(date,format){
      if(null==date || ""==date){
        return "";
      }
      if(date==''||date==null){
        return '';
      }
      date = new Date(date);
      let o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'H+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //second
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
      };
      if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));

      for (let k in o)
        if (new RegExp('(' + k + ')').test(format))
          format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));

      return format;
    },


    //确认延期
    requireToDelay(){
      let newTime = this.dateFormat(this.delayTime ,"yyyy-MM-dd HH:mm:ss")

      this.$axios.post("http://localhost:8084/staffAllTickets/delay",{
        workOrderNum:202201081138000001,
        delayTime:this.delayTime,
        delayReason: this.delayReason
      }).then((res)=>{
        if(res.data != "false"){
          alert("发起延期成功，创建的延期工单号为" + res.data);
        }else{
          alert("延期失败");
        }
      })
      this.refresh();
      this.$store.state.staffAllOrder_DelayDialogVisible = false;
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
