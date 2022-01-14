import Vue from 'vue'
import Vuex from 'vuex'
import fa from "element-ui/src/locale/lang/fa";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    workOrderDetailVisibleDetail: false,
    staffAllOrder_OfflineDialogVisible:false,
    staffAllOrder_DelayDialogVisible:false,
    staffAllOrder_DetailDialogVisible:false,
    order_detail_dialogTableVisible:false,
    pendtickets_dialogTableVisible:false
  },
  mutations: {

  }
})
