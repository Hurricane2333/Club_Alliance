<template>
  <div class="club-list">
    <div class="scroll-container">
      <ClubCard
        v-for="club in clubs"
        :key="club.clubId"
        :club="club"
        class="club-item"
        @view-detail="handleViewDetail"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getHomeClubs } from '@/api/home'
import ClubCard from './ClubCard.vue'
import { ElMessage } from 'element-plus';


const clubs = ref([])

onMounted(() => {
  fetchHomeClubs()
})

const fetchHomeClubs = async () => {
  try {
    const data= await getHomeClubs()
    clubs.value = data.map(club => ({
      ...club,
      tag: getClubTag(club)
    }));
    if (data.length === 0) {
      ElMessage.info('暂无推荐社团'); // 空数据提示
    }
  } catch (error) {
    console.error('获取社团列表失败:', error)
    // 使用后备数据
    clubs.value = getFallbackClubs()
  }
}

const getClubTag = (club) => {
  if (club.favoriteCount > 30) return '热门'
  if (club.currentMembers > 80) return '火爆'
  const oneMonthAgo = new Date()
  oneMonthAgo.setMonth(oneMonthAgo.getMonth() - 1)
  if (new Date(club.createdAt) > oneMonthAgo) return '新晋'
  return ''
}

const handleViewDetail = (clubId) => {
  console.log('查看社团详情:', clubId)
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
.club-item {
  flex: 0 0 300px;
}
</style>
