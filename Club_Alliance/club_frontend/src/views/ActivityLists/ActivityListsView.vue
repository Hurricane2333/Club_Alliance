<template>
  <div class="home-container">
    <NavBar />
    <div class="main-content">
      <div class="activity-lists-view">
        <div class="map-container">
          <ActivityListsMapView v-if="activities.length > 0" :activities="activities" />
        </div>
        <activity-lists />
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { getAllActivities } from '@/api/activitylists';
import activityLists from './component/activityLists.vue'
import ActivityListsMapView from './component/ActivityListsMapView.vue';

const activities = ref([]);

onMounted(async () => {
  try {
    const response = await getAllActivities();
    activities.value = response || [];
  } catch (error) {
    console.error('获取活动数据失败:', error);
  }
});

import NavBar from '../Home/component/NavBar.vue'
</script>

<style scoped>
.title {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 24px;
}
.map-container {
  width: 100%;
  margin: 0 auto;
  max-width: 1200px;
  padding: 20px 0;
}

/* 调整整体布局 */
.activity-lists-view {
  padding: 32px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 40px; /* 新增间距 */
}

/* 新增活动列表容器样式 */
.activity-lists-view > :not(.map-container) {
  width: 100%;
  max-width: 1200px;
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
</style>
