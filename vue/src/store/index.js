import Vue from 'vue'
import Vuex from 'vuex'
import {fetchSiteInfo} from '../api'

Vue.use(Vuex)

const state = {
    websiteInfo: ''
}
const mutations = {
    SET_SITE_INFO: (state, v) =>{
      state.websiteInfo = v;
    }
}
const actions = {
    getSiteInfo: ({commit,state}) =>{
        return new Promise(resolve => {
            if (state.websiteInfo){
                resolve(state.websiteInfo)
            }else {
                fetchSiteInfo().then(res => {
                    let data = res.data || {}
                    commit('SET_SITE_INFO',data);
                    resolve(data);
                }).catch(err => {
                    resolve({});
                })
            }
        })
    }
}
const getters = {
    websiteInfo: state => state.websiteInfo
}
export default new Vuex.Store({
    state,
    mutations,
    actions,
    modules: {},
    getters
})
