<template>
  <div class="event-card bg-white rounded-xl shadow-card overflow-hidden hover:shadow-hover transition-custom group">
    <div class="relative h-48 overflow-hidden">
      <el-image
        :src="event.clubIcon || 'https://picsum.photos/id/1011/800/600'"
        fit="cover"
        class="w-full h-full object-cover group-hover:scale-105 transition-custom duration-500"
      />
    </div>
    <div class="p-6">
      <div class="mb-3">
        <h3 class="text-xl font-bold text-dark">{{ event.title }}</h3>
        <h3 class="text-xl font-bold text-dark">{{ event.clubName }}</h3>
      </div>
      <div class="text-gray-500 text-xs">发布于 {{ event.createdAt ? event.createdAt.replace('T', ' ').slice(0, 16) : '' }}</div>
      <div class="text-sm mt-1">
        {{ event.startTime ? event.startTime.replace('T', ' ').slice(0, 16) : '' }}
        <span v-if="event.endTime"> ~ {{ event.endTime.replace('T', ' ').slice(0, 16) }}</span>
      </div>
      <p class="text-gray-600 text-sm mb-4 line-clamp-2">
        {{ event.content || '这个活动还没有添加描述...' }}
      </p>
      <div class="flex items-center text-sm text-gray-500 mb-4">
        <div class="flex items-center">
          <el-icon class="text-gray-400 mr-1"><Location /></el-icon>
          <span>{{ event.location || '待获取' }}</span>
        </div>
      </div>
      <div class="flex items-center justify-between">
        <el-button
          type="primary"
          size="small"
          class="px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom"
          @click="$emit('view-detail', event.activityId)"
        >
          查看详情
        </el-button>
        <el-button
          type="primary"
          size="small"
          class="px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom"
        >
          报名参加
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { Location } from '@element-plus/icons-vue'

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
</script>

<style scoped>
.event-card {
  /* 可添加自定义样式 */
}
</style>
