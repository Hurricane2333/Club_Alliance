import { createRouter, createWebHistory } from 'vue-router'
import DashBoard from '../views/Admin/DashBoard.vue'
import ClubInfo from '../views/Admin/ClubInfo.vue'
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

export default router
