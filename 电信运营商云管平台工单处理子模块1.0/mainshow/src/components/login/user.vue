<template>
  <div class="background">
    <div class="title">
      <div style="position: relative;left: 10px;line-height: 75px;position: relative;top: 0;">
        电信运营商云管平台工单处理子模块
      </div>
    </div>
    <!--登录界面-->
    <img :src="imgSrc" width="100%" height="100%" alt="" />
    <div class="warp">
      <!-- 管理员与用户切换按钮-->
      <div class="warp-form">
        <img src="../../assets/white.png" height="64" width="64" style="margin-left: 40%">
        <h2>用户登录</h2>
        <!--el-form  rules属性用来设置表单验证规则    status-icon属性为输入框添加了表示校验结果的反馈图标-->
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  >
          <el-form-item  prop="work_num">
            <el-input type="text" v-model="ruleForm.work_num" prefix-icon="el-icon-user"
                      placeholder="请输入账号"  autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item  prop="password">
            <el-input type="password" @keyup.enter.native="logging" v-model="ruleForm.password" prefix-icon="el-icon-setting" placeholder="请输入密码"  autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item>
            <div class="code_input">
              <el-input type="text" @keyup.enter.native="logging" v-model="code" placeholder="请输入验证码"  autocomplete="off"></el-input>
            </div>
            <div style="float: right">
              <img @click="getVertifyCode" id="verifyCode" />
            </div>
          </el-form-item>

          <el-form-item>
            <el-button style="margin-left: 40%" type="primary" @click="logging">登录</el-button>
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
      // 背景图片
      imgSrc: require('../../assets/userBack.jpg'),

      ruleForm: {
        work_num:'',
        password:'',
        operate:'',
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
      document.getElementById("verifyCode").src = this.timestamp("https://localhost:8084/verifycode/getVertifyCodebyId");
    },

    //登录按钮触发事件  向后端传输当前输入框中的账号密码，后台比对返回布尔类型，登录成功将进入 /main 界面
    logging() {
      //存储密码输错次数
      if(!this.$cookies.isKey(this.ruleForm.work_num))
        this.$cookies.set(this.ruleForm.work_num, 0);
      if(this.$cookies.isKey(this.ruleForm.work_num+"ban")){
        let banTime = this.$cookies.get(this.ruleForm.work_num+"ban");
        if(banTime==="m")
          this.$message({
            message:'五秒时间还未到，请稍候再尝试！',
            type:'warning',
            center:true
          });
        else if(banTime==="2m")
          this.$message({
            message:'十秒时间还未到，请稍候再尝试！',
            type:'warning',
            center:true
          })
        return
      }
      this.getdate();
      this.$axios.get(this.$store.state.url+'/verifycode/getStringOfVertifyCode').then((res)=>{
        if(true){
          this.$axios.get(this.$store.state.url+"/login/user?work_num=" + this.ruleForm.work_num + "&password=" + this.ruleForm.password).then((res) => {
            if (res.data>=0) {
              //   $message消息提示框
              this.$message({
                message: '登录成功',
                type: 'success',
                center: true
              });
              //登录成功后移除cookie
              this.$cookies.remove(this.ruleForm.work_num);

              sessionStorage.setItem("work_num",this.ruleForm.work_num);
              //设置登录类型为员工
              if(res.data===1){
                sessionStorage.setItem("type", "staff");
                sessionStorage.setItem("staff", 'true');
                this.$router.push('/staff');
              }
              else {
                sessionStorage.setItem("type", "leader");
                sessionStorage.setItem("level", res.data);
                sessionStorage.setItem("leader", 'true');
                this.$router.push('/leader');
              }
              //登录成功后设置登录状态
              this.ruleForm.operate="登录成功";
              //增加登录日志
              this.$axios.post(this.$store.state.url+"/login/addLog", this.ruleForm);
            } else if (res.data===-1){
              this.$message({
                message: '不允许同时登录!',
                type: 'warning',
                center: true
              });
            }
            else {
              this.$message({
                message: '用户名或密码错误',
                type: 'error',
                center: true
              });
              //登录失败后设置登录状态
              this.ruleForm.operate="登录失败";
              //登录失败后设置cookie
              this.setCookie(this.ruleForm.work_num);
              this.getVertifyCode();
              //增加登录日志
              this.$axios.post(this.$store.state.url+"/login/addLog", this.ruleForm);
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



    },
    setCookie(workNum){
      this.$cookies.set(workNum,parseInt(this.$cookies.get(workNum))+1);
      console.log(this.$cookies.get(workNum));
      let errors=this.$cookies.get(workNum);
      if(errors==="5"){
        this.$cookies.set(workNum+"ban","1m","5s");
        this.$message({
          message:'输错密码五次，请五秒后再尝试登录！',
          type:'warning',
          center:true
        })
      }
      else if(errors==="10"){
        this.$cookies.set(workNum+"ban","2m","10s");
        this.$message({
          message:'输错密码十次，请十秒后再尝试登录！',
          type:'warning',
          center:true
        })
      }
      else if(errors==="15"){
        this.$axios.get(this.$store.state.url+"/account/lockAccount?work_num="+workNum).then((res)=>{
          this.$message({
            message:'账户已被锁定，请联系管理员进行解锁！',
            type:'error',
            center:true
          })
        })
      }
    }
  }
}
</script>

<style scoped>
.warp{
  position:absolute;
  /*定位方式绝对定位absolute*/
  top:50%;
  left:50%;
  /*顶和高同时设置50%实现的是同时水平垂直居中效果*/
  transform:translate(-50%,-50%);
  /*实现块元素百分比下居中*/
  width:450px;
  padding:50px;
  background: rgba(0,0,0,.5);
  /*背景颜色为黑色，透明度为0.8*/
  box-sizing:border-box;
  /*box-sizing设置盒子模型的解析模式为怪异盒模型，
  将border和padding划归到width范围内*/
  box-shadow: 0px 15px 25px rgba(0,0,0,.5);
  /*边框阴影  水平阴影0 垂直阴影15px 模糊25px 颜色黑色透明度0.5*/
  border-radius:15px;
  /*边框圆角，四个角均为15px*/
}
.title{
  position: absolute;
  top:0;
  left: 0;
  width: 100%;
  height: 13.45%;
  text-align: left;
  padding-left: 10px;
  font-size: 30px;
  line-height: 70px;
  color: #ffffff;
}
.background{
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 100%;
  background: #1a674e;
  overflow-x: hidden;
  overflow-y: hidden;
}

.code_input{
  width: 200px;
  float: left;
}

.warp-form h2{
  margin:0 0 30px;
  padding:0;
  color: #fff;
  text-align:center;
  /*文字居中*/
}

.warp-form{
  position:relative;
  width: 100%;
  padding:10px 0;
  font-size:16px;
  color:#fff;
  letter-spacing: 1px;
  /*字符间的间距1px*/
  margin-bottom: 30px;
  border:none;
  border-bottom: 1px solid #fff;
  outline:none;
  /*outline用于绘制元素周围的线
  outline：none在这里用途是将输入框的边框的线条使其消失*/
  background: transparent;
  /*背景颜色为透明*/
}

</style>
