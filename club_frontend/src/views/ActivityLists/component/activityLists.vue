<template>
  <div class="event-list">
    <div class="scroll-container">
      <activity-card
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
import { getAllActivities } from '@/api/activitylists'
import activityCard from './activityCard.vue'
import { ElMessage } from 'element-plus'

const events = ref([])

onMounted(() => {
  fetchAllActivities()
})

const fetchAllActivities = async () => {
  try {
    // 这里建议你实现一个 getAllActivities API，暂时用 getRecentActivities 代替
    const data = await getAllActivities() // 拉取足够多的活动
    events.value = data
    if (data.length === 0) {
      ElMessage.info('暂无活动数据')
    }
  } catch (error) {
    console.error('获取活动列表失败:', error)
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
  display: flex;
  justify-content: center;
  padding-bottom: 20px;
}
.scroll-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 10px 0;
  justify-content: center;
  width: 100%;
  max-width: 1200px;
}
.event-item {
  flex: 0 0 300px;
}
</style>
