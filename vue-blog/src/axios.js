import axios from 'axios'
import Element from 'element-ui'
import store from '@/store'
import router from '@/router'

axios.defaults.baseURL = process.env.VUE_APP_BASE_API
axios.defaults.timeout = 5000
// axios.defaults.withCredentials = true

axios.interceptors.request.use(config => {
  console.log('前置拦截')
  // 可以统一设置请求头
  return config
},
error => {
  console.log(error)// for debug
  return Promise.reject(error)
})
axios.interceptors.response.use(response => {
  const res = response.data
  console.log('后置拦截')
  if (res.code === 200) {
    return response
  } else {
    // 弹窗异常信息
    Element.Message({
      message: response.data.msg,
      type: 'error',
      duration: 2 * 1000
    })
    // 直接拒绝往下面返回结果信息
    return Promise.reject(response.data.msg)
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
  Element.Message({
    message: error.message,
    type: 'error',
    duration: 3 * 1000
  })
  return Promise.reject(error)
})
