<template>
  <div class="home animate">
    <!--        <banner isHome="true"></banner>-->
    <div class="site-content">
      <!--通知栏-->
      <div class="notify">
        <div class="search-result" v-if="hideSlogan">
          <span v-if="websiteInfo.notice"> {{ websiteInfo.notice }} </span>
          <span v-if="searchWords">搜索结果："{{ searchWords }}" 相关文章</span>
        </div>
      </div>

      <!--文章列表-->
      <main class="site-main" :class="{'search':hideSlogan}">
        <section-title>博客</section-title>
        <template v-for="item in records">
          <post :post="item" :key="item.id"></post>
        </template>
      </main>

      <!--加载更多-->
      <div class="more" v-if="hasNextPage">
        <div class="more-btn" @click="loadMore">More</div>
      </div>
    </div>
  </div>
</template>

<script>
import Banner from '@/components/banner'
import sectionTitle from '@/components/section-title'
import Post from '@/components/post'
import {fetchList} from '@/api'

export default {
  name: 'Home',
  props: ['cate', 'words'],
  data() {
    return {
      records: [],
      currentPage: 1,
      hasNextPage: false,
      websiteInfo: {},
    }
  },
  components: {
    // Banner,
    sectionTitle,
    Post
  },
  computed: {
    searchWords() {
      return this.$route.params.words
    },
    hideSlogan() {
      return (this.searchWords || this.websiteInfo.notice)
    }
  },
  methods: {
    fetchList() {
      fetchList().then(res => {
        this.records = res.data.data.records
        this.currentPage = res.data.data.current
        this.hasNextPage = (res.data.data.current < res.data.data.pages)
      }).catch(err => {
        console.log(err)
      })
    },
    loadMore() {
      fetchList( ++this.currentPage ).then(res => {
        this.records = this.records.concat(res.data.data.records || [])
        this.currentPage = res.data.data.current
        this.hasNextPage = (res.data.data.current < res.data.data.pages)
      })
    },
    getWebSiteInfo() {
      this.$store.dispatch('getSiteInfo').then(data => {
        this.websiteInfo = data
      })
    }
  },
  mounted() {
    this.fetchList();
  },
  created() {
    this.getWebSiteInfo()
  }
}
</script>


<style scoped lang="less">

.site-content {
  .notify {
    margin: 80px 0;
    border-radius: 3px;

    & > div {
      padding: 20px;
    }
  }


  .search-result {
    padding: 15px 20px;
    text-align: center;
    font-size: 20px;
    font-weight: 400;
    border: 1px dashed #ddd;
    color: #828282;
  }
}

.site-main {
  padding-top: 80px;

  &.search {
    padding-top: 0;
  }
}

.more {
  margin: 50px 0;

  .more-btn {
    width: 100px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    color: #ADADAD;
    border: 1px solid #ADADAD;
    border-radius: 20px;
    margin: 0 auto;
    cursor: pointer;

    &:hover {
      color: #8fd0cc;
      border: 1px dashed #8fd0cc;
    }
  }
}

/******/
@media (max-width: 800px) {
  .site-main {
    padding-top: 40px;
  }

  .site-content {
    .notify {
      margin: 30px 0 0 0;
    }

    .search-result {
      margin-bottom: 20px;
      font-size: 16px;
    }
  }
}

/******/
</style>
