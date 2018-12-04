import Vue from 'vue'
import Router from 'vue-router'
import CompanyList from '@/components/CompanyList'
import RechargeList from '@/components/RechargeList'
import MessageRecord from '@/components/MessageRecord'
import BackupData from '@/components/BackupData'
import Login from '@/components/Login'
import Test from '@/components/Test'

Vue.use(Router)

export default new Router({
  routes: [
  	{ path: '/', redirect: '/login' },
    { path: '/companyList',name: 'companyList',component: CompanyList },
    { path: '/rechargeList',name: 'rechargeList',component: RechargeList },
    { path: '/messageRecord',name: 'messageRecord',component: MessageRecord },
    { path: '/backupData',name: 'backupData',component: BackupData },
    { path: '/test',name: 'test',component: Test },
    { path: '/login',name: 'login',component: Login }   
  ]
})
