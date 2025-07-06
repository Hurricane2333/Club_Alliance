<template>
  <div class="event-management-container">
    <div class="header-toolbar">
      <el-button type="primary" @click="handleCreateClick">
        <el-text style="font-weight: bold; font-size: 25px; color: white">+&nbsp;</el-text> 新建活动
      </el-button>
    </div>

    <el-table :data="events.data" style="width: 100%" border>
      <el-table-column label="活动名称">
        <template #default="scope">
          <div style="display: flex; align-items: center; gap: 8px">
            <span>{{ scope.row.title }}</span>
            <el-tag
              :type="{
                PENDING: 'info',
                ACTIVE: 'success',
                CONCLUDED: 'primary',
                CANCELLED: 'danger'
              }[scope.row.status]"
              size="small"
              effect="light"
            >
              {{ { PENDING: '待定', ACTIVE: '进行', CONCLUDED: '完成', CANCELLED: '取消' }[scope.row.status] }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="startTime" label="开始时间" />
      <el-table-column prop="endTime" label="结束时间" />
      <el-table-column prop="location" label="地点" />
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="editEvent(scope.row)">编辑</el-button>
          <el-button
            size="small"
            type="danger"
            @click="deleteEvent(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 创建/编辑对话框 -->
    <el-dialog v-model="showCreateDialog" :title="currentEvent.activityId ? '编辑活动' : '新建活动'">
      <el-form :model="currentEvent" label-width="80px">
        <el-form-item label="活动名称">
          <el-input v-model="currentEvent.title" />
        </el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker
            v-model="currentEvent.startTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="选择日期时间"
          />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker
            v-model="currentEvent.endTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="选择日期时间"
          />
        </el-form-item>
        <el-form-item label="活动地点">
          <el-input v-model="currentEvent.location" />
        </el-form-item>
        <el-form-item label="活动详情">
          <el-input
            v-model="currentEvent.content"
            type="textarea"
            :rows="4"
          />
        </el-form-item>
        <el-form-item label="活动状态">
          <el-radio-group
            v-model="currentEvent.status"
            :disabled="!currentEvent.activityId || currentEvent.status === 'PENDING'"
          >
            <el-radio-button
              v-if="!currentEvent.activityId || currentEvent.status === 'PENDING'"
              label="PENDING"
              type="info"
            >待定</el-radio-button>
            <el-radio-button
              v-for="label in ['ACTIVE', 'CONCLUDED', 'CANCELLED']"
              :key="label"
              :label="label"
              :type="{ ACTIVE: 'success', CONCLUDED: 'primary', CANCELLED: 'danger' }[label]"
            >{{ { ACTIVE: '进行', CONCLUDED: '完成', CANCELLED: '取消' }[label] }}</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="最大人数">
          <el-input v-model="currentEvent.maxParticipants" type="number" />
        </el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="showCreateDialog = false">取消</el-button>
        <el-button type="primary" @click="saveEvent">
          {{ currentEvent.activityId ? '保存修改' : '创建活动' }}
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, reactive} from 'vue'
import request from '@/utils/request.js'
import { ElMessage, ElMessageBox } from 'element-plus'
import {useRoute} from "vue-router";
import {useUserStore} from "@/stores/user.js";

const route = useRoute()

const events = reactive({
  data: []
})

const showCreateDialog = ref(false)
const currentEvent = reactive({
  activityId: null,
  clubId: route.params.id,
  userId: useUserStore().user.userId,
  title: '',
  content: '',
  startTime: '',
  endTime: '',
  location: '',
  status: 'PENDING',
  maxParticipants: '',
})

import dayjs from 'dayjs';

const loadEvents = async () => {
  try {
    const res = await request.get(`/group/activity/selectClubId/${route.params.id}`)

    // 自动更新过期活动状态
    await Promise.all(res.map(async (event) => {
      if (event.status === 'ACTIVE' && dayjs(event.endTime).isBefore(dayjs())) {
        await request.put('/group/activity/update', {
          ...event,
          status: 'CONCLUDED'
        });
      }
    }));

    events.data = res.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt))
  } catch (error) {
    ElMessage.error('加载活动列表失败')
  }
}

const editEvent = (event) => {
  Object.assign(currentEvent, event)
  showCreateDialog.value = true
}

const deleteEvent = async (event) => {
  try {
    await ElMessageBox.confirm('确定删除该活动？', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await request.delete(`/group/activity/delete/${event.activityId}`)
    ElMessage.success('删除成功')
    loadEvents()
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

const saveEvent = async () => {
  try {
    const url = currentEvent.activityId ? `/group/activity/update` : '/group/activity/insert'
    const method = currentEvent.activityId ? 'put' : 'post'

    await request[method](url, {
      ...currentEvent,
    })

    ElMessage.success(currentEvent.activityId ? '修改成功' : '创建成功')
    showCreateDialog.value = false
    resetForm()
    loadEvents()
  } catch (error) {
    ElMessage.error('保存失败')
  }
}

const resetForm = () => {
  currentEvent.activityId = null
  currentEvent.clubId = route.params.id
  currentEvent.userId = useUserStore().user.userId
  currentEvent.title = ''
  currentEvent.content = ''
  currentEvent.startTime = ''
  currentEvent.endTime = ''
  currentEvent.location = ''
  currentEvent.status = 'PENDING'
  currentEvent.maxParticipants = ''
}

const handleCreateClick = () => {
  resetForm();
  showCreateDialog.value = true;
}

loadEvents()
</script>

<style scoped>
.event-management-container {
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.header-toolbar {
  margin-bottom: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
