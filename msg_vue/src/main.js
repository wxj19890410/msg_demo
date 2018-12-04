// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Sidebar from '@/components/public/Sidebar'
import store from './vuex/index.js'
import VueResource from 'vue-resource'
import service from './service/index.js'
import filter from './filter/index.js'
import 'babel-polyfill'

Vue.component('Sidebar',Sidebar)


import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

import $ from 'jquery'


// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

Vue.use(VueResource)
Vue.prototype.$service = service
Vue.prototype.$filter = filter
Vue.http.options.emulateJSON = true
Vue.http.interceptors.push((request, next) => {
  // console.log(window.GLOBLE.apiUrl)
  request.url = window.GLOBLE.apiUrl + request.url
  // request.headers.set('Content-Type', 'application/json')
  request.headers.set('loginUuid', service.localStorage.get('loginUuid'))

  if (request.method === 'POST' && request.body) {
    var params = {}

    for (let [key, value] of Object.entries(request.body)) {
      if (value instanceof Array) {
        params[key] = JSON.stringify(value)
      } else if (value instanceof Object) {
        params[key] = JSON.stringify(value)
      } else if (value != null) {
        params[key] = value
      }
    }
    request.body = params
  }
  next(response => {
    let result = {
      ok: true
    }
    if (response.status === 200) {
      result.data = response.body
    } else {
      if (response.body.message === 'not loged in') {
        Vue.prototype.$message({
          type: 'error',
          message: '请登录用户'
        })
        store.commit('setUserInfo')
        router.push('/')
      } else if (response.body.error) {
        result.data = response.body/*
        result.error = response.body.error
        result.message = response.body.message*/
      }
    }
    result.response = response
    return result
  })
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
