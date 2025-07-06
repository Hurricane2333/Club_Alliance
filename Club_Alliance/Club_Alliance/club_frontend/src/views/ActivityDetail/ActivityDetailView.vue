<template>
  <div class="home-container">
    <NavBar />
    <el-divider />
    <div class="main-content">
      <div class="activity-content-card bg-white rounded-xl shadow-card p-8 mx-auto" style="max-width: 2000px;">

        <ActivityContent/>
      </div>
    </div>
  </div>
</template>

<script setup>
import ActivityContent from './component/ActivityContent.vue'
import NavBar from '../Home/component/NavBar.vue'
</script>

<style scoped>
.title {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 24px;
}
.activity-detail-view {
  padding: 32px;
}
.home-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.main-content {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: flex-start; /* 或 center，如果你想垂直居中 */
  max-width: 1300px;      /* 设置最大宽度 */
  width: 100%;           /* 宽度自适应 */
  margin: 0 auto;
}
</style>
