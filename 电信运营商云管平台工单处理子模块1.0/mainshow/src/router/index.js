import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import admin from "../components/login/admin";
import header from "../components/header";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'admin',
      component: admin
    },
    {
      path:'/head',
      name:'header',
      component: header
    }
  ],
  mode: "history"
})
