import Vue from 'vue'
import VueRouter from 'vue-router'
import AppMain from '@/views/AppMain'
import AppAddress from '@/views/AppAddress'
import AppSignIn from '@/views/AppSignIn' //  로그인테스트

import AppCarInfo from '@/views/AppCarInfo'
import AppCarReserve from '@/views/AppCarReserve'
import AppPayment from '@/views/AppPayment'
import AppCarOrder from '@/views/AppCarOrder'

import AppReserve from '@/views/AppReserve'
import AppReturn from '@/views/AppReturn'

import AppUserCardReg from '@/views/AppUserCardReg'
import AppUserDriverLicense from '@/views/AppUserDriverLicense'
import AppUserLogin from '@/views/AppUserLogin'
import AppUserMyPage from '@/views/AppUserMyPage'
import AppUserProfileRevise from '@/views/AppUserProfileRevise'
import AppUserReview from '@/views/AppUserReview'
import AppUserSignUp from '@/views/AppUserSignUp'

import AppAdminMain from '@/views/AppAdminMain'
import AppAdminCar from '@/views/AppAdminCar'
import AppAdminReview from '@/views/AppAdminReview'
import AppAdminReviewDetail from '@/views/AppAdminReviewDetail'
import AppAdminUserManagement from '@/views/AppAdminUserManagement'


Vue.use(VueRouter)

const routes = [
  {path: '/', name: 'AppMain', component: AppMain},
  {path: '/address', name: 'AppAddress', component: AppAddress},
  {path: '/signin', name: 'AppSignIn', component: AppSignIn},
  {path: '/carinfo', name: 'AppCarInfo', component: AppCarInfo},
  {path: '/carreserve', name: 'AppCarReserve', component: AppCarReserve},
  {path: '/payment', name: 'AppPayment', component: AppPayment},
  {path: '/carorder', name: 'AppCarOrder', component: AppCarOrder},
  {path: '/reserve', name: 'AppReserve', component: AppReserve},
  {path: '/return', name: 'AppReturn', component: AppReturn},

  {path: '/usercardreg', name: 'AppUserCardReg', component: AppUserCardReg},
  {path: '/userdriverlicense', name: 'AppUserDriverLicense', component: AppUserDriverLicense},
  {path: '/userlogin', name: 'AppUserLogin', component: AppUserLogin},
  {path: '/usermypage', name: 'AppUserMyPage', component: AppUserMyPage},
  {path: '/userprofilerevise', name: 'AppUserProfileRevise', component: AppUserProfileRevise},
  {path: '/userreviews', name: 'AppUserReview', component: AppUserReview},
  {path: '/usersignup', name: 'AppUserSignUp', component: AppUserSignUp},
  
  {
    path: '/adminmain', name: 'AppAdminMain', component: AppAdminMain,
    children: [
      {path: '/admincars', name: 'AppAdminCar', component: AppAdminCar},
      {path: '/adminreviews', name: 'AppAdminReview', component: AppAdminReview},
      {path: '/adminreviewdetail', name: 'AppAdminReviewDetail', component: AppAdminReviewDetail},
      {path: '/adminusermanager', name: 'AppAdminUserManagement', component: AppAdminUserManagement}
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
