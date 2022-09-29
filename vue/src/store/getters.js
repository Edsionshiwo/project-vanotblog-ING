// 全局获取数据的途径

const getters = {
    websiteInfo: state => {
        if (state.websiteInfo) {
            return state.websiteInfo
        }
        else if (sessionStorage.getItem('websiteInfo')){
            state.websiteInfo = JSON.parse(localStorage.getItem('websiteInfo'))
            return state.websiteInfo
        }
        return null
    },
    getToken: state => {
        if (state.token)
            return state.token
        else if (sessionStorage.getItem('token')){
            state.token = sessionStorage.getItem('token')
            return state.token
        }

        return ''
    }

}

export default getters
