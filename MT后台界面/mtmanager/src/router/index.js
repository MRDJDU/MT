import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/index'
    },
    {
      path: '/',
      component: resolve => require(['../components/common/Home.vue'], resolve),
      meta: { title: '自述文件' },
      children:[
          {
            path: '/index',
            component: resolve => require(['../components/page/Index.vue'], resolve),
            meta: { title: '系统首页' }
          },
          {
            path: '/category',
            component: resolve => require(['../components/page/Category.vue'], resolve),
            meta: { title: '分类管理' }
          },
          {
            path: '/brand',
            component: resolve => require(['../components/page/Brand.vue'], resolve),
            meta: { title: '品牌管理' }
          },
          {
            path: '/goods',
            component: resolve => require(['../components/page/Goods.vue'], resolve),
            meta: { title: '商品管理' }
          },
          {
            path: '/orders',
            component: resolve => require(['../components/page/Orders.vue'], resolve),
            meta: { title: '订单管理' }
          },
          {
            path: '/maintain',
            component: resolve => require(['../components/page/Maintain.vue'], resolve),
            meta: { title: '维修管理' }
          },
          {
            path: '/evaluation',
            component: resolve => require(['../components/page/Evaluation.vue'], resolve),
            meta: { title: '评价管理' }
          },
          {
            path: '/article',
            component: resolve => require(['../components/page/Article.vue'], resolve),
            meta: { title: '文章管理' }
          },
          {
            path: '/user',
            component: resolve => require(['../components/page/User.vue'], resolve),
            meta: { title: '用户管理' }
          }
      ]
    },
    {
      path: '/login',
      component: resolve => require(['@/components/page/Login.vue'], resolve)
    }
  ]
})
