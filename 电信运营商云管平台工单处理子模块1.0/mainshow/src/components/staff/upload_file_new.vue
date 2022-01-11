<template>
  <el-upload
    ref="upload"
    :limit="fileLimit"
    :on-remove="handleRemove"
    :on-error="onError"
    :file-list="fileList"
    :auto-upload="false"
    :http-request="uploadFile"
    :on-preview="handlePreview"
    action="https://jsonplaceholder.typicode.com/posts/"
    class="upload-demo">
    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
    <!-- <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button> -->
    <div slot="tip" class="el-upload__tip">支持上传 {{ strRebuild(fileType) }} 格式，且不超过 {{ fileSize }}M</div>
  </el-upload>

</template>

<script>
import { strRebuild, lastSubstring } from './strUtil'
import { message } from './message'
export default {
  name: "upload_file_new",
  data() {
    return {
      // 附件列表
      fileList: [],
      // 允许的文件类型
      fileType: ['xls', 'xlsx', 'pdf', 'doc', 'docx', 'txt', 'jpg', 'png', 'jpeg'],
      // 运行上传文件大小，单位 M
      fileSize: 10,
      // 附件数量限制
      fileLimit: 1,
    }
  },
  methods: {
    // 清空表单
    clear() {
      // 清空附件
      this.$refs.upload.clearFiles()
    },

    // 附件检查
    // 检查附件是否属于可上传类型
    // 检查附件是否超过限制大小
    checkFile() {
      var flag = true
      var tip = ''
      var files = this.$refs.upload.uploadFiles
      files.forEach(item => {
        // 文件过大
        if (this.size !== null && this.size !== '' && item.size > this.fileSize * 1024 * 1024) {
          flag = false
          tip = ' 文件超过' + this.fileSize + 'M'
        }
        // 文件类型不属于可上传的类型
        if (this.fileType !== null && this.fileType !== '' && !this.fileType.includes(lastSubstring(item.name, '.'))) {
          flag = false
          tip = ' 文件类型不可上传'
        }
      })
      if (!flag) {
        message('error', tip)
      }
      return flag
    },

    // 提交附件
    submitUpload() {
      if (this.checkFile()) {
        console.log('上传附件...')
        this.$refs.upload.submit()
      } else {
        console.log('取消上传')
      }
    },

    // 自定义文件上传方法
    uploadFile(file) {
      // 把文件放入 FormData 进行提交
      const param = new FormData()
      param.append('files', file.file)
      uploadFile(param).then(response => {

      })
    },

    // 移除附件
    handleRemove(file, fileList) {
      console.log('移除附件...')
    },

    // 附件上传失败，打印下失败原因
    onError(err) {
      message('error', '附件上传失败')
      console.log(err)
    },

    // 字符串重组
    strRebuild(str) {
      return strRebuild(str)
    }
  },
  handlePreview(file) {
    console.log(file);
    window.open( file.response.url);
  }

}
</script>

<style scoped>

</style>
