<template>
    <div>
        <div class="form-unlock">
            <!-- Vue 在提交表单的时候会自动刷新页面，添加字段阻止自动刷新 -->
            <form ref="ruleForm" class="demo-ruleForm" v-on:submit.prevent="submitForm">
                <div label="密钥" prop="key">
                    <input type="password" v-model="ruleForm.key" autocomplete="off">
                </div>
            </form>
        </div>
    </div>
</template>
<script>
    import {processUnlock} from "@/api";
    import layoutHeader from "@/components/layout/layout-header"
    import Vue from 'vue'
    export default {
        name: 'unlock',
        components: {
            layoutHeader
        },
        data() {
            return {
                ruleForm: {
                    key: ''
                }
            }
        },
        methods: {
            submitForm(e) {
                if (e.keyCode === 13) {
                    processUnlock(this.ruleForm).then((res) => {
                        const token = res.headers.authorization
                        this.$store.commit('SET_TOKEN', token)

                        this.$router.push('/')
                        Vue.$forceUpdate()
                    }).catch(err => {
                        this.$message({
                            message: err,
                            type: 'warning',
                            showClose: true,
                            center: true
                        });
                    })

                }
            }
        },
        created() {
            window.addEventListener('keydown', this.submitForm)
        }
    }
</script>

<style>
    .form-unlock {
        margin-left: 30%;
        margin-right: 30%;

        margin-top: 15%;
    }

    .form-unlock input {
        outline-style: none;
        border: 1px solid #ccc;
        border-radius: 3px;
        padding: 13px 14px;
        width: 100%;
        font-size: 14px;
        font-weight: 700;
        font-family: "Microsoft soft";
    }
</style>
