const getters = {
    websiteInfo: state => {
        if (state.websiteInfo)
            return state.websiteInfo
        else if (localStorage.getItem('websiteInfo')){
            state.websiteInfo = JSON.parse(localStorage.getItem('websiteInfo'))
            return state.websiteInfo
        }
        return null
    },
    getUser: state => {
        if (state.userInfo)
            return state.userInfo
        else if (localStorage.getItem('userInfo')){
            state.userInfo = JSON.parse(localStorage.getItem('userInfo'))
            return state.userInfo
        }
        return null
    }

}

export default getters
