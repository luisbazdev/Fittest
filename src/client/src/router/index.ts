import { createRouter, createWebHistory} from 'vue-router'

import axios from 'axios'

import Login from '../components/Login.vue'

import Schedule from '../components/Schedule.vue'
import Routines from '../components/Routines.vue'
import Workouts from '../components/Workouts.vue'

import { useUserStore } from '../stores/UserStore'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
      { 
        path: '/:pathMatch(.*)*', 
        redirect: '/schedule' 
      },
      {
        path: '/login',
        name: 'login',
        component: Login
      },
      {
        path: '/routines',
        name: 'routines',
        component: Routines
      },
      {
        path: '/workouts',
        name: 'workouts',
        component: Workouts
      },
      {
        path: '/schedule',
        name: 'schedule',
        component: Schedule
      }
    ]
})

// router.beforeEach(async(to, from) => {
//   const userStore = useUserStore()

//   console.log(userStore.isUserLoggedIn)

//   await axios.post('http://localhost:8686/auth/info', {}, {
//     withCredentials: true
//   })
//   .then((res: any) => {
//     const { sub, name, email } = res.data
//     userStore.logUserIn(sub, name, email)
//   }).catch((e) => {
//     userStore.logUserOut()
//   })
  
//   if(userStore.isUserLoggedIn){
//     if(to.name === 'login' || to.name === 'register')
//       return { name: 'home' }
//   }
//   else{
//     if(to.name === 'home'){
//       return { name: 'login'}
//     }
//   }
// })

export default router
