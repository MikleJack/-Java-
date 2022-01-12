<template>

  <el-popover
    placement="right"
    width="880"
    height="1500"
    trigger="click">

<!--    表格-->
    <el-table border style="width:100%" :data="formData">
      <el-table-column prop="specific" label="规格族" width="120"></el-table-column>
      <el-table-column prop="v_cpu" label="VCPu" width="120"></el-table-column>
      <el-table-column prop="men" label="内存(G)" width="120"></el-table-column>
      <el-table-column prop="frequency" label="处理机主频/睿频" width="120"></el-table-column>
      <el-table-column prop="model" label="处理机型号" width="120"></el-table-column>
      <el-table-column label="价格" width="240">
<!--        计数器-->
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.index" @change.native="changeNum" :min="0" :max="10000"></el-input-number>
        </template>
      </el-table-column>
    </el-table>

<!--输入框-->
    <el-form :inline="true" :model="formInline" class="demo-form-inline" label-width="100px" style="padding-bottom: 10px;">
      <el-form-item label="存储(G)">
        <el-input v-model="formInline.user" placeholder="存储(G)"></el-input>
      </el-form-item>
    </el-form>

    <el-button slot="reference">click 激活2</el-button>
  </el-popover>
</template>

<script>
export default {
  name: "adminer_onepop_up",
  data () {
    return {
      formData: [
        {
          specific: 's6',
          v_cpu: 1,
          men: 100,
          frequency:90,
          model:'intel1'
        },
        {
          specific: 's6',
          v_cpu: 1,
          men: 100,
          frequency:90,
          model:'intel1'
        },
        {
          specific: 's6',
          v_cpu: 1,
          men: 100,
          frequency:90,
          model:'intel1'
        },
        {
          specific: 's6',
          v_cpu: 1,
          men: 100,
          frequency:90,
          model:'intel1'
        }
      ],
      num: '',
      formInline: {
        user: 0.5
      }
    }
  },
  methods: {
    changeNum (value) {
      console.log(value)
    }
  },

}
</script>

<style scoped>

/*.page-tail{*/
/*  width:100%;*/
/*  height: 60px;*/
/*  position: absolute;*/
/*  bottom: 0;*/
/*}*/
</style>
