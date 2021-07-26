const Mock = require('mockjs');
const count = 10


const List = [{
    id: 0,
    isTop: true,
    isHot: true,
    pubTime: +Mock.Random.date('T'),
    title: '看一遍闭着眼都会安装Lua了',
    summary: 'Lua 是一种轻量小巧的脚本语言，能为应用程序提供灵活的扩展和定制功能。',
    content: '',
}]
for (let i = 0; i < count; i++) {
    List.push(Mock.mock({
        id: '@increment',
        'isTop|1-4': true,
        'isHot|1-3': true,
        pubTime: +Mock.Random.date('T'),
        title: Mock.Random.ctitle(10,20),
        summary: Mock.Random.cparagraph(),
        content: '',
    }))
}

export default [
    {
        url: '/blogs',
        type: 'get',
        response: config => {
            let {page = 1, size = 10} = config.query;
            page = page instanceof Number ? page : parseInt(page)
            size = size instanceof Number ? size : parseInt(size)
            const pageList = List.filter((item, index) => index < size * page && index >= size * (page - 1));
            return {
                code: 200,
                data: {
                    total:List.length,
                    items:pageList.sort((a,b)=>a.isTop===b.isTop?0:a.isTop?-1:1),
                    hasNextPage: page * size < List.length,
                    page: page,
                    size: size
                }
            }
        }
    }
]
