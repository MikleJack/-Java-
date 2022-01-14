<template>
  <el-dialog
    style="text-align: center"
    title="虚拟机资源及价格配置"
    :visible.sync="dialogVisible_vir"
    width="78%"

    :before-close="handleClose">


    <!--      //折叠面板-->
    <el-collapse v-model="activeNames" @change="handleChange_collapse">
      <el-collapse-item title="虚拟机总资源配置" name="2">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="虚拟机每G存储价格(元)">
            <el-input v-model="formInline.storage_price" placeholder="输入每G的虚拟机存储价格"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit_storagePrice">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>

      <!--      //折叠面板-->
      <el-collapse-item title="虚拟机总资源配置" name="0">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="总CPU核数">
            <el-input v-model="formInline.cpuCore" placeholder="输入总cup核数"></el-input>
          </el-form-item>
          <el-form-item label="总内存数(G)">
            <el-input v-model="formInline.ram" placeholder="输入总内存数"></el-input>
          </el-form-item>
          <el-form-item label="总存储数(G)">
            <el-input v-model="formInline.storage" placeholder="输入总存储数"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit_allvirtual">确定</el-button>
          </el-form-item>
        </el-form>
      </el-collapse-item>
      <!--      //折叠面板-->
      <el-collapse-item title="新增虚拟机" name="1">
        <el-form :model="ruleForm_virtual" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
          <el-form-item label="规格族" prop="description">
            <el-input v-model="ruleForm_virtual.description"></el-input>
          </el-form-item>

          <el-form-item label="处理器型号" prop="processorModel">
            <el-input v-model="ruleForm_virtual.processorModel"></el-input>
          </el-form-item>

          <el-form-item label="VCPu(核)" prop="cpuCore">
            <el-input v-model="ruleForm_virtual.cpuCore"></el-input>
          </el-form-item>
        </el-form>

        <el-form :model="ruleForm_virtual" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" inline>
          <el-form-item label="内存(G)" prop="ram">
            <el-input v-model="ruleForm_virtual.ram"></el-input>
          </el-form-item>

          <el-form-item label="处理器频率" prop="processorFrequency">
            <el-input v-model="ruleForm_virtual.processorFrequency"></el-input>
          </el-form-item>

          <el-form-item label="单价(元)" prop="price">
            <el-input v-model="ruleForm_virtual.price"></el-input>
          </el-form-item>
        </el-form>

        <el-button @click="reset_virtual">重置</el-button>
        <el-button @click="add_virtual" style="color: white;background-color: #52b69a " :plain="true">新增</el-button>


      </el-collapse-item>
    </el-collapse>
    <p></p>


    <div class="block" style="text-align: center">
      <el-table class="budTable" frame=above
                :data="tableData_vir"
                border
                style="width: 100%"
                :cell-style="{textAlign:'center'}">
        <el-table-column prop="comNum" label="虚拟机编号" width="120" align="center"></el-table-column>
        <el-table-column prop="description" label="规格族" width="150" align="center"></el-table-column>
        <el-table-column prop="processorModel" label="处理机型号" width="150" align="center"></el-table-column>
        <el-table-column prop="cpuCore" label="VCPu(核)" width="120" align="center"></el-table-column>
        <el-table-column prop="ram" label="内存(G)" width="120" align="center"></el-table-column>
        <el-table-column prop="processorFrequency" label="处理器频率(GHz)" width="160" align="center"></el-table-column>
        <el-table-column prop="price" label="价格(元/月)" width="220" align="center">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.index" controls-position="right" @change="handleChange_vir"
                             :step="50" :min="0" :max="9999"
                             style="margin-left: 8%" size="mini"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          align="center"
          width="100">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="delete_virtual(scope.$index, scope.row)"
              type="text"
              size="small"
              style="color: #52b69a"
            >
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_vir = false">取 消</el-button>
                <el-button style="margin-right: 45%;color: white;background-color: #52b69a " @click="virtual_confirm()">确 定</el-button>
      </span>
  </el-dialog>
</template>

<script>
export default {
  name: "insertVm",
  props:{dialogVisible_vir}
}
</script>

<style scoped>

</style>
