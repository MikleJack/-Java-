import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import admin from "../components/login/admin";
import header from "../components/header";
import user from "../components/login/user";
import all_work_order from "../components/leader/all_work_order";
import pending_ticket from "../components/leader/pending_ticket";
import ticket_details from "../components/leader/ticket_details";
import leader_header from "../components/leader/leader_header";
import examine from "../components/leader/examine";

Vue.use(Router)

export default new Router({
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
      path:'/head',
      name:'header',
      component: header
    },
    {
      path:'/admin_query',
      name:admin_query,
      component:admin_query
    },
    {
      path:'/all_work_order',
      name:'all_work_order',
      component:all_work_order
    },
    {
      path:'/pending_ticket',
      name:'pending_ticket',
      component:pending_ticket
    },
    {
      path:'/ticket_details',
      name:'ticket_details',
      component:ticket_details
    },
    {
      path:'/leader_header',
      name:'leader_header',
      component:leader_header,
      children:[{
        path:'/leader_header/examine',
        component:examine,
        meta:{
          requireAuth:true
        }
      },{
        path:'/leader_header/pending_ticket',
        component:pending_ticket,
        meta:{
          requireAuth:true
        }
      }, {
        path: '/leader_header/all_work_order',
        component: all_work_order,
        meta: {
          requireAuth: true
        }
      }
      ]
    }
  ],
  mode: "history"
})
