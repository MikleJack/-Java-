import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import admin from "../components/login/admin";
import header from "../components/header";
import user from "../components/login/user";

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
    }
  ],
  mode: "history"
})
