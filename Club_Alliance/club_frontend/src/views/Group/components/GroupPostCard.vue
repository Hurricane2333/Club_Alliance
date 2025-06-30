<template>
  <el-card shadow="hover" style="width: max(300px,24%); margin: 0 2% 0 2%">
    <template #header>
      <div style="height: 30px">
        <el-text truncated style="font-size: 20px; font-weight: 400">
          {{ post.title }}
        </el-text>
      </div>
    </template>

    <div style="height: 100px">
      <el-text line-clamp="5">
        {{ post.content }}
      </el-text>
    </div>

    <template #footer>
      <div style="height: 40px">
        <el-row :span="24">
          <el-text>
            <el-icon>
              <Edit/>
            </el-icon>
            {{ post.userId }}
          </el-text>
        </el-row>
        <el-row>
          <el-text>
            <el-icon>
              <Calendar />
            </el-icon>
            {{ post.createdAt }}
          </el-text>
        </el-row>
      </div>
    </template>
  </el-card>
</template>

<script setup>

import {Calendar, Edit} from "@element-plus/icons-vue";

defineProps({
  post: {
    type: Object,
    required: true
  }
});

</script>

<style scoped>

.el-card ::v-deep .el-card__header {
  padding: 10px 20px;
}

.el-card ::v-deep .el-card__footer {
  padding: 10px 20px;
}

</style>
