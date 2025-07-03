<template>
  <div class="sidebar-container">
    <div class="stats-box">
      <h3 class="blue-title">📊 社团统计</h3>
      <div class="stat-item">
        <span class="label">成员总数</span>
        <span class="value">{{ club.currentMembers }}人</span>
      </div>
      <div class="stat-item">
        <span class="label">收藏总数</span>
        <span class="value">{{ club.favoriteCount }}人</span>
      </div>
    </div>

    <div class="notice-box">
      <h3 class="blue-title">📌 置顶公告</h3>
      <div class="notice-content">
        {{ announcements }}
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive, ref} from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request.js';

const route = useRoute();
const announcements = ref('');
const club = reactive({
  clubId: 0,
  clubName: '',
  description: '',
  category: '',
  icon: '',
  presidentId: 0,
  requirements: '',
  favoriteCount: 0,
  currentMembers: 0,
  createdAt: '0',
})

request.get(`/group/selectId/${route.params.id}`).then(res => {
  Object.assign(club, res)
});

request.get(`/group/announcements/${route.params.id}`).then(res => {
  announcements.value = res.content;
});
</script>

<style scoped>
.sidebar-container {
  background: #fff;
  border-radius: 6px;
  box-shadow: 0 2px 6px rgba(0,0,0,0.08);
  padding: 15px;
}

.blue-title {
  color: #0084ff;
  font-size: 16px;
  margin: 0 0 12px 0;
  padding-bottom: 8px;
  border-bottom: 2px solid #f0f0f0;
}

.stats-box {
  margin-bottom: 20px;
}

.stat-item {
  display: flex;
  justify-content: space-between;
  margin: 10px 0;
  color: #666;
}

.label {
  font-weight: 500;
}

.value {
  color: #0084ff;
}

.notice-content {
  background: #f8f9fa;
  padding: 12px;
  border-radius: 4px;
  line-height: 1.5;
  color: #444;
}
</style>
