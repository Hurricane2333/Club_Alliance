import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Home/HomeView.vue'

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


  ],
})

router.beforeEach((to, from, next) => {
  document.title= to.meta.title
  next()
})

export default router
