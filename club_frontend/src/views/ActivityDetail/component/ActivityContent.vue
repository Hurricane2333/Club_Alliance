<template>
  <div class="activity-content-card bg-white rounded-xl shadow-card p-8 max-w-3xl mx-auto">
    <!-- 活动头部 -->
    <div class="flex items-center mb-6">
      <el-avatar :src="activity.clubIcon" size="large" class="mr-6" />
      <div>
        <div class="club-name text-2xl font-bold text-[#165DFF] mb-1">{{ activity.clubName }}</div>
        <div class="activity-time text-base text-gray-600 font-medium">
          <span>活动时间：</span>
          <span>{{ formatTime(activity.startTime) }}</span>
          <span v-if="activity.endTime"> ~ {{ formatTime(activity.endTime) }}</span>
        </div>
      </div>
    </div>
    <!-- 活动标题 -->
    <h2 class="activity-title font-bold text-[28px] text-gray-800 mb-6" style="letter-spacing:0.5px;">{{ activity.title }}</h2>
    <!-- 活动内容 -->
    <div class="activity-desc text-lg text-black mb-6" style="white-space: pre-wrap;">
      {{ activity.content }}
    </div>
    <!-- 活动地点 -->
    <div class="flex items-center text-base text-gray-600 space-y-6 mb-6">
      <el-icon class="mr-2"><Location /></el-icon>
      <span>{{ activity.location || '待获取' }}</span>
    </div>
    <div class="w-[800px] h-[800px] mt-6 rounded-lg overflow-hidden border border-gray-100">
      <MapView :location="activity.location" />
    </div>
    <!-- 活动发布时间 -->
    <div class="text-sm text-gray-400 mb-2">
      发布于 {{ activity.createdAt ? formatTime(activity.createdAt) : '' }}
    </div>
    <!-- 参加活动按钮 -->
    <div class="flex justify-end mt-6">
      <el-button type="primary" size="large" class="!px-8 !h-12 !text-base" @click="dialogVisible = true">
        参加活动
      </el-button>
    </div>
    <!-- 弹窗 -->
    <el-dialog v-model="dialogVisible" title="参加活动" width="400px">
      <div style="font-size: 18px; text-align: center; margin: 30px 0;">
        是否申请参加活动
      </div>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmJoin">确认</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRoute} from 'vue-router'
import {Location} from '@element-plus/icons-vue'
import request from '@/utils/request'
import {useUserStore} from '@/stores/user'
import MapView from './MapView.vue'
import {ElMessage} from 'element-plus'


const route = useRoute()
const userStore = useUserStore()
const activity = ref({
  activityId: 0,
  title: '',
  content: '',
  clubName: '',
  clubIcon: '',
  location: '',
  startTime: '',
  endTime: '',
  createdAt: ''
})

const dialogVisible = ref(false)

function formatTime(time) {
  return time ? time.replace('T', ' ').slice(0, 16) : ''
}

async function confirmJoin() {
  const userId = userStore.user?.userId
  if (!userId) {
    ElMessage.error('请先登录')
    dialogVisible.value = false
    return
  }
  const activityId = activity.value.activityId
  try {
    const res = await request.post('/api/activity_participant/join', null, {
      params: {activityId, userId}
    })
    if (res.code === 'success') {
      ElMessage.success('已成功申请参加活动')
    } else {
      ElMessage.warning(res.msg || '报名失败')
    }
  } catch {
    ElMessage.error('网络错误，报名失败')
  }
  dialogVisible.value = false
}


onMounted(async () => {
  const id = route.params.id
  const res = await request.get(`/api/activitylists/detail/${id}`)
  if (res && res.code === 'success') {
    activity.value = res.data
  }
})
</script>

<style scoped>
.activity-content-card {
  font-family: 'Inter', '微软雅黑', Arial, sans-serif;
}

.club-name {
  font-size: 24px;
  font-weight: 600;
  color: #0e0e0e;
  margin-bottom: 4px;
}

.activity-title {
  font-size: 28px;
  font-weight: bold;
  color: #222;
  margin-bottom: 16px;
}

.activity-desc {
  font-size: 18px;
  color: #222;
  margin-bottom: 24px;
}
</style>
