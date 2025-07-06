<template>
  <el-card class="recommended-clubs-card" shadow="never">
    <template #header>
      <div class="card-header">
        <el-icon><Star /></el-icon>
        <span>推荐社团</span>
      </div>
    </template>
    <div class="club-list">
      <div v-for="club in recommendedClubs" :key="club.id" class="club-item">
        <el-avatar :size="48" :src="club.logo" />
        <div class="club-info">
          <h4 class="club-name">{{ club.name }}</h4>
          <p class="club-category">{{ club.category }}</p>
        </div>
        <el-button type="primary" plain round size="small" :icon="Plus">关注</el-button>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import { defineProps } from 'vue';
import { Star, Plus } from '@element-plus/icons-vue';

defineProps({
  recommendedClubs: Array
});
</script>

<style scoped>
.recommended-clubs-card {
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
  border-radius: 8px;
  transition: background-color 0.3s;
}

.club-item:hover {
  background-color: var(--el-fill-color-light);
}

.club-info {
  margin-left: 12px;
  flex: 1;
}

.club-name {
  font-weight: 500;
}

.club-category {
  font-size: 12px;
  color: var(--el-text-color-secondary);
}

.club-item .el-button {
  margin-left: auto;
}
</style>
