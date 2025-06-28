<template>
  <el-card class="club-card" shadow="hover">
    <template #header>
      <div class="flex justify-between items-center">
        <span class="text-lg font-bold">{{ club.name }}</span>
        <el-tag :type="club.tagType">{{ club.members }}人</el-tag>
      </div>
    </template>

    <div class="relative h-48 overflow-hidden rounded-lg mb-4">
      <el-image
        :src="club.image"
        fit="cover"
        class="w-full h-full transition-transform duration-500 hover:scale-105"
      />
      <el-tag v-if="club.tag" :type="club.tag === '热门' ? 'danger' : 'warning'" class="absolute top-2 right-2">
        {{ club.tag }}
      </el-tag>
    </div>

    <p class="text-gray-600 text-sm mb-4 line-clamp-2">{{ club.description }}</p>

    <div class="flex items-center text-sm text-gray-500 mb-4">
      <div class="flex items-center mr-4">
        <el-icon class="mr-1"><User /></el-icon>
        <span>{{ club.leader }}</span>
      </div>
      <div class="flex items-center">
        <el-icon class="mr-1"><UserFilled /></el-icon>
        <span>{{ club.members }} 成员</span>
      </div>
    </div>

    <div class="flex justify-between items-center">
      <div class="flex">
        <el-button text circle>
          <el-icon><Star /></el-icon>
        </el-button>
        <el-button text circle>
          <el-icon><Collection /></el-icon>
        </el-button>
      </div>
      <el-button type="primary" size="small" @click="router.push(`/group/${club.id}`)">查看详情</el-button>
    </div>
  </el-card>
</template>

<script setup>
import { User, UserFilled, Star, Collection } from '@element-plus/icons-vue'
import router from "@/router/index.js";


defineProps({
  club: {
    type: Object,
    required: true
  }
})


</script>

<style scoped>
.club-card {
  margin-bottom: 20px;
  transition: all 0.3s ease;
}

.club-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
}
</style>
