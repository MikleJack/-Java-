<template>
  <el-upload
    action="http://localhost:8084/applyTickets/upload"
    :on-preview="handlePreview"
    multiple
    :limit="10"
  >
    <el-button size="small" type="primary">点击上传</el-button>
    <div slot="tip" class="el-upload__tip">不超过100MB</div>
  </el-upload>

</template>

<script>
export default {
  name: "upload_file_new",

  methods: {
    handlePreview(file) {
      console.log(file);
      window.open( file.response.url);
    }

  },

}
</script>

<style scoped>

</style>
