import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Home/HomeView.vue'
import DashBoard from '../views/Admin/DashBoard.vue'
import ClubInfo from '../views/Admin/ClubInfo.vue'
import LoginView from '../views/LoginView.vue'
import ProfileView from '../views/User/ProfileView.vue'
import {useUserStore} from "@/stores/user.js";
import { ElMessage } from 'element-plus'
import ActivityDetailView from '@/views/ActivityDetail/ActivityDetailView.vue'
import RegisterView from '../views/RegisterView.vue'
import ClubMember from "@/views/Admin/ClubMember.vue";
import ClubMemberSpecified from "@/views/Admin/ClubMemberSpecified.vue";
import ClubReview from "@/views/Admin/ClubReview.vue";
import ActivityReview from "@/views/Admin/ActivityReview.vue";
import AdminInfo from "@/views/Admin/AdminInfo.vue";
import StudentInfo from "@/views/Admin/StudentInfo.vue";


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
      path: '/group/create',
      component: () => import('@/views/Group/GroupCreateView.vue'),
      meta: { title: '社团创建' }
    },
    {
      path: '/group/:id/postCreate',
      component: () => import('@/views/Group/GroupPostCreate.vue'),
      meta: { title: '发帖' }
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
      path: '/manageClub/member',
      component: ClubMember,
    },
    {
      path: '/manageClub/member/:clubId',
      component: ClubMemberSpecified,
    },
    {
      path:'/review/club',
      component:ClubReview,
    },
    {
      path:'/review/activity',
      component:ActivityReview,
    },
    {
      path:'/manageUser/admin',
      component:AdminInfo,
    },
    {
      path:'/manageUser/student',
      component:StudentInfo,
    }
  ],
})

router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore();

  // 成员权限验证
  if (to.path.startsWith('/groupHome/')) {
    if (!userStore.token) {
      ElMessage.warning('请先登录')
      return next('/login')
    }
    try {
      const response = await fetch(`/api/group/member/selectMemberStatus/${userStore.user?.userId}/${to.params.id}`)
      const member = await response.json();
      if (member.status !== 'APPROVED') {
        ElMessage.warning('仅社团成员可访问该页面')
        return next('/')
      }
    } catch (error) {
      console.error('成员状态验证失败:', error)
      ElMessage.error('权限验证失败，请稍后重试')
      return next('/')
    }
  }

  // 权限验证
  if (to.path.startsWith('/dashboard')) {
    if (!userStore.token) {
      ElMessage.warning('请先登录')
      return next('/login')
    }
    try {
      if ( !userStore.user?.isAdmin ) {
        ElMessage.warning('仅管理员可访问该页面')
        return next('/')
      }
    } catch (error) {
      console.error('状态验证失败:', error)
      ElMessage.error('权限验证失败，请稍后重试')
      return next('/')
    }
  }

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
