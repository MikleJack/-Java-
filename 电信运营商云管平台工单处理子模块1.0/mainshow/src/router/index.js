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
import staffHeader from "../components/staff/staffHeader";
import applytable from "../components/staff/applytable";
import changetable from "../components/staff/changetable";
import staffAllOrder from "../components/staff/staffAllOrder";
import employeePortal from "../components/staff/staffPortal";
import leaderPortal from "../components/leader/leaderPortal"
import adminPortal from "../components/admin/adminPortal"

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
        requireAuth:true,
        role:'admin'
      },
      children:[
        {
          path: 'home',
          name:'home',
          component: adminPortal,
          meta:{
            requireAuth: true,
            role:'admin'
          },
        },
        {
          path:'search',
          name:'search',
          component:adminOerderSearch,
          meta:{
            requireAuth:true,
            role:'admin'
          },
        },
        {
          path:'accountManage',
          name:'accountManage',
          component:admin_employee,
          meta:{
            requireAuth:true,
            role:'admin'
          },
        },
        {
          path:'OrganManage',
          name:'OrganManage',
          component:dep_manage,
          meta:{
            requireAuth:true,
            role:'admin'
          },
        },
        {
          path:'logManage',
          name:'logManage',
          component:admin_operation_log,
          meta:{
            requireAuth:true,
            role:'admin'
          },
        }
      ]
    },
    {
      path:'/leader',
      name:'leader',
      component:leader_header,
      meta:{
        requireAuth: true,
        role:'leader'
      },
      children:[
        {
          path: 'home',
          name:'home',
          component: leaderPortal,
          meta:{
            requireAuth: true,
            role:'leader'
          },
        },
        {
          path: 'pendTickets',
          name:'pendTickets',
          component: pending_ticket,
          meta:{
            requireAuth: true,
            role:'leader'
          },
        },
        {
          path: 'examineLog',
          name: 'examineLog',
          component: examine,
          meta:{
            requireAuth: true,
            role:'leader'
          },
        },
        {
          path: 'allOrder',
          name: 'allOrder',
          component: all_work_order,
          meta:{
            requireAuth: true,
            role:'leader'
          },
        }
      ]
    },
    {
      path:'/staff',
      name:'staff',
      component:staffHeader,
      meta:{
        requireAuth: true,
        role:'staff'
      },
      children:[
        {
          path: 'home',
          name:'home',
          component: employeePortal,
          meta:{
            requireAuth: true,
            role:'staff'
          },
        },
        {
          path: 'apply',
          name: 'applyTable',
          component: applytable,
          meta:{
            requireAuth: true,
            role:'staff'
          },
        },
        {
          path: 'change',
          name:'changeTable',
          component: changetable,
          meta:{
            requireAuth: true,
            role:'staff'
          },
        },
        {
          path: 'allOrder',
          name:'allOrder',
          component: staffAllOrder,
          meta:{
            requireAuth: true,
            role:'staff'
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
    let type = to.meta.role;
    if(sessionStorage.getItem(type)==='true'){
      next()
    }
    else{
      if(type==="root")
        next({path:"/root"})
      else
        next({path:"/user"})
    }

  } else {
      next();
  }
});
