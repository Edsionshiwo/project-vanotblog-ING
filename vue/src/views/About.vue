<template>
    <div class="about animate">
        <div class="site-content">
            <div class="content-warp">
                <div class="about-site about-info">
                    <section-title><span></span>关于博客</section-title>
                    <div class="info-card">
                      <p>一个简单的博客。</p>
                      <p><a target="_blank" href="https://github.com/vanot313/project-vanotblog-ING" style="color: #ff6d6d;">Github仓库</a></p>
                    </div>
                </div>
              <div class="about-me about-info">
                <section-title id="Guestbook">关于我</section-title>
                <div class="info-card">
                  <p> {{websiteInfo.slogan}} </p>
                  <p>一个焦虑的人。</p>
                </div>
              </div>
            </div>
        </div>
    </div>
</template>
<script>
    import sectionTitle from '@/components/section-title'

    export default {
        name: "About",
        data() {
            return {
              websiteInfo: {},
            }
        },
        components: {
            sectionTitle
        },
        methods: {},
        created(){
          this.websiteInfo = this.$store.getters.websiteInfo
        },
        mounted() {
        }
    }
</script>
<style scoped lang="less">
    .about {
        padding-top: 40px;
    }

    .content-warp {
        margin-top: 80px;

        .about-info {
            margin: 30px 0;

            span {
                color: red;
                margin-right: 10px;
            }

            .info-card {
                min-height: 100px;
                padding: 20px;
                border-radius: 3px;
                margin: 30px 0 50px 0;
                box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
                p{
                    line-height: 1.7rem;
                }
            }
        }
        .contactForm{
            width: 100%;
            padding: 20px;
            .form-item{
                align-items: center;
                display: flex;
                &:not(:last-child){
                    margin-bottom: 20px;
                }
                label{
                  width: 80px;
                }
                .v{
                    min-height: 40px;
                    line-height: 20px;
                    border-radius: 3px;
                    padding: 2px 10px;
                    outline:none;
                    border: 1px solid #8fd0cc;
                    width: 100%;
                    resize: vertical;
                }
                button{
                    width: 100px;
                    height: 40px;
                    border-radius: 3px;
                    outline:0;
                    border-style: none;
                    cursor: pointer;
                    background-color: #409eff;
                    color: white;
                    &:hover{
                        opacity: 0.8;
                    }
                }
            }
        }
    }

    /*******/
    @media (max-width: 800px) {
        .content-warp {
            margin-top: 0;
        }
    }
</style>
