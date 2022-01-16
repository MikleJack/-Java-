<template>
  <div>
    <el-upload
      :action="action"
      :headers="headers"
      :multiple="multiple"
      :data="parameterData"
      name="file"
      with-credentials="ture"
      show-file-list="true"
      drag="false"
      :accept="accept"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleFileRemove"
      :on-success="handleFileSuccess"
      :on-error="handleUploadError"
      :on-progress="handleFileUploadProgress"
      :on-change="handleFileUploadChange"
      :before-upload="beforeAvatarUpload"
      :before-remove="beforeRemoveUpload"
      list-type="text"
      auto-upload="true"
      :file-list="fileList"
      :http-request="httpRequestUpload"
      :disabled="isUploading"
      :limit="limit"
      :on-exceed="onExceedUpload"
      ref="upload"
    >
      <!--简介式上传文件 -->
      <el-button size="small" icon="el-icon-upload2">点击上传文件</el-button>
      <!--上传图片 -->
      <!-- <i class="el-icon-plus"></i> -->
      <!--solt
          trigger	触发文件选择框的内容,需要时候使用
          tip	提示说明文字，用的较多，在solt标签中写出提示的文字即可
      -->
      <div style="color: #999999; font-size: 12px; margin-top: 4px;" slot="tip">
        <slot>
          <!-- 提示的文字 -->
        </slot>
      </div>
    </el-upload>
    <!--图片放大的dialog -->
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt />
    </el-dialog>
  </div>




</template>

<script>
import Cookie from 'js-cookie'
export default {
  name: "upload_file",
  props: {
    action: '/upload',
    multiple: false,
    files: {
      type: Array,
      default: () => [],
    },
    accept: {
      type: String,
      default: '',
    },
    limit: {
      type: Number,
      default: 3,
    },
    parameterData: '',
  },
  data: function () {
    return {
      dialogImageUrl: '',
      fileList: [],
      dialogVisible:false,
      /**
       * 设置上传的请求头部
       */
      headers: {
        Authorization: 'Bearer ' + Cookie.get('token'),
      },
      /**
       * 是否禁用上传
       */
      isUploading: false,
    }
  },
  watch: {
    files: {
      handler(value) {
        this.fileList = value
      },
      immediate: true,
    },
  },
  methods: {
    /**
     * 点击文件列表中已上传的文件时的钩子
     */
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    /**
     * 文件移除时触发的函数
     */
    handleFileRemove(file, fileList) {
      this.handleEmitFilesChange(fileList)
    },
    /**
     * 文件上传成功出发的函数
     */
    handleFileSuccess(response, file, fileList) {
      this.isUploading = false
      // this.msgSuccess("上传成功");
      // this.$alert(response.msg, '导入结果', { dangerouslyUseHTMLString: true })
      this.handleEmitFilesChange(fileList)
      console.log()
    },
    /**
     * 文件上传失败触发的函数
     */
    handleUploadError() {
      this.$message({
        type: 'error',
        message: '上传失败',
      })
      this.loading.close()
    },
    /**
     * 文件上传时触发的函数
     */
    handleFileUploadProgress(event, file, fileList) {
      this.isUploading = true
    },

    /**
     * 文件状态改变时触发的函数
     */
    handleFileUploadChange(file, fileList) {},
    // 限制图片上传大小
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!')
        return false
      }

      // 判断图片类型来筛选图片格式上传
      let _type = file.type
    },
    /**
     * 删除文件时触发的函数，若返回 false 或者返回 Promise 且被 reject,停止删除
     */
    beforeRemoveUpload(file, fileList) {},
    /**
     * 覆盖默认的上传行为，可以自定义上传的实现
     */
    httpRequestUpload() {
      console.log('覆盖上传')
    },
    /**
     * 文件超出个数限制时触发的函数
     */
    onExceedUpload(files, fileList) {
      this.$alert('文件数量超出限制', '上传结果', {
        dangerouslyUseHTMLString: true,
      })
    },

    handleEmitFilesChange(fileList = []) {
      const files = fileList.map((i) => i.response.fileName)
      this.$emit('file-list-change', { files })
    },
    /**
     clearFiles	清空已上传的文件列表（该方法不支持在 before-upload 中调用）	— 比如点击重新上传的时候
     abort	取消上传请求	（ file: fileList 中的 file 对象 ）
     submit	手动上传文件列表
     */
    _clearFiles() {
      this.$refs.upload.clearFiles()
    },
    _submit() {
      this.$refs.upload.submit()
    },
  },
}

</script>

<style scoped>

</style>
