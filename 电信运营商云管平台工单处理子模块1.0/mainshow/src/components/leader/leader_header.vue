<template>
  <el-container class="container">
    <!-- 上方导航栏 -->
    <el-header class="header">
      <div style="position:relative;float: left;top: 5px;">
        <img src="../../assets/logo.png" height="40" width="40"/>
      </div>
      <div style="position:relative;float: left;top: 13px; width:400px;font-size:20px;">
        电信运营商云管平台工单处理子模块_领导版
      </div>

      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" text-color="black">
        <el-submenu index="2" >
          <template slot="title">个人中心</template>
          <el-menu-item index="2-1" >选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
          <el-menu-item index="2-3">选项3</el-menu-item>
        </el-submenu>
        <el-menu-item index="2" @click="logoutFn">
          退出登录 </el-menu-item>
      </el-menu>
    </el-header>
    <!-- 侧边导航栏 -->
    <el-container>
      <el-aside class="aside" width=150px>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#52b69a"
          text-color="#fff"
          active-text-color="#ffd04b"
          @click="jump1"
        >
          <div @click="jump1">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>首页</span>
              </template>
            </el-submenu>
          </div>


<!--            <el-menu-item-group>-->
<!--              <template slot="title"></template>-->
<!--              <el-menu-item index="1-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="1-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->

          <div @click="jump1">
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>待审批工单</span>
              </template>
            </el-submenu>
          </div>

<!--            <el-menu-item-group>-->
<!--              <template slot="title"></template>-->
<!--              <el-menu-item index="2-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->

          <div @click="jump1">
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>工单审批日志</span>
              </template>
              <!--            <el-menu-item-group>-->
              <!--              <template slot="title"></template>-->
              <!--              <el-menu-item index="3-1">选项1</el-menu-item>-->
              <!--              <el-menu-item index="3-2">选项2</el-menu-item>-->
              <!--            </el-menu-item-group>-->

            </el-submenu>
          </div>

          <div @click="jump1">
            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>全部工单</span>
              </template>
              <!--            <el-menu-item-group>-->
              <!--              <template slot="title"></template>-->
              <!--              <el-menu-item index="4-1">选项1</el-menu-item>-->
              <!--              <el-menu-item index="4-2">选项2</el-menu-item>-->
              <!--            </el-menu-item-group>-->
            </el-submenu>
          </div>

        </el-menu>
      </el-aside>
      <!-- main区域 -->
      <el-main class="main" index="/">
        <router-view>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import All_work_order from "./all_work_order";
import Examine from "./examine";
import Pending_ticket from "./pending_ticket";
import Router from "vue-router";
import all_work_order from "./all_work_order";

const router1 = new Router({
  routes:[
    {
      path:'all_work_order',
      name:'all_work_order',
      component:all_work_order
    }
  ]
});

export default{
  name: "leader_header",
  components: {Pending_ticket, Examine, All_work_order},
  methods:{
    //退出
    logoutFn() {
      this.$confirm("退出登录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$router.push({ path: "/" });
      });
    },
    jump1(){
      // console.log("jump!");
      router1.push({path:'/all_work_order'})
    }
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
