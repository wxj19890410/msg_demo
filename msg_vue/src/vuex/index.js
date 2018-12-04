import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  userInfo: {},
  login: false
}
const mutations = {
  setUserInfo (state, userInfo) {
    if (userInfo) {
      state.login = true
    } else {
      state.login = false
    }
    state.userInfo = userInfo || {}
  }
}
export default new Vuex.Store({
  state,
  mutations
})
