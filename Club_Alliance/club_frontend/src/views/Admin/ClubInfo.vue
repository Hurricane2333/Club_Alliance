<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-menu
          default-active="5"
          class="el-menu-main"
          mode="horizontal"
          :ellipsis="false"
          @select="handleTop"
        >
          <el-menu-item index="0">
            <el-icon><UserFilled /></el-icon>
            <el-text class="title-tag" type="primary" size="large">社团联盟后台管理</el-text>
          </el-menu-item>
          <el-menu-item index="1">首页</el-menu-item>
          <el-sub-menu index="2">
            <template #title>我的社团</template>
            <el-menu-item index="2-1">item one</el-menu-item>
            <el-menu-item index="2-2">item two</el-menu-item>
            <el-menu-item index="2-3">item three</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="3">我的活动</el-menu-item>
          <el-menu-item index="4">个人中心</el-menu-item>
          <el-menu-item index="5">后台管理</el-menu-item>
          <el-space wrap :size="'large'">
            <el-image style="width: 40px; height: 40px; border-radius:40px; box-shadow:3px 3px 10px 0px dimgray" src="/avatar.jpg"></el-image>
            <div class="border">
              <div class="border-title">管理员</div>
            </div>
          </el-space>
          <el-dropdown>
            <span class="el-dropdown-link">
              <el-text class="user-name" size="large">荷塘月色</el-text>
              <el-icon class="el-icon--right">
                <arrow-down-bold />
              </el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>Action 1</el-dropdown-item>
                <el-dropdown-item>Action 2</el-dropdown-item>
                <el-dropdown-item>Action 3</el-dropdown-item>
                <el-dropdown-item disabled>Action 4</el-dropdown-item>
                <el-dropdown-item divided>Action 5</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-menu>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-row class="tac">
            <el-col :span="23">
              <h5 class="mb-2"></h5>
              <el-menu
                class="el-menu-vertical"
                @select="handleSide"
                default-active="/dashboard"
                router="true"
              >
                <el-menu-item index="/dashboard">
                  <el-icon><Menu /></el-icon>
                  <span>控制台</span>
                </el-menu-item>
                <el-sub-menu>
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>社团管理</span>
                  </template>
                  <el-menu-item-group title="管理">
                    <el-menu-item index="/manageClub/info">社团信息管理</el-menu-item>
                    <el-menu-item index="1-2">社团成员管理</el-menu-item>
                    <el-menu-item index="1-3">社团帖子管理</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="审核">
                    <el-menu-item index="1-4">新建社团审核</el-menu-item>
                    <el-menu-item index="1-5">活动申请审核</el-menu-item>
                  </el-menu-item-group>
                </el-sub-menu>
                <el-sub-menu index="2">
                  <template #title>
                    <el-icon><Menu /></el-icon>
                    <span>用户管理</span>
                  </template>
                  <el-menu-item index="2-1">管理员信息</el-menu-item>
                  <el-menu-item index="2-2">学生信息</el-menu-item>
                </el-sub-menu>
                <el-menu-item index="3">
                  <el-icon><Menu /></el-icon>
                  <span>公告管理</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <el-icon><Menu /></el-icon>
                  <span>系统设置</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>
          <el-button class="buttons" type="primary" style="margin-top:40px;margin-left: 40px" plain>
            <el-icon size="18"><DocumentAdd /></el-icon>
            <el-text type="primary">新增</el-text>
          </el-button>
          <el-button type="danger" style="margin-top: 40px" plain>
            <el-icon size="18"><FolderDelete /></el-icon>
            <el-text type="danger">批量删除</el-text>
          </el-button>
          <el-input
            v-model="input"
            style="width: 240px;margin-top:40px;margin-left:30px"
            placeholder="输入名称查询"
            clearable
          />
          <el-button type="success" style="margin-top: 40px;margin-left: 10px" plain>
            <el-icon size="18"><Search /></el-icon>
            <el-text type="success">搜索</el-text>
          </el-button>
          <el-table :data="clubData">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column property="clubId" label="ID" width="120"/>
            <el-table-column property="clubName" label="社团名称" width="120"/>
            <el-table-column property="category" label="分类" width="120"/>
            <el-table-column property="currentMembers" label="成员数" width="120"/>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue'
import { reactive } from "vue"
import axios from 'axios'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting, UserFilled, Menu,
} from '@element-plus/icons-vue'
export default{
  components: {Menu, Location, UserFilled},
  data(){
    return{
      userData:[],
      clubData:[],
      iniTable:[],
      input:''
    }
  },
  methods:{
  },
  mounted(){
      axios.get("http://localhost:8080/allClub").then((res)=>{
        console.log(res);
        this.clubData=res.data;
      })
  }
}
</script>

<style scoped>
.common-layout {
  position:absolute;
  top:0;
  right:0;
  bottom:0;
  left:0;
}
.el-container {
  height: 100%;
}
.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.el-menu-main{
  background: linear-gradient(to right,lightblue,white);
}
.el-menu--horizontal > .el-menu-item:nth-child(6) {
  margin-right: auto;
}
.border {
  width: 49px;
  height: 20px;
  margin-top: 0px;
  margin-left: 0px;
  border: 2px solid red;
  border-radius: 20px;
}
.border-title {
  width: 50px;
  height: 30px;
  text-align: center;
  margin-top: 0px;
  margin-left: 0px;
  color:red;
  font-weight: bold;
  font-size: 13px;
}
.title-tag{
  font-weight: bold;
}
.user-name {
  margin-left: 2px;
  font-weight: bold;
}
.el-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
}
</style>
