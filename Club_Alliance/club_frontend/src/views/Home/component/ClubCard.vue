<template>
  <div
    class="club-card bg-white rounded-xl shadow-card overflow-hidden hover:shadow-hover transition-custom group border-4 border-gray-400 p-4 padding:20px">
    <!-- 图片区域 -->
     <div class="relative h-44 overflow-hidden mt-[-16px] mx-[-16px]">
      <el-image
        :src="club.icon || 'https://picsum.photos/id/1011/800/600'"
        fit="cover"
        class="w-full h-full object-cover group-hover:scale-105 transition-custom duration-500"
      />
      <el-tag
        v-if="club.tag"
        :type="club.tag === '热门' ? 'danger' : 'warning'"
        class="absolute top-4 right-4 text-xs font-semibold px-3 py-1 rounded-full"
        effect="dark"
      >
        {{ club.tag }}
      </el-tag>
    </div>

    <!-- 内容区域 -->
    <div class="p-4">
      <!-- 标题和分类 -->
      <div class="flex items-center justify-between mb-3">
        <h3 class="text-xl font-bold text-dark">{{ club.clubName }}</h3>
        <span
          class="text-xs px-2 py-1 rounded"
          :class="getCategoryClass(club.category)"
        >
          {{ mapCategory(club.category) }}
        </span>
      </div>
      <div class="w-full">
        <p class="text-gray-600 text-sm mb-4">
          {{
            club.description ? club.description.slice(0, 12) + (club.description.length > 12 ? '...' : '') : '这个社团还没有添加描述...'
          }}
        </p>
      </div>
      <!-- 负责人和成员信息 -->
      <div class="flex items-center text-sm text-gray-500 mb-4">
        <div class="flex items-center mr-4">
          <el-icon class="text-gray-400 mr-1">
            <User/>
          </el-icon>
          <span>{{ club.presidentName || '待获取' }}</span>
        </div>
        <div class="flex items-center">
          <el-icon class="text-gray-400 mr-1">
            <UserFilled/>
          </el-icon>
          <span>{{ club.currentMembers || 0 }} 成员</span>
        </div>
      </div>

      <!-- 操作按钮 -->
      <div class="flex items-center justify-between mt-6"> <!-- 从mt-4改为mt-6 -->
        <div class="flex items-center">
          <el-button text circle class="text-gray-400 hover:text-primary transition-custom">
            <el-icon>
              <Star/>
            </el-icon>
            <span class="ml-1 text-xs">{{ club.favoriteCount || 0 }}</span>
          </el-button>
          <el-button text circle class="text-gray-400 hover:text-primary transition-custom ml-2">
            <el-icon>
              <Collection/>
            </el-icon>
          </el-button>
        </div>
        <el-button
          type="primary"
          size="small"
          class="px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom"
          @click="router.push(`/group/${club.clubId}`)"
        >
          查看详情
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {User, UserFilled, Star, Collection} from '@element-plus/icons-vue'
import {useRouter} from 'vue-router'

const router = useRouter()

defineProps({
  club: {
    type: Object,
    required: true,
    default: () => ({
      clubId: 0,
      clubName: '',
      icon: 'https://picsum.photos/id/1076/800/600',
      description: '',
      presidentName: '',
      currentMembers: 0,
      category: 'OTHER',
      tag: '',
      favoriteCount: 0
    })
  }
})

// 类别到颜色的映射
const getCategoryClass = (category) => {
  const map = {
    'ACADEMIC': 'bg-blue-100 text-blue-600',
    'ARTS': 'bg-purple-100 text-purple-600',
    'SPORTS': 'bg-green-100 text-green-600',
    'VOLUNTEER': 'bg-red-100 text-red-600',
    'HOBBY': 'bg-yellow-100 text-yellow-600',
    'CAREER': 'bg-indigo-100 text-indigo-600',
    'DEBATE': 'bg-pink-100 text-pink-600',
    'MAKER': 'bg-teal-100 text-teal-600',
    'OTHER': 'bg-gray-100 text-gray-600'
  }
  return map[category] || 'bg-gray-100 text-gray-600'
}

// 类别枚举值转换为中文
const mapCategory = (category) => {
  const map = {
    'ACADEMIC': '学术',
    'ARTS': '艺术',
    'SPORTS': '体育',
    'VOLUNTEER': '公益',
    'HOBBY': '兴趣',
    'CAREER': '职业',
    'DEBATE': '辩论',
    'MAKER': '创客',
    'OTHER': '其他'
  }
  return map[category] || '其他'
}
</script>


<style scoped>
.club-card {
  border-radius: 16px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
  border-width: 4px;
  border-color: #9ca3af;
  margin: 2px;
  padding: 20px;
}


.club-card {
  @apply rounded-xl shadow-md border border-gray-200;
}
</style>
