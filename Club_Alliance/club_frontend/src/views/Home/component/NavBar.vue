<template>
  <el-menu
    mode="horizontal"
    :ellipsis="false"
    class="custom-navbar"
    router
  >
    <div class="flex items-center px-4">
      <img
        src="@/assets/title.jpg"
        alt="社团联盟"
        class="h-10"
      />
    </div>

    <div class="flex-grow"></div>

    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/clublists">社团列表</el-menu-item>
    <el-menu-item index="/activitylists">活动信息</el-menu-item>
    <el-menu-item index="5">个人信息</el-menu-item>



    <div class="flex items-center px-4">
      <el-input
        placeholder="搜索社团、活动..."
        class="w-64"
        :prefix-icon="Search"
      />
    </div>
  </el-menu>
</template>

<script setup>
import {  Search } from '@element-plus/icons-vue'
</script>


<style scoped>
.navbar-wrapper {
  display: flex;
  justify-content: center;
  width: 100%;
}

.custom-navbar {
  height: 80px;
  line-height: 80px;
  max-width: 1200px; /* 限制导航栏最大宽度 */
  width: 100%;
  margin: 0 auto; /* 水平居中 */
}

.custom-navbar .el-menu-item {
  font-size: 18px;
  padding: 0 25px;
  font-weight: 500;
}
</style>
