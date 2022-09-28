import axios from 'axios'
import store from '@/store'
import router from "@/router";

const request = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,
    timeout: 5000
})

// request 过滤器
request.interceptors.request.use(
    config => {
        return config
    },
    error => {
        return Promise.reject(error || 'Error')
    }
)

// response 过滤器
request.interceptors.response.use(
    response => {
        const res = response

        if (res.data.code !== 200) {
            return Promise.reject(new Error(res.data.msg || 'Error'))
        } else {
            return res
        }
    },
    error => {
        if (error.response.data) {
            error.message = error.response.data.msg
        }

        // 未登录
        if (error.response.status === 401) {
            store.commit('REMOVE_INFO')
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
