import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/icon.css';
// 引入axios
import axios from 'axios'
// 引入echarts
import echarts from 'echarts'


Vue.config.productionTip = false
Vue.use(ElementUI)
// 将axios挂载在Vue的原型上
Vue.prototype.axios = axios
Vue.prototype.$echarts = echarts 

router.beforeEach((to, from, next) => {
  const role = sessionStorage.getItem('manage_id');
  if (!role && to.path !== '/login') {
      next('/login');
  } else{
    next();
  }
// else {
//       // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
//       if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
//           Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
//               confirmButtonText: '确定'
//           });
//       } else {
//           next();
//       }
//   }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
