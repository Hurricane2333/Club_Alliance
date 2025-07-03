<template>
  <el-menu
    mode="horizontal"
    :ellipsis="false"
    class="custom-navbar"
  >
    <div class="flex items-center px-4">
      <img
        src="@/assets/title.jpg"
        alt="社团联盟"
        class="h-10"
      />

    </div>

    <div class="flex-grow"></div>

    <el-menu-item index="1">首页</el-menu-item>
    <el-menu-item index="2">社团列表</el-menu-item>
    <el-menu-item index="3">社团分类</el-menu-item>
    <el-menu-item index="4">活动信息</el-menu-item>
    <el-menu-item index="5">我的社团</el-menu-item>

    <div class="flex items-center px-4">
      <el-input
          placeholder="搜索社团、活动..."
          class="w-64"
          :prefix-icon="Search"
      />
      <div class="nav-auth-btns">
        <el-button type="primary" plain>登录</el-button>
        <el-button type="primary" class="ml-2">注册</el-button>
      </div>

    </div>
  </el-menu>
</template>

<script setup>
import { User, Search } from '@element-plus/icons-vue'
</script>


<style scoped>
/* 调整整体高度 */
.custom-navbar {
  height: 80px; /* 默认约60px */
  line-height: 80px;
  width:1200px;
}

/* 调整菜单项样式 */
.custom-navbar .el-menu-item {
  font-size: 18px;
  padding: 0 25px;
  font-weight: 500;
}

/* 调整logo和标题大小 */
.custom-navbar .logo-container {
  font-size: 24px;
}

/* 调整搜索框样式 */
.custom-navbar .search-input {
  width: 300px;
  margin: 0 20px;
}

/* 调整按钮样式 */
.custom-navbar .nav-buttons {
  margin-left: 20px;
}
</style>
