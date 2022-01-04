<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="formInline.user" placeholder="工单标题"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
          prop="worker_num"
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
          prop="dep_name"
          label="部门名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="dep_level"
          label="部门级别"
          width="180">
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


      <el-dialog
        title="提示"
        :visible.sync="dialogVisible_reset"
        width="30%"
        :before-close="handleClose">
        <span>是否确定重置该用户账号？</span>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_reset = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_reset = false">确 定</el-button>
              </span>
      </el-dialog>

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible_lock"
        width="30%"
        :before-close="handleClose">
        <span>是否锁定该用户账号？</span>
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
        <span>是否解锁该用户账号？</span>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_unlock = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_unlock = false">确 定</el-button>
              </span>
      </el-dialog>

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible_delect"
        width="30%"
        :before-close="handleClose">
        <span>是否解锁该用户账号？</span>
        <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_delect = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible_delect = false">确 定</el-button>
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
      dialogVisible_reset:false,
      dialogVisible_lock:false,
      dialogVisible_unlock:false,
      dialogVisible_delect:false,
      input: '',
      formInline: {
        user: '',
        region: ''
      },
      tableData: [{
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-08',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-06',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-07',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }]
    }

  },
  methods: {
    onSubmit() {
      console.log('submit!');
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
