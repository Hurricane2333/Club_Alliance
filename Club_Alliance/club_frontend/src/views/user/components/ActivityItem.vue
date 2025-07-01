<template>
  <el-card class="activity-item-card" shadow="hover">
    <div class="user-info">
      <el-avatar :size="32" :src="activity.user.avatar" />
      <div class="user-details">
        <div class="user-name">{{ activity.user.name }}</div>
        <div class="activity-meta">{{ activity.time }} · {{ activityTypeText }}</div>
      </div>
    </div>
    <p class="activity-content" v-html="activity.content"></p>
    
    <div v-if="activity.activity" class="activity-details">
      <img :src="activity.activity.image" :alt="activity.activity.title" class="activity-image">
      <div class="activity-info">
        <h4 class="activity-title">{{ activity.activity.title }}</h4>
        <p class="activity-meta">
          <el-icon><Calendar /></el-icon> {{ activity.activity.date }}
        </p>
        <p class="activity-meta">
          <el-icon><Location /></el-icon> {{ activity.activity.location }}
        </p>
      </div>
    </div>
    
    <div v-if="activity.comment" class="comment-box">
      <p>"{{ activity.comment }}"</p>
    </div>
    
    <div v-if="activity.club" class="club-details">
      <el-avatar :size="48" :src="activity.club.logo" />
      <div class="club-info">
        <h4 class="club-name">{{ activity.club.name }}</h4>
        <p class="club-description">{{ activity.club.description }}</p>
      </div>
    </div>
    
    <div class="actions">
      <div class="action-buttons">
        <el-button link :icon="Pointer">{{ activity.likes }}</el-button>
        <el-button link :icon="ChatDotRound">{{ activity.comments }}</el-button>
      </div>
      <el-button link :icon="Share">分享</el-button>
    </div>
  </el-card>
</template>

<script setup>
import { defineProps, computed } from 'vue';
import { ElCard, ElAvatar, ElButton, ElIcon } from 'element-plus';
import { Calendar, Location, Pointer, ChatDotRound, Share } from '@element-plus/icons-vue';

const props = defineProps({
  activity: {
    type: Object,
    required: true
  }
});

const activityTypeText = computed(() => {
  switch (props.activity.type) {
    case 'participated':
      return '参与了活动';
    case 'commented':
      return '评论了社团';
    case 'joined':
      return '加入了社团';
    default:
      return '';
  }
});
</script>

<style scoped>
.activity-item-card {
  margin-bottom: 20px;
}

.user-info {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.user-details {
  margin-left: 12px;
}

.user-name {
  font-weight: 500;
}

.activity-meta {
  font-size: 12px;
  color: var(--el-text-color-secondary);
}

.activity-content {
  margin-bottom: 16px;
  color: var(--el-text-color-regular);
}

.activity-details {
  background-color: #f7f7f7;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
  display: flex;
  align-items: flex-start;
}

.activity-image {
  width: 80px;
  height: 80px;
  border-radius: 8px;
  object-fit: cover;
}

.activity-info {
  margin-left: 16px;
}

.activity-title {
  font-weight: 500;
  margin-bottom: 8px;
}

.comment-box {
  background-color: #f7f7f7;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
  font-style: italic;
  color: var(--el-text-color-regular);
}

.club-details {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.club-info {
  margin-left: 16px;
}

.club-name {
  font-weight: 500;
}

.club-description {
  font-size: 14px;
  color: var(--el-text-color-secondary);
}

.actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.action-buttons {
  display: flex;
  gap: 16px;
}

.el-button {
  color: var(--el-text-color-secondary);
}

.el-button:hover {
  color: var(--el-color-primary);
}
</style>