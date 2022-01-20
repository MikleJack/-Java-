<template>

  <!--确认密码的dialog-->
  <el-dialog
    title="确认密码"
    :visible.sync="this.$store.state.dialogVisible_modify"
    width="30%"
    :before-close="handleClose">
    <span></span>
    <el-input placeholder="请输入密码" v-model="confirm_password" show-password></el-input>

    <span slot="footer" class="dialog-footer">
                <el-button @click="cancel_confirm()">取 消</el-button>
                <el-button type="primary" @click="complete_confirm()" >确 定</el-button>
              </span>
  </el-dialog>

</template>

<script>
export default {
  name: "ConfirmPW",
  data(){
    return{
      confirm_password:''
    }
  },
  methods:{
    handleClose(done) {
      this.$store.state.dialogVisible_modify = false;
      done();
    },
    //密码验证取消
    cancel_confirm(){
      this.$store.state.dialogVisible_modify = false;
      this.$message.error('error，密码验证失败');
      this.$store.state.password_confirm = false;
      this.which_page_confirm = 0;
    },
    //密码验证确认
    complete_confirm(){



      this.$axios.post(this.$store.state.url+"/adminHome/confirmPassword?password="+this.confirm_password).then((res)=>{
        this.$store.state.password_confirm = res.data === true;

        // alert(this.$store.state.password_confirm);
        // alert(this.$store.state.which_page_confirm);


        if (this.$store.state.which_page_confirm === 1){
          if(this.$store.state.password_confirm === true){
            // alert("调用修改部门预算接口");
            this.$axios.put(this.$store.state.url+"/adminHome/setDepBudget?DepNum=" + this.$store.state.DepNum + "&DepBudget=" +
              this.$store.state.DepBudget).then((res)=>{
                this.$message({
                  message:"修改成功",
                  type:"success",
                  center:true
                })
                this.$store.state.dialogVisible_modify = false;
                // this.$store.state.dialogVisible_budget = false;
            })
          }else{
            this.$message({
              message:"密码验证失败",
              type:"error",
              center:true
            })
          }

        //  修改物理机预算
        }else if(this.$store.state.which_page_confirm === 2){
          if(this.$store.state.password_confirm === true){
            this.$axios.put(this.$store.state.url + "/adminHome/setPhyPrice?comNum=" + this.$store.state.comNum
              + "&price=" + this.$store.state.comprice).then((res)=>{
                if (res.data === true){
                  this.$message({
                    message: '成功修改物理机价格',
                    type: 'success',
                    center:true
                  });
                  this.$store.state.dialogVisible_modify = false;
                }else{
                  this.$message({
                    message: '修改失败',
                    type: 'error',
                    center:true
                  });
                  this.$store.state.dialogVisible_modify = false;
                }
            })
          }else{
            this.$message({
              message: '密码验证失败',
              type: 'error'
            });
            this.$store.state.dialogVisible_modify = false
          }

        }else if(this.$store.state.which_page_confirm === 3){

          if(this.$store.state.password_confirm === true){
            this.$axios.put(this.$store.state.url+"/adminHome/setDiskPrice?diskPrice="+this.$store.state.formInline.diskPrice).then((res)=>{
              if(res.data===true){
                this.$message({
                  message: 'success，密码验证成功，成功修改硬盘价格',
                  type: 'success'
                });
                this.$store.state.dialogVisible_modify = false
              }
            })
          }else{
            this.$message({
              message: '密码验证失败',
              type: 'error'
            });
            this.$store.state.dialogVisible_modify = false
          }

        }else if(this.$store.state.which_page_confirm === 4){
          if(this.$store.state.password_confirm === true){
            this.$axios.put(this.$store.state.url+"/adminHome/updateVm?cpuCore="+this.$store.state.formInline.cpuCore+
              "&ram="+this.$store.state.formInline.ram+"&storage="+this.$store.state.formInline.storage).then((res)=>{
              if(res.data===true){
                this.$message({
                  message: 'success，密码验证成功，成功修改虚拟机总资源',
                  type: 'success'
                });
                this.$store.state.dialogVisible_modify = false
              }
            })
          }else{
            this.$message({
              message: '密码验证失败',
              type: 'error'
            });
            this.$store.state.dialogVisible_modify = false
          }

        }else if(this.$store.state.which_page_confirm === 5){

          if(this.$store.state.password_confirm === true){
            this.$axios.put(this.$store.state.url + "/adminHome/setVmPrice?comNum=" +
              this.$store.state.comNum + "&price="
            + this.$store.state.comprice).then((res) =>{
              if(res.data === true){
                this.$message({
                  message: '成功修改虚拟机价格',
                  type: 'success',
                  center:true,
                });
                this.$store.state.dialogVisible_modify = false;
              }else {
                this.$message({
                  message: '修改失败',
                  type: 'error',
                  center:true,
                });
                this.$store.state.dialogVisible_modify = false;
              }
            })

          }else{
            this.$message({
              message: '密码验证失败',
              type: 'error',
              center:true,
            });
            this.$store.state.dialogVisible_modify = false;
          }

        }else{
          this.$store.state.dialogVisible_modify = false
        }



      });



    }

  }
}
</script>

<style scoped>

</style>
