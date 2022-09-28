import router from '@/router'

// 路由判断登录 根据路由配置文件的参数
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')
    if (token) {
        if (to.path === '/login') {
        } else {
            next()
        }
    } else {
        next({
          path: '/login'
        })
    }
})
