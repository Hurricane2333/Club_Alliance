<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HomeView from './views/Home/HomeView.vue';
</script>

<template>
  <div class="app-container">
    <header class="app-header">
      <div class="header-content">
        <NavBar />
      </div>
    </header>

    <main class="app-main">
      <HomeView />
    </main>

    <footer class="app-footer">
      <!-- 页脚内容 -->
    </footer>
  </div>
</template>

<style scoped>
/* 基础布局样式 - 填充整个宽度 */
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  width: 100%;
}

.app-header {
  background-color: #ffffff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
  width: 100%;
}

.header-content {
  width: 100%;
  padding: 1rem;
}

.app-main {
  flex: 1;
  padding: 2rem 0; /* 移除左右padding使内容填充宽度 */
  width: 100%;
}

.app-footer {
  background-color: #f8f9fa;
  padding: 1.5rem;
  text-align: center;
  width: 100%;
}

/* 导航样式 */
.main-nav {
  display: flex;
  gap: 1rem;
  padding: 0.5rem 1rem; /* 添加左右padding */
}

.main-nav a {
  color: #2c3e50;
  text-decoration: none;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.main-nav a:hover {
  background-color: #f0f0f0;
}

.main-nav a.router-link-exact-active {
  color: #42b983;
  background-color: #e8f5e9;
}
</style>
