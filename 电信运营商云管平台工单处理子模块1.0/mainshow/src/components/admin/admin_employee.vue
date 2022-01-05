<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="formInline.user" placeholder="请输入工号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <el-button @click="add_AccountNumber()" type="primary" icon="el-icon-plus">新增账号</el-button>
    <div>
      <el-table
        :data="tableData"
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
          width="100">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="联系方式"
          width="150">
        </el-table-column>
        <el-table-column
          prop="dep_name"
          label="部门名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="dep_level"
          label="部门级别"
          width="100">
        </el-table-column>
        <el-table-column
          prop="state"
          label="账户状态"
          width="180">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="300">
          <templte slot-scope="scope">
            <el-button @click="handleClick_reset(scope.row)" type="text" size="small">重置密码</el-button>
            <el-button @click="handleClick_lock(scope.row)" type="text" size="small">锁定</el-button>
            <el-button @click="handleClick_unlock(scope.row)" type="text" size="small">解锁</el-button>
            <el-button @click="handleClick_delect(scope.row)" type="text" size="small">删除</el-button>
          </templte>

        </el-table-column>
      </el-table>

<!--增加账号的dialog-->
      <el-dialog
        title="添加账号"
        :visible.sync="dialogVisible_add"
        width="30%"
        :before-close="handleClose">
        <span>确定添加一个账户？</span>
        <el-input placeholder="请输入密码" v-model="input_password" show-password></el-input>

        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_add = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_add = false">确 定</el-button>
              </span>
      </el-dialog>

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

      <el-dialog
        title="提示"
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

    </div>
  </div>


</template>

<script>
export default {
  name: "admin_employee",
  data() {
    return {
      dialogVisible_add:false,
      dialogVisible_reset:false,
      dialogVisible_lock:false,
      dialogVisible_unlock:false,
      dialogVisible_delete:false,
      input_password: '',
      input_delete: '',
      input_reset: '',
      input_lock: '',
      input_unlock: '',
      input: '',
      formInline: {
        user: '',
        region: ''
      },
      tableData: [{
        worker_num: '00000001',
        name: '王小虎',
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
      }]
    }

  },
  methods: {
    //查询函数
    onSubmit() {
      console.log('submit!');
    },
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
