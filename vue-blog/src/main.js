import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import Element from 'element-ui'
import axios from 'axios'

import mavonEditor from 'mavon-editor'

import 'element-ui/lib/theme-chalk/index.css'
import 'mavon-editor/dist/css/index.css'

import '@/axios'
import '@/permission'

import i18n from './i18n/i18n'

import fHeader from './components/f-header'
import fFooter from './components/f-footer'

Vue.use(Element)
Vue.use(mavonEditor)

Vue.config.productionTip = false
Vue.prototype.$axios = axios

Vue.component('f-header', fHeader)
Vue.component('f-footer', fFooter)

new Vue({
  router,
  i18n,
  store,
  render: h => h(App)
}).$mount('#app')

console.log(process.env.VUE_APP_BASE_API)
