<template>
  <div class="event-list">
    <div class="scroll-container">
      <EventCard
        v-for="event in events"
        :key="event.activityId"
        :event="event"
        class="event-item"
        @view-detail="handleViewDetail"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getRecentActivities } from '@/api/home'
import EventCard from './EventCard.vue'
import { ElMessage } from 'element-plus';

const events = ref([])

onMounted(() => {
  fetchRecentActivities()
})

const fetchRecentActivities = async () => {
  try {
    const data = await getRecentActivities()
    events.value = data
    if (data.length === 0) {
      ElMessage.info('暂无推荐活动');
    }
  } catch (error) {
    console.error('获取活动列表失败:', error)
    // 使用后备数据
    events.value = getFallbackEvents()
  }
}

const handleViewDetail = (activityId) => {
  console.log('查看活动详情:', activityId)
}

const getFallbackEvents = () => [
  {
    activityId: 1,
    clubName: '示例社团',
    content: '网络异常时显示的默认数据',
    location: '示例地点',
    clubIcon: 'https://picsum.photos/id/1011/800/600',
  }
]
</script>

<style scoped>
.event-list {
  width: 100%;
  overflow-x: auto;
  padding-bottom: 20px;
}
.scroll-container {
  display: flex;
  gap: 20px;
  padding: 10px 0;
  width: max-content;
  overflow-x: auto;
}
.event-item {
  flex: 0 0 300px;
}
</style>
