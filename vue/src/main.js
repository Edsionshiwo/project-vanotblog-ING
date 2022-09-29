import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'

import '@/assets/css/style.less'
import '@/assets/font/iconfont.css'

import mavonEditor from 'mavon-editor'
Vue.config.productionTip = false

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// MarkDown 编辑组件
Vue.use(mavonEditor)
Vue.prototype.$message = ElementUI.Message

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
