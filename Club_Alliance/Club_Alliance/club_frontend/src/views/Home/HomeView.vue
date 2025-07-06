<template>
  <div class="home-container">
    <NavBar />
    <div class="main-content">
      <h1>欢迎</h1>
      <el-divider />
      <h1>特色社团推荐</h1>


      <ClubList />

      <!-- 活动区 -->
      <h1>活动推荐</h1>
      <EventLists />

    </div>
  </div>
</template>

<script setup>
import NavBar from './component/NavBar.vue'
import ClubList from './component/ClubList.vue'
import EventLists from './component/EventLists.vue'
</script>

<style scoped>
.home-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.main-content {
  padding: 20px;
  max-width: 1200px;
  width: 100%;
  margin: 0 auto;
}
.activity-list {
  width: 100%;
}
</style>
