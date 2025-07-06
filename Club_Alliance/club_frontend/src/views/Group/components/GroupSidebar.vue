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
      <h3 class="blue-title">📌 当前活动</h3>
      <el-table
        :data="activities"
        stripe
        class="activity-table"
        height="200"
        @row-click="handleRowClick"
      >
        <el-table-column show-overflow-tooltip prop="title" label="活动标题" width="90"/>
        <el-table-column show-overflow-tooltip prop="location" label="活动地点"/>
        <el-table-column show-overflow-tooltip prop="currentParticipants" label="参与人数" width="90"/>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import {reactive} from 'vue';
import {useRoute} from 'vue-router';
import request from '@/utils/request.js';

const route = useRoute();
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
const activities = reactive([])

request.get(`/group/selectId/${route.params.id}`).then(res => {
  Object.assign(club, res)
});

request.get(`/group/activity/selectClubId/${route.params.id}`).then(res => {
  console.log('活动接口原始数据:', res);
  activities.splice(0, activities.length, ...res.map(item => ({
    activityId: item.activityId,
    title: item.title,
    location: item.location,
    currentParticipants: item.currentParticipants,
    status: item.status,
    endTime: item.endTime
  })).filter(item => item.status === 'ACTIVE').sort((a,b) => new Date(a.endTime) - new Date(b.endTime)));
  console.log('处理后的活动数据:', activities);
});
import { useRouter } from 'vue-router';

const router = useRouter();

const handleRowClick = (row) => {
  router.push(`/activity/${row.activityId}`);
};
</script>

<style scoped>
.sidebar-container {
  background: #fff;
  border-radius: 6px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
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
  margin-left: 10px;
}

.value {
  color: #0084ff;
  margin-right: 10px;
}

.activity-table {
  margin-top: 10px;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);

  :deep(.el-table__cell) {
    padding: 8px 0;
  }

  :deep(th.el-table__cell) {
    background-color: #f8f9fa;
  }
  :deep(.el-table__row:hover) {
    cursor: pointer;
    background-color: #f5f7fa;
  }
}
</style>
