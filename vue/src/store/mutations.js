const mutations = {
    SET_TOKEN: (state, token) => {
        state.token = token
        localStorage.setItem('token', token)
    },
    SET_USERINFO: (state, userInfo) => {
        state.userInfo = userInfo
        sessionStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    REMOVE_INFO: (state) => {
        state.token = ''
        state.userInfo = {}
        localStorage.setItem('token', '')
        sessionStorage.setItem('userInfo', JSON.stringify(''))
        localStorage.setIten('websiteInfo', null)
    },
    SET_SITE_INFO: (state, v) =>{
        state.websiteInfo = v;
        localStorage.setItem('websiteInfo', JSON.stringify(v))

    }
}

export default mutations
