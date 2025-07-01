import { createRouter, createWebHistory } from 'vue-router'

import ProfileView from '../views/user/ProfileView.vue'
import LoginView from '../views/LoginView.vue'
import HomeView from '../views/Home/HomeView.vue'
import DashBoard from '@/views/Admin/DashBoard.vue';
import ClubInfo from '../views/Admin/ClubInfo.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: LoginView,
      meta: { title: "登录"}
    },

    {
      path: '/user/:id',
      name: 'UserProfile',
      meta: { title: "个人主页"},
      component: ProfileView
    },
    {
      path: '/',
      name: 'Home',
      component: HomeView,
      meta: { title: "首页"}
    },
    {
      path: '/dashboard',
      component: DashBoard,
    },
    {
      path: '/manageClub/info',
      component: ClubInfo,
    },
    {
      path: '/admin/dashboard',
      name: 'admin-dashboard',
      component: DashBoard
    }
  ],
})



export default router

