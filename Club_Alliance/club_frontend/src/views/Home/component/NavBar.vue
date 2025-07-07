<template>
  <div class="navbar-wrapper">
    <div class="flex items-center px-4">
      <img
        src="@/assets/title.jpg"
        alt="社团联盟"
        class="h-10"
      />
    </div>
    <el-menu
      mode="horizontal"
      :ellipsis="false"
      class="custom-navbar"
      router
    >
      <el-menu-item index="/">首页</el-menu-item>
      <el-menu-item index="/clublists">社团列表</el-menu-item>
      <el-menu-item index="/activitylists">活动信息</el-menu-item>
      <el-menu-item :index="'/user/' + userId" v-if="isLoggedIn">个人主页</el-menu-item>
      <el-menu-item index="/group/create" v-if="isLoggedIn">社团创建</el-menu-item>
      <el-menu-item index="/dashboard" v-if="isLoggedIn && useUserStore().user.isAdmin">网站管理</el-menu-item>
    </el-menu>
    <div class="nav-auth-btns flex items-center px-4" style="margin-right: 20px">
      <div v-if="!isLoggedIn">
        <el-button type="primary" plain @click="goToLogin">登录</el-button>
        <el-button type="primary" class="ml-2" @click="goToRegister">注册</el-button>
      </div>
      <div v-else class="flex items-center gap-3" style="display: flex">
        <el-avatar :size="40" :src="userStore.user.avatar" class="hover:ring-2 ring-primary transition-all">
          <span v-if="!userStore.user.avatar">{{ userStore.user.stuName?.slice(0,1) }}</span>
        </el-avatar>
        <div style="margin: 0 15px 0 15px; display: flex; flex-direction: column;  align-items: center">
          <span class="text-gray-600 font-medium text-base" style="font-size: 12px">{{ userStore.user.stuName }}</span>
          <el-tag size="small" round :type="userStore.user.isAdmin ? 'success' : 'info'" style="margin-top: 5px">
            {{ userStore.user.isAdmin ? '管理员' : '学生' }}
          </el-tag>
        </div>
        <div style="display: flex; align-items: center">
          <el-button size="small" type="danger" plain @click="logout">注销</el-button>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import {User, Search} from '@element-plus/icons-vue';
import {computed} from 'vue';
import {useRouter} from 'vue-router';
import {useUserStore} from '@/stores/user';

const router = useRouter();
const userStore = useUserStore();

const isLoggedIn = computed(() => !!userStore.token);
const userId = computed(() => userStore.user?.userId);

const goToLogin = () => {
  router.push('/login');
};

const goToRegister = () => {
  router.push('/register');
};

const logout = () => {
  userStore.logout();
  router.push('/login').then(() => {
    window.location.reload();
  });
};
</script>

<style scoped>
.navbar-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 70px;
  background: #f7f7f8;
}

.custom-navbar {
  flex: 1;
  display: flex;
  justify-content: center;
  background: transparent;
  border-bottom: none;
  box-shadow: none;
  margin-right: 80px;
}

.custom-navbar .el-menu-item {
  font-size: 18px;
  padding: 0 25px;
  font-weight: 500;
  margin: 0 20px;
}
</style>
