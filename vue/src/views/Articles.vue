<template>
  <div class="articles">
    <div class="site-content">
      <!-- 文章目录 -->
      <div id="article-menus">
        <menu-tree :menus="menus" child-label="child"></menu-tree>
      </div>
      <main class="site-main animate">
        <article class="hentry">
          <!-- 文章头部 -->
          <header class="entry-header">
            <!-- 标题输出 -->
            <h1 class="entry-title">{{ blog.title }}</h1>
            <hr>
            <div class="breadcrumbs">
              <div id="crumbs">{{ blog.created }}</div>
            </div>
          </header>
          <!-- 正文输出 -->
          <div id="markdown-body" class="content markdown-body entry-content" v-html="blog.content"></div>

          <!--声明-->
          <div class="open-message">
            <p>本网站采用<a href="https://creativecommons.org/licenses/by-nc-sa/3.0/"target="_blank">BY-NC-SA</a>协议进行授权</p>
            <p>转载：转载请注明原文链接 - <a href="/">{{ blog.title }}</a></p>
          </div>
        </article>
      </main>
    </div>
  </div>
</template>

<script>
import sectionTitle from '@/components/section-title'
import menuTree from '@/components/menu-tree'
import {fetchBlog} from '@/api'

export default {
  name: 'articles',
  data() {
    return {
      menus: [],
      blog: {},
    }
  },
  components: {
    sectionTitle,
    menuTree
  },
  methods: {
    getArticle() {
      let id = this.$route.params.id
      fetchBlog(id).then(res => {
        this.blog = res.data.data
        const MarkdownIt = require('markdown-it')
        const md = new MarkdownIt()
        this.blog.content = md.render(this.blog.content)
      }).catch(err => {
        console.log(err)
      })
    },
    fetchH(arr, left, right) {
      if (right) {
        return arr.filter(item => item.offsetTop > left && item.offsetTop < right)
      } else {
        return arr.filter(item => item.offsetTop > left)
      }
    },
    createMenus() {
      let arr = []
      for (let i = 6; i > 0; i--) {
        let temp = []
        let e = document.querySelector("#markdown-body").querySelectorAll(`h${i}`)

        for (let j = 0; j < e.length; j++) {
          e[j].id = "P" + j.toString()

          let child = this.fetchH(arr, e[j].offsetTop, (j + 1 === e.length) ? undefined : e[j + 1].offsetTop)
          temp.push({
            h: i,
            title: e[j].innerText,
            id: e[j].id,
            offsetTop: e[j].offsetTop,
            child
          })
        }
        if (temp.length) {
          arr = temp
        }
      }
      this.menus = arr

      console.log(this.menus)
    }
  },
  watch: {
    blog:function () {
      this.$nextTick(this.createMenus)
    },
  },
  mounted() {
  },
  created() {
    this.getArticle()
  }
}
</script>

<style scoped lang="less">
.site-content {
  position: relative;

  .site-main {
    position: relative;

    padding: 80px 0 0 0;
  }
}

#article-menus {
  position: fixed;
  top: 0px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, .1);
  border-radius: 3px;
  padding: 15px;
  width: 300px;
  transform: translateX(-120%) translateY(30%);
  font-size: 14px;
}

article.hentry {
  .entry-header {
    .entry-title {
      font-size: 23px;
      font-weight: 600;
      color: #737373;
      margin: 0.67em 0;

      &:before {
        content: "#";
        margin-right: 6px;
        color: #d82e16;
        font-size: 20px;
        font-weight: 600;
      }
    }

    hr {
      height: 1px;
      border: 0;
      background: #EFEFEF;
      margin: 15px 0;
    }

    .breadcrumbs {
      font-size: 14px;
      color: #D2D2D2;
      text-decoration: none;
      margin-bottom: 30px;
    }
  }

  .entry-content {
  }

  footer.post-footer {
    width: 100%;
    padding: 20px 10px;
    margin-top: 30px;
    height: 65px;
    position: relative;

    i {
      font-size: 18px;
      margin-right: 5px;
    }

    .post-like {
      float: right;
      margin: 7px 0 0 20px;
    }

    .post-share {
      float: right;
      list-style: none;
      margin-right: 20px;
    }

    .donate {
      float: left;
      line-height: 36px;
      border-radius: 100%;
      -webkit-border-radius: 100%;
      -moz-border-radius: 100%;
      border: 1px solid #2B2B2B;

      &:hover {
        border: 1px solid goldenrod;

        span {
          color: goldenrod;
        }
      }

      span {
        color: #2B2B2B;
        padding: 10px;
        position: relative;
        cursor: pointer;
      }

      .donate_inner {
        display: none;
        margin: 0;
        list-style: none;
        position: absolute;
        left: 80px;
        top: -40px;
        background: #FFF;
        padding: 10px;
        border: 1px solid #ddd;
        box-shadow: 0 2px 6px rgba(0, 0, 0, .08);
        border-radius: 3px;
        &.show {
          display: block;
        }

        li {
          float: left;
        }

        img {
          width: 100px;
        }

        p {
          text-align: center;
          font-size: 15px;
          color: #D2D2D2;
          line-height: 1rem;
        }
      }

      .donate_inner:after, .donate_inner:before {
        content: "";
        position: absolute;
        left: 0;
        bottom: 45%;
        margin-left: -8px;
        border-top: 8px solid transparent;
        border-bottom: 8px solid transparent;
        border-right: 8px solid #fff;
      }

      .donate_inner:before {
        left: -1px;
        border-right: 8px solid #ddd;
      }

    }

    .post-tags {
      margin: 7px 0 0 20px;
      float: left;
      text-transform: uppercase;

      a:hover {
        color: #ff6d6d;
      }
    }
  }

  .open-message {
    margin: 50px 0;
    position: relative;
    background: #2B2B2B;
    padding: 10px 30px;
    border-radius: 3px;
    font-size: 14px;
    color: #fff;

    &:after {
      content: "";
      border-left: 10px solid transparent;
      border-right: 10px solid transparent;
      border-bottom: 10px solid #2B2B2B;
      position: absolute;
      top: -8px;
      left: 48%;
    }

    p {
      margin: 10px 0;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }

    a {
      color: #A0DAD0;
      padding: 0 5px;
    }
  }
}

#article-menus{
    overflow-y: scroll;
  height: 500px;
}
</style>
