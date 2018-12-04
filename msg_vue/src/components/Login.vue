<template>
  <div class="login">
  	<div class="title">万华化学（宁波）氯碱活力指数</div>
  	<div class="login-form">
  		
  		<div class="login-input">
  			<el-input
			    placeholder="请输入用户名"
			    v-model="loginForm.username">
			</el-input>
			<img src="../assets/login.png">
  		</div>
		
		<div class="login-input">
			<el-input
			    placeholder="请输入密码" type="password"
			    v-model="loginForm.password">
			</el-input>
			<img src="../assets/password.png">
		</div>
		
        <el-button type="primary" style="width:100%;"  @click="login">
          登录
        </el-button>

  	</div>
  	
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: 'Login',
  data () {
    return {
    	loginForm: {
        username: 'admin',
        password: ''
      }
    }
  },
  mounted(){
    $(".login").width($(window).width())
    $(".login").height($(window).height())
  },
  methods:{
/*  	login(){
  		this.$router.push({name: 'HelloWorld'});
  	},*/
  async login (formName) {
      if (!this.loginForm.username){
        this.$notify.error({
          title: '错误',
          message: '请输入正确的用户名',
          offset: 100
        })
        return false
      }
      if (!this.loginForm.password){
        this.$notify.error({
          title: '错误',
          message: '请输入正确的密码',
          offset: 100
        })
        return false
      }
      const params = {}
      params.account = this.loginForm.username
      params.password = this.loginForm.password
      this.$http.get('/login/loadInfo', {params: params}).then(({ data }) => {
        if (data && !data.error) {
          this.$notify.success({
            title: '成功',
            message: '登录成功',
            offset: 100
          })
          this.$store.commit('setUserInfo', data)
          this.$service.localStorage.save('loginUuid', data.loginUuid)
          this.$router.push({name: 'companyList'})
        } else {
          this.$notify.error({
            title: '错误',
            message: data.message,
            offset: 100
          })
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login{background-color: #1B2B34;color:#eee;position: relative;padding-top:200px;}
.login-form{
	position: absolute;
	width: 300px;
	left: 50%;
	top:300px;
	margin-left: -150px;
}
.title{font-size: 30px;text-align: center;font-weight: 600;}
.login-input{position: relative;margin:20px 0;}
.login-input img{position: absolute;top:8px;width: 25px;height: 25px;left: 10px;}
/deep/ .el-input__inner{padding:0 50px;}
</style>
