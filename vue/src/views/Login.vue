<template>
  <div>
    <div class="form-login">
      <form ref="ruleForm"
            class="demo-ruleForm">
        <div label="用户名" prop="username">
          <input  type="text" maxlength="12" v-model="ruleForm.username">
        </div>
        <div label="密码" prop="password">
          <input  type="password" v-model="ruleForm.password" autocomplete="off">
        </div>
        <div>
          <button type="button" @click="submitForm('ruleForm')">登录</button>
<!--          <button type="button">登录</button>-->
        </div>
      </form>
    </div>

  </div>
</template>
<script>
import {processLogin} from "@/api";

export default {
  name: 'Login',
  components: {},
  data () {
    return {
      ruleForm: {
        password: '',
        username: ''
      }
    }

  },
  methods: {
    submitForm (formName) {
      const _this = this

      processLogin(this.ruleForm).then((res) => {

        const token = res.headers.authorization

        console.log(token)

        _this.$store.commit('SET_TOKEN', token)
        _this.$store.commit('SET_USERINFO', res.data.data)


        console.log(res)
      }).catch(err => {
        console.log(err) // for debug
      })


    }
  }
}
</script>

<style>
.form-login{
  margin: 30% 30% 30% 30%;
}
</style>
