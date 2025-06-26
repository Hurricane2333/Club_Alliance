<template>
  <div class="home-container">
    <NavBar /> <!-- 顶部导航栏 -->
    <div class="main-content">
      <h1>欢迎来到社团管理系统</h1>
      <el-divider />
      <div class="club-list">
        <div class="scroll-container">
          <ClubCard
            v-for="club in clubs"
            :key="club.id"
            :club="club"
            class="club-item"
          />
        </div>
      </div>
      <EventLists />
    </div>
  </div>
</template>

<script setup>
import NavBar from './component/NavBar.vue';
import ClubCard from './component/ClubCard.vue';
import EventLists from './component/EventLists.vue';
import axios from "axios";




const clubs = [
  { id: 1, name: "篮球社",tag:"热门", description: "热爱篮球的同学们聚集地", members: 50 },
  { id: 2, name: "编程社", description: "学习编程技术，参加竞赛", members: 80 },
  { id: 3, name: "摄影社", description: "记录美好校园生活", members: 30 },

];




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
  margin: 0 auto;
}
.club-list {
  width: 100%;
  overflow-x: auto;
  padding-bottom: 20px;
}
.scroll-container {
  display: flex;
  gap: 20px;
  padding: 10px 0;
  width: max-content; /* 使容器宽度随内容扩展 */
}

.club-item {
  flex: 0 0 300px; /* 固定卡片宽度，不伸缩 */
}
</style>
