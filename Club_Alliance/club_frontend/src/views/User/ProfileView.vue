<template>
  <div class="app-container">
    <NavBar />

    <div class="main-container">
      <el-alert
        v-if="error"
        :title="error"
        type="error"
        :closable="false"
        class="error-alert"
      />
      <div v-if="isLoading" class="loading-spinner">
        <el-spinner size="large"></el-spinner>
      </div>
      <template v-else-if="!error">
        <UserBanner
          v-if="userInfo.stuId"
          :userInfo="userInfo"
          :isSelf="isSelf"
          @edit-profile="openEditProfile"
        />
        <UserProfile v-if="userInfo.stuId" :userInfo="userInfo" />
      </template>

      <el-container class="content-container">
        <el-aside width="300px" class="sidebar">
          <JoinedClubs :joinedClubs="joinedClubs" />
          <el-card class="box-card" style="margin-top: 20px;" v-if="favoriteClubs.length > 0">
            <template #header>
              <div class="clearfix">
                <span>喜欢的社团</span>
              </div>
            </template>
            <div v-for="club in favoriteClubs" :key="club.clubId">
              <p><strong>社团名称:</strong> {{ club.clubName }}</p>
              <p><strong>社团介绍:</strong> {{ club.description }}</p>
              <el-divider />
            </div>
          </el-card>
        </el-aside>

        <el-main class="main-content">
          <div class="activity-list">
           <h3>参与的活动</h3>
           <div v-if="joinedActivitiesLoading" class="loading-spinner">
             <el-spinner size="large"></el-spinner>
           </div>
            <div v-else-if="joinedActivities.length > 0">
              <ActivityItem
                v-for="activity in joinedActivities"
                :key="activity.id"
                :activity="activity"
              />
            </div>
            <el-empty v-else description="暂无参与的活动" />
          </div>
        </el-main>
      </el-container>
    </div>

    <!-- 编辑资料对话框 -->
    <el-dialog
      v-model="isEditModalOpen"
      title="编辑个人资料"
      width="30%"
      :close-on-click-modal="false"
    >
      <el-form :model="editForm" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="editForm.stuName" />
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="editForm.stuId" disabled />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editForm.email" />
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="editForm.password" type="password" show-password />
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="isEditModalOpen = false">取消</el-button>
          <el-button type="primary" @click="submitEditProfile">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import { ArrowDown } from '@element-plus/icons-vue';
import NavBar from '../Home/component/NavBar.vue';
import UserBanner from './component/UserBanner.vue';
import UserProfile from './component/UserProfile.vue';
import JoinedClubs from './component/JoinedClubs.vue';
import RecommendedClubs from './component/RecommendedClubs.vue';
import { useUserStore } from '@/stores/user';
import request from '@/utils/request';
import ActivityItem from './component/ActivityItem.vue';
import { ElMessage } from 'element-plus';
const navbarShadow = ref('shadow-sm');
const mobileMenuOpen = ref(false);
const userStore = useUserStore();

const isSelf = ref(true); // 当前用户是否是自己
const isEditModalOpen = ref(false);
const error = ref(null);
const isLoading = ref(true);


const joinedActivities = ref([]);
const joinedActivitiesLoading = ref(true);

const fetchJoinedActivities = async () => {
  try {
    joinedActivitiesLoading.value = true;
    const userId = userStore.user?.userId;
    const response = await request.get('/api/activity_participant/list', {
      params: { userId }
    });

    if (response.code === 'success') {
      joinedActivities.value = response.data;
    } else {
      console.error('获取参与活动失败:', response.msg);
      ElMessage.error(response.msg || '获取参与活动失败');
    }
  } catch (err) {
    console.error('API请求错误:', err);
    ElMessage.error('加载参与活动失败，请稍后重试');
  } finally {
    joinedActivitiesLoading.value = false;
  }
};


// 导航栏滚动效果
onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

const handleScroll = () => {
  navbarShadow.value = window.scrollY > 10 ? 'shadow-md' : 'shadow-sm';
};

// 移动端菜单切换
const toggleMobileMenu = () => {
  mobileMenuOpen.value = !mobileMenuOpen.value;
};



// 编辑资料相关
const openEditProfile = () => {
  isEditModalOpen.value = true;
};

const editForm = ref({
  stuName: '',
  stuId: '',
  email: '',
  password: ''
});

const submitEditProfile = () => {
  const userId = userStore.user?.userId;
  axios.put(`/api/user/${userId}`, {
    stuName: editForm.value.stuName,
    email: editForm.value.email,
    password: editForm.value.password
  })
    .then(response => {
      if (response.data.code === 'success') {
        userInfo.value = {
          ...userInfo.value,
          stuName: editForm.value.stuName,
          email: editForm.value.email
        };
        isEditModalOpen.value = false;
      } else {
        error.value = response.data.msg || 'Failed to update user data.';
        console.error('Failed to update user data:', response.data.msg);
      }
    })
    .catch(err => {
      console.error('API Error:', err);
      error.value = '更新数据时出错，请稍后重试。';
    });
};

const userInfo = ref({});
const route = useRoute();

onMounted(() => {
  const userId = userStore.user?.userId; // Fallback to 2 if no id
  isLoading.value = true;
  const userRequest = axios.get(`/api/user/${userId}`);
  const favoriteClubsRequest = axios.get(`/api/favorite/list?userId=${userId}`);

  Promise.all([userRequest, favoriteClubsRequest]).then(([userResponse, favoriteClubsResponse]) => {
    if (userResponse.data.code === 'success') {
      const userData = userResponse.data.data;
      if (userData) {
        console.log(userData);
        userInfo.value = userData;
        editForm.value = {
          stuName: userInfo.value.stuName,
          stuId: userInfo.value.stuId,
          email: userInfo.value.email
        };
      } else {
        console.error('User data is null or undefined despite success code.');
      }
    } else {
      error.value = userResponse.data.msg || 'Failed to fetch user data.';
      console.error('Failed to fetch user data:', userResponse.data.msg);
    }

    if (favoriteClubsResponse.data.code === 'success') {
      favoriteClubs.value = favoriteClubsResponse.data.data;
    }

  }).catch(err => {
    console.error('API Error:', err);
    error.value = '加载数据时出错，请稍后重试。';
  }).finally(() => {
    isLoading.value = false;
  });
});

const joinedClubs = ref([]);
onMounted(() => {
  const userId = userStore.user?.userId; // 或用当前登录用户ID
  axios.get(`/club_member/joined?userId=${userId}`)
    .then(res => {
      console.log('joined clubs api result:', res.data);
      if (Array.isArray(res.data)) {
        joinedClubs.value = res.data;
      } else if (res.data && res.data.code === 'success' && Array.isArray(res.data.data)) {
        joinedClubs.value = res.data.data;
      }
    })
    .catch(err => {
      console.error('加载已加入社团失败', err);
    });
});


onMounted(() => {
  fetchJoinedActivities();
  // 其他初始化逻辑...
});

const favoriteClubs = ref([]);


const recommendedClubs = ref([
  {
    id: 1,
    name: '机器人社',
    category: '计算机学院',
    logo: 'https://picsum.photos/id/1035/60/60'
  },
  {
    id: 2,
    name: '编程社',
    category: '信息学院',
    logo: 'https://picsum.photos/id/1045/60/60'
  },
  {
    id: 3,
    name: '电竞社',
    category: '全校性社团',
    logo: 'https://picsum.photos/id/1055/60/60'
  }
]);




</script>

<style scoped>
.app-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-container {
  flex: 1;
  margin-top: 64px;
  display: flex;
  flex-direction: column;
}

.content-container {
  max-width: 1200px;
  margin: 24px auto;
  padding: 0 24px;
  display: flex;
  gap: 24px;
}

.sidebar {
  width: 300px !important;
}

.main-content {
  flex: 1;
  padding: 0 !important;
}

.activity-list {
  margin-bottom: 24px;
}

.load-more {
  text-align: center;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
}

@media (max-width: 768px) {
  .content-container {
    flex-direction: column;
  }

  .sidebar {
    width: 100% !important;
  }
}
</style>
