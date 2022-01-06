<template>
  <div class="all-container">
    <div class="all-container-padding bg" >
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="first">
          <el-form :model="userlist" :rules="rules" ref="EditorUserForms">
            <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
              <el-col :span="8">  <el-input v-model="userlist.username" disabled ></el-input></el-col>
            </el-form-item>
            <el-form-item label="工号" prop="user_num" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="userlist.user_num" disabled ></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="所在部门" prop="user_depart" :label-width="formLabelWidth">
              <el-col :span="8">  <el-input v-model="userlist.user_depart" disabled ></el-input></el-col>
            </el-form-item>
            <el-form-item label="电话" prop="phone" :label-width="formLabelWidth">
              <el-col :span="8">   <el-input v-model="userlist.phone" placeholder="请输入电话"></el-input></el-col>
            </el-form-item>

          </el-form>
          <div class="grid-content bg-purple">
            <el-button type="primary"  @click="EditorUserClick('userlist')" >保存</el-button>
          </div>
        </el-tab-pane>
        <el-tab-pane label="修改密码" name="second">
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
          <div class="grid-content bg-purple">
            <el-button type="primary"  @click="submitForm('ruleForm')">保存</el-button>
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
            pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
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
            validator: validatePass,
            trigger: "blur"
          }
        ],
        checknewpass: [
          {
            validator: validatePass2,
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
    //修改密码
    submitForm(ruleForm) {
      var obj = {
        username: this.username,
        oldpwd: this.ruleForm.pass,
        newpwd: this.ruleForm.newpass
      };
      console.log(obj);
      postData("接口", obj).then(response => {
        if (response.status == 200) {
          this.$message({
            message: "保存成功",
            type: "success"
          });
        } else {
          this.$message({
            message: "修改失败" + response.message,
            type: "error"
          });
        }
      });
    },
    // 编辑提交的方法
    EditorUserClick() {
      this.$refs.EditorUserForms.validate(valid => {
        if (valid) {
          console.log(this.userlist);
          putData("接口", this.userlist).then(response => {
            if (response.status == 200) {
              this.$message({
                message: "编辑成功",
                type: "success"
              });
            } else {
              this.$message({
                message: "修改失败" + response.message,
                type: "error"
              });
            }
          });

        }
      });
    }
  }
};
</script>
