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

// export function fetchFriend() {
//     return request({
//         url: '/friend',
//         method: 'get',
//         params: {}
//     })
// }

export function fetchSiteInfo() {
    return request({
        url: '/site',
        method: 'get',
        params: {}
    })
}

// export function fetchComment() {
//     return request({
//         url: '/comment',
//         method: 'get',
//         params: {}
//     })
// }
