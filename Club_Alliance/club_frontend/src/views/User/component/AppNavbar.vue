<template>
  <el-header class="navbar" :class="navbarShadow">
    <div class="navbar-container">
      <!-- Logo -->
      <div class="logo">
        <el-icon class="logo-icon"><User /></el-icon>
        <span class="logo-text">社团联盟</span>
      </div>

      <!-- 桌面导航 -->
      <el-menu
        mode="horizontal"
        :default-active="activeIndex"
        class="desktop-menu"
        @select="handleSelect"
      >
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="2">社团列表</el-menu-item>
        <el-menu-item index="3">社团分类</el-menu-item>
        <el-menu-item index="4">活动信息</el-menu-item>
        <el-menu-item index="5">个人主页</el-menu-item>
        <el-menu-item index="6">我的社团</el-menu-item>
      </el-menu>

      <!-- 搜索框 -->
      <el-input
        v-model="searchText"
        class="search-input"
        placeholder="搜索社团、活动..."
        :prefix-icon="Search"
      />

      <!-- 用户操作 -->
      <div class="user-actions">
        <el-dropdown trigger="click">
          <div class="user-info">
            <el-avatar :size="32" :src="userInfo.avatar" />
            <span class="user-name">{{ userInfo.name }}</span>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="handleLogout">
                <el-icon><SwitchButton /></el-icon> 退出登录
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>


      </div>
    </div>


  </el-header>
</template>

<script setup>
import { ref } from 'vue';
import { User, Search, SwitchButton } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';

defineProps({
  userInfo: Object,
  navbarShadow: String
});

const searchText = ref('');
const activeIndex = ref('6'); // 默认选中"个人主页"

const router = useRouter();

const handleSelect = (index) => {
  activeIndex.value = index;
  switch (index) {
    case '1':
      router.push('/home');
      break;
    case '2':
      router.push('/clubs');
      break;
    case '3':
      router.push('/categories');
      break;
    case '4':
      router.push('/events');
      break;
    case '5':
      router.push('/profile');
      break;
    case '6':
      router.push('/my-clubs');
      break;
  }
};

const handleLogout = () => {
  // 清除本地存储的用户信息和token
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  // 跳转到登录页面
  router.push('/login');
};
</script>

<style scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  background-color: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(8px);
  transition: all 0.3s;
  padding: 0;
  height: 64px;
}

.navbar-container {
  max-width: 1200px;
  margin: 0 auto;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
}

.logo {
  display: flex;
  align-items: center;
  gap: 8px;
}

.logo-icon {
  font-size: 24px;
  color: var(--el-color-primary);
}

.logo-text {
  font-size: 18px;
  font-weight: bold;
  color: var(--el-color-primary);
}

.desktop-menu {
  flex-grow: 1;
  justify-content: center;
  border-bottom: none;
}

.search-input {
  width: 240px;
  margin: 0 24px;
}

.user-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
}

.user-name {
  font-size: 14px;
}



@media (max-width: 768px) {
  .search-input {
    display: none;
  }
  .navbar-container {
    padding: 0 16px;
  }
}
</style>
