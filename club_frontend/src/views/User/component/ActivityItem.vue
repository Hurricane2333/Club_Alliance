<template>
  <el-card
    class="activity-item-card"
    shadow="hover"
    @click="navigateToDetail"
  >
    <div class="activity-details">
      <img v-if="activity.clubIcon" :src="activity.clubIcon" :alt="activity.title" class="activity-image">
      <div class="activity-info">
        <h4 class="activity-title">{{ activity.title }}</h4>
        <p class="activity-meta">
          <el-icon><Calendar /></el-icon> {{ formatDate(activity.start_time) }} - {{ formatTime(activity.end_time) }}
        </p>
        <p class="activity-meta">
          <el-icon><Location /></el-icon> {{ activity.location }}
        </p>
        <p class="activity-meta">
          <el-icon><OfficeBuilding /></el-icon> {{ activity.clubName }}
        </p>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import { defineProps } from 'vue';
import { ElCard, ElIcon } from 'element-plus';
import { Calendar, Location, OfficeBuilding } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';

const props = defineProps({
  activity: {
    type: Object,
    required: true
  }
});

const router = useRouter();

const navigateToDetail = () => {
  router.push({
    name: 'ActivityDetail',
    params: { id: props.activity.id }
  });
};

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleDateString();
};

const formatTime = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'});
};
</script>

<style scoped>
.activity-item-card {
  margin-bottom: 20px;
}

.activity-details {
  display: flex;
  align-items: flex-start;
  padding: 16px;
}

.activity-image {
  width: 80px;
  height: 80px;
  border-radius: 8px;
  object-fit: cover;
  margin-right: 16px;
}

.activity-info {
  flex: 1;
}

.activity-title {
  font-weight: 500;
  margin-bottom: 8px;
}

.activity-meta {
  font-size: 14px;
  color: var(--el-text-color-secondary);
  margin-bottom: 4px;
  display: flex;
  align-items: center;
}

.el-icon {
  margin-right: 8px;
}
.activity-item-card {
  cursor: pointer;
}
</style>
