<template>
  <el-form :model="form" >
    <el-form-item>
      <el-upload  :action="uploadUrl" :before-upload="handleBeforeUpload"  :on-error="handleUploadError" :before-remove="beforeRemove" multiple :limit="1" :on-exceed="handleExceed" :file-list="fileList">
        <el-button class="upload-demo" size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">最多上传一个文件，不超过1Gb</div>
      </el-upload>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "uploadFileByfhj",
  data() {
    return {
      form: {
        fileName: '',
      },
      uploadUrl: 'http://localhost:8080/file/upload',
      fileList: []
    }
  },

  methods: {
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleUploadError(error, file) {
      console.log(error);
      console.log(file);
      this.$notify.error({
        title: 'error',
        message: '上传出错:' +  error,
        type: 'error',
        position: 'bottom-right'
      })
    },
    //测试上传文件(注意web的上下文)
    handleBeforeUpload(file){
      this.uploadUrl =`http://localhost:8080/file/upload`
    }
  }
}
</script>

<style scoped>
.upload-demo{
  color: #fff;
  background-color: rgba(82, 182, 154, 0.8);
  border-color: #52b69a;
}
</style>
