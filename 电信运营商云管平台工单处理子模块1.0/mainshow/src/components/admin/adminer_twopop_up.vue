<template>
  <el-popover
    placement="right"
    width="640"
    trigger="click">
    <el-table border style="width:100%" :data="formData">
<!--   弹出表格，   -->
      <el-table-column prop="cpu" label="CPU(核)" width="120"></el-table-column>
      <el-table-column prop="memory" label="内存(G)" width="120"></el-table-column>
      <el-table-column prop="men" label="存储(G)" width="120"></el-table-column>
      <el-table-column label="价格" width="240">
<!--        计数器-->
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.index" @change.native="changeNum" :min="0" :max="10000"></el-input-number>
        </template>
      </el-table-column>
    </el-table>
    <el-button slot="reference">click 激活</el-button>
  </el-popover>
</template>

<script>
export default {
  name: "adminer_twopop_up",
  data () {
    return {
      formData: [
        {
          cpu: '双核',
          memory: 100,
          men: 100
        },
        {
          cpu: '双核',
          memory: 100,
          men: 100
        },
        {
          cpu: '双核',
          memory: 100,
          men: 100
        }
      ],
      num: ''
    }
  },
  methods: {
    changeNum (value) {
      console.log(value)
    }
  }

}
</script>

<style scoped>

</style>
