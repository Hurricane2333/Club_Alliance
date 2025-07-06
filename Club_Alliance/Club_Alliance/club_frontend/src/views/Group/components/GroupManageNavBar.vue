<template>
  <div class="group-nav">
    <el-menu :default-active="activeTab" mode="horizontal" @select="handleSelect">
      <el-menu-item index="members">成员审核</el-menu-item>
      <el-menu-item index="info">信息管理</el-menu-item>
      <el-menu-item index="events">活动管理</el-menu-item>
    </el-menu>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();

const activeTab = computed(() => {
  const segments = route.path.split('/');
  return segments[3]; // 匹配/group/manage/xxx结构
});

const handleSelect = (key) => {
  router.push(`/group/manage/${route.params.id}/${key}`);
};
</script>

<style scoped>
.group-nav {
  margin: 20px auto;
  background: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  max-width: 1200px;
}

.el-menu--horizontal {
  border-bottom: none;
  padding-left: 20px;
}
</style>
