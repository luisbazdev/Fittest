import { createRouter, createWebHistory} from 'vue-router'

import axios from 'axios'

import Login from '../components/Login.vue'

import MyRoutines from '../components/MyRoutines.vue'
import Explore from '../components/Explore.vue'

import { useUserStore } from '../stores/UserStore'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
      { 
        path: '/:pathMatch(.*)*', 
        redirect: '/explore' 
      },
      {
        path: '/login',
        name: 'login',
        component: Login
      },
      {
        path: '/explore',
        name: 'explore',
        component: Explore
      },
      {
        path: '/me',
        name: 'me',
        component: MyRoutines
      }
    ]
})

router.beforeEach(async(to, from) => {
  const userStore = useUserStore()

  await axios.post('http://localhost:8686/auth/info', {}, {
    withCredentials: true
  })
  .then((res: any) => {
    const { sub, name, email } = res.data
    userStore.logUserIn(sub, name, email)
  }).catch((e) => {
    userStore.logUserOut()
  })
  
  if(userStore.isUserLoggedIn){
    if(to.name === 'login' || to.name === 'register')
      return { name: 'explore' }
  }
  else{
    if(to.name === 'explore' || to.name === 'routines'){
      return { name: 'login'}
    }
  }
})

export default router
