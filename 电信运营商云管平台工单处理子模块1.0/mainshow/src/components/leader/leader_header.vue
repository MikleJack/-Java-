<template>
  <el-container class="container">
    <!-- 上方导航栏 -->
    <el-header class="header">
      <div style="position:relative;float: left;top: 5px;">
        <img src="../../assets/LOGO5.0.png" height="40" width="40"/>
      </div>
      <div style="position:relative;float: left;top: 13px; width:400px;font-size:20px;">
        电信运营商云管平台工单处理子模块_领导版
      </div>

      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" text-color="black">
        <el-submenu index="2" >
          <template slot="title">个人中心</template>
          <el-menu-item index="2-1" @click="changePassword">个人信息修改</el-menu-item>

        </el-submenu>
        <el-menu-item index="2" @click="logoutFn">
          退出登录 </el-menu-item>
      </el-menu>
    </el-header>
    <!-- 侧边导航栏 -->
    <el-container>
      <el-aside class="aside" width=150px>
        <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          background-color="#52b69a"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
            <el-menu-item index="1" @click="changePage(1)">

                <i class="el-icon-s-home" style="color: green"></i>
                <span>首页</span>
            </el-menu-item>

            <el-menu-item index="2" @click="changePage(2)">
                <i class="el-icon-edit-outline" style="color: green"></i>
                <span>待审批工单</span>
            </el-menu-item>

            <el-menu-item index="3" @click="changePage(3)">
                <i class="el-icon-notebook-2" style="color: green"></i>
                <span>工单审批日志</span>
            </el-menu-item>

            <el-menu-item index="4" @click="changePage(4)">
                <i class="el-icon-tickets" style="color: green"></i>
                <span>全部工单</span>
            </el-menu-item>

        </el-menu>
      </el-aside>
      <!-- main区域 -->
      <el-main class="main" index="/">
<!--        嵌套路由-->
        <router-view>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import examine from "./examineLog";
import all_work_order from "./allOrder";
import pending_ticket from "./pendTickets";

export default{
  name: "leader_header",
  components:{examine,all_work_order,pending_ticket },
  mounted() {
    this.$router.push({path:"/leader/home"});
  },
  methods:{
    //退出
    logoutFn() {
      this.$confirm("退出登录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$router.push({ path: "/user" });
        sessionStorage.removeItem("leader")
        this.$axios.get(this.$store.state.url+"/login/userLogout?work_num="+sessionStorage.getItem("work_num")).then((res)=>{
          if (res.data)
            sessionStorage.removeItem("work_num");
        })
      });
    },
    //跳转修改个人信息页面
    changePassword(){
      this.$router.push({path:"/leader/changePass"})
    },
    //改变页面
    changePage(page){
      if(page===1){
        this.$router.push({path:"/leader/home"});
      }
      else if(page===2){
        this.$router.push({path:"/leader/pendTickets"});
      }
      else if(page===3){
        this.$router.push({path:"/leader/examineLog"});
      }
      else if(page===4){
        this.$router.push({path:"/leader/allOrder"});
      }
    },

  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  font-size: 15px;
}
.header {
  background: #0c805f;
  color: #fff;
}

.page-title{
  width:200px;
}

.logo{
  width: 40px;
}
.el-menu-demo{
  background-color: transparent;
  float: right;
  color: #fff;
  width:300px;

}
.aside {
  background: #52b69a;
  color: #fff;
  /* height: 100%; */

}
.main {
  /* height: 100%; */
  color: #52b69a;
  /*background-color: #409EFF;*/
}
.headerlogo {
  /* line-height: 60px; */
  margin-top: 10px;
}
.rightsection {
  line-height: 60px;
  text-align: center;
}
</style>
