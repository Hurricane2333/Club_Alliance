import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Home/HomeView.vue'
import DashBoard from '../views/Admin/DashBoard.vue'
import ClubInfo from '../views/Admin/ClubInfo.vue'
import LoginView from '../views/LoginView.vue'
import ProfileView from '../views/User/ProfileView.vue'
import {useUserStore} from "@/stores/user.js";
import ActivityDetailView from '@/views/ActivityDetail/ActivityDetailView.vue'
import RegisterView from '../views/RegisterView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: HomeView,
      meta: { title: '首页' }
    },
    { path: '/group', component: () => import('../views/Group/GroupIntroView.vue'), children: [
        {path: ':id', component: () => import('@/views/Group/components/GroupInfo.vue'), props:true, meta:{ title: (to, clubName) => `${clubName || to.params.id}简介` }}
  ]},
    {
      path: '/groupHome/:id',
      component: () => import('@/views/Group/GroupHomeView.vue'),
      meta: { title: (to, clubName) => `${clubName || to.params.id}` },
      children: [
        { path: '', redirect: (to) => `/groupHome/${to.params.id}/posts` },
        { path: 'posts', component: () => import('@/views/Group/components/GroupPostList.vue') },
        { path: 'activities', component: () => import('@/views/Group/components/GroupActivityList.vue') }
      ]
    },
    {
      path: '/group/manage/:id',
      component: () => import('@/views/Group/GroupManageView.vue'),
      meta: { requiresPresident: true },
      children: [
        { path: '', redirect: (to) => `/group/manage/${to.params.id}/members` },
        { path: 'members', component: () => import('@/views/Group/components/MemberReview.vue') },
        { path: 'info', component: () => import('@/views/Group/components/InfoManagement.vue') },
        { path: 'events', component: () => import('@/views/Group/components/EventManagement.vue') }
      ]
    },
    {
      path: '/clublists',
      component: () => import('../views/ClubLists/ClubListsView.vue'),
      meta: { title: '社团列表' }
    },
    {
      path: '/activitylists',
      component: () => import('../views/ActivityLists/ActivityListsView.vue'),
      meta: { title: '活动列表' }
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
      path: '/register',
      name: 'register',
      component: RegisterView,
      meta: { title: "注册"}
    },
    {
      path: '/activity/:id',
      name: 'ActivityDetail',
      component: ActivityDetailView,
      props:true
    }
  ],
})

router.beforeEach(async (to, from, next) => {
  // 社长权限验证
  if (to.meta.requiresPresident) {
    try {
      const clubRes = await fetch(`/api/group/selectId/${to.params.id}`)
      const club = await clubRes.json()
      const userStore = useUserStore().user.userId
      console.log('用户ID:', userStore, typeof userStore, '社长ID:', club.presidentId, typeof club.presidentId)
      console.log('用户对象:', userStore)
      console.log('社团对象:', club)
      if (userStore !== club.presidentId) {
        alert('只有社长可以访问管理页面')
        return next('/')
      }
    } catch (error) {
      console.error('权限验证失败:', error)
      return next('/login')
    }
  }

  // 标题设置
  const title = to.meta?.title
  if (typeof title === 'function') {
    try {
      const response = await fetch(`/api/group/selectId/${to.params.id}`)
      const club = await response.json()
      document.title = title(to, club.clubName)
    } catch (error) {
      console.error('获取社团信息失败:', error)
      document.title = title(to)
    }
  } else {
    document.title = title || '社团联盟'
  }
  next()
})

export default router
