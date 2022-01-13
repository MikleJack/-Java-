<template>
  <div class="adminPortal">
    <!--页面顶部：用户信息-->
    <div class="left-top">
      <!--头像-->
      <div class="headPortrait" :key="fit">
        <el-image class="head" :src="url" :fit="fit">
        </el-image>
      </div>
      <!--信息-->
      <div class="worker_information">
        <!--权限-->
        <div class="powerInf">
          <el-tag class="power" type="success">{{worker_power}}</el-tag>
        </div>
        <!--姓名及所属部门-->
        <div class="nameInf">
          <p>{{worker_name}}</p>
        </div>
        <div class="depInf">
          <p>{{worker_dep}}</p>
        </div>
        <div class="workerInf">
          <p>工号 ：{{worker_num}}</p>
        </div>
      </div>
    </div>
    <!--页面饼图部分-->
    <div class="right-top">
      <!--物理机-->
      <div class="phy">
        <!--饼图：物理机利用率饼图-->
        <div class="phyPie">
          <el-progress :percentage="total_Phyutilization" :color="resCustomColor"
                       style="margin-left: 27%; margin-top: 20%"
                       type="circle" stroke-width="15" width="120"></el-progress>
        </div>
        <div class="phyMessage">
          <p style="text-align: center" >系统物理机利用率</p>
        </div>
      </div>
      <!--预算-->
      <div class="bud">
        <!--饼图：预算使用情况饼图-->
        <div class="budPie">
          <el-progress :percentage="depBudget" :color="budCustomcolors"
                       style="margin-left: 26%; margin-top: 20%"
                       type="dashboard" stroke-width="15" width="120"></el-progress>
        </div>
        <div class="budMessage">
          <p style="text-align: center">公司预算使用情况</p>
        </div>
      </div>
      <!--虚拟机-->
      <div class="vir">
        <!--饼图：虚拟机利用率饼图-->
        <div class="virPie">
          <el-progress :percentage="total_Virutilization" :color="resCustomColor"
                       style="margin-left:27%; margin-top: 20%"
                       type="circle" :stroke-width="15" :width="120"></el-progress>
        </div>
        <div class="virMessage">
          <p style="text-align: center">系统虚拟机利用率</p>
        </div>
      </div>
    </div>
    <!--通知栏-->
    <div class="left-bottom">
      <div class="size-icon">
        <i class="el-icon-message-solid" ></i>
      </div>
      <div class="message_title">
        通知中心
      </div>
      <div class="round-i1">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729已被审批通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729审批未通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729即将到期 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 您的账号已被解除锁定 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 请尽快完善个人信息 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729已被审批通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>
      <div class="round-i">
        <i class="round"></i>
        <div class="message"> 工单202234567891235729审批未通过 </div>
        <div class="message_date">2022.02.12</div>
      </div>

      <div class="pagination">
        <el-pagination
          layout="prev, pager, next"
          :total="1000"
          style="color: #0c805f">
        </el-pagination>
      </div>
    </div>
    <!--右下方-->
    <div class="right-bottom">
      <div class="chart">
        <!--公司预算折线图-->
        <div class="budChart" id="budmain"></div>
      </div>
      <!--管理员按钮-->
      <div class="adminBottom">
        <el-row class="adminButton1" type="flex" justify="center" align="middle">
          <el-tooltip effect="light" content="部门预算设置" placement="left">
          <el-button style="margin-top: 20%; color: white;background-color: #52b69a;font-size: 30px"
                     @click="dialogVisible_budget=true" size="mini"
                     icon="el-icon-odometer" circle
                     ></el-button>
          </el-tooltip>
        </el-row>
        <el-row class="adminButton2" type="flex" justify="center" align="middle">
          <el-tooltip effect="light" content="物理机资源及价格配置" placement="left">
          <el-button style="margin-top: 10%;color: white;background-color: #52b69a;font-size: 30px"
                     @click="dialogVisible_phy=true" size="mini"
                     icon="el-icon-monitor" circle></el-button>
          </el-tooltip>
        </el-row>
        <el-row class="adminButton2" type="flex" justify="center" align="middle">
          <el-tooltip effect="light" content="虚拟机资源及价格配置" placement="left">
            <el-button style="margin-top: 10%;color: white;background-color: #52b69a;font-size: 30px"
                       @click="dialogVisible_vir=true" size="mini"
                       icon="el-icon-connection" circle></el-button>
          </el-tooltip>
        </el-row>
      </div>
    </div>

    <!--    点击部门预算设置后的dialog界面-->
    <el-dialog
      style="text-align: center"
      title="部门预算设置"
      :visible.sync="dialogVisible_budget"
      width="500px"
      height="700px"
      :before-close="handleClose">
      <div class="block" style="text-align: center">
        <el-table class="budTable" frame=above
          :data="tableData_bud"
          border
          style="width: 100%"
        :cell-style="{textAlign:'center'}">
          <el-table-column prop="department_name" label="部门名称" width="180" align="center">
          </el-table-column>
          <el-table-column prop="budget" label="预算(万元)" width="278.5" align="center">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.budget" controls-position="right" @change="handleChange_bud"
                               :precision="2" :step="1" :min="0" :max="9999"
                               style="margin-left: 8%" size="mini"></el-input-number>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_budget=false">取 消</el-button>
                <el-button style="margin-right: 32%;color: white;background-color: #52b69a " @click="budget_confirm()">确 定</el-button>
      </span>
    </el-dialog>

    <!--    点击物理机资源配置后的dialog界面-->

    <el-dialog
      style="text-align: center"
      title="物理机资源及价格配置"
      :visible.sync="dialogVisible_phy"
      width="58%"


      :before-close="handleClose">

<!--      <el-button @click="dialogVisible_phy = false" style="float: left">新增</el-button>-->

      <el-collapse v-model="activeNames" @change="handleChange_collapse">
        <el-collapse-item title="新增物理机" name="0">
            <el-form :model="ruleForm_physics" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
              <el-form-item label="CPU(核)" prop="cpu_core">
                <el-input v-model="ruleForm_physics.cpu_core"></el-input>
              </el-form-item>

              <el-form-item label="内存(G)" prop="ram">
                <el-input v-model="ruleForm_physics.ram"></el-input>
              </el-form-item>
            </el-form>

          <el-form :model="ruleForm_physics" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
            <el-form-item label="存储(G)" prop="storage">
              <el-input v-model="ruleForm_physics.storage"></el-input>
            </el-form-item>

            <el-form-item label="单价(元)" prop="price">
              <el-input v-model="ruleForm_physics.price"></el-input>
            </el-form-item>
          </el-form>

          <el-button @click="reset_physics">重置</el-button>
          <el-button @click="add_physics" style="color: white;background-color: #52b69a ">新增</el-button>


        </el-collapse-item>
      </el-collapse>
      <p></p>
      <div class="block" style="text-align: center">
        <el-table class="budTable" frame=above
                  :data="tableData_phy"
                  border
                  style="width: 100%"
                  :cell-style="{textAlign:'center'}">
<!--          <el-table-column-->
<!--            type="selection"-->
<!--            width="55">-->
<!--          </el-table-column>-->
          <el-table-column prop="phyNum" label="物理机编号" width="120" align="center"></el-table-column>
          <el-table-column prop="cpu_core" label="CPU(核)" width="120" align="center"></el-table-column>
          <el-table-column prop="ram" label="内存(G)" width="120" align="center"></el-table-column>
          <el-table-column prop="storage" label="存储(G)" width="120" align="center"></el-table-column>
          <el-table-column prop="price" label="价格(元/月)" width="240" align="center">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.index" controls-position="right" @change="handleChange_phy"
                                :step="50" :min="0" :max="9999"
                               style="margin-left: 8%" size="mini"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            align="center"
            width="100">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="delete_physics(scope.$index, scope.row)"
                type="text"
                size="small"
                style="color: #52b69a"
              >
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_phy = false">取 消</el-button>
                <el-button style="margin-right: 40%;color: white;background-color: #52b69a " @click="physics_confirm()">确 定</el-button>
      </span>
    </el-dialog>



    <!--点击虚拟机资源配置后的dialog界面-->
    <el-dialog
      style="text-align: center"
      title="虚拟机资源及价格配置"
      :visible.sync="dialogVisible_vir"
      width="78%"

      :before-close="handleClose">


<!--      //折叠面板-->
      <el-collapse v-model="activeNames" @change="handleChange_collapse">
        <el-collapse-item title="虚拟机总资源配置" name="2">
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="虚拟机每G存储价格(元)">
              <el-input v-model="formInline.storage_price" placeholder="输入每G的虚拟机存储价格"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit_storagePrice">确定</el-button>
            </el-form-item>
          </el-form>
        </el-collapse-item>

        <!--      //折叠面板-->
        <el-collapse-item title="虚拟机总资源配置" name="0">
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="总CPU核数">
              <el-input v-model="formInline.cpu_core" placeholder="输入总cup核数"></el-input>
            </el-form-item>
            <el-form-item label="总内存数(G)">
              <el-input v-model="formInline.ram" placeholder="输入总内存数"></el-input>
            </el-form-item>
            <el-form-item label="总存储数(G)">
              <el-input v-model="formInline.storage" placeholder="输入总存储数"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit_allvirtual">确定</el-button>
            </el-form-item>
          </el-form>
        </el-collapse-item>
        <!--      //折叠面板-->
        <el-collapse-item title="新增虚拟机" name="1">
          <el-form :model="ruleForm_virtual" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
            <el-form-item label="规格族" prop="description">
              <el-input v-model="ruleForm_virtual.description"></el-input>
            </el-form-item>

            <el-form-item label="处理器型号" prop="processor_model">
              <el-input v-model="ruleForm_virtual.processor_model"></el-input>
            </el-form-item>

            <el-form-item label="VCPu(核)" prop="cpu_core">
              <el-input v-model="ruleForm_virtual.cpu_core"></el-input>
            </el-form-item>
          </el-form>

          <el-form :model="ruleForm_virtual" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
            <el-form-item label="内存(G)" prop="ram">
              <el-input v-model="ruleForm_virtual.ram"></el-input>
            </el-form-item>

            <el-form-item label="处理器频率" prop="processor_frequency">
              <el-input v-model="ruleForm_virtual.processor_frequency"></el-input>
            </el-form-item>

            <el-form-item label="单价(元)" prop="price">
              <el-input v-model="ruleForm_virtual.price"></el-input>
            </el-form-item>
          </el-form>

          <el-button @click="reset_virtual">重置</el-button>
          <el-button @click="add_virtual" style="color: white;background-color: #52b69a " :plain="true">新增</el-button>


        </el-collapse-item>
      </el-collapse>
      <p></p>


      <div class="block" style="text-align: center">
        <el-table class="budTable" frame=above
                  :data="tableData_vir"
                  border
                  style="width: 100%"
                  :cell-style="{textAlign:'center'}">
          <el-table-column prop="virNum" label="虚拟机编号" width="120" align="center"></el-table-column>
          <el-table-column prop="description" label="规格族" width="150" align="center"></el-table-column>
          <el-table-column prop="processor_model" label="处理机型号" width="150" align="center"></el-table-column>
          <el-table-column prop="cpu_core" label="VCPu(核)" width="120" align="center"></el-table-column>
          <el-table-column prop="ram" label="内存(G)" width="120" align="center"></el-table-column>
          <el-table-column prop="processor_frequency" label="处理器频率(GHz)" width="160" align="center"></el-table-column>
          <el-table-column label="价格(元/月)" width="220" align="center">
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.index" controls-position="right" @change="handleChange_vir"
                               :step="50" :min="0" :max="9999"
                               style="margin-left: 8%" size="mini"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            align="center"
            width="100">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="delete_virtual(scope.$index, scope.row)"
                type="text"
                size="small"
                style="color: #52b69a"
              >
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_vir = false">取 消</el-button>
                <el-button style="margin-right: 45%;color: white;background-color: #52b69a " @click="virtual_confirm()">确 定</el-button>
      </span>
    </el-dialog>



    <!--确认密码的dialog-->
    <el-dialog
      title="确认密码"
      :visible.sync="dialogVisible_modify"
      width="30%"
      :before-close="handleClose">
      <span>确定添加一个账户？</span>
      <el-input placeholder="请输入密码" v-model="confirm_password" show-password></el-input>

      <span slot="footer" class="dialog-footer">
                <el-button @click="cancel_confirm()">取 消</el-button>
                <el-button type="primary" @click="complete_confirm()" >确 定</el-button>
              </span>
    </el-dialog>
  </div>



</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "adminPortal",
  components: {},


// 表格数据
  data() {
    return {
      confirm_password:'',

      //dialog中的密码验证参数
      password_confirm: false,
      //对哪个dialog页面进行密码验证，1：预算页面，2：物理机页面，3：虚拟机存储价格页面，4：虚拟机总资源页面，5：虚拟机页面
      which_page_confirm:0,

      activeNames: [],//折叠面板
      // 全部物理机资源利用率
      total_Phyutilization:'76.29',
      // 全部虚拟机资源利用率
      total_Virutilization:'35.83',
      // 部门预算使用率
      depBudget:'78.90',
      fits: ['cover'],
      //头像地址
      url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F40%2Fc9%2F53%2F40c9533e47b9ce0945a2030f9320b80e.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644379289&t=487f84fb7f4d9252f4fa8ef334c39618',
      //左上角信息展示数据
      //权限
      worker_power:'管理员',
      //姓名
      worker_name:'Admin',
      //部门
      worker_dep:'软件学院',
      //工号
      worker_num:'root',

      //部门预算
      // //部门名称
      // department_name:'小组1',
      // //具体部门预算
      // department_nameBud:'10',

      //确认密码的dialog
      dialogVisible_modify:false,

      dialogVisible_budget: false,
      dialogVisible_phy: false,
      dialogVisible_vir: false,

      formInline: {
        cpu_core: 0,
        ram: 0,
        storage:0,
        storage_virtual:0,
        storage_price:0.5
      },

      tableData_bud: [{
        department_name: '小组1',
        budget: '10',
      },{
        department_name: '小组2',
        budget: '10',
      }
      ],

      tableData_phy:[{
        phyNum: 1,
        cpu_core: 1,
        ram: 100,
        storage: 100,
        price:100
      },{
        phyNum: 2,
        cpu_core: 2,
        ram: 100,
        storage: 100,
        price:100
      },{
        phyNum: 3,
        cpu_core: 3,
        ram: 100,
        storage: 100,
        price:100
      },
      ],
      num: '',

      tableData_vir:[{
        virNum:1,
        description:'s6',
        processor_model: 'intel 1',
        cpu_core: 1,
        ram:8,
        processor_frequency: 2.4,
        price: 100,
      },{
        virNum:2,
        description:'s6',
        processor_model: 'intel 1',
        cpu_core: 3,
        ram:8,
        processor_frequency: 2.4,
        price: 200,
      },{
        virNum:3,
        description:'s6',
        processor_model: 'intel 1',
        cpu_core: 5,
        ram:8,
        processor_frequency: 2.4,
        price: 300,
      }],


      ruleForm_physics: {
        phyNum:1,
        cpu_core: 1,
        ram:1,
        storage:1,
        price:0
      },
      ruleForm_virtual: {
        virNum:1,
        description:'',
        processor_model: '',
        cpu_core: 1,
        ram:1,
        processor_frequency: 0,
        price: 0,
      },
      rules: {
        description: [
          { required: true, message: '请输入规格族', trigger: 'blur' },
        ],
        processor_model: [
          { required: true, message: '请输入处理器型号', trigger: 'blur' },
        ],
        processor_frequency: [
          { required: true, message: '请输入处理器频率', trigger: 'blur'},
        ],
        cpu_core: [
          { required: true, message: '请输入cpu核数', trigger: 'blur' },
        ],
        ram: [
          { required: true, message: '请输入内存大小', trigger: 'blur' },
        ],
        storage: [
          { required: true, message: '请输入存储大小', trigger: 'blur' },
        ],
        price: [
          { required: true, message: '请输入该物理机单价', trigger: 'blur'},
        ],

      }
    }

  },
  mounted(){
    const chartDom = document.getElementById('budmain');
    const budChart = echarts.init(chartDom);
    let budOption;
    budOption = {
      width: '600px',
      height: '250px',
      color:'#349165',
      title:{
        tooltip: {
          trigger: 'item',
          triggerOn: 'click',
          formatter: '{b}:{c}'
        },
        subtext:'近四年部门预算使用情况',
        x:'center',
        subtextStyle: {//副标题文本样式{"color": "#aaa"}
          // fontFamily: 'Arial, Verdana, sans...',
          fontSize: 15,
          fontStyle: 'normal',
          fontWeight: 'normal',
          "color": "#48b981"
        },
      },
      xAxis: {
        type: 'category',
        data: ['2019', '2020', '2021', '2022'],
        scale: true
      },
      yAxis: {
        type: 'value',
        scale: true
      },
      series: [
        {
          data: [8.7, 9.6, 7.4, 0.9],
          type: 'line',
          label: {
            show: true,	// 是否可见
            // color:'green',
            rotate: 0 	// 旋转角度
          },
          lineStyle:{
            color: 'green',
            // type: 'dashed'//可选值还有 dotted  solid
          },
          //显示最大值与最小值
          markPoint: {
            data: [
              {
                type: 'max', name: '最大值'
              },
              {
                type: 'min', name: '最小值'
              }
            ]
          },
          //显示平均值
          markLine: {
            data: [
              {
                type: 'average', name: '平均值'
              }
            ]
          }
        }
      ]
    };
    budOption && budChart.setOption(budOption);

  },
  methods: {
    //折叠面板
    handleChange_collapse(val) {
      console.log(val);
    },

    resCustomColor(total_Phyutilization) {
      if (total_Phyutilization < 50 ) {
        return 'rgb(255,186,39)';
      } else if (total_Phyutilization > 80) {
        return 'rgb(226,131,22)';
      } else return '#41c6a2'
    },

    budCustomcolors(depBudget){
      if (depBudget <= 90){
        return '#41c6a2';
      } else return '#f56c6c';
    },

    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },

    //部门预算设置的dialog函数
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },

    handleChange_bud(budValue) {
      console.log(value);
    },
    handleChange_phy(phyValue) {
      console.log(value);
    },
    handleChange_vir(phyValue) {
      console.log(value);
    },

    //增加物理机函数
    add_physics() {
      this.$message({
        message: 'success，成功添加虚拟机',
        type: 'success'
      });
      this.tableData_phy.push(this.ruleForm_physics)
    },
    //重置新增物理机填写的值
    reset_physics(){
      this.ruleForm_physics.price=0
      this.ruleForm_physics.cpu_core=1
      this.ruleForm_physics.storage=1
      this.ruleForm_physics.ram=1
    },
    //删除所点击行的物理机
    delete_physics(index,rows){
      this.tableData_phy.splice(index,1)
    },

    //增加虚拟机函数
    add_virtual(){
      if (this.ruleForm_virtual.processor_model === '' || this.ruleForm_virtual.description === ''){
        this.$message.error('error，请将信息填写完整');
      }else{
        this.$message({
          message: 'success，成功添加虚拟机',
          type: 'success'
        });
        this.tableData_vir.push(this.ruleForm_virtual)
      }
    },

    //重置新增虚拟机填写的值
    reset_virtual(){
      this.ruleForm_virtual.price=0
      this.ruleForm_virtual.cpu_core=1
      this.ruleForm_virtual.processor_frequency=0
      this.ruleForm_virtual.ram=1
      this.ruleForm_virtual.description=''
      this.ruleForm_virtual.processor_model=''
    },

    //删除所点击行的虚拟机
    delete_virtual(index,rows){
      this.tableData_vir.splice(index,1)
    },


    //密码验证取消
    cancel_confirm(){
      this.dialogVisible_modify = false
      this.$message.error('error，密码验证失败');
      this.password_confirm = false
      this.which_page_confirm = 0
    },
    //密码验证确认
    complete_confirm(){
      this.password_confirm = true
      if (this.which_page_confirm === 1){

        if(this.password_confirm === true){

        }else{

        }

      }else if(this.which_page_confirm === 2){

        if(this.password_confirm === true){

        }else{

        }

      }else if(this.which_page_confirm === 3){

        if(this.password_confirm === true){

        }else{

        }

      }else if(this.which_page_confirm === 4){

        if(this.password_confirm === true){

        }else{

        }

      }else if(this.which_page_confirm === 5){

        if(this.password_confirm === true){

        }else{

        }

      }else{
        this.dialogVisible_modify = false
      }
      this.$message({
        message: 'success，密码验证成功，成功修改虚拟机总资源',
        type: 'success'
      });

    },

    //确定修改虚拟机总资源
    onSubmit_allvirtual(){
      this.which_page_confirm = 4
      this.dialogVisible_modify = true
    },
    //确认修改虚拟机存储价格
    onSubmit_storagePrice(){
      this.which_page_confirm = 3
      this.dialogVisible_modify = true

    },

    //对虚拟机的dialog的确认
    virtual_confirm(){
      this.which_page_confirm = 5
      this.dialogVisible_modify = true

    },

    //对物理机的dialog的确认
    physics_confirm(){
      this.which_page_confirm = 2
      this.dialogVisible_modify = true
    },

    //对预算dialog的确认
    budget_confirm(){
      this.which_page_confirm = 1
      this.dialogVisible_modify = true
    }

  },
}
</script>

<style scoped>
.adminPortal{
  width: 100%;
  height: 100%;
  position: relative;
  overflow-y: hidden;
}
.left-top{
  width: 37%;
  height: 38%;
  float: left;
  /*background-color: #73c8b3;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.headPortrait{
  width: 45%;
  height: 100%;
  float: left;
  /*background-color: #55cfb0;*/
  /*布局线框颜色*/
  /*border:3px solid #000*/
}
.head{
  width: 90%;
  height: 90%;
  margin-top: 5%;
  margin-left: 5%;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  border:2px solid #53c59e;

}
.worker_information{
  width: 55%;
  height: 100%;
  float: left;
  /*background-color: #1caf8a;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.powerInf{
  width: 100%;
  height: 15%;
  float: left;
  /*background-color: #147960;*/
}
.power{
  margin-left: 70%;
  margin-top: 5%;
}
.nameInf{
  width: 100%;
  height: 20%;
  font-size: 35px;
  margin-left: 10%;
  /*background-color: #55cfb0;*/
}
.depInf{
  width: 100%;
  height: 15%;
  font-size: 25px;
  margin-left: 10%;
  /*background-color: #52b69a;*/
}
.workerInf{
  width: 100%;
  height: 50%;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.55);
  margin-top: 5%;
  margin-left: 10%;
  /*background-color: #19755e;*/
}
.right-top{
  width: 63%;
  height: 38%;
  float: left;
  /*background-color: #235c4e;*/
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.phy{
  width: 30%;
  height: 100%;
  float: left;
  /*background-color: #73c8b3;*/
}
.phyPie{
  width: 100%;
  height: 75%;
  float: left;

  /*background-color: #73c8b3;*/
}
.phyMessage{
  width: 100%;
  height: 25%;
  float: left;
  font-size: 15px;
  /*margin-left: 14%;*/
  /*background-color: #73c8b3;*/
}
.bud{
  width: 30%;
  height: 100%;
  float: left;
  /*background-color: #3eb698;*/
}
.budPie{
  width: 100%;
  height: 75%;
  float: left;
  /*background-color: #3eb698;*/
}
.budMessage{
  width: 100%;
  height: 25%;
  float: left;
  font-size: 15px;
  /*background-color: #36ba99;*/
}
.vir{
  width: 30%;
  height: 100%;
  float: left;
  /*background-color: #1e4d42;*/
}
.virPie{
  width: 100%;
  height: 75%;
  float: left;
  /*background-color: #1e4d42;*/
}
.virMessage{
  width: 100%;
  height: 25%;
  float: left;
  font-size: 15px;
  /*background-color: #4cbda3;*/
}
.left-bottom{
  width: 36%;
  height: 61%;
  float: left;
  /*background-color: #33ab8a;*/
  border:2px solid #53c59e
  /*布局线框颜色*/
  /*border-bottom:2px dashed #55c97e*/
}
.right-bottom{
  width: 63%;
  height: 62%;
  float: left;
  /*background-color: #50b299;*/
  /*布局线框颜色*/
  /*border:2px solid #53c59e*/
  /*border-bottom:2px dashed #55c97e*/
}
.chart{
  width: 85%;
  height: 100%;
  float: left;
  /*background-color: #34957a*/
}
.budChart{
  width: 100%;
  height: 100%;
  float: left;
  margin-top: 5%;
  /*background-color: #34957a*/
}
.adminButton1 {
  height: 20%;
}
.adminButton2 {
  height: 20%;
}
.budTable{
  /*border:2px solid #53c59e;*/
  /*border-color: #52b69a;*/
  border:solid #add9c0;
  border-width:1px 1px 1px 1px;
}
.size-icon{
  font-size: 50px;
  color: #0c805f;
  width:60px;
  float: left;
  height:50px;
}
.message_title{
  font-size: 25px;
  color: #3e8f76;
  font-weight: bolder;
  margin-left: 40%;
  margin-top: 2%;
}
.round-i1{


  height: 10px;
  /*width: 10px;*/
  margin-top: 10%;
  margin-left: 3%;
}
.round-i{


  height: 10px;
  /*width: 10px;*/
  margin-top: 30px;
  margin-left: 3%;
}
.round{
  display: block;
  float: left;
  height: 10px;
  width: 10px;
  border-radius: 5px;
  margin-top: 6px;

  background: rgb(62, 143, 118) !important;

  margin-left: 0;
}
.message{
  display: block;
  float: left;
  width: fit-content;
  color: rgba(12, 12, 12, 0.51);
  margin-left: 5px;
}
.message_date{
  display: block;
  float: right;
  color: rgba(12, 12, 12, 0.51);

  margin-right: 5px;
}
.pagination {
  height: fit-content;
  margin-bottom: 1px;
  margin-top: 5%;
}

</style>
