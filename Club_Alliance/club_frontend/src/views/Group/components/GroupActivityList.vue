<template>
  <div class="activity-container">
    <div
      v-for="activity in activities"
      :key="activity.id"
      class="activity-card"
    >
      <div class="card-content">
        <h3 class="activity-title">{{ activity.title }}</h3>

        <div class="meta-section">
          <div class="meta-item">
            <i class="icon icon-time"></i>
            <span>{{ activity.startTime }}</span>
          </div>
          <div class="meta-item">
            <i class="icon icon-location"></i>
            <span>{{ activity.location }}</span>
          </div>
        </div>

        <p class="activity-desc">{{ activity.content }}</p>

        <div class="action-bar">
          <button class="signup-button" @click="router.push(`/activity/${activity.activityId}`)">
            查看详情
          </button>
          <span class="participants">
            👥 已报名 {{ activity.currentParticipants || 0 }} / {{ activity.maxParticipants || 0 }} 人
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import request from "@/utils/request.js";
import {ref} from "vue";
import {useRoute} from "vue-router";
import router from "@/router/index.js";

const route = useRoute();

const activities = ref([]);

const fetchActivities = async () => {
  const res = await request.get(`group/activity/selectClubId/${route.params.id}`);
  activities.value = (await Promise.all(res.map(async activity => ({
    ...activity,
  })))).filter(a => a.status !== 'PENDING')
  .sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
};

fetchActivities();

</script>

<style scoped>
.activity-container {
  max-width: 1200px;
  margin: 0 auto;
  gap: 2rem;
  padding: 1rem;
  background: #f5f5f5;
}

.activity-card {
  box-sizing: border-box;
  background: white;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  width: 100%;
  max-width: 100%;
  margin: 0 auto 1rem;
}

.activity-desc {
  width: 100%;
  box-sizing: border-box;
  padding-right: 1rem;
}

.activity-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.card-content {
  padding: 1.5rem;
}

.activity-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #1a1a1a;
}

.meta-section {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
  margin-bottom: 1.25rem;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  color: #666;
  font-size: 0.9rem;
}

.icon {
  width: 18px;
  height: 18px;
}

.icon-time {
  background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="%23666"><path d="M12 2C6.5 2 2 6.5 2 12s4.5 10 10 10 10-4.5 10-10S17.5 2 12 2zm0 18c-4.4 0-8-3.6-8-8s3.6-8 8-8 8 3.6 8 8-3.6 8-8 8zm.5-13H11v6l5.2 3.2.8-1.3-4.5-2.7V7z"/></svg>');
}

.icon-location {
  background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="%23666"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg>');
}

.activity-desc {
  color: #444;
  line-height: 1.6;
  margin-bottom: 1.5rem;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
  text-overflow: ellipsis;
  width: 100%;
}

.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.signup-button {
  background: #007bff;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.2s ease;
}

.signup-button:hover {
  background: #0056b3;
}

.participants {
  color: #666;
  font-size: 0.9rem;
}
</style>
