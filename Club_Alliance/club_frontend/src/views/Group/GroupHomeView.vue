<template>
  <div class="home-container">
    <NavBar />


  <div class="main-content">
    <div class="group-header">
      <img :src="club.icon" class="group-avatar" />
      <h1 class="group-name">{{ club.clubName }}</h1>
      <el-button
        v-if="club.presidentId === currentUserId"
        type="primary"
        class="manage-button"
        @click="$router.push(`/group/manage/${route.params.id}`)"
      >
        管理社团
      </el-button>
    </div>
    <GroupNavBar />

    <div class="group-container">
      <main class="main-content">
        <router-view />
      </main>

      <aside class="side-bar">
        <GroupSidebar />
        <div style="margin-top: 20px">
          <el-button @click="router.push(`/group/${club.clubId}/postCreate`)" type="primary" style="width: 100%">
            发表帖子
          </el-button>
        </div>
      </aside>
    </div>
  </div>
  </div>
</template>

<script setup>
import NavBar from "@/views/Home/component/NavBar.vue";
import GroupSidebar from "@/views/Group/components/GroupSidebar.vue";
import GroupNavBar from "@/views/Group/components/GroupNavBar.vue";
import { reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useUserStore } from "@/stores/user.js";
import request from "@/utils/request.js";
import router from "@/router/index.js";

const route = useRoute();
const currentUserId = useUserStore().user.userId;

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
})

request.get(`/group/selectId/${route.params.id}`).then(res => {
  Object.assign(club, res);
});
</script>

<style scoped>

.home-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: linear-gradient(rgba(222, 246, 250, 0.9), rgba(231, 247, 250, 0.9)),
              url('@/assets/homeback.jpg') center/cover no-repeat;
  position: relative;
}



.group-container {
  background: rgba(255, 255, 255, 0.85);
  backdrop-filter: blur(3px);
  display: flex;
  max-width: 1200px;
  margin: 0 auto 20px;
  gap: 20px;
}

.group-header {
  background:rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(2px);
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

.group-avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  margin-left: 0;
}

.group-name {
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
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.side-bar {
  width: 25%;
  min-width: 280px;
}
</style>
