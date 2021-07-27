import axios from 'axios'
import store from '@/store'
import router from "@/router";

// create an axios instance
const request = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,
    timeout: 5000
})

// request interceptor
request.interceptors.request.use(
    config => {
        console.log('前置拦截')
        return config
    },
    error => {
        console.log(error) // for debug
        return Promise.reject(error || 'Error')
    }
)

// response interceptor
request.interceptors.response.use(
    response => {
        console.log('后置拦截')

        const res = response.data

        // store.commit('SET_LOADING',false);

        if (res.code !== 200) {
            return Promise.reject(new Error(res.message || 'Error'))
        } else {
            return res
        }
    },
    error => {
        console.log(error)// for debug
        if (error.response.data) {
            error.message = error.response.data.msg
        }
        // 未登录
        if (error.response.status === 401) {
            store.commit('REMOVE_INFO')
            router.push({
                path: '/login'
            })
            error.message = '请重新登录'
        }
        // 权限不足
        if (error.response.status === 403) {
            error.message = '权限不足，无法访问'
        }
        return Promise.reject(error || 'Error')
    }
)

export default request
