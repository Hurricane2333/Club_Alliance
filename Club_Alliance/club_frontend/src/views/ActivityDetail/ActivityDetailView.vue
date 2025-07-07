<template>
  <div class="home-container">
    <NavBar />
    <el-divider />
    <div class="main-content">
      <div class="content-card">
        <el-button
          type="primary"
          @click="router.go(-1)"
          class="mb-4"
          :icon="ArrowLeft"
        >返回</el-button>
        <h1 class="title">活动详情</h1>
        <el-divider />

        <ActivityContent/>
      </div>
    </div>
  </div>
</template>

<script setup>
import ActivityContent from './component/ActivityContent.vue'
import NavBar from '../Home/component/NavBar.vue'
import { useRouter } from 'vue-router'

const router = useRouter()
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
  background: linear-gradient(rgba(238, 241, 242, 0.9), rgba(231, 247, 250, 0.9)),
  url('@/assets/homeback.jpg') center/cover no-repeat;
  position: relative;
}
.background-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(231, 235, 236, 0.6);
  z-index: 1;
}
.main-content {
  padding: 32px;
  max-width: 1300px;
  width: 100%;
  margin: 0 auto;
}

.content-card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1);
  padding: 32px;
  margin-bottom: 24px;
  width: 600px;
  margin: 0 auto;
}
</style>
