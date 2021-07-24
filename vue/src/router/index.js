import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: { name: 'Blogs' }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },
  {
    path: '/blogs',
    name: 'Blogs',
    // 懒加载
    component: () => import('@/views/Blogs.vue')
  },
  {
    path: '/blog/add', // 注意放在 path: '/blog/:blogId'之前
    name: 'BlogAdd',
    meta: {
      requireAuth: true
    },
    component: () => import('@/views/BlogEdit')
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: () => import('@/views/BlogDetail')
  },
  {
    path: '/blog/:blogId/edit',
    name: 'BlogEdit',
    meta: {
      requireAuth: true
    },
    component: () => import('@/views/BlogEdit')
  }
]
const router = createRouter({
  history: createWebHistory(),
  base: process.env.BASE_URL,
  routes
})
export default router
