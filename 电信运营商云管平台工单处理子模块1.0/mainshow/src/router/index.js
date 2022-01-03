import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import admin from "../components/login/admin";
import admin1 from "../components/login/admin1";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'admin',
      component: admin1
    },

  ],
  mode: "history"
})
