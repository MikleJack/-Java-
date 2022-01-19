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
    pendtickets_dialogTableVisible:false,

    //管理员显示控制部门预算的弹窗
    dialogVisible_budget: false,
    //管理员显示控制物理机的弹窗
    dialogVisible_phy: false,
    //管理员显示控制虚拟机的弹窗
    dialogVisible_vir: false,

    //确认密码的dialog
    dialogVisible_modify:false,

    //管理员验证密码的正确性
    password_confirm:false,
    //对哪个dialog页面进行密码验证，1：预算页面，2：物理机页面，3：虚拟机存储价格页面，4：虚拟机总资源页面，5：虚拟机页面
    which_page_confirm:0,

    //修改的信息
    formInline: {
      cpuCore: 0,
      ram: 0,
      storage:0,
      diskPrice:0.5
    },


    url:"https://localhost:8084",
    FileName:''
  },
  mutations: {

  },



})
