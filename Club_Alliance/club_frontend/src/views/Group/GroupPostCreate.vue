<template>
  <div>
    <NavBar />
  </div>
  <div class="info-management-container">
    <el-form :model="postData" :rules="rules" label-width="100px" ref="formRef">
      <el-form-item label="标题" prop="title">
        <el-input v-model="postData.title" />
      </el-form-item>

      <el-form-item label="内容" prop="content">
        <el-input
          v-model="postData.content"
          type="textarea"
          :rows="20"
          placeholder="请输入内容"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">发布</el-button>
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
import {useRoute, useRouter} from "vue-router";

const router = useRouter()
const route = useRoute()
const formRef = ref()

const rules = {
  title: [{ required: true, message: '请输入文章标题', trigger: 'blur' }],
  content: [{ required: true, message: '请输入文章内容', trigger: 'blur' }]
}

const postData = reactive({
  clubId: route.params.id,
  userId: useUserStore().user.userId,
  title: '',
  content: '',
})

const submitForm = async () => {
  try {
    await formRef.value.validate()
  } catch (error) {
    throw { type: 'validation', message: '请填写必要信息' }
  }
  try {
    await ElMessageBox.confirm('确认发布吗？', '确认操作', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    await request.post('/group/post/insert', {
      clubId: postData.clubId,
      userId: postData.userId,
      title: postData.title,
      content: postData.content,
    })

    ElMessage.success('文章发布成功')

    await router.push(`/groupHome/${postData.clubId}/posts`)

  } catch (error) {
    if (error !== 'cancel') {
      const message = error.type === 'validation' ? error.message : `${error.message || '未知错误'}`
      ElMessage.error(`发布失败：${message}`)
    }
  }
}

</script>

<style scoped>
.category-group {
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

</style>
