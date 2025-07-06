<template>
  <div class="home-container">
    <NavBar />
    <div class="main-content">
      <div class="activity-lists-view">
        <ActivityListsMapView v-if="activities.length > 0" :activities="activities" />
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
.activity-lists-view {
  padding: 32px;
}
.home-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>
