<template>
      <el-upload  :action="uploadUrl" :before-upload="handleBeforeUpload"  :on-error="handleUploadError"
                  :before-remove="beforeRemove"  :limit="1" :on-exceed="handleExceed"
                  :file-list="fileList" :on-progress="success">
        <el-button class="upload-demo" size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">最多上传一个文件，不超过1Gb</div>
      </el-upload>
</template>

<script>
export default {
  name: "uploadFileByfhj",
  data() {
    return {
      form: {
        fileName: '',
      },
      uploadUrl: 'http://localhost:8083/file/upload',
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
      this.$notify.error({
        title: 'error',
        message: '上传出错:' +  error,
        type: 'error',
        position: 'bottom-right'
      })
    },
    //测试上传文件(注意web的上下文)
    handleBeforeUpload(file){
    },
    //文件上传成功
    success(event, file, fileList){
      this.$store.state.FileName=file.name;
      console.log(this.$store.state.FileName)
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
