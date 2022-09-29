import state from "@/store/state";

const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token
        sessionStorage.setItem('token', token)
    },
    REMOVE_INFO: (state) => {
        state.token = ''
        localStorage.setItem('token', '')
    },
    SET_SITE_INFO: (state, v) =>{
        state.websiteInfo = v;
        localStorage.setItem('websiteInfo', JSON.stringify(v))
    }
}

export default mutations
