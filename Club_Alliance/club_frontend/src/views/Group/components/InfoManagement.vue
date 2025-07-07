<template>
  <div class="info-management-container">
    <el-form :model="clubData" label-width="100px" ref="formRef">
      <el-form-item label="社团名称">
        <el-input v-model="clubData.clubName" />
      </el-form-item>

      <el-form-item label="社团简介">
        <el-input
          v-model="clubData.description"
          type="textarea"
          :rows="4"
          placeholder="请输入社团详细描述"
        />
      </el-form-item>

      <el-form-item label="社团分类">
        <el-radio-group v-model="clubData.category" class="category-group">
          <div class="category-grid">
            <el-radio-button
              v-for="item in categoryOptions"
              :key="item.value"
              :label="item.value"
              class="category-button"
            >
              {{ item.label }}
            </el-radio-button>
          </div>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="现任社长">
        <el-input :model-value="presidentName" :readonly="true"/>

      </el-form-item>

      <el-form-item label="入社要求">
        <el-input
          v-model="clubData.requirements"
          type="textarea"
          :rows="3"
          placeholder="请输入加入社团的要求"
        />
      </el-form-item>

      <el-form-item label="社团图标">
        <el-upload
          class="avatar-uploader"
          action="/picgo/api/1/upload"
          :show-file-list="false"
          :data="{ key: 'chv_SdO9i_06b936112c56b74d9839811834701d588141e9d40b08f5d0f77f80f00a46b7c44452d28b97f7d5465837ae3e83b2a78fff44de2e79acb5790f61616e2dd6d916' }"
          name="source"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="clubData.icon" :src="clubData.icon" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon" style="background-color: #eaeaea"><Plus /></el-icon>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">保存修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {inject} from 'vue'
import { getUserName } from '@/utils/userCache'

import {ElMessage, ElMessageBox} from 'element-plus'
import request from '@/utils/request.js'
import {useRoute} from "vue-router";
import { ref } from 'vue'
import {Plus} from "@element-plus/icons-vue";

const route = useRoute()

const clubData = inject('clubData', {})

const presidentName = ref('')

const loadClubInfo = async () => {
  try {
    const res = await request.get(`/group/selectId/${route.params.id}`)
    clubData.value = res
    presidentName.value = await getUserName(res.presidentId)
  } catch (error) {
    ElMessage.error('获取社团信息失败')
  }
}

const submitForm = async () => {
  try {
    await ElMessageBox.confirm('确定要保存修改吗？', '确认操作', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    await request.put('/group/update', {
      clubId: clubData.clubId,
      clubName: clubData.clubName,
      description: clubData.description,
      category: clubData.category,
      icon: clubData.icon,
      presidentId: clubData.presidentId,
      requirements: clubData.requirements,
      status: clubData.status,
    })

    await loadClubInfo()
    ElMessage.success('修改保存成功')
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error(`保存失败：${error.message || '未知错误'}`)
    }
  }
}

const handleAvatarSuccess = (response) => {
  clubData.icon = response.image.display_url
}

const categoryOptions = [
  { value: 'ACADEMIC', label: '学术' },
  { value: 'ARTS', label: '艺术' },
  { value: 'SPORTS', label: '体育' },
  { value: 'VOLUNTEER', label: '公益' },
  { value: 'HOBBY', label: '兴趣' },
  { value: 'CAREER', label: '职业' },
  { value: 'DEBATE', label: '辩论' },
  { value: 'MAKER', label: '创客' },
  { value: 'OTHER', label: '其他' }
];

loadClubInfo()
</script>

<style scoped>
.category-group {
  width: 100%;
}

.category-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  padding: 8px 0;
  width: 100%;
}

.el-radio-button :deep(.el-radio-button__inner) {
  border: 1px solid var(--el-border-color) !important;
  border-radius: 6px !important;
  margin: 0 !important;
}

.el-radio-button.is-active :deep(.el-radio-button__inner) {
  border-color: var(--el-color-primary) !important;
  box-shadow: none !important;
}

.el-radio-button :deep(.el-radio-button__inner) {
  width: 100%;
  padding: 12px;
}

.info-management-container {
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.avatar-uploader {
  :deep() .avatar {
    width: 120px;
    height: 120px;
    display: block;
  }
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
</style>
