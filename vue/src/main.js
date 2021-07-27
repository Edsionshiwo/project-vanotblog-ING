import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import '@/assets/css/style.less'
import '@/assets/font/iconfont.css'
require('@/Mock')

import mavonEditor from 'mavon-editor'
Vue.config.productionTip = false

Vue.use(mavonEditor)


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
