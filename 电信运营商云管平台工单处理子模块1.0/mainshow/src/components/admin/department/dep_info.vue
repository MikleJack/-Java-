<template>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="部门名称">
      <el-input v-model="form.dep_name"></el-input>
    </el-form-item>
    <el-form-item label="部门编号">
      <el-input v-model="form.dep_num" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="上级部门">
      <el-input v-model="form.dep_superior" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">保存</el-button>
      <el-button>重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  name:'dep_info',
  data() {
    return {
      form: {
        dep_num:'0001',
        dep_name:'软件学院',
        dep_superior:'3'
      }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    }
  }
}
</script>
