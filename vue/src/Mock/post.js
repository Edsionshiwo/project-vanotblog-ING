const Mock = require('mockjs');
const count = 100

const baseContent = '<p>I am testing data, I am testing data.</p><p><img src="https://wpimg.wallstcn.com/4c69009c-0fd4-4153-b112-6cb53d1cf943"></p>'

const List = [{
    id: 0,
    created: +Mock.Random.date('T'),
    title: '看一遍闭着眼都会安装Lua了',
    description: 'Lua 是一种轻量小巧的脚本语言，能为应用程序提供灵活的扩展和定制功能。',
    content: '',
    status: 0
}]
for (let i = 0; i < count; i++) {
    List.push(Mock.mock({
        id: '@increment',
        title: Mock.Random.ctitle(10,20),
        created: +Mock.Random.date('T'),
        description: Mock.Random.cparagraph(),
        content: baseContent,
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
                msg: "Success",
                data: {
                    total:List.length,
                    size: size,
                    current: page,
                    // items:pageList.sort((a,b)=>a.isTop===b.isTop?0:a.isTop?-1:1),
                    records:pageList.sort((a,b)=>a.created>b.created?-1:1),
                    orders: [],
                    searchCount: true,
                    pages: 10,
                }
            }
        }
    }
]
