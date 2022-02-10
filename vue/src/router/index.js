import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('@/views/Home.vue'),
        meta: { title: '首页'}
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/Login.vue'),
        meta: { title: '首页'}
    },
    // {
    //     path: '/search/:words',
    //     name: 'search',
    //     component: () => import('@/views/Home.vue'),
    //     meta: { title: '搜索', params: 'words'}
    // },
    {
        path: '/about',
        name: 'about',
        component: () => import('@/views/About.vue'),
        meta: { title: '关于'}
    },
    // {
    //     path: '/friend',
    //     name: 'friend',
    //     component: () => import('../views/Friend.vue'),
    //     meta: { title: '友链'}
    // },
    {
        path: '/blog/:id',
        name: 'blog',
        component: () => import('@/views/Articles.vue'),
        meta: { title: '博客', params: 'id'}
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
router.beforeEach((to, from, next) => {

    // 处理页面 title 的层次
    let title = ''
    if (to.meta.params){
        title = `${to.meta.title}:${to.params[to.meta.params] || ''} - ${title}`
    }else {
        title = `${to.meta.title} - ${title}`
    }

    document.title = title

    next();
})
router.afterEach((to, from) => {

})
export default router
