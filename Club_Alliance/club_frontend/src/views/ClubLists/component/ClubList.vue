<template>
  <div class="club-list">
    <div class="scroll-container">
      <ClubCard
        v-for="club in clubs"
        :key="club.clubId"
        :club="club"
        class="club-item"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getAllClubs } from '@/api/clublists' // 你需要实现此API
import ClubCard from './ClubCard.vue'
import { ElMessage } from 'element-plus'

const clubs = ref([])

onMounted(() => {
  fetchAllClubs()
})

const fetchAllClubs = async () => {
  try {
    const data = await getAllClubs()
    clubs.value = data.map(club => ({
      ...club,
      tag: getClubTag(club)
    }))
    if (data.length === 0) {
      ElMessage.info('暂无社团数据')
    }
  } catch (error) {
    console.error('获取社团列表失败:', error)
    clubs.value = getFallbackClubs()
  }
}

const getClubTag = (club) => {
  if (club.favoriteCount > 50) return '热门'
  if (club.currentMembers > 80) return '火爆'
  const oneMonthAgo = new Date()
  oneMonthAgo.setMonth(oneMonthAgo.getMonth() - 1)
  if (new Date(club.createdAt) > oneMonthAgo) return '新晋'
  return ''
}

const getFallbackClubs = () => [
  {
    clubId: 1,
    clubName: '示例社团',
    description: '网络异常时显示的默认数据',
    currentMembers: 0,
    presidentName: '管理员',
    icon: 'https://picsum.photos/id/1011/800/600',
    category: 'HOBBY',
    favoriteCount: 0
  }
]
</script>

<style scoped>
.club-list {
  width: 100%;
  display: flex;
  justify-content: center; /* 水平居中 */
  padding-bottom: 20px;
}
.scroll-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 10px 0;
  justify-content: center; /* 卡片组居中 */
  width: 100%;
  max-width: 1200px; /* 可选，限制最大宽度 */
}
.club-item {
  flex: 0 0 300px;
}
</style>
