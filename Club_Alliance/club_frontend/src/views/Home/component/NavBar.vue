<template>
  <el-menu
    mode="horizontal"
    :ellipsis="false"
    class="custom-navbar"
    router
  >
    <div class="flex items-center px-4">
      <img
        src="@/assets/title.jpg"
        alt="社团联盟"
        class="h-10"
      />
    </div>

    <div class="flex-grow"></div>

    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/clublists">社团列表</el-menu-item>
    <el-menu-item index="/activitylists">活动信息</el-menu-item>
    <el-menu-item :index="'/user/' + userId" v-if="isLoggedIn">个人主页</el-menu-item>



     <div class="flex items-center px-4">
      <el-input
        placeholder="搜索社团、活动..."
        class="w-64"
        :prefix-icon="Search"
      />
    <div class="nav-auth-btns">
      <div v-if="!isLoggedIn">
        <el-button type="primary" plain @click="goToLogin">登录</el-button>
        <el-button type="primary" class="ml-2">注册</el-button>
      </div>
      <div v-else>
        <el-button type="danger" plain @click="logout">注销</el-button>
      </div>
    </div>

    </div>
  </el-menu>
</template>

<script setup>
import { User, Search } from '@element-plus/icons-vue';
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user';

const router = useRouter();
const userStore = useUserStore();

const isLoggedIn = computed(() => !!userStore.token);
const userId = computed(() => userStore.user?.userId);

const goToLogin = () => {
  router.push('/login');
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
  justify-content: center;
  width: 100%;
}

.custom-navbar {
  height: 80px;
  line-height: 80px;
  max-width: 1200px; /* 限制导航栏最大宽度 */
  width: 100%;
  margin: 0 auto; /* 水平居中 */
}

.custom-navbar .el-menu-item {
  font-size: 18px;
  padding: 0 25px;
  font-weight: 500;
}
</style>
