<template>
  <div class="all-container">
    <div class="all-container-padding bg" >
<!--      修改个人信息表单-->
      <el-tabs v-model="activeName" @tab-click="handleClick" class="tabs" >
<!--        上方导航栏-->
        <el-tab-pane label="基本信息" name="first" style="margin-left: 30%">
          <el-form :model="userlist" :rules="rules" ref="EditorUserForms">
            <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
              <el-col :span="8">  <el-input v-model="userlist.name" disabled ></el-input></el-col>
            </el-form-item>
            <el-form-item label="工号" prop="user_num" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="userlist.workNum" disabled ></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="所在部门" prop="user_depart" :label-width="formLabelWidth">
              <el-col :span="8">  <el-input v-model="userlist.depName" disabled ></el-input></el-col>
            </el-form-item>
            <el-form-item label="电话" prop="phone" :label-width="formLabelWidth">
              <el-col :span="8">   <el-input v-model="userlist.phone" placeholder="请输入电话"></el-input></el-col>
            </el-form-item>
<!--保存按钮-->
          </el-form>
          <div class="grid-content bg-purple" style="margin-left: 20%">
            <el-button type="primary"  @click="EditorUserClick" >保存</el-button>
          </div>
<!--        修改密码表单-->
        </el-tab-pane>
        <el-tab-pane label="修改密码" name="second" style="margin-left: 30%">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
            <el-form-item label="原密码" prop="pass" :label-width="formLabelWidth">
              <el-col :span="8">   <el-input v-model="ruleForm.pass" placeholder="请输入原密码" type="password"></el-input></el-col>
            </el-form-item>
            <el-form-item label="新密码" prop="newpass" :label-width="formLabelWidth">
              <el-col :span="8"><el-input v-model="ruleForm.newpass" placeholder="请输入新密码" id="newkey" type="password"></el-input></el-col>
            </el-form-item>
            <el-form-item label="重复新密码" prop="checknewpass" :label-width="formLabelWidth">
              <el-col :span="8">   <el-input v-model="ruleForm.checknewpass" placeholder="请再次输入新密码" id='newkey1' type="password"></el-input></el-col>
            </el-form-item>
          </el-form>
<!--          保存-->
          <div class="grid-content bg-purple" style="margin-left: 20%">
            <el-button type="primary"  @click="submitForm">保存</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
export default {
  name: "changepassword",
  data() {
    /*****检验两次密码是否一致***/
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checknewpass !== "") {
          this.$refs.ruleForm.validateField("checknewpass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.newpass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };

    return {
      ruleForm: {},//修改密码的表单
      activeName: "first",//tab导航栏
      userlist: {},//用户信息表单
      formLabelWidth: "150px",
      /***校验***/
      rules: {
        phone: [
          {
            required: true,
            message: "请输入电话号码"
          },
          {
            pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,//确保输入为13位
            message: "手机格式不对"
          }
        ],
        pass: [
          {
            required: true,
            trigger: "blur",
            message: "请输入密码"
          }
        ],
        newpass: [
          {
            validator: validatePass,//用来校验新密码是否重复输入正确
            trigger: "blur"
          }
        ],
        checknewpass: [
          {
            validator: validatePass2,//用来校验新密码是否重复输入正确
            trigger: "blur"
          }
        ]
      }
    };
  },

  methods: {
    //tab切换
    handleClick(tab, event) {
      console.log(tab, event);
    },
    //保存新密码
    submitForm(ruleForm) {
      // if(this.ruleForm.)

      this.$axios.post(this.$store.state.url +
      "/personaldetails/changepw?workernum=" +
        sessionStorage.getItem("work_num") +
        "&oldpw=" +
        this.ruleForm.pass +
        "&newpw=" +
        this.ruleForm.checknewpass
      ).then((res)=>{
        if(res.data){
          this.$message({
            message: '修改成功',
            type: 'success',
            center: true
          })
        }else{
          this.$message({
            message: '修改失败',
            type: 'error',
            center: true
          })
        }
      })
    },
    //保存个人信息
    EditorUserClick() {
      this.$axios.post(this.$store.state.url +
      "/personaldetails/changephone?workernum=" +
        sessionStorage.getItem("work_num") + "&phone=" +
        this.userlist.phone).then((res)=>{
          if(res.data === true){
            this.$message({
              message: '修改成功',
              type: 'success',
              center: true
            })
          }else {
            this.$message({
              message:'修改失败',
              type:'error',
              center:true
            })
          }
      })
    }
  },
  mounted() {
    this.$axios.get(this.$store.state.url +
      "/personaldetails/queryPersonInformById?workernum=" +
      sessionStorage.getItem("work_num")).then((res)=>{
        this.userlist = res.data;
    });
  }
}
</script>
<style scoped></style>
<style>
/*按钮颜色改变*/
.el-button {
  color: #fff;
  background-color: rgba(82, 182, 154, 0.8);
  border-color: #52b69a;
}

/*鼠标移到标签上时颜色改变*/
.el-tabs__item:hover {
  color: #52b69a;
  /*background-color: #52b69a ;*/
}
/*鼠标选中标签时颜色改变*/
.el-tabs__item.is-active {
  color:#52b69a;
  /*background-color: #52b69a;*/
}
/*tabs el-tab-pane切换时的蓝色下划线改为绿色*/
.el-tabs__active-bar {
  background-color: #52b69a !important;
}
</style>

