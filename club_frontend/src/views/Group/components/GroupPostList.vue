<template>
  <div class="post-list">
    <div
      v-for="post in posts"
      :key="post.id"
      class="post-card"
      @mouseenter="(e) => hoverEffect(e.currentTarget)"
      @mouseleave="(e) => resetEffect(e.currentTarget)"
    >
      <div class="post-header">
        <div class="post-title">{{ post.title }}</div>
        <div class="user-info">
          <span class="user-id">@{{ post.userId }}</span>
        </div>
        <span class="time">{{ post.createdAt }}</span>
      </div>
      <div class="post-content">
        {{ post.content }}
      </div>
      <div class="post-footer">
        <div class="interaction">
          <span class="icon comment">💬 {{ post.commentCount }}</span>
          <span class="icon like">👍 {{ post.likes }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request.js';

const route = useRoute();
const posts = ref([]);

request.get(`/group/post/selectClubId/${route.params.id}`).then(res => {
  posts.value = res;
});

const hoverEffect = (element) => {
  element.style.transform = 'translateY(-3px)';
  element.style.boxShadow = '0 4px 12px rgba(0,0,0,0.15)';
};

const resetEffect = (element) => {
  element.style.transform = 'none';
  element.style.boxShadow = '0 2px 6px rgba(0,0,0,0.1)';
};
</script>

<style scoped>
.post-list {
  background: #f5f5f5;
  padding: 15px;
  border-radius: 4px;
}

.post-card {
  background: white;
  margin-bottom: 15px;
  padding: 15px;
  border-radius: 6px;
  transition: all 0.3s ease;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

.post-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  color: #666;
  font-size: 0.9em;
}

.post-content {
  color: #333;
  line-height: 1.6;
  margin-bottom: 12px;
}

.post-footer {
  border-top: 1px solid #eee;
  padding-top: 10px;
}

.interaction {
  display: flex;
  gap: 20px;
}

.icon {
  cursor: pointer;
  color: #8590a6;
  transition: color 0.2s;
}

.icon:hover {
  color: #0084ff;
}

.user-info {
  display: flex;
  flex-direction: column;
}

.user-id {
  font-size: 0.8em;
  color: #8590a6;
}

.post-title {
  font-size: 20px;
  font-weight: 500;
  margin-bottom: 8px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
