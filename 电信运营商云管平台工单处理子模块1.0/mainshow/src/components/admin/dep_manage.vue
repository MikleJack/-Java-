<template>
    <div class="manage">
<!--      树形列表-->
      <div class="tree">
<!--        模糊搜索部门-->
        <div style="top: 5px;margin-bottom: 5px;position: relative">
          <el-input
            placeholder="请输入部门名称"
            v-model="filterText">
          </el-input>
        </div>
        <el-tree
          :data="data"
          node-key="id"
          default-expand-all
          :expand-on-click-node="false"
          :filter-node-method="filterNode"
          ref="tree">
        <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span style="right: 0;position: absolute;">
          <el-button
            type="text"
            size="mini"
            @click="() => append(data)">
            增加子部门
          </el-button>
        </span>
      </span>
        </el-tree>
<!--        新增部门对话框-->
        <el-dialog title="新增部门" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="部门名称" >
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="hied(false)">取 消</el-button>
            <el-button type="primary" @click="hied(true)">确 定</el-button>
          </div>
        </el-dialog>
<!--右侧详情页面-->
      </div>
      <div class="info">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="基本信息" name="first">
            <div class="window">
<!--              展示部门详细信息同时可以修改-->
              <dep_info></dep_info>
            </div>
          </el-tab-pane>
          <el-tab-pane label="部门权限" name="second">
            <div class="window">

            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
</template>

<script>
import Dep_info from "./department/dep_info";
export default {
  name: 'dep_manage',
  components: {Dep_info},
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
  data() {

      return {
        data : [],
        form:{
          name:''
        },
        dialogFormVisible:false,
        temp:{},
        activeName:'first',
        filterText:''
      }
    },
    mounted() {
      this.$axios.get(this.$store.state.url+'/Organ/getTree').then((res)=>{
        this.data=res.data;
        console.log(this.data)
      })
    },
    methods: {
      //过滤函数
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      //隐藏对话框
      hied(isInsert){
        this.dialogFormVisible=false;
        if(isInsert){
          this.$axios.post("https://localhost:8084/Organ/insert?depName="+this.form.name+"&depLevel="
            +(this.temp.depLevel-1).toString()).then((res)=>{
              this.$axios.post("https://localhost:8084/Organ/addRelate?supe="+this.temp.depNum+"&low="+res.data.depNum)
            if (!this.temp.children) {
              this.$set(this.temp, 'children', []);
            }
            this.temp.children.push(res.data);
          })

        }
      },

    //添加部门
      append(data) {
        this.dialogFormVisible=true;
        this.temp=data;
        console.log(this.temp)
      },

    }
}
</script>

<style>
.manage{
  width: 100%;
  height: 100%;
  position: relative;
  /*background-color: #888888;*/
}
.tree{
  width: 50%;
  height: 50%;
  position: relative;
  float: left;
  /*background-color: white;*/
  /*background-color: rgba(165, 111, 111, 0.5);*/
  border: 3px #888888;
}
.info{
  float: left;
  left: 20px;
  width: 50%;
  position: relative;
  /*background-color: white;*/
}
.window{
  width: 100%;
  height: 100%;
  /*background-color: #888888;*/
}
</style>
