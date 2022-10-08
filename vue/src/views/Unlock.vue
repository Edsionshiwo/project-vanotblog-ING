<template>
  <div>
    <div class="form-unlock">
      <form ref="ruleForm" class="demo-ruleForm">
        <div label="密钥" prop="key">
          <input type="password" v-model="ruleForm.key" autocomplete="off">
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import {processUnlock} from "@/api";

export default {
  name: 'unlock',
  components: {},
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
          console.log(token)

          this.$store.commit('SET_TOKEN', token)
        }).catch(err => {
          this.$message({
            message: "87987",
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
