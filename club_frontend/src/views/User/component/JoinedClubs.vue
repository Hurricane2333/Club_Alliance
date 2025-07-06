<template>
  <el-card class="joined-clubs-card" shadow="never">
    <template #header>
      <div class="card-header">
        <el-icon><OfficeBuilding /></el-icon>
        <span>加入的社团</span>
      </div>
    </template>
    <div class="club-list">
      <div
        v-for="club in joinedClubs"
        :key="club.id"
        class="club-item"
        @click="navigateToClubDetail(club.id)"
      >
        <el-avatar :size="48" :src="club.logo"/>
        <div class="club-info">
          <h4 class="club-name">{{ club.name }}</h4>
          <p class="club-meta"> {{ club.joinDate.replace('T', ' ').slice(0, 16) }} 加入</p>
        </div>
        <el-button
          type="text"
          :icon="ArrowRight"
          class="details-button"
          @click.stop="navigateToClubDetail(club.id)"
        />
      </div>
    </div>
    <template #footer>
      <el-button type="text" class="view-all-button">
        查看全部
        <el-icon class="el-icon--right">
          <ArrowDown/>
        </el-icon>
      </el-button>
    </template>
  </el-card>
</template>

<script setup>
import { defineProps } from 'vue';
import { useRouter } from 'vue-router';
import { OfficeBuilding, ArrowRight, ArrowDown } from '@element-plus/icons-vue';

const props = defineProps({
  joinedClubs: Array
});

const router = useRouter();

const navigateToClubDetail = (clubId) => {
  router.push({
    path: `/group/${clubId}`
  });
};
</script>

<style scoped>
/* 添加可点击样式 */
.club-item {
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.club-item:hover {
  background-color: var(--el-fill-color);
}

/* 其他样式保持不变 */
</style>
