import Vue from 'vue'
import VueRouter from 'vue-router'
import Sky from '../components/Sky.vue'
import Situation from '../components/Situation.vue'
import Files from '../components/Files.vue'
import Share from '../components/Share.vue'
import MyFiles from '../components/cates/My-files.vue'
import MyImages from '../components/cates/My-images.vue'
import MyVideo from '../components/cates/My-video.vue'
import MyMusic from '../components/cates/My-music.vue'
import OtherFiles from '../components/cates/Other-files.vue'
import Help from '../components/Help.vue'
import Login2 from '../components/login/Login2.vue'
import Login from '../components/login/Login.vue'
import Register from '../components/login/Register.vue'
import One from '../components/folder/One.vue';
import Two from '../components/folder/Two.vue';
import Three from '../components/folder/Three.vue';

// import  axios from 'axios'
// axios.defaults.baseURL='http://fangyulong.online:8702/uploadFolder'

Vue.use(VueRouter)



const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/login2', component: Login2 },
  { path: '/register', component: Register },
  {
    path: '/sky', component: Sky, redirect: '/situation', children: [
      { path: '/situation', component: Situation },
      { path: '/files', component: Files },
      { path: '/share', component: Share },
      { path: '/myFiles', component: MyFiles },
      { path: '/myImages', component: MyImages },
      { path: '/myVideo', component: MyVideo },
      { path: '/myMusic', component: MyMusic },
      { path: '/otherFiles', component: OtherFiles },
      { path: '/help', component: Help },
      { path: '/folderOne', name: 'one', component: One},
      { path: '/folderTwo', name: 'two', component: Two},
      { path: '/folderThree', name: 'three', component: Three},
    ]
  }
]


const router = new VueRouter({
  routes

})


// 挂载路由导航守卫
router.beforeEach((to, form, next) => {

  if (to.path === '/login' || to.path === '/login2' || to.path === '/register') return next()

  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login2')
  next()
})

export default router
