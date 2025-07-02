<template>
  <el-card class="user-profile-card" shadow="never">
    <template #header>
      <div class="card-header">
        <el-icon><User /></el-icon>
        <span>个人资料</span>
      </div>
    </template>
    <el-descriptions :column="1" border>
      <el-descriptions-item>
        <template #label>
          <el-icon><Postcard /></el-icon> 学号
        </template>
        {{ userInfo.stu_id }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <el-icon><User /></el-icon> 姓名
        </template>
        {{ userInfo.stu_name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <el-icon><Message /></el-icon> 邮箱
        </template>
        {{ userInfo.email }}
      </el-descriptions-item>
    </el-descriptions>
  </el-card>
</template>

<script setup>
import { defineProps } from 'vue';
import { User, Postcard, Message } from '@element-plus/icons-vue';

defineProps({
  userInfo: Object
});
</script>

<style scoped>
.user-profile-card {
  margin-bottom: 24px;
}

.card-header {
  display: flex;
  align-items: center;
  font-weight: bold;
}

.card-header .el-icon {
  margin-right: 8px;
}

.el-descriptions-item__label .el-icon {
  vertical-align: middle;
  margin-right: 4px;
}
</style>
