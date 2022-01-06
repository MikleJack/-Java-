<template>
  <el-container class="container">
    <!-- 上方导航栏 -->
    <el-header class="header">
      <div style="position:relative;float: left;top: 5px;">
        <img src="../../assets/logo.png" height="40" width="40"/>
      </div>
      <div style="position:relative;float: left;top: 13px; width:400px;font-size:20px;">
        电信运营商云管平台工单处理子模块
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
          default-active="this.$route.path"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#52b69a"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-menu-item index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>首页</span>
            </template>
          </el-menu-item>

          <el-menu-item index="2" >
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>申请工单</span>
            </template>
          </el-menu-item>

          <el-menu-item index="3">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>变更工单</span>
            </template>
          </el-menu-item>
          <el-menu-item index="4">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>全部工单列表</span>
            </template>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <!-- main区域 -->
      <el-main class="main">
        <!-- <changetable></changetable> -->
<!--        <changetable></changetable>-->
        <employee-portal></employee-portal>
        <!-- <changepassword></changepassword> -->
      </el-main>
    </el-container>
  </el-container>
<!--  <el-container class="container">-->
<!--    &lt;!&ndash; 上方导航栏 &ndash;&gt;-->
<!--    <el-header class="header">-->
<!--      <div style="position:relative;float: left;top: 5%;">-->
<!--        <img src="../../assets/logo.png" height="40" width="40"/>-->
<!--      </div>-->
<!--      <div style="position:relative;float: left;top: 13px; width:400px;font-size:20px;">-->
<!--        电信运营商云管平台工单处理子模块-->
<!--      </div>-->

<!--      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" text-color="black">-->
<!--        <el-submenu index="2" >-->
<!--          <template slot="title">个人中心</template>-->
<!--          <el-menu-item index="2-1" >选项1</el-menu-item>-->
<!--          <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--          <el-menu-item index="2-3">选项3</el-menu-item>-->
<!--        </el-submenu>-->
<!--        <el-menu-item index="2" @click="logoutFn">-->
<!--          退出登录 </el-menu-item>-->
<!--      </el-menu>-->
<!--    </el-header>-->
<!--    &lt;!&ndash; 侧边导航栏 &ndash;&gt;-->
<!--    <el-container>-->
<!--      <el-aside class="aside" width=15%>-->
<!--        <el-menu-->
<!--          default-active="2"-->
<!--          class="el-menu-vertical-demo"-->
<!--          @open="handleOpen"-->
<!--          @close="handleClose"-->
<!--          background-color="#52b69a"-->
<!--          text-color="#fff"-->
<!--          active-text-color="#ffd04b"-->
<!--        >-->
<!--          <el-submenu index="1">-->
<!--            <template slot="title">-->
<!--              <i class="el-icon-location"></i>-->
<!--              <span>导航一</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--              <template slot="title"></template>-->
<!--              <el-menu-item index="1-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="1-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->

<!--          </el-submenu>-->
<!--          <el-submenu index="2">-->
<!--            <template slot="title">-->
<!--              <i class="el-icon-location"></i>-->
<!--              <span>导航二</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--              <template slot="title"></template>-->
<!--              <el-menu-item index="2-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->

<!--          </el-submenu>-->
<!--          <el-submenu index="3">-->
<!--            <template slot="title">-->
<!--              <i class="el-icon-location"></i>-->
<!--              <span>导航三</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--              <template slot="title"></template>-->
<!--              <el-menu-item index="3-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="3-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->

<!--          </el-submenu>-->
<!--          <el-submenu index="4">-->
<!--            <template slot="title">-->
<!--              <i class="el-icon-location"></i>-->
<!--              <span>导航四</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--              <template slot="title"></template>-->
<!--              <el-menu-item index="4-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="4-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->

<!--          </el-submenu>-->
<!--        </el-menu>-->
<!--      </el-aside>-->
<!--      &lt;!&ndash; main区域 &ndash;&gt;-->
<!--      <el-main class="main">-->
<!--&lt;!&ndash;        <employee-portal></employee-portal>&ndash;&gt;-->
<!--&lt;!&ndash;        <changepassword></changepassword>&ndash;&gt;-->
<!--        <changetable></changetable>-->
<!--      </el-main>-->
<!--    </el-container>-->
<!--  </el-container>-->
</template>

<script>
import EmployeePortal from "./employeePortal";
import Changepassword from "../changepassword";
import Changetable from "./changetable";
export default {
  name: "header",
  components: {Changetable, Changepassword, EmployeePortal},
  methods: {
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
  },
};
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
