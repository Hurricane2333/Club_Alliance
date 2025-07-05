<template>
  <div class="home-container">
    <NavBar />
    <div class="main-content">
      <div class="activity-lists-view">
        <el-divider />
        <activity-lists />
      </div>
    </div>
  </div>
</template>


<script setup>
import activityLists from './component/activityLists.vue'
import NavBar from '../Home/component/NavBar.vue'
</script>

<style scoped>
.title {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 24px;
}
.activity-lists-view {
  padding: 32px;
}
.home-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>
