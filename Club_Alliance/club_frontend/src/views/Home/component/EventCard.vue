<template>
  <div
    class="event-card bg-white rounded-xl shadow-card overflow-hidden hover:shadow-hover transition-custom group border-4 border-gray-400 p-4 padding:20px">
    <!-- 图片区域 -->
    <div class="relative h-48 overflow-hidden">
      <el-image
        :src="event.clubIcon || 'https://picsum.photos/id/1011/800/600'"
        fit="cover"
        class="w-full h-full object-cover group-hover:scale-105 transition-custom duration-500"
      />
    </div>

    <!-- 内容区域 -->
    <div class="p-6">
      <!-- 标题 -->
      <div class="mb-3">
        <h3 class="text-xl font-bold text-dark">{{ event.title }}</h3>
        <h3 class="text-xl font-bold text-dark">{{ event.clubName }}</h3>
      </div>
      <div class="text-gray-500 text-xs">发布于 {{ formatTime(event.createdAt) }}</div>
      <div class="text-sm mt-1">
        {{ formatTime(event.startTime) }}
        <span v-if="event.endTime"> ~ {{ formatTime(event.endTime) }}</span>
      </div>

      <!-- 描述 -->
      <!-- <p class="text-gray-600 text-sm mb-4 line-clamp-2">
        {{ event.content || '这个活动还没有添加描述...' }}
      </p> -->

      <!-- 活动地点信息 -->
      <div class="flex items-center text-sm text-gray-500 mb-4">
        <div class="flex items-center">
          <el-icon class="text-gray-400 mr-1">
            <Location/>
          </el-icon>
          <span>{{ event.location || '待获取' }}</span>
        </div>
      </div>

      <!-- 操作按钮 -->
      <!-- 操作按钮 -->
      <div class="flex items-center justify-between">
        <el-button
          type="primary"
          size="small"
          class="px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom"
          @click="goToDetail(event.activityId)"
        >
          查看详情
        </el-button>

      </div>
    </div>
  </div>
</template>

<script setup>
import {Location} from '@element-plus/icons-vue'
import {useRouter} from 'vue-router'
import dayjs from 'dayjs'
import utc from 'dayjs/plugin/utc'
import timezone from 'dayjs/plugin/timezone'

const router = useRouter()


dayjs.extend(utc)
dayjs.extend(timezone)

const formatTime = (timeString) => {
  if (!timeString) return ''
  return dayjs.utc(timeString).tz(dayjs.tz.guess()).format('YYYY-MM-DD HH:mm')
}


defineProps({
  event: {
    type: Object,
    required: true,
    default: () => ({
      activityId: 0,
      clubName: '',
      clubIcon: 'https://picsum.photos/id/1076/800/600',
      content: '',
      location: '',
    })
  }
})

const goToDetail = (id) => {
  router.push(`/activity/${id}`)
}
</script>

<style scoped>
.event-card {
  border-radius: 16px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
  border-width: 4px;
  border-color: #9ca3af;
  margin: 2px;
  padding: 20px;
}


.event-card {
  @apply rounded-xl shadow-md border border-gray-200;
}
</style>
