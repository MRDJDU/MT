import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 状态
const state = {
    userInfo:{}
}

// mutations,state
const mutations = {
    SAVE_USERINFO(state,userInfo){
        console.log(userInfo)
        state.userInfo = userInfo;
    }
}

// 创建数据仓库，暴露出去
export default new Vuex.Store({
    state,
    mutations
})