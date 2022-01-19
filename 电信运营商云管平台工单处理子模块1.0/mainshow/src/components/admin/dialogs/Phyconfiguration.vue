<template>
  <el-dialog
    style="text-align: center"
    title="物理机资源及价格配置"
    :visible.sync="this.$store.state.dialogVisible_phy"
    width="58%"


    :before-close="handleClose">

    <!--      <el-button @click="dialogVisible_phy = false" style="float: left">新增</el-button>-->

    <el-collapse v-model="activeNames" @change="handleChange_collapse">
      <el-collapse-item name="0">
        <template slot="title">
          <p class="title">新增物理机</p>
        </template>
        <el-form :model="ruleForm_physics" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
          <el-form-item label="CPU(核)" prop="cpuCore">
            <el-input v-model="ruleForm_physics.cpuCore"></el-input>
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
        <el-table-column prop="comNum" label="物理机编号" width="120" align="center"></el-table-column>
        <el-table-column prop="cpuCore" label="CPU(核)" width="120" align="center"></el-table-column>
        <el-table-column prop="ram" label="内存(G)" width="120" align="center"></el-table-column>
        <el-table-column prop="storage" label="存储(G)" width="120" align="center"></el-table-column>
        <el-table-column prop="price" label="价格(元/月)" width="240" align="center">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.price" controls-position="right" @change="handleChange_phy"
                             :step="50" :min="0" :max="999999"
                             style="margin-left: 8%" size="mini"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          align="center"
          width="auto">
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

      <el-pagination
        @current-change="pageChange"
        :current-page="currentPage"
        :page-size="this.pagesize"
        layout="prev, pager, next, jumper"
        :total=this.Phytotal>
      </el-pagination>

    </div>
    <span slot="footer" class="dialog-footer">
                <el-button @click="$store.state.dialogVisible_phy = false">取 消</el-button>
                <el-button style="margin-right: 40%;color: white;background-color: #52b69a " @click="physics_confirm()">确 定</el-button>
      </span>
  </el-dialog>
</template>

<script>
export default {
  name: "Phyconfiguration",
  data(){
    return{

      //物理机总条目数
      Phytotal:0,
      //分页大小
      pagesize:5,
      //当前页
      currentPage:1,

      activeNames: [],
      tableData_phy:[],
      //折叠面板
      ruleForm_physics: {
        comNum:1,
        cpuCore: 1,
        ram:1,
        storage:1,
        price:0
      },
      rules: {
        description: [
          { required: true, message: '请输入规格族', trigger: 'blur' },
        ],
        processorModel: [
          { required: true, message: '请输入处理器型号', trigger: 'blur' },
        ],
        processorFrequency: [
          { required: true, message: '请输入处理器频率', trigger: 'blur'},
        ],
        cpuCore: [
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
  methods:{
    //物理机资源配置
    setphy(){
      this.$axios.get(this.$store.state.url+"/adminHome/getAllPhy?page=0&size=" + this.pagesize).then((res) => {

        this.tableData_phy = res.data.content;
        this.currentPage = 1;
        this.Phytotal = res.data.totalPages * res.data.size;
      });
      // this.dialogVisible_phy=true;
    },
    //折叠面板
    handleChange_collapse(val) {
      console.log(val);
    },
    //头部关闭关闭的函数
    handleClose(done) {
      this.$store.state.dialogVisible_phy = false;
      done();
    },
    //删除所点击行的物理机
    delete_physics(index,rows){

      this.tableData_phy.splice(index,1)


    },
    //对物理机的dialog的确认
    physics_confirm(){
      this.$store.state.which_page_confirm = 2
      this.$store.state.dialogVisible_modify = true
    },
    handleChange_phy(phyValue) {
      console.log(value);
    },
    //重置新增物理机填写的值
    reset_physics(){
      this.ruleForm_physics.price=0
      this.ruleForm_physics.cpuCore=1
      this.ruleForm_physics.storage=1
      this.ruleForm_physics.ram=1
    },
    //增加物理机函数
    add_physics() {
      this.$axios.post(this.$store.state.url+"/adminHome/insertPhysics",{
        cpuCore : this.ruleForm_physics.cpuCore,
        ram: this.ruleForm_physics.ram,
        storage: this.ruleForm_physics.storage,
        price: this.ruleForm_physics.price
      }).then((res)=>{
        if (res.data===true)
        {
          this.$message({
            message:'添加成功',
            type:'success'
          })
          this.tableData_phy.push(this.ruleForm_physics);
        }else
          this.$message({
            message: '添加失败',
            type: 'error'
          });

      })
    },
    //改变分页调用的函数
    pageChange(val){
      this.currentPage=parseInt(val);
      let page = this.currentPage-1;
      this.$axios.get(this.$store.state.url + "/adminHome/getAllPhy?page=" + page + "&size=" + this.pagesize).then((res)=>{
        this.tableData_phy = res.data.content;
        this.currentPage = res.data.number + 1;
        this.Phytotal = res.data.totalPages * res.data.size;
      })
    },
  }
}
</script>

<style scoped>

</style>
