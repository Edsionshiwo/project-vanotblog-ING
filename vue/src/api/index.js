import request from '@/utils/request'

export function fetchList(currentPage) {
    return request({
        url: '/blogs',
        method: 'get',
        params: {currentPage}
    })
}

export function fetchBlog(params) {
    return request({
        url: '/blog/' + params.toString(),
        method: 'get',
        params: {}
    })
}

export function fetchSiteInfo() {
    return request({
        url: '/site',
        method: 'get',
        params: {}
    })
}

export function processUnlock(data){
    return request({
        url: '/unlock',
        method: 'post',
        data
    })
}

export function processLock() {
    return request({
        url: '/lock',
        method: 'get'
    })
}

