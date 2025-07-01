import { createRouter, createWebHistory } from 'vue-router'

import ProfileView from '../views/user/ProfileView.vue'
import LoginView from '../views/LoginView.vue'
import HomeView from '../views/Home/HomeView.vue'

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
      path: '/',
      redirect: '/login',
    },
    {
      path: '/user/:id',
      name: 'UserProfile',
      meta: { title: "个人主页"},
      component: ProfileView
    },
    {
      path: '/home',
      name: 'Home',
      component: HomeView,
      meta: { title: "首页"}
    },
  ],
})



export default router

