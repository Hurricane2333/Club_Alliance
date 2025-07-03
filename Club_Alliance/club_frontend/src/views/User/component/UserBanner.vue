<template>
  <div class="user-banner">
    <img :src="userInfo.background" alt="个人主页背景" class="banner-background">
    <div class="banner-overlay">
      <div class="banner-content">
        <el-avatar :size="96" :src="userInfo.avatar" class="user-avatar" />
        <div class="user-details">
          <h2 class="user-name">{{ userInfo.stuName }}</h2>
          <p class="user-info">{{ userInfo.college }} | {{ userInfo.enrollmentYear }}级本科生</p>
          <div class="actions">
            <div v-if="!isSelf">
              <el-button type="primary" :icon="Plus" round>关注</el-button>
              <el-button :icon="Message" round>私信</el-button>
            </div>
            <el-button v-else type="primary" :icon="Edit" round @click="openEditProfile">
              编辑资料
            </el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';
import { Plus, Message, Edit } from '@element-plus/icons-vue';

defineProps({
  userInfo: Object,
  isSelf: Boolean
});

const emits = defineEmits(['edit-profile']);

const openEditProfile = () => {
  emits('edit-profile');
};
</script>

<style scoped>
.user-banner {
  position: relative;
  height: 256px;
}

.banner-background {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.banner-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(to top, rgba(0, 0, 0, 0.6), transparent);
  display: flex;
  align-items: flex-end;
}

.banner-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px 32px;
  display: flex;
  align-items: center;
}

.user-avatar {
  border: 4px solid white;
}

.user-details {
  margin-left: 24px;
}

.user-name {
  font-size: 24px;
  font-weight: bold;
  color: white;
  margin-bottom: 8px;
}

.user-info {
  color: rgba(255, 255, 255, 0.8);
  margin-bottom: 16px;
}

.actions .el-button {
  margin-right: 12px;
}

@media (max-width: 768px) {
  .banner-content {
    flex-direction: column;
    text-align: center;
  }

  .user-avatar {
    margin-bottom: 16px;
  }

  .user-details {
    margin-left: 0;
  }
}
</style>
