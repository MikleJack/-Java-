import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    workOrderDetailVisibleDetail: false,
    staffAllOrder_OfflineDialogVisible:false,
    staffAllOrder_DelayDialogVisible:false,
    staffAllOrder_DetailDialogVisible:false,
  },
  mutations: {

  }
})
