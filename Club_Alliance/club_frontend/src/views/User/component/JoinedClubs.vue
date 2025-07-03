<template>
  <el-card class="joined-clubs-card" shadow="never">
    <template #header>
      <div class="card-header">
        <el-icon><OfficeBuilding /></el-icon>
        <span>加入的社团</span>
      </div>
    </template>
    <div class="club-list">
      <div v-for="club in joinedClubs" :key="club.id" class="club-item">
        <el-avatar :size="48" :src="club.logo" />
        <div class="club-info">
          <h4 class="club-name">{{ club.name }}</h4>
          <p class="club-meta"> {{ club.joinDate.replace('T', ' ').slice(0, 16) }} 加入</p>
        </div>
        <el-button type="text" :icon="ArrowRight" class="details-button" />
      </div>
    </div>
    <template #footer>
      <el-button type="text" class="view-all-button">
        查看全部 <el-icon class="el-icon--right"><ArrowDown /></el-icon>
      </el-button>
    </template>
  </el-card>
</template>

<script setup>
import { defineProps } from 'vue';
import { OfficeBuilding, ArrowRight, ArrowDown } from '@element-plus/icons-vue';

defineProps({
  joinedClubs: Array
});


</script>

<style scoped>
.joined-clubs-card {
  margin-bottom: 24px;
}

.card-header {
  display: flex;
  align-items: center;
  font-weight: bold;
}

.card-header .el-icon {
  margin-right: 8px;
}

.club-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.club-item {
  display: flex;
  align-items: center;
  padding: 12px;
  background-color: var(--el-fill-color-light);
  border-radius: 8px;
}

.club-info {
  margin-left: 12px;
  flex: 1;
}

.club-name {
  font-weight: 500;
}

.club-meta {
  font-size: 12px;
  color: var(--el-text-color-secondary);
}

.details-button {
  margin-left: auto;
}

.view-all-button {
  width: 100%;
}
</style>
