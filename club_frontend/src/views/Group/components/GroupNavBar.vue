<template>
  <div class="group-nav">
    <el-menu :default-active="activeTab" mode="horizontal" @select="handleSelect">
      <el-menu-item index="posts">帖子</el-menu-item>
      <el-menu-item index="activities">活动</el-menu-item>
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
  return segments[segments.length - 1];
});

const handleSelect = (key) => {
  activeTab.value = key;
  router.push(`/groupHome/${route.params.id}/${key}`);
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
