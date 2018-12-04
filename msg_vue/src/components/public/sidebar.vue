<template>
  <div class="sidebar" style="height:100%;position:relative;width:100%;">
    <el-menu  class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" background-color="#1B2B34"
      @select="handleSelect"
      text-color="#fff"
      active-text-color="#4D95FD"
      :default-active="this.$route.path" router>
      <el-menu-item >
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span slot="title">会员管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">{{ item.navItem }}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span slot="title">历史记录</span>
        </template>
        <el-menu-item-group>
          <el-menu-item  v-for="(item,i) in navList1" :key="i" :index="item.name" >{{ item.navItem }}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-time"></i>
          <span slot="title">测试</span>
        </template>
        <el-menu-item-group>
          <el-menu-item v-for="(item,i) in navList2" :key="i" :index="item.name">{{ item.navItem }}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="6">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span slot="title">网站管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item v-for="(item,i) in navList3" :key="i" :index="item.name">{{ item.navItem }}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>

    <!-- top --> 
    <el-menu
      
      class="el-menu-demo"
      mode="horizontal"
      @select="tuichu"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-radio-group v-model="isCollapse" class="zhankaiTab" :class="isCollapse?'zhankaiTab2':'' ">
       <!--  <img :src="zhankaiUrl" @click="zhankai()"> -->
      </el-radio-group>
      <div style="display:flex;position:absolute;right:0;">
        <el-submenu index="0">
          <template slot="title">
            <div class="headUrl-title">
              <img src="/static/headUrl.png" class="headUrl">
              <div>{{userInfo.name}}</div>
            </div>
          </template>
          <el-menu-item index="0-1" key="0-1">退出登录</el-menu-item>
          <el-menu-item index="0-2" key="0-2">忘记密码</el-menu-item>
        </el-submenu>
       
      </div>
    </el-menu>
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="原密码" :label-width="formLabelWidth">
          <el-input type="password"  v-model="form.oldPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.newPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="再次输入" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.newPassword2" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        
        <el-button type="primary" @click="dialogFormVisible = false">取 消</el-button>
        <el-button @click="changPassword()">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      form: {oldPassword: null, newPassword: null, newPassword2: null},
      dialogFormVisible: false,
      formLabelWidth: '120px',
      password: '222',
      userInfo: {},
      navList:[
        {name:'/companyList',navItem:'会员列表',xitong:1},
      ],
      navList1:[
        {name:'/rechargeList',navItem: '充值记录',xitong:2},
        {name:'/messageRecord',navItem: '消息记录',xitong:3},
      ],
      navList2:[
        {name:'/test',navItem:'测试',xitong:4}
      ],
      navList3:[
        {name:'/backupData',navItem:'数据库管理'},
      ],
      isCollapse: false,
      activeIndex: '1',
      zhankaiUrl:'/static/shouqi.png'
    }
  },
  watch: {
   
  },
  mounted(){
    this.userInfo = this.$store.state.userInfo
    console.log(this.userInfo)
    console.log(this.$route.path)
  },
  methods: {
    zhankai(){
      console.log(this.isCollapse)
      if(this.isCollapse){
        this.isCollapse = false
        this.zhankaiUrl = "/static/shouqi.png"
      }else{
        this.isCollapse = true
        this.zhankaiUrl = "/static/zhanki.png"
      }
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(key, keyPath) {
      // console.log(key);
      // console.log(keyPath)
    },
    // 退出登录
    tuichu(key, keyPath){
      console.log(key)
      if(key == "0-1"){
        this.$http.get('/huoli/org/loadOut').then(({ data }) => {
            if (data) {
              this.$notify.success({
                title: '成功',
                message: '退出成功',
                offset: 100
              })
              this.$store.commit('setUserInfo')
              this.$router.push({name: 'login'})
            } else {
              this.$notify.error({
                title: '错误',
                message: '已退出',
                offset: 100
              })
            }
          })
      }else{
        // 修改密码
        this.dialogFormVisible = true
      }
    },
    changPassword() {
      if(null === this.form.oldPassword){
        this.$notify.error({
          title: '错误',
          message: '原密码不能为空',
          offset: 100
        })
        return
      }
      if(null === this.form.newPassword){
        this.$notify.error({
          title: '错误',
          message: '新密码不能为空',
          offset: 100
        })
        return
      }
      if(this.form.newPassword !== this.form.newPassword2){
        this.$notify.error({
          title: '错误',
          message: '两次密码需要相同',
          offset: 100
        })
        return
      }
      const params = {}
      params.oldPassword = this.form.oldPassword
      params.newPassword = this.form.newPassword
      this.$http.get('/huoli/user/changPassword', {params: params}).then(({ data }) => {
          if (data  && !data.error) {
            this.$notify.success({
              title: '成功',
              message: '修改成功',
              offset: 100
            })
            this.dialogFormVisible = false
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
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu-vertical-demo{
  position: absolute;
  left: 0;
  height: 100%;
  z-index: 99;

}

/deep/ .el-menu{border:none;}
.el-menu-demo{
    display: flex;
    background-color: rgb(84, 92, 100);
    width: 100%;
    justify-content:space-between;
    z-index: 98;
    position: relative;
    height: 58px;
    padding-top:0px;
}
:focus{


outline:0;


}

.zhankaiTab{position: absolute;left:210px;transition:all 0.5s ease;}
.zhankaiTab img{height: 30px;width: 30px;margin-top:10px;}
.zhankaiTab2{left: 65px;}
.headUrl{height: 40px;width: 40px;border-radius: 50%;margin-right: 20px;margin-top:10px;}
.headUrl-title{display: flex;width: 170px;}
/deep/ .el-menu-item-group__title{padding:0px;}
/deep/ .el-submenu .el-menu-item{height: 42px;line-height: 42px;font-size: 12px;}
/deep/ [data-v-9b739eb4] .el-submenu .el-menu-item{padding-left: 62px !important;}
</style>
