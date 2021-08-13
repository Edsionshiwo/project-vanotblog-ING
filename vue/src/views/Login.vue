<template>
  <div>
    <div class="form-login">
      <form :model="ruleForm"  :rules="rules" ref="ruleForm"
            class="demo-ruleForm">
        <div label="用户名" prop="username">
          <input  type="text" maxlength="12" v-model="ruleForm.username">
        </div>
        <div label="密码" prop="password">
          <input  type="password" v-model="ruleForm.password" autocomplete="off">
        </div>
        <div>
          <button  @click="submitForm('ruleForm')">登录</button>
        </div>
      </form>
    </div>

  </div>
</template>
<script>
import About from "@/views/About";
export default {
  name: 'Login',
  components: {About},
  data () {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        password: '',
        username: ''
      },
      rules: {
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 提交逻辑
          this.$axios.post('/login', this.ruleForm).then((res) => {
            const token = res.headers.authorization
            _this.$store.commit('SET_TOKEN', token)
            _this.$store.commit('SET_USERINFO', res.data.data)
            _this.$router.push('/')
          })
        } else {
          console.log('error submit!!')
          return false
        }
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
