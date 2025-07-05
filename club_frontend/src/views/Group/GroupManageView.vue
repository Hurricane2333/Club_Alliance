<template>
  <div>
    <NavBar />
  </div>

  <div class="club-header">
    <img :src="club.icon" class="club-avatar" />
    <h1 class="club-name">{{ club.clubName }}</h1>
    <el-button
      type="primary"
      class="manage-button"
      @click="$router.push(`/groupHome/${route.params.id}`)"
    >
      回到主页
    </el-button>
  </div>

  <GroupManageNavBar />

  <div class="group-container">
    <el-row :gutter="20">
      <el-col :span="18">
        <main class="main-content">
          <router-view />
        </main>
      </el-col>
      <el-col :span="6">
        <GroupManageSidebar />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import NavBar from "@/views/Home/component/NavBar.vue";
import GroupManageSidebar from "@/views/Group/components/GroupManageSidebar.vue";
import { reactive, provide } from 'vue';
import { useRoute } from 'vue-router';
import request from "@/utils/request.js";
import GroupManageNavBar from "@/views/Group/components/GroupManageNavBar.vue";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/user.js";

const route = useRoute();

const club = reactive({
  clubId: 0,
  clubName: '',
  description: '',
  category: '',
  icon: '',
  presidentId: 0,
  requirements: '',
  favoriteCount: 0,
  currentMembers: 0,
  createdAt: '0',
  pendingMembers: 0
})

provide('clubData', club);

request.get(`/group/selectId/${route.params.id}`).then(res => {
  Object.assign(club, res);
  if (club.presidentId !== useUserStore().user.userId) {
    ElMessage.error('只有社长可以访问管理页面');
    router.push('/');
  }
}).catch(() => {
  router.push('/');
});
</script>

<style scoped>
.group-container {
  max-width: 1200px;
  margin: 0 auto 20px;
  padding: 0 20px;
}

.club-header {
  background: #fff;
  padding: 20px 20px;
  display: flex;
  align-items: center;
  gap: 30px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  max-width: 1200px;
  margin: 20px auto;
  border-radius: 4px;
  box-sizing: border-box;
}

.club-avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  margin-left: 0;
}

.club-name {
  margin: 0;
  font-size: 28px;
  color: #333;
  letter-spacing: 1px;
}

.manage-button {
  margin-left: auto;
  margin-right: 20px;
  padding: 12px 24px;
  border-radius: 20px;
}

.main-content {
  flex: 1;
  min-width: 0;
}

.side-bar {
  width: 280px;
  flex-shrink: 0;
}
</style>
