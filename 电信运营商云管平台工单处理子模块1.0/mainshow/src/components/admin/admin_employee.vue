<template>
  <div>
<!--查询-->
    <el-form :inline="true" :model="searchForm" class="demo-form-inline">
      <el-form-item label="员工姓名">
        <!--          通过项目名称搜索项目-->
        <el-input
          placeholder="输入员工姓名搜索"
          v-model="searchOrderWorkerName">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleClick_search" class="query">查询</el-button>
        <el-button @click="handleClick_clean">清空</el-button>

        <!--    新增账户-->
        <el-button @click="dialogVisible_add = true" type="primary" icon="el-icon-plus" class="query">新增账号</el-button>
      </el-form-item>
    </el-form>
    <div>

<!--       .slice((currentPage-1)*pageSize,currentPage*pageSize)"-->
<!--      人员管理页面的列表-->
      <el-table
        :data="tableData"

        border
        style="width: 100%">
        <el-table-column
          prop="workNum"
          label="工号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="联系方式"
          width="180">
        </el-table-column>
        <el-table-column
          prop="depName"
          label="部门名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="depLevel"
          label="部门级别"
          width="120">
        </el-table-column>
        <el-table-column
          prop="state_string"
          label="账户状态"
          width="150">
        </el-table-column>

        <el-table-column
          fixed="right"
          label="操作"


          >
<!--          对账户的各种操作-->
          <templte slot-scope="scope" >
            <el-button v-if="scope.row.inService"  @click="reset(scope.row)" type="text" size="small">重置密码</el-button>
            <el-button  @click="lock(scope.row)" type="text" size="small" v-if="scope.row.state_string==='未锁定' && scope.row.inService">锁定</el-button>
            <el-button  @click="unlock(scope.row)" type="text" size="small"  style="color: red " v-if="scope.row.state_string!=='未锁定' && scope.row.inService">解锁</el-button>
            <el-button v-if="scope.row.inService" @click="delect(scope.row)" type="text" size="small">删除</el-button>
            <div v-if="!scope.row.inService" style="color: red;font-size: 10px">已离职</div>
          </templte>
        </el-table-column>
      </el-table>

      <div class="paging">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pageSize"
          layout=" prev, pager, next, jumper"
          :total="totalSize">
        </el-pagination>
      </div>

<!--确认密码的dialog-->
      <el-dialog
        title="确认密码"
        :visible.sync="dialogVisible_add"
        width="30%"
        :before-close="handleClose">
        <span>确定添加一个账户？</span>
        <el-input placeholder="请输入密码" v-model="password_confirm" show-password></el-input>

        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_add = false">取 消</el-button>
                <el-button type="primary" @click="addAccount()" >确 定</el-button>
              </span>
      </el-dialog>
<!--      增加账户的dialog-->
      <el-dialog
        title="添加账户"
        :visible.sync="dialogVisible_addAccount"
        width="30%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="请输入姓名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="请选择部门" prop="department">
            <el-select v-model="ruleForm.department" placeholder="请选择部门">
              <el-option
                v-for="item in options"
                :key="item.depNum"
                :label="item.depName"
                :value="item.depNum">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_addAccount = false">取 消</el-button>
                <el-button type="primary" @click="add_AccountNumber">确 定</el-button>
              </span>
      </el-dialog>
<!--      重置账户的dialog-->
      <el-dialog
        title="重置"
        :visible.sync="dialogVisible_reset"
        width="30%"
        :before-close="handleClose">
        <span>确定重置该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="password_confirm" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_reset = false">取 消</el-button>
                <el-button type="primary" @click="handleClick_reset">确 定</el-button>
              </span>
      </el-dialog>
<!--锁定账户的dialog-->
      <el-dialog
        title="锁定"
        :visible.sync="dialogVisible_lock"
        width="30%"
        :before-close="handleClose">
        <span>确定锁定该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="password_confirm" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_lock = false">取 消</el-button>
                <el-button type="primary" @click="handleClick_lock">确 定</el-button>
              </span>
      </el-dialog>
<!--解锁账户的dialog-->
      <el-dialog
        title="解锁"
        :visible.sync="dialogVisible_unlock"
        width="30%"
        :before-close="handleClose">
        <span>确定解锁该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="password_confirm" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_unlock = false">取 消</el-button>
                <el-button type="error" @click="handleClick_unlock">确 定</el-button>
              </span>
      </el-dialog>
<!--删除账户的dialog-->
      <el-dialog
        title="删除"
        :visible.sync="dialogVisible_delete"
        width="30%"
        :before-close="handleClose">
        <span>确定删除该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="password_confirm" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_delete = false">取 消</el-button>
                <el-button type="primary" @click="handleClick_delect">确 定</el-button>
              </span>
      </el-dialog>
    </div>
  </div>


</template>

<script>

export default {
  name: "admin_employee",
  mounted() {
    this.$axios.get(this.$store.state.url+"/account/getDep").then((res)=>{
      this.options = res.data;
    })
    this.init();
  },
  data() {
    return {
      // 分页
      currentPage:1,
      pageSize:8,
      totalSize:0,
      ifPagination: false,
      // dialog显示与不显示的参数
      dialogVisible_add:false,
      dialogVisible_addAccount:false,
      dialogVisible_reset:false,
      dialogVisible_lock:false,
      dialogVisible_unlock:false,
      dialogVisible_delete:false,
      //dialog中的密码验证参数
      password_confirm: '',

      //搜索栏中人员姓名
      searchOrderWorkerName: '',

      input: '',
      //存储人员信息表
      tableData: [],
      ruleForm: {
        name: '',
        department: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入用户姓名', trigger: 'blur' },
          { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
        ],
        department: [
          { required: true, trigger: 'blur' },
        ]
      },
      options: [],
      value: '',
      //保存操作选择的行
      row:{}
    }


  },
  methods: {
    //页面初始化
    init(){
      this.$axios.get(this.$store.state.url+"/account/all?page="+0+"&size="+this.pageSize).then((res)=>{
        this.tableData= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      });

    },
    addAccount() {
      this.dialogVisible_add = false;
      this.dialogVisible_addAccount = true;
    },
    //查询函数
    onSubmit() {
    },
    //增加账户函数
    add_AccountNumber() {
      this.$axios.get(this.$store.state.url+"/account/addAccount?root_num=root&admin_password="+
        this.password_confirm+"&name="+this.ruleForm.name+"&depNum="+this.ruleForm.department+"&phone="+"&work_password=brccq123456").then((res)=>{
          if (res.data===true)
          {
            this.$message({
              message:'添加成功',
              type:'success'
            })

          }

          else
            this.$message({
              message: '添加失败',
              type: 'error'
            });
          this.password_confirm='';
        this.dialogVisible_addAccount = false;
        this.init();
      })
    },
    //重置密码的函数
    reset(row){
      this.row = row
      this.dialogVisible_reset=true;
    },
    handleClick_reset() {
      this.$axios.get(this.$store.state.url+"/account/reset?work_num="+this.row.workNum+"&root_num=root&password="+
        this.password_confirm).then((res)=>{
        if (res.data===true)
        {
          this.$message({
            message:'重置成功',
            type:'success'
          })

        }
        else
          this.$message({
            message: '重置失败',
            type: 'error'
          });
      })
      this.password_confirm='';
      this.row={};
      this.dialogVisible_reset = false;

    },
    //锁定用户账号的函数
    lock(row){
      this.dialogVisible_lock = true;
      this.row=row;
    },
    handleClick_lock() {
      this.$axios.get(this.$store.state.url+"/account/lockAccount?work_num="+this.row.workNum).then((res)=>{
        if (res.data===true)
        {
          this.$message({
            message:'锁定成功',
            type:'success'
          })

        }
        else
          this.$message({
            message: '锁定失败',
            type: 'error'
          });
      })
      this.password_confirm='';
      this.row={};
      this.dialogVisible_lock = false;
      this.init();
    },
    //解锁用户账号的函数
    unlock(row){
      this.dialogVisible_unlock = true;
      this.row=row;
    },
    handleClick_unlock() {
      this.$axios.get(this.$store.state.url+"/account/unlockAccount?work_num="+this.row.workNum+"&root_num=root&password="+
        this.password_confirm).then((res)=>{
        if (res.data===true)
        {
          this.$message({
            message:'解锁成功',
            type:'success'
          })

        }
        else
          this.$message({
            message: '解锁失败',
            type: 'error'
          });
        this.row={};
      })
      this.password_confirm='';
      this.dialogVisible_unlock = false;
      this.init();
    },
    //删除用户函数
    delect(row){
      this.dialogVisible_delete=true;
      this.row=row;
    },
    handleClick_delect() {
      this.$axios.get(this.$store.state.url+"/account/deleteAccount?work_num="+this.row.workNum+"&root_num=root&password="+
        this.password_confirm).then((res)=>{
        if (res.data===true)
        {
          this.$message({
            message:'删除成功',
            type:'success'
          })

        }
        else
          this.$message({
            message: '删除失败',
            type: 'error'
          });
        this.row={};
      })
      this.password_confirm='';
      this.dialogVisible_delete = false;
      this.init();
    },
    //dialog弹窗关闭提示函数
    handleClose(done) {
          done();
    },
    //查询所有员工信息
    handleCurrentChange(val){
      if(!this.ifPagination){
        this.currentPage=parseInt(val);
        let page = this.currentPage-1;
        this.$axios.get(this.$store.state.url+"/account/all?page="+page+"&size="+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }else{
        this.currentPage=parseInt(val);
        let page = this.currentPage-1;
        this.$axios.get(this.$store.state.url + '/account/criteriaQueryByPage?name=' + this.searchOrderWorkerName+'&page='+page+'&size='+this.pageSize).then((res)=>{
          this.tableData= res.data.content;
          this.totalSize = res.data.totalPages*this.pageSize;
        })
      }
    },

    //根据姓名分页查询
    handleClick_search(){
      this.ifPagination = true;
      this.resetPageSituation();
      this.$axios.get(this.$store.state.url + '/account/criteriaQueryByPage?name=' + this.searchOrderWorkerName + '&page=0&size=' + this.pageSize).then((res)=>{
        this.tableData = res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    },
    //在进行查询时重置当前页状态，防止上一次查询的结果影响到当前的分页结果
    resetPageSituation(){
      this.currentPage = 1;
      this.pageSize = 8;
      this.totalSize = 0;
    },

    //重置查询
    handleClick_clean(){
      this.ifPagination = false;
      this.resetPageSituation();
      this.searchOrderWorkerName = '';
      this.init();
    },
  }
}
</script>

<style scoped>
.paging{
  bottom: 0;
  position: absolute;
}
.query{
  color: #fff;
  background-color: rgba(82, 182, 154, 0.8);
  border-color: #52b69a;
}
</style>
