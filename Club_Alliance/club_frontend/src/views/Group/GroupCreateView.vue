<template>
  <div>
    <NavBar />
  </div>
  <div class="info-management-container">
    <el-form :model="clubData" :rules="rules" label-width="100px" ref="formRef">
      <el-form-item label="社团名称" prop="clubName">
        <el-input v-model="clubData.clubName" />
      </el-form-item>

      <el-form-item label="社团简介" prop="description">
        <el-input
          v-model="clubData.description"
          type="textarea"
          :rows="4"
          placeholder="请输入社团详细描述"
        />
      </el-form-item>

      <el-form-item label="社团分类" prop="category">
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
          action="/api/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="clubData.icon" :src="clubData.icon" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon" style="background-color: #eaeaea"><Plus /></el-icon>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">申请创建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {reactive, ref} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import request from '@/utils/request.js'
import NavBar from "@/views/Home/component/NavBar.vue";
import {useUserStore} from "@/stores/user.js";
import {Plus} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";

const router = useRouter()
const formRef = ref()

const rules = {
  clubName: [{ required: true, message: '请输入社团名称', trigger: 'blur' }],
  category: [{ required: true, message: '请选择社团分类', trigger: 'change' }],
  description: [{ required: true, message: '请输入社团简介', trigger: 'blur' }]
}

const clubData = reactive({
  clubName: '',
  description: '',
  category: '',
  icon: null,
  presidentId: useUserStore().user.userId,
  requirements: '',
  currentMembers: 1,
})

const submitForm = async () => {
  try {
    await formRef.value.validate()
  } catch (error) {
    throw { type: 'validation', message: '请填写必要信息' }
  }
  try {
  await ElMessageBox.confirm('确定要申请创建社团吗？', '确认操作', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })

    await request.post('/group/insert', {
      clubName: clubData.clubName,
      description: clubData.description,
      category: clubData.category,
      icon: clubData.icon,
      presidentId: useUserStore().user.userId,
      requirements: clubData.requirements,
      currentMembers: 1,
    })

    ElMessage.success('申请创建成功')

    await router.push('/')

  } catch (error) {
    if (error !== 'cancel') {
      const message = error.type === 'validation' ? error.message : `${error.message || '未知错误'}`
      ElMessage.error(`申请失败：${message}`)
    }
  }
}

const handleAvatarSuccess = (response) => {
  clubData.icon = response.url
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
  max-width: 1200px;
  margin: 50px auto;
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
