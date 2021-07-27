const Mock = require('mockjs');
export default [
    // 站点信息
    {
        url: '/site',
        type: 'get',
        response: () => {
            return {
                code: 200,
                data: {
                    avatar: 'https://s2.ax1x.com/2020/01/17/1SCadg.png',
                    slogan: '撸起袖子加油干.',
                    name: '蒋荷包蛋',
                    notice: '目前为 Demo 版本。',
                    description: '一个涉猎广泛的后端工程师'
                }
            }
        }
    }
]
