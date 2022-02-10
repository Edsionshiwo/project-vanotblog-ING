import {fetchSiteInfo} from "@/api";

const actions = {
    getSiteInfo: ({commit,state}) => {
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

export default actions
