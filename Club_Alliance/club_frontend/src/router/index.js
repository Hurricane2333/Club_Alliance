import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Home/HomeView.vue'
import DashBoard from '../views/Admin/DashBoard.vue'
import ClubInfo from '../views/Admin/ClubInfo.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import ProfileView from '../views/User/ProfileView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: HomeView,
    },
    { path: '/group', component: () => import('../views/Group/GroupHomeView.vue'), children: [
        {path: ':id', component: () => import('@/views/Group/components/GroupInfo.vue')}
  ]},
    {
      path: '/clublists',
      component: () => import('../views/ClubLists/ClubListsView.vue'),
    },
    {
      path: '/activitylists',
      component: () => import('../views/ActivityLists/ActivityListsView.vue'),
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
      path: '/register',
      name: 'register',
      component: RegisterView,
      meta: { title: "注册"}
    },
    {
      path: '/user/:id',
      name: 'UserProfile',
      meta: { title: "个人主页"},
      component: ProfileView
    },
  ],
})

router.beforeEach((to, from, next) => {
  document.title= to.meta.title
  next()
})

export default router
