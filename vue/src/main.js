import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import '@/axios.js' // 请求拦截

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import VueAxios from 'vue-axios'
import axios from 'axios'

// 全局注册
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

// use
const app = createApp(App)

app.use(router)
app.use(store)
app.use(mavonEditor)
app.use(VueAxios, axios)
// app.prototype.$axios = axios
app.use(Element)

app.mount('#app')
