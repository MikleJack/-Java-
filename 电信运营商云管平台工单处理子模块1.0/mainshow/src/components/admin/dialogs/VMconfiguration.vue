<template>
  <!--点击虚拟机资源配置后的dialog界面-->
  <el-dialog
    style="text-align: center"
    title="虚拟机资源及价格配置"
    :visible.sync="this.$store.state.dialogVisible_vir"
    width="78%"

    :before-close="handleClose">
    <!--      //折叠面板-->
    <el-collapse v-model="activeNames" @change="handleChange_collapse">
      <el-collapse-item  name="2">
        <template slot="title">
          <p class="title">虚拟机存储价格配置</p>
        </template>
        <el-form :inline="true" :model="this.$store.state.formInline" class="demo-form-inline">
          <el-form-item label="虚拟机每G存储价格(元)">
            <el-input v-model="$store.state.formInline.diskPrice" placeholder="输入每G的虚拟机存储价格"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="color: white;background-color: #52b69a;border-color: #52b69a " @click="onSubmit_storagePrice">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>
      <!--      //折叠面板-->
      <el-collapse-item name="0">
        <template slot="title">
          <p class="title">虚拟机总资源配置</p>
        </template>
        <el-form :inline="true" :model="$store.state.formInline" class="demo-form-inline">
          <el-form-item label="总CPU核数">
            <el-input v-model="$store.state.formInline.cpuCore" placeholder="输入总cup核数"></el-input>
          </el-form-item>
          <el-form-item label="总内存数(G)">
            <el-input v-model="$store.state.formInline.ram" placeholder="输入总内存数"></el-input>
          </el-form-item>
          <el-form-item label="总存储数(G)">
            <el-input v-model="$store.state.formInline.storage" placeholder="输入总存储数"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="color: white;background-color: #52b69a;border-color: #52b69a " @click="onSubmit_allvirtual">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>

      <!--      //折叠面板-->
      <el-collapse-item name="1">
        <template slot="title">
          <p class="title">新增虚拟机</p>
        </template>
        <el-form :model="ruleForm_virtual" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
          <el-form-item label="规格族" prop="description">
            <el-input v-model="ruleForm_virtual.description"></el-input>
          </el-form-item>

          <el-form-item label="处理器型号" prop="processorModel">
            <el-input v-model="ruleForm_virtual.processorModel"></el-input>
          </el-form-item>

          <el-form-item label="VCPu(核)" prop="cpuCore">
            <el-input v-model="ruleForm_virtual.cpuCore"></el-input>
          </el-form-item>
        </el-form>

        <el-form :model="ruleForm_virtual" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
          <el-form-item label="内存(G)" prop="ram">
            <el-input v-model="ruleForm_virtual.ram"></el-input>
          </el-form-item>

          <el-form-item label="处理器频率" prop="processorFrequency">
            <el-input v-model="ruleForm_virtual.processorFrequency"></el-input>
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
        <el-table-column prop="comNum" label="虚拟机编号" width="120" align="center"></el-table-column>
        <el-table-column prop="description" label="规格族" width="150" align="center"></el-table-column>
        <el-table-column prop="processorModel" label="处理机型号" width="150" align="center"></el-table-column>
        <el-table-column prop="cpuCore" label="VCPu(核)" width="120" align="center"></el-table-column>
        <el-table-column prop="ram" label="内存(G)" width="120" align="center"></el-table-column>
        <el-table-column prop="processorFrequency" label="处理器频率(GHz)" width="160" align="center"></el-table-column>
        <el-table-column prop="price" label="价格(元/月)" width="220" align="center">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.price" controls-position="right" @change="handleChange_vir"
                             :step="50" :min="0" :max="9999"
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
              @click="changeprice(scope.row)"
              type="text"
              size="small"
              style="color: #52b69a"
            >
              修改价格
            </el-button>

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

      <el-pagination
        @current-change="pageChange"
        :current-page="currentPage"
        :page-size="this.pagesize"
        layout="prev, pager, next, jumper"
        :total=this.VMtotal>
      </el-pagination>

    </div>
    <span slot="footer" class="dialog-footer">
                <el-button @click="$store.state.dialogVisible_vir = false">取 消</el-button>
<!--                <el-button style="margin-right: 45%;color: white;background-color: #52b69a " @click="virtual_confirm()">确 定</el-button>-->
      </span>
  </el-dialog>
</template>

<script>
export default {
  name: "VMconfiguration",
  data(){
    return{

      //虚拟机总条目数
      VMtotal:0,
      //分页大小
      pagesize:5,
      //当前页
      currentPage:1,

      activeNames: [],//折叠面板
      ruleForm_virtual: {
        comNum:1,
        description:'',
        processorModel: '',
        cpuCore: 1,
        ram:1,
        processorFrequency: 0,
        price: 0,
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
      },
      tableData_vir:[]
    }
  },
  methods:{

    //虚拟机资源配置
    setVm(){
      // this.$axios.get(this.$store.state.url+"")

      this.$axios.get(this.$store.state.url+"/adminHome/getAllVm/?page=0&size=" + this.pagesize).then((res) => {

        this.tableData_vir = res.data.content;
        this.currentPage = 1;
        this.VMtotal = res.data.totalPages * res.data.size;
        // console.log(res.data)
        this.$store.state.formInline.diskPrice = this.tableData_vir[0].diskPrice;
      });
      this.$axios.get(this.$store.state.url+"/adminHome/getVm").then((res)=>{
        if(res.data){
          console.log(res.data)
          this.$store.state.formInline.cpuCore=res.data.cpuCore;
          this.$store.state.formInline.storage=res.data.storage;
          this.$store.state.formInline.ram =res.data.ram;
        }
      });
      // this.dialogVisible_vir=true;
    },

    //折叠面板
    handleChange_collapse(val) {
      console.log(val);
    },

    //头部关闭关闭的函数
    handleClose(done) {
      this.$store.state.dialogVisible_vir = false;
      done();
    },
    //确定修改虚拟机总资源
    onSubmit_allvirtual(){
      this.$store.state.which_page_confirm = 4
      this.$store.state.dialogVisible_modify = true
    },
    //确认修改虚拟机存储价格
    onSubmit_storagePrice(){
      this.$store.state.which_page_confirm = 3
      this.$store.state.dialogVisible_modify = true

    },

    //对虚拟机的dialog的确认
    // virtual_confirm(){
    //   this.$store.state.which_page_confirm = 5
    //   this.$store.state.dialogVisible_modify = true
    //
    // },
    //删除所点击行的虚拟机
    delete_virtual(index,rows){

      this.tableData_vir.splice(index,1)
    },
    //
    handleChange_vir(phyValue) {
      console.log(value);
    },
    //增加虚拟机函数
    add_virtual(){
      if (this.ruleForm_virtual.processorModel === '' || this.ruleForm_virtual.description === ''){
        this.$message.error('error，请将信息填写完整');
      }else{
        this.$axios.post(this.$store.state.url+"/adminHome/insertVirtual",this.ruleForm_virtual).then(
          (res)=>{
            if(res.data===true){
              this.$message({
                message: 'success，成功添加虚拟机',
                type: 'success'
              });
              this.tableData_vir.push(this.ruleForm_virtual)
            }
          })

      }
    },
    //改变分页调用的函数
    pageChange(val){
      this.currentPage=parseInt(val);
      let page = this.currentPage-1;
      this.$axios.get(this.$store.state.url + "/adminHome/getAllVm?page=" + page + "&size=" + this.pagesize).then((res)=>{
        this.tableData_vir = res.data.content;
        this.currentPage = res.data.number + 1;
        this.VMtotal = res.data.totalPages * res.data.size;
      })
    },


    //重置新增虚拟机填写的值
    reset_virtual(){
      this.ruleForm_virtual.price=0
      this.ruleForm_virtual.cpuCore=1
      this.ruleForm_virtual.processorFrequency=0
      this.ruleForm_virtual.ram=1
      this.ruleForm_virtual.description=''
      this.ruleForm_virtual.processorModel=''
    },
    //修改虚拟机的价格
    changeprice(row){
      this.$store.state.comNum = row.comNum;
      this.$store.state.comprice = row.price;

      this.$store.state.which_page_confirm = 5
      this.$store.state.dialogVisible_modify = true
    }
  }
}
</script>

<style scoped>

</style>
