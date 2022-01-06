import Vue from 'vue'
import Router from 'vue-router'

import admin from "../components/login/admin";

import user from "../components/login/user";
import all_work_order from "../components/leader/allOrder";
import pending_ticket from "../components/leader/pendTickets";

import leader_header from "../components/leader/leader_header";
import examine from "../components/leader/examineLog";
import admin_header from "../components/admin/admin_header";
import adminOerderSearch from "../components/admin/adminOerderSearch";
import dep_manage from "../components/admin/dep_manage";
import admin_operation_log from "../components/admin/admin_operation_log";
import admin_employee from "../components/admin/admin_employee";

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/admin',
      name: 'admin',
      component: admin
    },
    {
      path:'/user',
      name:'user',
      component:user
    },
    {
      path:'/adminMain',
      name:'adminMain',
      component:admin_header,
      meta:{
        requireAuth:true
      },
      children:[
        {
          path:'search',
          name:'search',
          component:adminOerderSearch,
          meta:{
            requireAuth:true
          },
        },
        {
          path:'accountManage',
          name:'accountManage',
          component:admin_employee,
          meta:{
            requireAuth:true
          },
        },
        {
          path:'OrganManage',
          name:'OrganManage',
          component:dep_manage,
          meta:{
            requireAuth:true
          },
        },
        {
          path:'logManage',
          name:'logManage',
          component:admin_operation_log,
          meta:{
            requireAuth:true
          },
        }
      ]
    },
    {
      path:'/leader',
      name:'leader',
      component:leader_header,
      meta:{
        requireAuth: true
      },
      children:[
        {
          path: 'pendTickets',
          name:'pendTickets',
          component: pending_ticket,
          meta:{
            requireAuth: true
          },
        },
        {
          path: 'examineLog',
          name: 'examineLog',
          component: examine,
          meta:{
            requireAuth: true
          },
        },
        {
          path: 'allOrder',
          name: 'allOrder',
          component: all_work_order,
          meta:{
            requireAuth: true
          },
        }
      ]
    }
  ],
  mode: "history"
})


export default router

// 配置路由权限
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    let type = sessionStorage.getItem("type");
    if(type === "staff"){
      if(sessionStorage.getItem("staff") === 'true'){
        next();
      }
      else{
        next({path:'/user'})
      }
    }
    else if(type === "leader"){
      if(sessionStorage.getItem("leader") === 'true'){
        next();
      }
      else{
        next({path:'/user'})
      }
    }
    else if(type === "root"){
      if(sessionStorage.getItem("root") === 'true'){
        next();
      }
      else{
        next({path:'/root'})
      }
    }
  } else {
      next();
  }
});
