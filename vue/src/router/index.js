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
        path: '/unlock',
        name: 'unlock',
        component: () => import('@/views/Unlock.vue'),
        meta: { title: '解锁'}
    },
    {
        path: '/about',
        name: 'about',
        component: () => import('@/views/About.vue'),
        meta: { title: '关于'}
    },
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
