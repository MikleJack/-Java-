<template>
  <div>

<!--    新增账户-->
    <el-button @click="dialogVisible_add = true" type="primary" icon="el-icon-plus">新增账号</el-button>
    <div>

<!--       .slice((currentPage-1)*pageSize,currentPage*pageSize)"-->
<!--      人员管理页面的列表-->
      <el-table
        :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"

        border
        style="width: 100%">
        <el-table-column
          prop="workNum"
          label="工号"
          width="150">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="150">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="联系方式"
          width="200">
        </el-table-column>
        <el-table-column
          prop="depName"
          label="部门名称"
          width="200">
        </el-table-column>
        <el-table-column
          prop="depLevel"
          label="部门级别"
          width="100">
        </el-table-column>
        <el-table-column
          prop="state_string"
          label="账户状态"
          width="200">
        </el-table-column>

        <el-table-column
          fixed="right"
          width="300">
<!--          搜索操作-->
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              placeholder="输入关键字搜索"/>
          </template>
<!--          对账户的各种操作-->
          <templte slot-scope="scope">
            <el-button @click="reset(scope.row)" type="text" size="small">重置密码</el-button>
            <el-button @click="lock(scope.row)" type="text" size="small">锁定</el-button>
            <el-button @click="unlock(scope.row)" type="text" size="small">解锁</el-button>
            <el-button @click="delect(scope.row)" type="text" size="small">删除</el-button>
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
            <el-select v-model="value" placeholder="请选择部门">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
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
                <el-button type="primary" @click="handleClick_unlock">确 定</el-button>
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

<!--      分页-->

    </div>
  </div>


</template>

<script>

export default {
  name: "admin_employee",
  mounted() {
    this.$axios.get("http://localhost:8084/staffDep/allStaff?page="+0+"&size="+this.pageSize).then((res)=>{
      this.tableData= res.data.content;
      this.totalSize = res.data.totalPages*this.pageSize;
    })
  },
  data() {
    return {
      // 分页
      currentPage:1,
      pageSize:8,
      totalSize:0,
      // dialog显示与不显示的参数
      dialogVisible_add:false,
      dialogVisible_addAccount:false,
      dialogVisible_reset:false,
      dialogVisible_lock:false,
      dialogVisible_unlock:false,
      dialogVisible_delete:false,
      //dialog中的密码验证参数
      password_confirm: '',

      input: '',
      search: '',
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
    addAccount() {
      this.dialogVisible_add = false;
      this.dialogVisible_addAccount = true;
    },
    //查询函数
    onSubmit() {
      console.log('submit!');
    },
    //增加账户函数
    add_AccountNumber() {
      this.$axios.get("http://localhost:8084/staff/addAccount?root_num=root&admin_password="+
        this.password_confirm+"&name="+this.ruleForm.name+"&depNum=0003"+"&phone="+"&work_password=brccq123456").then((res)=>{
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
      })
    },
    //重置密码的函数
    reset(row){
      this.row = row
      this.dialogVisible_reset=true;
    },
    handleClick_reset() {
      this.$axios.get("http://localhost:8084/staff/reset?work_num="+this.row.workNum+"&root_num=root&password="+
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
      this.$axios.get("http://localhost:8084/staff/lockAccount?work_num="+this.row.workNum).then((res)=>{
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
    },
    //解锁用户账号的函数
    unlock(row){
      this.dialogVisible_unlock = true;
      this.row=row;
    },
    handleClick_unlock() {
      this.$axios.get("http://localhost:8084/staff/unlockAccount?work_num="+this.row.workNum+"&root_num=root&password="+
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
    },
    //删除用户函数
    delect(row){
      this.dialogVisible_delete=true;
      this.row=row;
    },
    handleClick_delect() {
      this.$axios.get("http://localhost:8084/staff/deleteAccount?work_num="+this.row.workNum+"&root_num=root&password="+
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
    },
    //dialog弹窗关闭提示函数

    handleClose(done) {
          done();
    },
    //查询所有员工信息
    handleCurrentChange(val){
      this.currentPage=parseInt(val);
      let page = this.currentPage-1;
      this.$axios.get("http://localhost:8084/staffDep/allStaff?page="+page+"&size="+this.pageSize).then((res)=>{
        this.tableData= res.data.content;
        this.totalSize = res.data.totalPages*this.pageSize;
      })
    },

  }
}
</script>

<style scoped>
.paging {
  width:100%;
  height: 60px;
  position: absolute;
  bottom: 0;
}
</style>
