import { createRouter, createWebHistory } from 'vue-router'
import DashBoard from '../views/Admin/DashBoard.vue'
import ClubInfo from '../views/Admin/ClubInfo.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/dashboard',
      component: DashBoard,
    },
    {
      path: '/manageClub/info',
      component: ClubInfo,
    }
  ],
})

export default router
