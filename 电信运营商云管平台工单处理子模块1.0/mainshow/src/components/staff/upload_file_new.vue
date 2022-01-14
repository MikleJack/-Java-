<template>
  <el-upload
    action="http://localhost:8084/applyTickets/upload"
    :on-preview="handlePreview"
    multiple
    accept=".pdf"
    :limit="10"
  >
    <el-button class="upload_button" type="primary">点击上传</el-button>
    <div slot="tip" class="el-upload__tip">只能上传.pdf格式，且不超过100MB</div>
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
.upload_button{
  color: #fff;
  background-color: rgba(82, 182, 154, 0.8);
  border-color: #52b69a;
}
</style>
