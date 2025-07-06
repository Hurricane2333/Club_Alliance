<template>
  <el-card class="sidebar-card">
    <div class="stats-container">
      <h3 class="stats-title">社团数据概览</h3>

      <el-row :gutter="16" class="stats-item">
        <el-col :span="12">
          <div class="stat-label">总成员数</div>
          <div class="stat-value text-primary">{{ clubData.currentMembers }}</div>
        </el-col>
        <el-col :span="12">
          <div class="stat-label">收藏人数</div>
          <div class="stat-value text-warning">{{ clubData.favoriteCount }}</div>
        </el-col>
      </el-row>

      <el-divider />

      <el-row :gutter="16" class="stats-item">
        <el-col :span="12">
          <div class="stat-label">待审核人数</div>
          <div class="stat-value text-success">{{ pendingMembers }}</div>
        </el-col>
        <el-col :span="12">
          <div class="stat-label">活动总数</div>
          <div class="stat-value text-info">{{ activitives }}</div>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script setup>
import {inject, ref} from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request.js';

const clubData = inject('clubData', {})

const route = useRoute();
const pendingMembers = ref();
const activitives = ref();

request.get(`/group/member/selectClubIdAndStatus/${route.params.id}/PENDING`).then(res => {
  // console.log(res)
  pendingMembers.value = res.length;
});

request.get(`/group/activity/selectClubId/${route.params.id}`).then(res => {
  activitives.value = res.length;
})

</script>

<style scoped>
.sidebar-card {
  margin-bottom: 20px;
}

.stats-container {
  padding: 12px;
}

.stats-title {
  font-size: 16px;
  color: #606266;
  margin-bottom: 20px;
}

.stats-item {
  margin: 12px 0;
}

.stat-label {
  font-size: 13px;
  color: #909399;
}

.stat-value {
  font-size: 18px;
  font-weight: 600;
  margin-top: 4px;
}

.text-primary { color: #409eff; }
.text-warning { color: #e6a23c; }
.text-success { color: #67c23a; }
.text-info { color: #909399; }
</style>
