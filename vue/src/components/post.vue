<template>
    <article class="post post-list">
        <div class="post-entry">
            <h1 class="entry-title">
<!--            TODO    这里能否将整个 post 实例传入 Articles 页面，避免二次查询-->
                <router-link :to="`/blog/${post.id}`">
                  <span v-if="post.isTop" style="color:#ff6d6d;font-weight:600">[置顶] </span>
                  {{post.title}}
                </router-link>
            </h1>
            <div class="p-time">
                <i v-if="post.isHot" class="iconfont iconfire" style="margin-left: 5px;color: #ff6d6d;"></i>
            </div>
            <p class="summary">{{post.description}}</p>
        </div>
        <hr/>
    </article>
</template>

<script>

export default {
    name: "post",
    props: {
        post: {
            type: Object
        }
    }
}
</script>

<style scoped lang="less">
    .post {
        margin: 0 0 0% 0;
        position: relative;
    }

    .post-entry {

        .entry-title {
            font-size: 21px;
            font-weight: 600;
            line-height: 50px;
            margin: 0 0 0 0%;
            position: relative;
            z-index: 1;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            width: 70%;
            color: #737373;

            & a:hover {
                color: #ff6d6d;
            }
        }

        .p-time {
            position: absolute;
            right: 0;
            top: 16px;
            font-size: 12px;
            color: #989898;
            letter-spacing: 1px;
            font-family: din, 'Hiragino Sans GB', 'Microsoft Yahei', Arial, sans-serif;
            display: flex;
            align-items: center;
        }

        p.summary {
            min-height: 60px;
            margin: 0 0 0 0%;
            font-size: 15px;
            color: #6f6f6f;
            letter-spacing: 1px;
            line-height: 30px;
        }

        footer.entry-footer {
            margin: 0 0 0 17%;
            list-style: none;

            .info-meta {
                margin-top: 10px;
                font-family: din, 'Hiragino Sans GB', 'Microsoft Yahei', Arial, sans-serif;
                position: absolute;
                top: 20px;
                opacity: 0;
                padding-top: 8px;
                border-top: 1px solid #ddd;
                -webkit-transform: translate3d(-150px, 0, 0);
                transform: translate3d(-150px, 0, 0);
                visibility: hidden;
                transition: .7s all ease;
                -webkit-transition: .6s all ease;
                -moz-transition: .6s all linear;
                -o-transition: .6s all ease;
                -ms-transition: .6s all ease;

                span, a {
                    color: #B3B3B3;
                    font-size: 12px;
                }

                i {
                    margin-top: 3px;
                    margin-right: 10px;
                    float: left
                }
            }

            .comnum {
                float: left;
            }
        }

    }

    .post-entry:hover footer.entry-footer .info-meta {
        -webkit-transform: translate3d(-230px, 0, 0);
        transform: translate3d(-230px, 0, 0);
        opacity: 1;
        visibility: visible;
    }

    .post hr {
        width: 30%;
        height: 1px;
        margin: 0 auto;
        border: 0;
        background: #EFEFEF;
    }

    /*******/
    @media (max-width: 1060px) {
        .info-meta{
            display: none;
        }
    }
    @media (max-width: 800px) {
        .post-entry {
            .entry-title{
                font-size: 16px;
                line-height: 30px;
                margin: 0 0 0 65px;
            }
            .p-time {
                position: relative;
                margin: -15px 0 0 65px;
            }
            p.summary {
                margin: 20px 0 0 65px;
                font-size: 14px;
                height: 30px;
                overflow: hidden;
            }
        }
        .post-list hr {
            margin-top: 20px;
        }
    }

    /******/
</style>
