<template>
  <div>
<!--    <el-form :inline="true" :model="formInline" class="demo-form-inline">-->
<!--      <el-form-item>-->
<!--        <el-input v-model="formInline.user" placeholder="请输入工号"></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" @click="onSubmit">查询</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    新增账户-->
    <el-button @click="add_AccountNumber()" type="primary" icon="el-icon-plus">新增账号</el-button>
    <div>

<!--      人员管理页面的列表-->
      <el-table
        :data="tableData.filter(data => !search || data.worker_num.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"
        border
        style="width: 100%">
        <el-table-column
          prop="worker_num"
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
          prop="dep_name"
          label="部门名称"
          width="200">
        </el-table-column>
        <el-table-column
          prop="dep_level"
          label="部门级别"
          width="100">
        </el-table-column>
        <el-table-column
          prop="state"
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
            <el-button @click="handleClick_reset(scope.row)" type="text" size="small">重置密码</el-button>
            <el-button @click="handleClick_lock(scope.row)" type="text" size="small">锁定</el-button>
            <el-button @click="handleClick_unlock(scope.row)" type="text" size="small">解锁</el-button>
            <el-button @click="handleClick_delect(scope.row)" type="text" size="small">删除</el-button>
          </templte>

        </el-table-column>


      </el-table>

<!--确认密码的dialog-->
      <el-dialog
        title="确认密码"
        :visible.sync="dialogVisible_add"
        width="30%"
        :before-close="handleClose">
        <span>确定添加一个账户？</span>
        <el-input placeholder="请输入密码" v-model="input_password" show-password></el-input>

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
                <el-button type="primary" @click="dialogVisible_addAccount = false">确 定</el-button>
              </span>
      </el-dialog>
<!--      重置账户的dialog-->
      <el-dialog
        title="重置"
        :visible.sync="dialogVisible_reset"
        width="30%"
        :before-close="handleClose">
        <span>确定重置该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="input_reset" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_reset = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_reset = false">确 定</el-button>
              </span>
      </el-dialog>
<!--锁定账户的dialog-->
      <el-dialog
        title="锁定"
        :visible.sync="dialogVisible_lock"
        width="30%"
        :before-close="handleClose">
        <span>确定锁定该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="input_lock" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_lock = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_lock = false">确 定</el-button>
              </span>
      </el-dialog>
<!--解锁账户的dialog-->
      <el-dialog
        title="解锁"
        :visible.sync="dialogVisible_unlock"
        width="30%"
        :before-close="handleClose">
        <span>确定解锁该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="input_unlock" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_unlock = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_unlock = false">确 定</el-button>
              </span>
      </el-dialog>
<!--删除账户的dialog-->
      <el-dialog
        title="删除"
        :visible.sync="dialogVisible_delete"
        width="30%"
        :before-close="handleClose">
        <span>确定删除该用户账号？</span>
        <el-input placeholder="请输入密码" v-model="input_delete" show-password></el-input>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_delete = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_delete = false">确 定</el-button>
              </span>
      </el-dialog>

<!--      分页-->
      <div>
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pageSize"
          layout="total, prev, pager, next, jumper ,sizes"
          :total="this.tableData.length">
        </el-pagination>
      </div>
    </div>
  </div>


</template>

<script>
export default {
  name: "admin_employee",
  data() {
    return {
      // 分页
      currentPage:1,
      pageSize:8,
      // dialog显示与不显示的参数
      dialogVisible_add:false,
      dialogVisible_addAccount:false,
      dialogVisible_reset:false,
      dialogVisible_lock:false,
      dialogVisible_unlock:false,
      dialogVisible_delete:false,
      //dialog中的密码验证参数
      input_password: '',
      input_delete: '',
      input_reset: '',
      input_lock: '',
      input_unlock: '',

      input: '',
      search: '',
      // formInline: {
      //   user: '',
      //   region: ''
      // },
      tableData: [{
        worker_num: '00000001',
        name: '王二虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }, {
        worker_num: '00000002',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }, {
        worker_num: '00000003',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }, {
        worker_num: '00000004',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }, {
        worker_num: '00000005',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }, {
        worker_num: '00000006',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }, {
        worker_num: '00000007',
        name: '王小虎',
        phone: '15155185464',
        dep_name: '软件学院',
        dep_level: '3',
        state: '正常'
      }],
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
      options: [{
        value: '选项1',
        label: '软件学院'
      }, {
        value: '选项2',
        label: '19-2班'
      }],
      value: ''
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
      this.dialogVisible_add = true;
    },
    //重置密码的函数
    handleClick_reset(row) {
      this.dialogVisible_reset = true;
    },
    //锁定用户账号的函数
    handleClick_lock(row) {
      this.dialogVisible_lock = true;
    },
    //解锁用户账号的函数
    handleClick_unlock(row) {
      this.dialogVisible_unlock = true;
    },
    //删除用户函数
    handleClick_delect(row) {
      this.dialogVisible_unlock = true;
    },
    //dialog弹窗关闭提示函数
    handleClose(done) {
      // this.$confirm('确认关闭？')
        // .then(_ => {
          done();
        // })
        // .catch(_ => {});
    }
  }
}
</script>

<style scoped>

</style>
