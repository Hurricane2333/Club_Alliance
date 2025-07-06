<template>
  <div class="activity-content-card bg-white rounded-xl shadow-card p-8 max-w-3xl mx-auto">
    <!-- 活动头部 -->
    <div class="flex items-center mb-6" style="display: flex">
      <el-avatar :src="activity.clubIcon" size="large" class="mr-6"/>
      <div style="margin-left: 20px">
        <div class="club-name text-2xl font-bold text-[#0e0e0e] mb-2">{{ activity.clubName }}</div>
        <div class="activity-time text-base text-gray-600 font-medium">
          <span>活动时间：</span>
          <span>{{ formatTime(activity.startTime) }}</span>
          <span v-if="activity.endTime"> ~ {{ formatTime(activity.endTime) }}</span>
        </div>
      </div>
    </div>
    <!-- 活动标题 -->
    <h2 class="activity-title font-bold text-[28px] text-gray-800 mb-6"
        style="letter-spacing:0.5px;">{{ activity.title }}</h2>
    <!-- 活动内容 -->
    <div class="activity-desc text-lg text-black mb-6"
         style="white-space: pre-wrap; word-break: break-all; overflow-wrap: break-word;">
      {{ activity.content }}
    </div>
    <!-- 活动地点 -->
    <div class="flex items-center text-base text-gray-600 space-y-6 mb-6">
      <el-icon class="mr-2">
        <Location/>
      </el-icon>
      <span>{{ activity.location || '待获取' }}</span>
    </div>
    <div class="w-[800px] h-[800px] mt-6 rounded-lg overflow-hidden border border-gray-100">
      <MapView :location="activity.location"/>
    </div>
    <!-- 活动发布时间 -->
    <div class="text-sm text-gray-400 mb-2 " style="margin: 15px 0">
      发布于 {{ activity.createdAt ? formatLocalTime(activity.createdAt) : '' }}
    </div>
    <!-- 参加活动按钮 -->
    <div class="flex justify-end mt-6">
      <el-button type="primary" size="large" class="!px-8 !h-12 !text-base"
                 @click="dialogVisible = true">
        参加活动
      </el-button>
      <el-button
        v-if="userStore.user?.userId === activity.userId"
        type="warning"
        size="large"
        @click="showReviewDialog = true"
      >
        审核参与申请
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

    <!-- 审核对话框 -->
    <el-dialog v-model="showReviewDialog" title="参与申请审核" width="60%">
      <el-table :data="participants" border>
        <el-table-column prop="userId" label="用户ID" width="95"/>
        <el-table-column label="用户姓名" width="150">
          <template #default="{row}">
            <template v-if="row.userName">
              {{ row.userName }}
            </template>
            <span v-else>加载中...</span>
          </template>
        </el-table-column>
        <el-table-column label="申请时间" width="200">
          <template #default="{row}">
            {{ formatLocalTime(row.applyTime) }}
          </template>
        </el-table-column>
        <el-table-column label="审核时间" width="200">
          <template #default="{row}">
            {{ formatLocalTime(row.reviewTime) }}
          </template>
        </el-table-column>
        <el-table-column label="申请状态" width="100">
          <template #default="{row}">
            <el-tag :type="statusTagType(row.status)">
              {{ statusText(row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="140">
          <template #default="{row}">
            <el-button
              size="small"
              type="success"
              @click="updateStatus(row.participationId, 'APPROVED')"
            >
              通过
            </el-button>
            <el-button
              size="small"
              type="danger"
              @click="updateStatus(row.participationId, 'REJECTED')"
            >
              拒绝
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, onMounted, watch} from 'vue'
import {useRoute} from 'vue-router'
import {Location} from '@element-plus/icons-vue'
import request from '@/utils/request'
import {useUserStore} from '@/stores/user'
import MapView from './MapView.vue'
import {ElMessage} from 'element-plus'
import dayjs from 'dayjs'
import utc from 'dayjs/plugin/utc'
import timezone from 'dayjs/plugin/timezone'
import {getUserName} from "@/utils/userCache.js";

dayjs.extend(utc)
dayjs.extend(timezone)

const route = useRoute()
const userStore = useUserStore()
const activity = ref({
  activityId: 0,
  userId: 0,
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
const showReviewDialog = ref(false)
const participants = ref([])

// 加载用户信息
async function loadUserInfo(participant) {
  try {
    const user = await getUserName(participant.userId)
    participant.userName = user
  } catch {
    participant.userName = {id: participant.userId, name: '未知用户'}
  }
}

function formatTime(time) {
  return time ? dayjs(time).format('YYYY-MM-DD HH:mm') : ''
}

function formatLocalTime(time) {
  return time ? dayjs(time).utc(0).format('YYYY-MM-DD HH:mm') : ''
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

// 获取参与申请列表
async function loadParticipants() {
  try {
    const res = await request.get(`/group/activity/participant/selectActivityId/${activity.value.activityId}`)
    participants.value = res
    participants.value.forEach(p => {
      loadUserInfo(p)
    })
  } catch (error) {
    ElMessage.error('获取参与列表失败')
  }
}

// 更新审核状态
async function updateStatus(participationId, status) {
  try {
    await request.put('/group/activity/participant/update', {
      participationId,
      status
    })
    ElMessage.success('操作成功')
    loadParticipants()
  } catch (error) {
    ElMessage.error('操作失败')
  }
}

// 状态显示转换
const statusText = (status) => ({
  PENDING: '待审核',
  APPROVED: '已通过',
  REJECTED: '已拒绝'
})[status]

const statusTagType = (status) => ({
  PENDING: 'warning',
  APPROVED: 'success',
  REJECTED: 'danger'
})[status]

// 打开对话框时加载数据
watch(showReviewDialog, (val) => val && loadParticipants())
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
