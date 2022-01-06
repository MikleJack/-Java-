<template>
  <div class="max">
    <div class="title">
      <div style="position:relative;float: left;top: 18px;">
        <img src="../../assets/white.png" height="64" width="64"/>
      </div>
      <div style="position: relative;left: 10px;line-height: 75px;position: relative;top: 15px;">
        电信运营商云管平台工单处理子模块
      </div>

    </div>

    <!--    登录界面-->
    <div class="warp">
<!--      <div class="warp-line">-->
<!--        <img src="../../assets/man.png" width="50px">-->
<!--      </div>-->
      <div class="warp-form">
        <!--        el-form  rules属性用来设置表单验证规则    status-icon属性为输入框添加了表示校验结果的反馈图标-->
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  >
          <el-form-item  prop="work_num">
            <el-input type="text" v-model="ruleForm.work_num" prefix-icon="el-icon-user" placeholder="请输入账号"  autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item  prop="password">
            <el-input type="password" @keyup.enter.native="logging" v-model="ruleForm.password" prefix-icon="el-icon-setting" placeholder="请输入密码"  autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item>
            <div class="code_input">
              <el-input type="text" v-model="code" placeholder="请输入验证码"  autocomplete="off"></el-input>
            </div>
            <div style="float: right">
              <img @click="getVertifyCode" id="verifyCode" />
            </div>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="logging">登录</el-button>
          </el-form-item>

        </el-form>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "admin",
  mounted(){
    this.getIP();
    this.getVertifyCode();
  },
  data(){

    //表单验证规则  当用户输入密码为空，密码表单项验证错误信息，使表单状态为error，并提示“请输入密码”
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };

    //表单验证规则  当用户输入密码为空，账号表单项验证错误信息，使表单状态为error，并提示“请输入密码”
    var validatework_num = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'));
      } else {
        callback();
      }
    };

    return {
      ruleForm: {
        work_num:'',
        password:'',
        operate:'登录',
        operate_time:'',
        ip:'',
        address:'',
      },

      //表单验证规则
      rules: {
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        work_num: [
          { validator: validatework_num,trigger: 'blur'}
        ]
      },
      work_num:'',
      password:'',
      code:'',
    }
  },
  methods: {
    go_admin(){
      this.$router.push({path:'/admin'});
    },
    go_user(){
      this.$router.push({path:'/user'});
    },
    // 获取当前时间
    getdate() {
      let date = new Date();
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let strDate = date.getDate();
      let hour = date.getHours();
      let minute = date.getMinutes();
      let second = date.getSeconds();

      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      this.ruleForm.operate_time = year + "-" + month + "-" + strDate  + " " +hour+':'+minute+':'+second;
    },

    //获取IP地址
    getIP() {
      this.ruleForm.ip = returnCitySN['cip'] // ip
      this.ruleForm.address = returnCitySN["cname"] // 地址

    },

    //为url添加时间戳
    timestamp(url) {
      var getTimestamp = new Date().getTime();
      if (url.indexOf("?") > -1) {
        url = url + "&timestamp=" + getTimestamp
      } else {
        url = url + "?timestamp=" + getTimestamp
      }
      return url;
    },

    //验证码
    getVertifyCode() {
      document.getElementById("verifyCode").src = this.timestamp("http://localhost:8084/verifycode/getVertifyCodebyId");
    },

    //登录按钮触发事件  向后端传输当前输入框中的账号密码，后台比对返回布尔类型，登录成功将进入 /main 界面
    logging() {
      this.getdate();
      this.$axios.get('http://localhost:8084/verifycode/getStringOfVertifyCode').then((res)=>{
        if(this.code===res.data){
          this.$axios.get("http://localhost:8084/admin/login?work_num=" + this.ruleForm.work_num + "&password=" + this.ruleForm.password).then((res) => {
            if (res.data) {
              //   $message消息提示框
              this.$message({
                message: '登录成功',
                type: 'success',
                center: true
              });
              //设置token以及路由
              sessionStorage.setItem("token", 'true');
              this.$router.push('/adminMain');
            } else {
              this.$message({
                message: '用户名或密码错误',
                type: 'error',
                center: true
              });
            }
          })
        }
        else
        {
          //验证码错误之后的操作
          this.getVertifyCode();
          this.$message({
            message:'验证码错误',
            type:'error',
            center:true,
          })
        }
      })
      // this.$axios.post("http://localhost:8084/operationLog/add", this.ruleForm);

    }
  }
}
</script>

<style scoped>

.max{
  position: absolute;
  height: 100%;
  width: 100%;
}
.warp{
  border-radius: 20px;
  position: absolute;
  left: 61.8%;
  top: 35%;
  width: 400px;
  height: 400px;
  background: rgb(255, 255, 255);
  box-shadow: 0px 0px 10px #888888;
}
.warp-line{
  position: relative;
  width: 80%;
  margin-top: 40px;
  left: 45%;
}
.title{
  position: relative;
  top:0;
  left: 0;
  width: 100%;
  height: 100px;
  text-align: left;
  padding-left: 10px;
  font-size: 30px;
  line-height: 70px;
  float: left;
  color: #ffffff;
  background: #409EFF;
}
.warp-form{
  position: relative;
  width: 80%;
  text-align: center;
  margin-top: 80px;
  left: 40px;
}

.code_input{
  width: 200px;
  float: left;
}
</style>
