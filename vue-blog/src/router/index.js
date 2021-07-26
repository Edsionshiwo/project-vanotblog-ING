import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import Blogs from '@/views/Blogs.vue'
import BlogEdit from '@/views/BlogEdit.vue'
import BlogDetail from '@/views/BlogDetail.vue'

import home from '@/views/home'
import about from '@/views/about'
import article from '@/views/article'
import archive from '@/views/archive'
import apply from '@/views/apply'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: { name: 'Blogs' }
  },
  {
    path: '/blogs',
    name: 'Blogs',
    component: Blogs
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/blog/add',
    name: 'BlogAdd',
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: BlogDetail
  },
  {
    path: '/blog/:blogId/edit',
    name: 'BlogEdit',
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },

  {
    path: '/',
    component: home
  },
  {
    path: '/home',
    name: 'home',
    component: home
  },
  {
    path: '/about',
    name: 'about',
    component: about
  },
  {
    path: '/archive',
    name: 'archive',
    component: archive
  },
  {
    path: '/tag/:name',
    name: 'tag',
    component: archive
  },
  {
    path: '/article',
    name: 'article',
    component: article
  },
  {
    path: '/apply',
    name: 'apply',
    component: apply
  },
  {
    path: '*',
    redirect: '/'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
