<template>
  <el-dialog
    style="text-align: center"
    title="部门预算设置"
    :visible.sync="this.$store.state.dialogVisible_budget"
    width="500px"
    height="700px"
    :before-close="handleClose">
    <div class="block" style="text-align: center">
      <el-table class="budTable" frame=above
                :data="tableData_bud"
                border
                style="width: 100%;overflow: hidden"
                :cell-style="{textAlign:'center'}">
        <el-table-column prop="depName" label="部门名称" width="100" align="center">
        </el-table-column>
        <el-table-column prop="depBudget" label="预算(元)" width="180" align="center">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.depBudget" controls-position="right" @change="handleChange_bud(scope.row.depBudget)"
                             :step="100" :min="0" :max="999999"
                             style="margin-left: 8%" size="mini"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column prop="rigth" label="操作" width="179" align="center">
          <template slot-scope="scope">
            <el-button style="margin-left: 6%;color: white;background-color: #52b69a " @click="budget_confirm(scope.row.depNum,scope.row.depBudget)">确 定</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <span slot="footer" class="dialog-footer">
                <el-button @click="$store.state.dialogVisible_budget = false">取 消</el-button>
      </span>
  </el-dialog>
</template>

<script>
export default {
  name: "Depbudget",


  data(){
    return{
      tableData_bud: [],
      // 部门预算使用率
      depBudget:'78.90',
    }
  },
  methods:{

    //部门预算初始化
    setbudget(){
      this.$axios.get(this.$store.state.url+"/adminHome/getDepBudget").then((res)=>{
        if(res.data!==null){
          this.tableData_bud = res.data;
        }
      })
      // this.dialogVisible_budget=true;
    },

    //部门预算设置的dialog函数
    //关闭隐藏弹窗的函数
    handleClose(done) {
      this.$store.state.dialogVisible_budget = false;
      done();
    },

    handleChange_bud(budValue) {
      console.log(value);
    },
    //对预算dialog的确认
    budget_confirm(DepNum,DepBudget){
      // alert(DepNum + "    " + DepBudget);
      this.$store.state.DepNum = DepNum;
      this.$store.state.DepBudget = DepBudget;
      this.$store.state.which_page_confirm = 1;
      this.$store.state.dialogVisible_modify = true;
    }
  }

}
</script>

<style scoped>

</style>
