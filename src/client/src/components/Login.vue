<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'

import router from '@/router';

// import { useUserStore } from '@/stores/UserStore'
// const userStore = useUserStore()

const email = ref("")
const password = ref("")

// async function LogIn(){
//     axios.post("http://localhost:8686/auth/login", {
//         email: email.value,
//         password: password.value
//     }, {
//         withCredentials: true
//     }).then((res) => {
//         // if request is successful. . .
//         if(res.data){
//             const { sub, name, email } = res.data
//             userStore.logUserIn(sub, name, email)
//             router.push("/app")
//         }
//     })
// }
</script>

<template>
Login page
<input v-model="email" type="text" placeholder="email"/>
<input v-model="password" type="text" placeholder="password"/>
<!-- <button @click="LogIn">login</button> -->
</template>
