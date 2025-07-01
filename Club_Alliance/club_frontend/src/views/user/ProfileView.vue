<template>
  <div class="app-container">
    <NavBar />
    
    <div class="main-container">
      <UserBanner 
        v-if="userInfo.stu_id"
        :userInfo="userInfo" 
        :isSelf="isSelf"
        @edit-profile="openEditProfile"
      />
      <UserProfile v-if="userInfo.stu_id" :userInfo="userInfo" />

      <el-container class="content-container">
        <el-aside width="300px" class="sidebar">
          <JoinedClubs :joinedClubs="joinedClubs" />
          <el-card class="box-card" style="margin-top: 20px;" v-if="favoriteClubs.length > 0">
            <template #header>
              <div class="clearfix">
                <span>喜欢的社团</span>
              </div>
            </template>
            <div v-for="club in favoriteClubs" :key="club.club_id">
              <p><strong>社团名称:</strong> {{ club.club_name }}</p>
              <p><strong>社团介绍:</strong> {{ club.description }}</p>
              <el-divider />
            </div>
          </el-card>
        </el-aside>
        
        <el-main class="main-content">
          <ActivityTabs 
            :activeTab="activeTab" 
            @tab-change="setActiveTab" 
          />
          
          <div class="activity-list">
            <ActivityItem 
              v-for="activity in filteredActivities" 
              :key="activity.id" 
              :activity="activity" 
            />
          </div>
          
          <div class="load-more">
            <el-button type="primary" plain round>
              加载更多 <el-icon class="el-icon--right"><ArrowDown /></el-icon>
            </el-button>
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
          <el-input v-model="editForm.stu_name" />
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="editForm.stu_id" disabled />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editForm.email" />
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
import UserBanner from './components/UserBanner.vue';
import UserProfile from './components/UserProfile.vue';
import JoinedClubs from './components/JoinedClubs.vue';
import RecommendedClubs from './components/RecommendedClubs.vue';
import ActivityTabs from './components/ActivityTabs.vue';
import ActivityItem from './components/ActivityItem.vue';

const navbarShadow = ref('shadow-sm');
const mobileMenuOpen = ref(false);
const activeTab = ref(0);
const isSelf = ref(true); // 当前用户是否是自己
const isEditModalOpen = ref(false);

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

// 标签页切换
const setActiveTab = (index) => {
  activeTab.value = index;
};

// 编辑资料相关
const openEditProfile = () => {
  isEditModalOpen.value = true;
};

const editForm = ref({
  stu_name: '',
  stu_id: '',
  email: ''
});

const submitEditProfile = () => {
  // 更新用户信息
  userInfo.value = {
    ...userInfo.value,
    stu_name: editForm.value.stu_name,
    email: editForm.value.email
  };
  
  isEditModalOpen.value = false;
};

const userInfo = ref({});
const route = useRoute();

onMounted(() => {
  const userId = route.params.id || 2; // Fallback to 2 if no id
  axios.get(`/api/user/${userId}`).then(response => {
    console.log('API Response:', response.data);
    const userData = response.data.data;
    if (userData) {
      userInfo.value = userData;
      console.log('User Info:', userInfo.value);
      editForm.value = {
        stu_name: userInfo.value.stu_name,
        stu_id: userInfo.value.stu_id,
        email: userInfo.value.email
      };
      console.log('Edit Form:', editForm.value);
    } else {
      console.error('User data is null or undefined:', userData);
    }
  }).catch(error => {
    console.error('API Error:', error);
  });
});

const joinedClubs = ref([
  { 
    id: 1,
    name: '科技创新社', 
    role: '成员', 
    joinDate: '2022-09-10',
    logo: 'https://picsum.photos/id/1005/60/60'
  },
  { 
    id: 2,
    name: '篮球社', 
    role: '成员', 
    joinDate: '2022-10-15',
    logo: 'https://picsum.photos/id/1015/60/60'
  },
  { 
    id: 3,
    name: '摄影协会', 
    role: '成员', 
    joinDate: '2023-03-05',
    logo: 'https://picsum.photos/id/1025/60/60'
  }
]);

const favoriteClubs = ref([]);

onMounted(() => {
  const userId = route.params.id || 2; // Fallback to 2 if no id
  axios.get(`/api/favorite/list?userId=${userId}`).then(response => {
    if (response.data.code === '200') {
      favoriteClubs.value = response.data.data;
    }
  });
});

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

const activities = ref([
  {
    id: 1,
    type: 'participated',
    user: {
      name: '张三',
      avatar: 'https://picsum.photos/id/1027/40/40'
    },
    time: '2小时前',
    content: '参加了 <span class="text-primary font-medium">科技创新社</span> 举办的 <span class="font-medium">科技创新讲座</span>',
    activity: {
      title: '科技创新讲座',
      date: '6月25日 14:00-17:00',
      location: '科技楼 301 会议室',
      image: 'https://picsum.photos/id/1047/100/100'
    },
    likes: 15,
    comments: 3
  },
  {
    id: 2,
    type: 'commented',
    user: {
      name: '张三',
      avatar: 'https://picsum.photos/id/1027/40/40'
    },
    time: '昨天',
    content: '在 <span class="text-primary font-medium">科技创新社</span> 发表了评论：',
    comment: '"这次讲座真的很精彩，专家分享的人工智能前沿技术让我受益匪浅，期待下次活动！"',
    likes: 8,
    comments: 2
  },
  {
    id: 3,
    type: 'joined',
    user: {
      name: '张三',
      avatar: 'https://picsum.photos/id/1027/40/40'
    },
    time: '3天前',
    content: '加入了 <span class="text-primary font-medium">摄影协会</span>',
    club: {
      name: '摄影协会',
      description: '记录校园美好瞬间，分享摄影技巧',
      logo: 'https://picsum.photos/id/1025/60/60'
    },
    likes: 12,
    comments: 4
  }
]);

// 根据活动类型过滤活动
const filteredActivities = computed(() => {
  if (activeTab.value === 0) {
    return activities.value; // 全部动态
  } else if (activeTab.value === 1) {
    return activities.value.filter(activity => activity.type === 'participated'); // 参与的活动
  } else if (activeTab.value === 2) {
    return activities.value.filter(activity => activity.type === 'commented'); // 发表的评论
  }
  return activities.value;
});
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