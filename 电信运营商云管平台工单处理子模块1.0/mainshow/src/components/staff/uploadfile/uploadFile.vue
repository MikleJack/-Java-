<template>
      <el-upload  :action="uploadUrl" :before-upload="handleBeforeUpload"  :on-error="handleUploadError"
                  :limit="1" :on-exceed="handleExceed"
                  :file-list="fileList" :on-progress="success" accept="txt">
        <el-button class="upload-demo" size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">最多上传一个文件，不超过200Mb，只支持pdf/docx文档</div>
      </el-upload>
</template>

<script>
export default {
  name: "uploadFile",
  data() {
    return {
      form: {
        fileName: '',
      },
      uploadUrl: 'https://101.200.200.1:8083/file/upload',
      fileList: []
    }
  },

  methods: {
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
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
      if(file.type==="application/vnd.openxmlformats-officedocument.wordprocessingml.document"
      ||file.type==="application/pdf"){
        return true;
      }
      else
      {
        this.$message({
          message:"上传了不支持的文件类型，请重新选择！",
          type:"error",
          center:true,
        })
        return false;
      }
    },
    //文件上传成功
    success(event, file, fileList){
      this.$store.state.FileName=file.name;
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
