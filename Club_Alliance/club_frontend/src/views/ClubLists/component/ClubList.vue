<template>
  <div>
    <div class="tabs-center">
      <el-tabs v-model="selectedCategory" @tab-click="filterClubs">
        <el-tab-pane label="全部" name="ALL"/>
        <el-tab-pane
          v-for="(label, key) in categories"
          :key="key"
          :label="label"
          :name="key"
        />
      </el-tabs>
      <div class="search-box">
        <el-input
          v-model="searchQuery"
          placeholder="输入社团名称搜索"
          clearable
          @input="filterClubs"
          class="w-64"
        >
          <template #prefix>
            <el-icon>
              <Search/>
            </el-icon>
          </template>
        </el-input>
      </div>
    </div>
    <div class="club-list">
      <div class="scroll-container">
        <ClubCard
          v-for="club in filteredClubs"
          :key="club.clubId"
          :club="club"
          class="club-item"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted, computed} from 'vue'
import {getAllClubs} from '@/api/clublists'
import ClubCard from './ClubCard.vue'
import {ElMessage} from 'element-plus'
import {Search} from '@element-plus/icons-vue'

const categories = {
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

const clubs = ref([])
const selectedCategory = ref('ALL')
const searchQuery = ref('')

const filteredClubs = computed(() => {
  return clubs.value.filter(club => {
    const matchCategory = selectedCategory.value === 'ALL' || club.category === selectedCategory.value
    const matchSearch = club.clubName.includes(searchQuery.value)
    return matchCategory && matchSearch
  })
})

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

const filterClubs = () => {
  // 这里不需要做任何事，切换tab时filteredClubs会自动更新
}

const getClubTag = (club) => {
  if (club.favoriteCount > 30) return '热门'
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

.tabs-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.tabs-center :deep(.el-tabs__item) {
  font-size: 18px;
}

.search-box {
  margin-top: 10px;
}
</style>
